package com.jms.dboard.manage.vo;

import java.util.List;
import org.apache.ibatis.type.Alias;

import com.jms.dboard.common.vo.BaseSearchVO;

/**
 * 통합 플랫폼 -> 스마트 정보 알림판 이벤트 정보  
 * */
@Alias("tonghapEventReqInfoVO")
public class TonghapEventReqInfoVO {
	
	private String uSvcOutbId;
	private String statEvetId;
	private String statEvetNm;
	private String procSt;
	private String outbPosX;
	private String outbPosY;
	private String outbPosNm;
	private String statEvetCntn;
	private String statEvetOutbDtm;
	private String evetDuration;
	/**
	 * @return the uSvcOutbId
	 */
	public String getuSvcOutbId() {
		return uSvcOutbId;
	}
	/**
	 * @param uSvcOutbId the uSvcOutbId to set
	 */
	public void setuSvcOutbId(String uSvcOutbId) {
		this.uSvcOutbId = uSvcOutbId;
	}
	/**
	 * @return the statEvetId
	 */
	public String getStatEvetId() {
		return statEvetId;
	}
	/**
	 * @param statEvetId the statEvetId to set
	 */
	public void setStatEvetId(String statEvetId) {
		this.statEvetId = statEvetId;
	}
	/**
	 * @return the statEvetNm
	 */
	public String getStatEvetNm() {
		return statEvetNm;
	}
	/**
	 * @param statEvetNm the statEvetNm to set
	 */
	public void setStatEvetNm(String statEvetNm) {
		this.statEvetNm = statEvetNm;
	}
	/**
	 * @return the procSt
	 */
	public String getProcSt() {
		return procSt;
	}
	/**
	 * @param procSt the procSt to set
	 */
	public void setProcSt(String procSt) {
		this.procSt = procSt;
	}
	/**
	 * @return the outbPosX
	 */
	public String getOutbPosX() {
		return outbPosX;
	}
	/**
	 * @param outbPosX the outbPosX to set
	 */
	public void setOutbPosX(String outbPosX) {
		this.outbPosX = outbPosX;
	}
	/**
	 * @return the outbPosY
	 */
	public String getOutbPosY() {
		return outbPosY;
	}
	/**
	 * @param outbPosY the outbPosY to set
	 */
	public void setOutbPosY(String outbPosY) {
		this.outbPosY = outbPosY;
	}
	/**
	 * @return the outbPosNm
	 */
	public String getOutbPosNm() {
		return outbPosNm;
	}
	/**
	 * @param outbPosNm the outbPosNm to set
	 */
	public void setOutbPosNm(String outbPosNm) {
		this.outbPosNm = outbPosNm;
	}
	/**
	 * @return the statEvetCntn
	 */
	public String getStatEvetCntn() {
		return statEvetCntn;
	}
	/**
	 * @param statEvetCntn the statEvetCntn to set
	 */
	public void setStatEvetCntn(String statEvetCntn) {
		this.statEvetCntn = statEvetCntn;
	}
	/**
	 * @return the statEvetOutbDtm
	 */
	public String getStatEvetOutbDtm() {
		return statEvetOutbDtm;
	}
	/**
	 * @param statEvetOutbDtm the statEvetOutbDtm to set
	 */
	public void setStatEvetOutbDtm(String statEvetOutbDtm) {
		this.statEvetOutbDtm = statEvetOutbDtm;
	}
	/**
	 * @return the evetDuration
	 */
	public String getEvetDuration() {
		return evetDuration;
	}
	/**
	 * @param evetDuration the evetDuration to set
	 */
	public void setEvetDuration(String evetDuration) {
		this.evetDuration = evetDuration;
	}

		
}
