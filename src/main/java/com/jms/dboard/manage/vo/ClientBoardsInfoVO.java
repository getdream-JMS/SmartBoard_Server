package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("clientBoardsInfoVO")
public class ClientBoardsInfoVO {
	int clientId;
    int boardIndex;
    int organId;
    String boardType;
    String activeYn;
    String resolutionId;
    int boardCount;
    int layoutType;
    String alarmYn;
    int boardMinIndex;
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
	 * @return the organId
	 */
	public int getOrganId() {
		return organId;
	}
	/**
	 * @param organId the organId to set
	 */
	public void setOrganId(int organId) {
		this.organId = organId;
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
	 * @return the activeYn
	 */
	public String getActiveYn() {
		return activeYn;
	}
	/**
	 * @param activeYn the activeYn to set
	 */
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	/**
	 * @return the resolutionId
	 */
	public String getResolutionId() {
		return resolutionId;
	}
	/**
	 * @param resolutionId the resolutionId to set
	 */
	public void setResolutionId(String resolutionId) {
		this.resolutionId = resolutionId;
	}
	/**
	 * @return the boardCount
	 */
	public int getBoardCount() {
		return boardCount;
	}
	/**
	 * @param boardCount the boardCount to set
	 */
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	/**
	 * @return the layoutType
	 */
	public int getLayoutType() {
		return layoutType;
	}
	/**
	 * @param layoutType the layoutType to set
	 */
	public void setLayoutType(int layoutType) {
		this.layoutType = layoutType;
	}
	/**
	 * @return the alarmYn
	 */
	public String getAlarmYn() {
		return alarmYn;
	}
	/**
	 * @param alarmYn the alarmYn to set
	 */
	public void setAlarmYn(String alarmYn) {
		this.alarmYn = alarmYn;
	}
	/**
	 * @return the boardMinIndex
	 */
	public int getBoardMinIndex() {
		return boardMinIndex;
	}
	/**
	 * @param boardMinIndex the boardMinIndex to set
	 */
	public void setBoardMinIndex(int boardMinIndex) {
		this.boardMinIndex = boardMinIndex;
	}
	
}
