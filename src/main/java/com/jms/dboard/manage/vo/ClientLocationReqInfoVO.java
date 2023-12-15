package com.jms.dboard.manage.vo;

import java.util.List;
import org.apache.ibatis.type.Alias;

import com.jms.dboard.common.vo.BaseSearchVO;

/**
 * 통합 플랫폼 스마트 정보 알림판 정보 조회 요청 정보 
 * */
@Alias("clientLocationReqInfoVO")
public class ClientLocationReqInfoVO {
	
	private String id;
	private String nm;
	private String adr;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nm
	 */
	public String getNm() {
		return nm;
	}
	/**
	 * @param nm the nm to set
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}
	/**
	 * @return the adr
	 */
	public String getAdr() {
		return adr;
	}
	/**
	 * @param adr the adr to set
	 */
	public void setAdr(String adr) {
		this.adr = adr;
	}	
	
}
