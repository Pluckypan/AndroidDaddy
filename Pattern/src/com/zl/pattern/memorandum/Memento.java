package com.zl.pattern.memorandum;

/**
 * 备忘录类
 */
public class Memento {
	public int mCheckpoint;//武器

	public int mLiftValue;//生命

	public String mWeapon;//关卡

	@Override
	public String toString() {
		return "Memento [mCheckpoint=" + mCheckpoint + ",mLiftValue="
				+ mLiftValue + ",mWeapon=" + mWeapon + "]";
	}

}
