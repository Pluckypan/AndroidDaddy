package com.zl.pattern.explain;

public class SubtractionExpression extends OperatorExpression{

	public SubtractionExpression(ArithemticExpression exp1,
			ArithemticExpression exp2) {
		super(exp1, exp2);
	}

	@Override
	public int interpreter() {
		return exp1.interpreter() - exp2.interpreter();
	}

}
