package com.zl.pattern.abfactory;

public class BFactory extends CarFactory{

	@Override
	public ITire createTire() {
		return new SUVTire();
	}

	@Override
	public IEngine createEngine() {
		return new ImportEngine();
	}

	@Override
	public IBrake createBrake() {
		return new SeniorBrake();
	}
}
