package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("tonghapEventClientVO")
public class TonghapEventClientVO extends BaseSearchVO {
	int clientId;
    int boardIndex;
    List<Integer> alarms;
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
	 * @return the alarms
	 */
	public List<Integer> getAlarms() {
		return alarms;
	}
	/**
	 * @param alarms the alarms to set
	 */
	public void setAlarms(List<Integer> alarms) {
		this.alarms = alarms;
	}
        
	
}
