package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("boardTemplateInfoVO")
public class BoardTemplateInfoVO extends BaseSearchVO {
	int clientId;
    int boardIndex;
    int templateId;
    String smartFrame;
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
	 * @return the templateId
	 */
	public int getTemplateId() {
		return templateId;
	}
	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
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
