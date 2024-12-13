package com.nice.dcm.distribution.rule.parser.listener;

import java.util.Map;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThrowingErrorListener extends BaseErrorListener {
	private static final Logger logger = LoggerFactory.getLogger(ThrowingErrorListener.class);

	private static final Map<String, String> keywords = Map.of(
			"LEAST_BUSY_OF", "least busy of",
			"QUEUE_TO", "queue to"
			);
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) throws ParseCancellationException {
		String error = String.format("line %1$s: %2$s %3$s", line, charPositionInLine, messageReplacement(msg));
		logger.debug(error);
		throw new ParseCancellationException(error);
	}
	
	private String messageReplacement(String msg) {
		for(Map.Entry<String, String> entry : keywords.entrySet()) {
            msg = msg.replace(entry.getKey(), entry.getValue());
        }
		return msg;
	}
}
