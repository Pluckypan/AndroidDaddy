package com.zl.pattern.abfactory;

public class AFactory extends CarFactory{

	@Override
	public ITire createTire() {
		return new NormalTire();
	}

	@Override
	public IEngine createEngine() {
		return new DomesticEngine();
	}

	@Override
	public IBrake createBrake() {
		return new NormalBrake();
	}
}
