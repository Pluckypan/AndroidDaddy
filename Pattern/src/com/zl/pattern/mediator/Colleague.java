package com.zl.pattern.mediator;

public abstract class Colleague {
	protected Mediator mediator;//ÿһ��ͬ�¶���֪�����н���

	public Colleague(Mediator mediator) {
		this.mediator = mediator;
	}
	
}
