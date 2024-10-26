package com.nice.dcm.distribution.parser;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.nice.dcm.distribution.listener.ThrowingErrorListener;

public class ParserCache {
	
	private static ParserCache instance = null;
	
	public static ParserCache getInstance() {
        if (instance == null) {
        	instance = new ParserCache();
        }
        return instance;
    }
	
	private Map<String, ParserWrapper> parserMap;
	
	private ParserUtil parserUtil;
	
	private ParserCache() {
		parserUtil = new ParserUtil();
		parserMap = new HashMap<>();
	}
	
	public void addParser(String parserName, String script) {
		parserMap.computeIfAbsent(parserName, k -> parser(parserName, script));
	}
	
	private ParserWrapper parser(String parserName, String script) {
		return new ParserWrapper(parserName, parserUtil.parserScript(script));
	}
	
	public ParserWrapper getParser(String parserName) {
		synchronized (parserMap) {
			ParserWrapper parserWrapper = parserMap.get(parserName);
			if (!parserWrapper.isUsed()) {
				return parserWrapper;
			} else {
				//if this happens, it means that the parser is being used in multiple threads
			    // which is not allowed. The parser is not thread safe
				// a parser pool should be used in this case
				throw new IllegalStateException("Parser already used");
			}
		}
	}
	
	public void releaseParser(ParserWrapper parserWrapper) {
		synchronized (parserMap) {
			if (this.parserMap.containsKey(parserWrapper.getParserName())) {
				//if pool is implemented, this method should return the parser to the pool
				parserWrapper.unuse();
			}
		}
	}

	
	public static class ParserWrapper {
		private String parserName;
        private DistributionRulesParser parser;
        private boolean used;
        
        private ParserWrapper(String parserName, DistributionRulesParser parser) {
            this.parserName = parserName;
            this.parser = parser;
            this.used = false;
        }
        
		private void unuse() {
			parser.reset();
			used = false;
		}
		
        public DistributionRulesParser getParser() {
        	used = true;
            return parser;
        }
        
		public String getParserName() {
			return parserName;
		}
		
		public boolean isUsed() {
			return used;
		}
	}
}
