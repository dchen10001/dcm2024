package com.nice.dcm.simulation.distribution.rule.operator;

/**
 * Binary operator for routing rule condition
 * 
 * @author David Chen
 * 
 */

public enum BinaryOperator {
	LESS_THAN("<") {
		@Override
		public boolean evaluate(int left, int right) {
			return left < right;
		}
	},
	LESS_THAN_OR_EQUAL("<=") {
		@Override
        public boolean evaluate(int left, int right) {
            return left <=	right;
		}
	}, 
	EQUAL("=") {
        @Override
        public boolean evaluate(int left, int right) {
            return left == right;
        }
    },
	NOT_EQUAL("<>") {
        @Override
        public boolean evaluate(int left, int right) {
            return left != right;
        }
    },
	GREATER_THAN(">") {
        @Override
        public boolean evaluate(int left, int right) {
            return left > right;
        }
    },
	GREATER_THAN_OR_EQUAL(">=") {
        @Override
        public boolean evaluate(int left, int right) {
            return left >= right;
        }
    },
    NOT_USED("")
    ;

	private String operator;

	public String getOperator() {
		return operator;
	}

	BinaryOperator(String operator) {
		this.operator = operator;
	}

	public boolean evaluate(int left, int right) {
		throw new IllegalArgumentException("Unknown operator: " + this);
	}

	public static BinaryOperator fromString(String operator) {
		switch (operator) {
		case "<":
			return LESS_THAN;
		case "<=":
			return LESS_THAN_OR_EQUAL;
		case "=":
			return EQUAL;
		case "<>":
			return NOT_EQUAL;
		case ">":
			return GREATER_THAN;
		case ">=":
			return GREATER_THAN_OR_EQUAL;
		default:
			throw new IllegalArgumentException("Unknown operator: " + operator);
		}
	}
}
