package com.nice.dcm.distribution.listener;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThrowingErrorListener extends BaseErrorListener {
	private static final Logger logger = LoggerFactory.getLogger(ThrowingErrorListener.class);
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) throws ParseCancellationException {
		logger.debug("line " + line + ":" + charPositionInLine + " " + msg);
		throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
	}
}
