package com.nice.dcm.opensearch;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.OpenSearchException;
import org.opensearch.client.opensearch.core.InfoResponse;
import org.opensearch.client.transport.TransportOptions;
import org.opensearch.client.transport.aws.AwsSdk2Transport;
import org.opensearch.client.transport.aws.AwsSdk2TransportOptions;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.signer.Aws4Signer;
import software.amazon.awssdk.auth.signer.params.Aws4SignerParams;
import software.amazon.awssdk.http.HttpExecuteRequest;
import software.amazon.awssdk.http.HttpExecuteResponse;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.SdkHttpFullRequest;
import software.amazon.awssdk.http.SdkHttpMethod;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;

public class OpenSearchAWSClient {
	public static void main(String[] args) {
		System.out.println("Hello, OpenSearch AWS Client!");
		//https://search-dev-es65-metrics-test-2-kewrbkft76t5o6krkyfscd2fkq.us-west-2.es.amazonaws.com/_cat/indices?v
		SdkHttpClient httpClient = ApacheHttpClient.builder().build();
		
		String host = "search-dev-es65-metrics-test-2-kewrbkft76t5o6krkyfscd2fkq.us-west-2.es.amazonaws.com";
		String serviceName = "es";
		Region signingRegion = Region.US_WEST_2;
		
		AwsSdk2TransportOptions transportOptions = AwsSdk2TransportOptions.builder().build();
		
		AwsSdk2Transport transport = new AwsSdk2Transport(httpClient, host, // OpenSearch endpoint, without https://
				serviceName, signingRegion, // signing service region
				transportOptions);
		        
		OpenSearchClient client = new OpenSearchClient(transport);
		
		InfoResponse info;
		try {
			info = client.info();
			System.out.println(info.version().distribution() + ": " + info.version().number());
		} catch (OpenSearchException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpClient.close();
	}
	
	private static SdkHttpFullRequest createRequest(String serviceName, Region signingRegion) {
		SdkHttpFullRequest.Builder req = SdkHttpFullRequest.builder()
                .method(SdkHttpMethod.GET);
		
		AwsCredentialsProvider credentials = DefaultCredentialsProvider.create();
		Aws4SignerParams signerParams = Aws4SignerParams.builder()
                .awsCredentials(credentials.resolveCredentials())
                .signingName(serviceName)
                .signingRegion(signingRegion)
                .build();
		return Aws4Signer.create().sign(req.build(), signerParams);
	}
	
	private static void executeSync(SdkHttpClient syncHttpClient, SdkHttpFullRequest httpRequest) throws IOException {
        HttpExecuteRequest.Builder executeRequest = HttpExecuteRequest.builder().request(httpRequest);
        if (httpRequest.contentStreamProvider().isPresent()) {
            executeRequest.contentStreamProvider(httpRequest.contentStreamProvider().get());
        }
        HttpExecuteResponse executeResponse = syncHttpClient.prepareRequest(executeRequest.build()).call();
         InputStream bodyStream = null;
        try {
            bodyStream = executeResponse.responseBody().orElse(null);
            SdkHttpResponse httpResponse = executeResponse.httpResponse();
            
            int statusCode = httpResponse.statusCode();
            
            boolean isZipped = httpResponse.firstMatchingHeader("Content-Encoding")
                    .map(enc -> enc.contains("gzip"))
                    .orElse(Boolean.FALSE);
            if (bodyStream != null && isZipped) {
                bodyStream = new GZIPInputStream(bodyStream);
            }

            //return parseResponse(httpResponse, bodyStream, endpoint, options);
        } finally {
            if (bodyStream != null) {
                bodyStream.close();
            }
        }
	}
}
