package com.jms.dboard.common.vo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import com.jms.dboard.common.utils.XMLBean;



/**
 *  /system/alarm정보 세팅에 관한 데이터 교환 bean
 */
@Root(name = "alarm")
public class AlarmVO extends XMLBean
{
	private static final long serialVersionUID = -9192225481031017470L;

	@Path("cpu")
	@Element(name = "critical")
	private int _iCpuCritical;

	@Path("cpu")
	@Element(name = "warning")
	private int _iCpuWarning;

	@Path("memory")
	@Element(name = "critical")
	private int _iMemoryCritical;

	@Path("memory")
	@Element(name = "warning")
	private int _iMemoryWarning;

	@Path("disk")
	@Element(name = "critical")
	private int _iDiskCritical;

	@Path("disk")
	@Element(name = "warning")
	private int _iDiskWarning;

	public int getCpuCritical() { return _iCpuCritical; }
	public int getMemoryCritical() { return _iMemoryCritical; }
	public int getDiskCritical() { return _iDiskCritical; }
	public int getCpuWarning() { return _iCpuWarning; }
	public int getMemoryWarning() { return _iMemoryWarning; }
	public int getDiskWarning() { return _iDiskWarning; }

} // AlarmVo
