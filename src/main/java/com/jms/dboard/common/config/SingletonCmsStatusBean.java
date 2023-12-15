package com.jms.dboard.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SingletonCmsStatusBean extends JSONObject{

	int cpu;
	int memory;
	double disk;
	/**
	 * @return the cpu
	 */
	public int getCpu() {
		return cpu;
	}
	/**
	 * @param cpu the cpu to set
	 */
	public void setCpu(int cpu) {
		this.cpu = cpu;
	}
	/**
	 * @return the memory
	 */
	public int getMemory() {
		return memory;
	}
	/**
	 * @param memory the memory to set
	 */
	public void setMemory(int memory) {
		this.memory = memory;
	}
	/**
	 * @return the disk
	 */
	public double getDisk() {
		return disk;
	}
	/**
	 * @param d the disk to set
	 */
	public void setDisk(double d) {
		this.disk = d;
	}	
}
