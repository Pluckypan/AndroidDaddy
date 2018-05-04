package com.zl.pattern.builder;

public interface Builder {

	//创建部件A　　比如创建汽车车轮
	void buildPartA(); 

	//创建部件B   比如创建汽车方向盘
	void buildPartB(); 

	//创建部件C   比如创建汽车发动机
	void buildPartC();

	//返回最后组装成品结果 (返回最后装配好的汽车)
	Product getResult();

}