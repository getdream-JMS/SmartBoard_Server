package com.jms.dboard.job;

import org.springframework.stereotype.Service;

public class Singleton4TranscoderStatus {
	private Singleton4TranscoderStatus() {}
	public static Singleton4TranscoderStatus getInstance() {
		System.out.println("get instance Singleton4TranscoderStatus");
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final Singleton4TranscoderStatus INSTANCE = new Singleton4TranscoderStatus();  
	}
}