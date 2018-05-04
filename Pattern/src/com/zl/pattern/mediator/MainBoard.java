package com.zl.pattern.mediator;

public class MainBoard extends Mediator{
	
	private CDDevice cdDevice; //光驱设备
	private CPU cpu; //CPU
	private SoundCard soundCard; //声卡设备
	private GraphicsCard graphicsCard; //显卡设备

	@Override
	public void changed(Colleague c) {
		//如果光驱读取了数据
		if(c == cdDevice){
			handleCD((CDDevice) c);
		}
		//如果CPU处理完数据
		else if(c == cpu){
			handleCD((CPU) c);
		}
	}

	/**
	 * 处理光驱读取数据后与其他设备的交互
	 * 
	 * @param cdDevice 光驱设备
	 */
	public void handleCD(CDDevice cdDevice){
		cpu.decodeData(cdDevice.read());
	}
	
	/**
	 * 处理CPU读取数据后与其他设备的交互
	 * 
	 * @param cpu CPU
	 */
	public void handleCD(CPU cpu){
		soundCard.soundPlay(cpu.getDataSound());
		graphicsCard.videoPlay(cpu.getDataVideo());
	}
	
	/**
	 * 设置CD设备
	 * 
	 * @param CDDevice CD设备
	 */
	public void setCDDevice(CDDevice cdDevice){
		this.cdDevice = cdDevice;
	}
	
	/**
	 * 设置CPU
	 * 
	 * @param cpu CPU
	 */
	public void setCPU(CPU cpu){
		this.cpu = cpu;
	}
	
	/**
	 * 设置声卡设备
	 * 
	 * @param soundCard 声卡设备
	 */
	public void setSoundCard(SoundCard soundCard){
		this.soundCard = soundCard;
	}
	
	/**
	 * 设置显卡设备
	 * 
	 * @param graphicsCard 显卡设备
	 */
	public void setGraphicsCard(GraphicsCard graphicsCard){
		this.graphicsCard = graphicsCard;
	}
}
