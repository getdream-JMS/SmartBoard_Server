package com.jms.dboard.common.utils;

import java.io.File;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResourceUtils {
	/*
	 * 디스크용량
	 */
	private void showDisk() {
		File root = null;
		try {
			root = new File("/");
			System.out.println("Total Space: " + toMB(root.getTotalSpace()));
			System.out.println("Usable Space: " + toMB(root.getUsableSpace()));

			/* added by cafe mocha */
			System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * cpu 사용량
	 */
	private void showCPU() {
//		OperatingSystemMXBean bean= (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
//
//        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
//        MemoryUsage heapMemoryUsage= memBean.getHeapMemoryUsage();
//        MemoryUsage nonHeapMemoryUsage = memBean.getNonHeapMemoryUsage();
//
//        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
//        int currentThreadCount=threadBean.getThreadCount();
//        int  daemonThreadCount= threadBean.getDaemonThreadCount();
//        long  beanCreatedThreadCount= threadBean.getTotalStartedThreadCount();
//        int  peakThreadCount = threadBean.getPeakThreadCount();
//
//        ClassLoadingMXBean classLoadingBean = ManagementFactory.getClassLoadingMXBean();
//        int loadedClassCount=classLoadingBean.getLoadedClassCount();
//        long totalLoadedClassCount=classLoadingBean.getTotalLoadedClassCount();
//        long unloadedClassCount=classLoadingBean.getUnloadedClassCount();
//        
//        int committedVirtualMemorySize = (bean.getCommittedVirtualMemorySize());
//        int freePhysicalMemorySize =(bean.getFreePhysicalMemorySize());
//        int totalPhysicalMemorySize =(bean.getTotalPhysicalMemorySize());
//
//        int freeSwapSpaceSize =(bean.getFreeSwapSpaceSize());
//        int totalSwapSpaceSize =(bean.getTotalSwapSpaceSize());
//        int processCpuTime =(bean.getProcessCpuTime());
//        int availableProcessors =bean.getAvailableProcessors();
//        int processCpuLoad =bean.getProcessCpuLoad();
//
//        int systemCpuLoad =bean.getSystemCpuLoad();
//        double systemLoadAverage =bean.getSystemLoadAverage();
//        String appStartUpTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(runtimeBean.getStartTime()));
//        long runtime = (new Date().getTime() - runtimeBean.getStartTime())/1000;
//        String os = bean.getName()+" "+bean.getVersion();
//
//        if(HostInfo.isLinux()){
//            try {
//                availableMemory = (LinuxInfoUtil.getAvailableMemKB()*1024l);
//            } catch (Throwable ignored) {
//            }
//        }
//
//        File[] roots = File.listRoots();
//        for(File file:roots){
//            rootFiles.add(new RootFile(file.getAbsolutePath(),file.getTotalSpace(),file.getFreeSpace()));
//        }
	}

	private void showRuntime() {
		RuntimeMXBean runbean = (RuntimeMXBean) ManagementFactory
				.getRuntimeMXBean();
	}
	/*
	 * 메모리 사용량
	 */
	private void showMemory() {
		MemoryMXBean membean = (MemoryMXBean) ManagementFactory
				.getMemoryMXBean();
		MemoryUsage heap = membean.getHeapMemoryUsage();
		System.out.println("Heap Memory: " + heap.toString());
		MemoryUsage nonheap = membean.getNonHeapMemoryUsage();
		System.out.println("NonHeap Memory: " + nonheap.toString());
	}
	private void showClassLoading() {
		ClassLoadingMXBean classbean = (ClassLoadingMXBean) ManagementFactory
				.getClassLoadingMXBean();
		System.out.println("TotalLoadedClassCount: "
	        + classbean.getTotalLoadedClassCount());
		System.out.println("LoadedClassCount: "
	        + classbean.getLoadedClassCount());
		System.out.println("UnloadedClassCount: "
	        + classbean.getUnloadedClassCount());
	}



	private void showThreadBean() {
		ThreadMXBean tbean = (ThreadMXBean) ManagementFactory.getThreadMXBean();
		long[] ids = tbean.getAllThreadIds();
		System.out.println("Thread Count: " + tbean.getThreadCount());
		for (long id : ids) {
			System.out.println("Thread CPU Time(" + id + ")"
	          + tbean.getThreadCpuTime(id));
			System.out.println("Thread User Time(" + id + ")"
	          + tbean.getThreadCpuTime(id));
		}
	}
	/**
	 * OS 정보
	 */
	private void showOSBean() {
//		OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory
//				.getOperatingSystemMXBean();
//		System.out.println("OS Name: " + osbean.getName());
//		System.out.println("OS Arch: " + osbean.getArch());
//		System.out.println("Available Processors: "
//	        + osbean.getAvailableProcessors());
//		System.out.println("TotalPhysicalMemorySize: "
//	        + toMB(osbean.getTotalPhysicalMemorySize()));
//		System.out.println("FreePhysicalMemorySize: "
//	        + toMB(osbean.getFreePhysicalMemorySize()));
//		System.out.println("TotalSwapSpaceSize: "
//	        + toMB(osbean.getTotalSwapSpaceSize()));
//		System.out.println("FreeSwapSpaceSize: "
//	        + toMB(osbean.getFreeSwapSpaceSize()));
//		System.out.println("CommittedVirtualMemorySize: "
//	        + toMB(osbean.getCommittedVirtualMemorySize()));
//		System.out.println("SystemLoadAverage: "
//	        + osbean.getSystemLoadAverage());
	}

//	public UseMxBean() {
//		seperator(); showOSBean();
//		seperator(); showThreadBean();
//		seperator(); showClassLoading();
//		seperator(); showMemory();
//		seperator(); showDisk();
//		seperator(); showCPU();
//		seperator();
//	}

	private void seperator() {
		System.out.println("-----------------------------------------------");
	}

	private String toMB(long size)
	{
		return (int)(size/(1024*1024))+"(MB)";
	}

}
