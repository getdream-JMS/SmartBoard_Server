package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("clientBoardDataReqVO")
public class ClientBoardDataReqVO extends BaseSearchVO {
	String organId;
	int clientId;
	int boardIndex;
	int resolutionWidth;
	int resolutionHeight;
	String curDate;
	String boardType;
	String contentType;
	String templateType;
	String urgentYn;
	int monthNumber;
	String smartFrame;
	
	/**
	 * @return the organId
	 */
	public String getOrganId() {
		return organId;
	}
	/**
	 * @param organId the organId to set
	 */
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the boardIndex
	 */
	public int getBoardIndex() {
		return boardIndex;
	}
	/**
	 * @param boardIndex the boardIndex to set
	 */
	public void setBoardIndex(int boardIndex) {
		this.boardIndex = boardIndex;
	}
	/**
	 * @return the resolutionWidth
	 */
	public int getResolutionWidth() {
		return resolutionWidth;
	}
	/**
	 * @param resolutionWidth the resolutionWidth to set
	 */
	public void setResolutionWidth(int resolutionWidth) {
		this.resolutionWidth = resolutionWidth;
	}
	/**
	 * @return the resolutionHeight
	 */
	public int getResolutionHeight() {
		return resolutionHeight;
	}
	/**
	 * @param resolutionHeight the resolutionHeight to set
	 */
	public void setResolutionHeight(int resolutionHeight) {
		this.resolutionHeight = resolutionHeight;
	}
	/**
	 * @return the curDate
	 */
	public String getCurDate() {
		return curDate;
	}
	/**
	 * @param curDate the curDate to set
	 */
	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}
	/**
	 * @return the boardType
	 */
	public String getBoardType() {
		return boardType;
	}
	/**
	 * @param boardType the boardType to set
	 */
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the templateType
	 */
	public String getTemplateType() {
		return templateType;
	}
	/**
	 * @param templateType the templateType to set
	 */
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	/**
	 * @return the urgentYn
	 */
	public String getUrgentYn() {
		return (urgentYn == null || urgentYn.equals("null")?"N":urgentYn);
	}
	/**
	 * @param urgentYn the urgentYn to set
	 */
	public void setUrgentYn(String urgentYn) {
		this.urgentYn = urgentYn;
	}
	/**
	 * @return the monthNumber
	 */
	public int getMonthNumber() {
		return monthNumber;
	}
	/**
	 * @param monthNumber the monthNumber to set
	 */
	public void setMonthNumber(int monthNumber) {
		this.monthNumber = monthNumber;
	}
	/**
	 * @return the smartFrame
	 */
	public String getSmartFrame() {
		return smartFrame;
	}
	/**
	 * @param smartFrame the smartFrame to set
	 */
	public void setSmartFrame(String smartFrame) {
		this.smartFrame = smartFrame;
	}
	
}
