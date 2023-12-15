package com.jms.dboard.manage.system.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.jms.dboard.common.dao.CommonCodeDao;
import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.manage.system.dao.SystemManageDao;
import com.jms.dboard.manage.user.dao.UserManageDao;
import com.jms.dboard.manage.vo.ContentInfoVO;
import com.jms.dboard.manage.vo.OrganMemberInfoVO;
import com.jms.dboard.manage.vo.UserInfoSearchVO;
import com.jms.dboard.manage.vo.UserInfoVO;
import com.jms.dboard.manage.vo.UserReqInfoVO;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Display;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.PhysicalMemory;
import oshi.hardware.PowerSource;
import oshi.hardware.Sensors;
import oshi.hardware.SoundCard;
import oshi.hardware.UsbDevice;
import oshi.hardware.VirtualMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OSService;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;
import oshi.util.Util;



@Service("systemManageService")
public class SystemManageServiceImpl implements SystemManageService{

	@Autowired
	SystemManageDao systemManageDao;

	List<String> oshi = new ArrayList<>();

//	Properties propSnmp = null;

	@Resource(name = "cmsStatusMap")
	private Map<String, Object> cmsStatusMap;
	
	@Value("${snmp.drive}")
	private String propertiesPath;
	
	@Override
	public Map<String, Object> getStatisticContents(ContentInfoVO param) {
		// TODO Auto-generated method stub

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> statisticsList = null;
		int resultCode = 200;
		String resultMsg = "";
		int totalCount = 0;
		try {
			if(param.getStartDate() == null || param.getStartDate().equals("")) {
				param.setStartDate("00000000000000");
			}
			if(param.getEndDate() == null || param.getEndDate().equals("")) {
				param.setEndDate("00000000000000");
			}
			statisticsList = systemManageDao.getStatisticContents(param);
			totalCount = systemManageDao.getTotalStatisticContents(param);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultCode = 500;
			resultMsg = "컨텐츠 통계 데이타 조회 중   오류가 발생했습니다.";

		}
		resultMap.put("list",statisticsList);
		resultMap.put("totalCount",totalCount);
		resultMap.put("result", resultCode);
		resultMap.put("resultMsg", resultMsg);
		return resultMap;
	}



	@Override
	public void retrieveResources() {
		//		System.out.println("Initializing System...");
//		String propertiesPath = "/config/snmp.properties";
//		System.out.println("propertiesPath :"+propertiesPath);
//		propSnmp = new Properties();
//		try {
//			propSnmp.load(new FileInputStream(propertiesPath));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			System.out.println(propertiesPath+"을 찾을 수 없습니다.");
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		SystemInfo si = new SystemInfo();
		try {
			HardwareAbstractionLayer hal = si.getHardware();
			OperatingSystem os = si.getOperatingSystem();

			printOperatingSystem(os);
			//
			//        System.out.println("Checking computer system...");
			//        printComputerSystem(hal.getComputerSystem());
			//
			//        System.out.println("Checking Memory...");
			printMemory(hal.getMemory());
			//        System.out.println("Checking CPU...");
			printCpu(hal.getProcessor());
			//        System.out.println("Checking Processes...");
			//        printProcesses(os, hal.getMemory());
			//
			//        System.out.println("Checking Services...");
			//        printServices(os);
			//
			//        System.out.println("Checking Sensors...");
			//        printSensors(hal.getSensors());
			//
			//        System.out.println("Checking Power sources...");
			//        printPowerSources(hal.getPowerSources());

			//        System.out.println("Checking Disks...");
			//        printDisks(hal.getDiskStores());
			//
			printProcessor(hal.getProcessor());
			//        System.out.println("Checking File System...");
			printFileSystem(os.getFileSystem());
			//
			//        System.out.println("Checking Network interfaces...");
			printNetworkInterfaces(hal.getNetworkIFs());
			//
			//        System.out.println("Checking Network parameters...");
			//        printNetworkParameters(os.getNetworkParams());
			//
			//        // hardware: displays
			//        System.out.println("Checking Displays...");
			//        printDisplays(hal.getDisplays());
			//
			//        // hardware: USB devices
			//        System.out.println("Checking USB Devices...");
			//        printUsbDevices(hal.getUsbDevices(true));
			//
			//        System.out.println("Checking Sound Cards...");
			//        printSoundCards(hal.getSoundCards());
			//      System.out.println("Checking Processor...");
			
			//        StringBuilder output = new StringBuilder();
			//        for (int i = 0; i < oshi.size(); i++) {
			//            output.append(oshi.get(i));
			//            if (oshi.get(i) != null && !oshi.get(i).endsWith("\n")) {
			//                output.append('\n');
			//            }
			//        }
			//        System.out.println("Printing Operating System and Hardware Info:\n" +output);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			si = null;
			// propSnmp = null;
		}	
	}

	private void printOperatingSystem(final OperatingSystem os) {
		//        oshi.add(String.valueOf(os));
		//        oshi.add("Booted: " + Instant.ofEpochSecond(os.getSystemBootTime()));
		//        oshi.add("Uptime: " + FormatUtil.formatElapsedSecs(os.getSystemUptime()));
		//        oshi.add("Running with" + (os.isElevated() ? "" : "out") + " elevated permissions.");
		if(String.valueOf(os) != null && !String.valueOf(os).equals("")) {
			Map<String,Object> osInfo = new HashMap<String,Object>();
			osInfo.put("name", String.valueOf(os));
			cmsStatusMap.put("os", osInfo);
		}
	}

	private void printComputerSystem(final ComputerSystem computerSystem) {
		oshi.add("system: " + computerSystem.toString());
		oshi.add(" firmware: " + computerSystem.getFirmware().toString());
		oshi.add(" baseboard: " + computerSystem.getBaseboard().toString());
	}

	private  void printProcessor(CentralProcessor processor) {
		//        oshi.add(processor.toString());
		Map<String,Object> cpuMap = (Map<String, Object>) cmsStatusMap.get("cpu");
		cpuMap.put("name", processor.getName());
	}

	private  void printMemory(GlobalMemory memory) {
		//        oshi.add("Memory: \n " + memory.toString());
		//        System.out.println("Memory: \n " + memory.toString());
		//        VirtualMemory vm = memory.getVirtualMemory();
		//        oshi.add("Swap: \n " + vm.toString());
		//        PhysicalMemory[] pmArray = memory.getPhysicalMemory();
		//        if (pmArray.length > 0) {
		//            oshi.add("Physical Memory: ");
		//            for (PhysicalMemory pm : pmArray) {
		//            	System.out.println("Physical Memory: " + pm.toString());
		//                oshi.add(" " + pm.toString());
		//            }
		//        }
		Map<String,Object> diskInfo = new HashMap<String,Object>();
		//        String[] memoryInfo = memory.toString().replaceAll("GiB", "").replaceAll("Available:","").replaceAll(" ","").split("/");
		diskInfo.put("total", CommonUtils.toGB(memory.getTotal()));
		diskInfo.put("used", CommonUtils.toGB(memory.getTotal() - memory.getAvailable()));        
		//        System.out.println((memory.getTotal() - memory.getAvailable()) / memory.getTotal());
		diskInfo.put("usage", String.format("%.2f", ((float)(memory.getTotal() - memory.getAvailable()) / memory.getTotal()) * 100));
		cmsStatusMap.put("memory", diskInfo);
	}

	private  void printCpu(CentralProcessor processor) {
		//        oshi.add("Context Switches/Interrupts: " + processor.getContextSwitches() + " / " + processor.getInterrupts());
		try {
			long[] prevTicks = processor.getSystemCpuLoadTicks();
			long[][] prevProcTicks = processor.getProcessorCpuLoadTicks();
			//        oshi.add("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
			// Wait a second...
			Util.sleep(1000);
			//        long[] ticks = processor.getSystemCpuLoadTicks();
			//        oshi.add("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
			//        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
			//        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
			//        long sys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
			//        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
			//        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
			//        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
			//        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
			//        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
			//        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;
			//
			//        oshi.add(String.format(
			//                "User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%% Steal: %.1f%%",
			//                100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
			//                100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu, 100d * steal / totalCpu));
			//        oshi.add(String.format("CPU load: %.1f%%", processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100));
			//        double[] loadAverage = processor.getSystemLoadAverage(3);
			//        oshi.add("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
			//                + (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
			//                + (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
			// per core CPU
			//        StringBuilder procCpu = new StringBuilder("CPU load per processor:");
			double[] load = processor.getProcessorCpuLoadBetweenTicks(prevProcTicks);
			//        for (double avg : load) {
			//            procCpu.append(String.format(" %.1f%%", avg * 100));
			//        }
			//        oshi.add(procCpu.toString());
			//        long freq = processor.getProcessorIdentifier().getVendorFreq();
			//        if (freq > 0) {
			//            oshi.add("Vendor Frequency: " + FormatUtil.formatHertz(freq));
			//        }
			//        freq = processor.getMaxFreq();
			//        if (freq > 0) {
			//            oshi.add("Max Frequency: " + FormatUtil.formatHertz(freq));
			//        }
			//        long[] freqs = processor.getCurrentFreq();
			//        if (freqs[0] > 0) {
			//            StringBuilder sb = new StringBuilder("Current Frequencies: ");
			//            for (int i = 0; i < freqs.length; i++) {
			//                if (i > 0) {
			//                    sb.append(", ");
			//                }
			//                sb.append(FormatUtil.formatHertz(freqs[i]));
			//            }
			//            oshi.add(sb.toString());
			//        }
			if((processor.getSystemCpuLoadBetweenTicks(prevTicks) > 0)) {
				Map<String,Object> diskInfo = new HashMap<String,Object>();
				diskInfo.put("cores", load.length);
				diskInfo.put("usage", String.format("%.2f", processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100));
				cmsStatusMap.put("cpu", diskInfo);
			}
		} catch(Exception e) {

		}

		//        return String.format("%.1f%%", processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100);
	}

	private  void printProcesses(OperatingSystem os, GlobalMemory memory) {
		oshi.add("My PID: " + os.getProcessId() + " with affinity "
				+ Long.toBinaryString(os.getProcessAffinityMask(os.getProcessId())));
		oshi.add("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());
		// Sort by highest CPU
		List<OSProcess> procs = Arrays.asList(os.getProcesses(5, ProcessSort.CPU));

		oshi.add("   PID  %CPU %MEM       VSZ       RSS Name");
		for (int i = 0; i < procs.size() && i < 5; i++) {
			OSProcess p = procs.get(i);
			oshi.add(String.format(" %5d %5.1f %4.1f %9s %9s %s", p.getProcessID(),
					100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
					100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
					FormatUtil.formatBytes(p.getResidentSetSize()), p.getName()));
		}
	}

	private  void printServices(OperatingSystem os) {
		oshi.add("Services: ");
		oshi.add("   PID   State   Name");
		// DO 5 each of running and stopped
		int i = 0;
		for (OSService s : os.getServices()) {
			if (s.getState().equals(OSService.State.RUNNING) && i++ < 5) {
				oshi.add(String.format(" %5d  %7s  %s", s.getProcessID(), s.getState(), s.getName()));
			}
		}
		i = 0;
		for (OSService s : os.getServices()) {
			if (s.getState().equals(OSService.State.STOPPED) && i++ < 5) {
				oshi.add(String.format(" %5d  %7s  %s", s.getProcessID(), s.getState(), s.getName()));
			}
		}
	}

	private  void printSensors(Sensors sensors) {
		oshi.add("Sensors: " + sensors.toString());
	}

	private  void printPowerSources(PowerSource[] powerSources) {
		StringBuilder sb = new StringBuilder("Power Sources: ");
		if (powerSources.length == 0) {
			sb.append("Unknown");
		}
		for (PowerSource powerSource : powerSources) {
			sb.append("\n ").append(powerSource.toString());
		}
		oshi.add(sb.toString());
	}

	private  void printDisks(HWDiskStore[] diskStores) {
		oshi.add("Disks:");
		for (HWDiskStore disk : diskStores) {
			oshi.add(" " + disk.toString());

			HWPartition[] partitions = disk.getPartitions();
			for (HWPartition part : partitions) {
				oshi.add(" |-- " + part.toString());
			}
		}

	}

	private  void printFileSystem(FileSystem fileSystem) {
		int resultUsage = 0;
		try {
			//	        oshi.add("File System:");
			//	        oshi.add(String.format(" File Descriptors: %d/%d", fileSystem.getOpenFileDescriptors(),
			//	                fileSystem.getMaxFileDescriptors()));

			OSFileStore[] fsArray = fileSystem.getFileStores();
			String targetDrive = propertiesPath; //propSnmp.getProperty("snmp.drive");
			resultUsage = 0;
			long usable = 0;
			long total = 0;
			for (OSFileStore fs : fsArray) {
				usable = fs.getUsableSpace();
				total = fs.getTotalSpace();
				//	            oshi.add(String.format(
				//	                    " %s (%s) [%s] %s of %s free (%.1f%%), %s of %s files free (%.1f%%) is %s "
				//	                            + (fs.getLogicalVolume() != null && fs.getLogicalVolume().length() > 0 ? "[%s]" : "%s")
				//	                            + " and is mounted at %s",
				//	                    fs.getName(), fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
				//	                    FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
				//	                    FormatUtil.formatValue(fs.getFreeInodes(), ""), FormatUtil.formatValue(fs.getTotalInodes(), ""),
				//	                    100d * fs.getFreeInodes() / fs.getTotalInodes(), fs.getVolume(), fs.getLogicalVolume(),
				//	                    fs.getMount()));
				//	            System.out.println("fs : "+fs.getMount());
				//	            System.out.println("fs : "+fs.getMount().indexOf(targetDrive));
				if(fs.getMount().indexOf(targetDrive) > -1) {
					resultUsage = (int) (100d * (total-usable) / total);
					break;
				}
			}


			Map<String,Object> diskInfo = new HashMap<String,Object>();
			diskInfo.put("total", CommonUtils.toGB(total));
			diskInfo.put("used", CommonUtils.toGB(total-usable));
			diskInfo.put("usage", resultUsage);
			cmsStatusMap.put("disk", diskInfo);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

	private  void printNetworkInterfaces(NetworkIF[] networkIFs) {
		StringBuilder sb = new StringBuilder("Network Interfaces:");
		if (networkIFs.length == 0) {
			sb.append(" Unknown");
		}
		for (NetworkIF net : networkIFs) {
			sb.append("\n ").append(net.toString());
			if(net.getSpeed() > 0) {
				Map<String,Object> nicInfo = new HashMap<String,Object>();
				nicInfo.put("name", net.getDisplayName());
				nicInfo.put("speed", CommonUtils.toGbps(net.getSpeed()));
				cmsStatusMap.put("nic", nicInfo);
			}
		}

		oshi.add(sb.toString());
	}

	private  void printNetworkParameters(NetworkParams networkParams) {
		oshi.add("Network parameters:\n " + networkParams.toString());
	}

	private  void printDisplays(Display[] displays) {
		oshi.add("Displays:");
		int i = 0;
		for (Display display : displays) {
			oshi.add(" Display " + i + ":");
			oshi.add(String.valueOf(display));
			i++;
		}
	}

	private  void printUsbDevices(UsbDevice[] usbDevices) {
		oshi.add("USB Devices:");
		for (UsbDevice usbDevice : usbDevices) {
			oshi.add(String.valueOf(usbDevice));
		}
	}

	private  void printSoundCards(SoundCard[] cards) {
		oshi.add("Sound Cards:");
		for (SoundCard card : cards) {
			oshi.add(" " + String.valueOf(card));
		}
	}
}
