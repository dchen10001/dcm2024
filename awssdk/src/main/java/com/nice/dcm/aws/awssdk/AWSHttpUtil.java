package com.nice.dcm.aws.awssdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.signer.Aws4Signer;
import software.amazon.awssdk.auth.signer.params.Aws4SignerParams;
import software.amazon.awssdk.http.ContentStreamProvider;
import software.amazon.awssdk.http.HttpExecuteRequest;
import software.amazon.awssdk.http.HttpExecuteResponse;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.SdkHttpFullRequest;
import software.amazon.awssdk.http.SdkHttpMethod;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;

public class AWSHttpUtil {
	private static final String SLASH = "/";
	private static final String HTTPS = "https://";
	
	private static final Logger logger = LoggerFactory.getLogger(AWSHttpUtil.class);

	private final String host;
	private final String serviceName;
	private final Region signingRegion;

	private ObjectMapper mapper;
	
	private SdkHttpClient httpClient = null;

	public AWSHttpUtil(String host) {
		this(host, "es", Region.US_WEST_2);
	}

	public AWSHttpUtil(String host, String serviceName, Region signingRegion) {
		super();
		this.host = host;
		this.serviceName = serviceName;
		this.signingRegion = signingRegion;
	}

	private SdkHttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = ApacheHttpClient.builder().maxConnections(5).build();
		}
		return httpClient;
	}

	private ObjectMapper getMapper() {
		if(mapper == null) {
			mapper = new ObjectMapper();
		}
        return mapper;
	}
	
	public JsonNode getJson(String path) throws URISyntaxException, IOException {
		return getJson(path, false, null, null);
	}
	
	public JsonNode getJson(String path, boolean responseCompression, Map<String, String> params,
			Collection<Map.Entry<String, String>> headers) throws URISyntaxException, IOException {
		Body body = get(path, responseCompression, params, headers);
		
		if (body == null) {
			return null;
		}
		
		if (!body.isJson()) {
			throw new IOException("Content-Type is not application/json: " + body.getContentType());
		}
		return getMapper().readTree(body.getContentBody());
	}
	public String getText(String path) throws URISyntaxException, IOException {
		return getText(path, false, null, null);
	}
	
	public String getText(String path, boolean responseCompression, Map<String, String> params,
			Collection<Map.Entry<String, String>> headers) throws URISyntaxException, IOException {
		Body body = get(path, responseCompression, params, headers);
		
		if (body == null) {
			return null;
		}
		return body.getContentBody();
	}
	
	protected Body get(String path, boolean responseCompression, Map<String, String> params,
			Collection<Map.Entry<String, String>> headers) throws URISyntaxException, IOException {
		SdkHttpFullRequest request = createRequest(path,
				responseCompression, params, headers, SdkHttpMethod.GET);
		return executeSync(getHttpClient(), request);
	}

	protected Body post(String path, boolean responseCompression, Map<String, String> params,
			Collection<Map.Entry<String, String>> headers) throws URISyntaxException, IOException {
		SdkHttpFullRequest request = createRequest(path,
				responseCompression, params, headers, SdkHttpMethod.POST);
		return executeSync(getHttpClient(), request);
	}

	private Body executeSync(SdkHttpClient syncHttpClient, SdkHttpFullRequest httpRequest) throws IOException {
		HttpExecuteRequest.Builder executeRequest = HttpExecuteRequest.builder().request(httpRequest);
		
		Optional<ContentStreamProvider> option = httpRequest.contentStreamProvider();
		if (option.isPresent()) {
			executeRequest.contentStreamProvider(option.get());
		}
		
		HttpExecuteResponse executeResponse = syncHttpClient.prepareRequest(executeRequest.build()).call();
		
		SdkHttpResponse httpResponse = executeResponse.httpResponse();
		int statusCode = httpResponse.statusCode();
		if (statusCode != HttpStatus.SC_OK) {
			throw new IOException("HTTP Status Code: " + statusCode);
		}
		
		try (InputStream bodyStream = executeResponse.responseBody().orElse(null)) {
			if(bodyStream == null) {
				return null;
			}
						
			boolean isZipped = httpResponse.firstMatchingHeader("Content-Encoding").map(enc -> enc.contains("gzip"))
					.orElse(Boolean.FALSE);
			String contentBody = null;
			if (isZipped) {
				GZIPInputStream inputStream = new GZIPInputStream(bodyStream);
				contentBody =  new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
			} else {
				contentBody =  new String(bodyStream.readAllBytes(), StandardCharsets.UTF_8);
			}
			String contentType = httpResponse.firstMatchingHeader("Content-Type").get();
			return new Body(contentType, contentBody);
		}
	}
	
	
	protected SdkHttpFullRequest createRequest(String path,
			boolean responseCompression, Map<String, String> params, Collection<Map.Entry<String, String>> headers, SdkHttpMethod method)
			throws URISyntaxException {
		SdkHttpFullRequest.Builder req = SdkHttpFullRequest.builder().method(method);

		if (responseCompression) {
			req.putHeader("Accept-Encoding", "gzip");
		} else {
			req.removeHeader("Accept-Encoding");
		}

		String url = buildUrl(host, path, params);
		logger.debug("request url: {} ", url);
		applyHeaders(req, headers);
		req.uri(new URI(url));

		Aws4SignerParams signerParams = createSignerParams(serviceName, signingRegion);		
		return Aws4Signer.create().sign(req.build(), signerParams);
	}

	private Aws4SignerParams createSignerParams(String serviceName, Region signingRegion) {
        try (DefaultCredentialsProvider provider = DefaultCredentialsProvider.create()) {
            AwsCredentials credentials = provider.resolveCredentials();
            return Aws4SignerParams.builder().awsCredentials(credentials).signingName(serviceName)
                    .signingRegion(signingRegion).build();
        } 
	}
	
	protected void applyParams(StringBuilder url, Map<String, String> params) {
		if (params != null && !params.isEmpty()) {
			char sep = url.indexOf("?") < 0 ? '?' : '&';
			for (Map.Entry<String, String> param : params.entrySet()) {
				url.append(sep).append(param.getKey()).append('=');
				url.append(URLEncoder.encode(param.getValue(), StandardCharsets.UTF_8));
				sep = '?';
			}
		}
	}

	protected void applyHeaders(SdkHttpFullRequest.Builder builder, Collection<Map.Entry<String, String>> headers) {
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, String> header : headers) {
				builder.appendHeader(header.getKey(), header.getValue());
			}
		}
	}

	protected String buildUrl(String host, String path, Map<String, String> params) {
		StringBuilder url = new StringBuilder(HTTPS);
		url.append(host);
		if (!path.startsWith(SLASH)) {
			url.append(SLASH);
		}
		url.append(path);
		applyParams(url, params);
		return url.toString();
	}
	
	class Body {
		private String contentType;
		private String contentBody;
		
		public Body(String contentType, String contentBody) {
			super();
			this.contentType = contentType;
			this.contentBody = contentBody;
		}

		public String getContentType() {
			return contentType;
		}

		public String getContentBody() {
			return contentBody;
		}

		public boolean isJson() {
			return contentType != null && contentType.contains("application/json");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Hello, OpenSearch AWS Client!");
		
		String host = "search-dev-es65-metrics-test-2-kewrbkft76t5o6krkyfscd2fkq.us-west-2.es.amazonaws.com"; //"search-dev-es65-metrics-test-2-kewrbkft76t5o6krkyfscd2fkq.us-west-2.es.amazonaws.com";
		
		//https://search-dev-es65-metrics-test-2-kewrbkft76t5o6krkyfscd2fkq.us-west-2.es.amazonaws.com/acd_skill_aggregation_2024_11/_search
		
		String path = "_cat/indices?v";

		AWSHttpUtil util = new AWSHttpUtil(host);
		
		try {
			String text = util.getText(path);
			System.out.println(text);
			path = "acd_skill_aggregation_2024_11/_search";
			JsonNode json = util.getJson(path);
			
			System.out.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
