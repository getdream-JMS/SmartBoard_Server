package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("clientTemplateInfoVO")
public class ClientTemplateInfoVO {
	private Integer templateId;
	private String contentType;
	private Integer templateType;
	private Integer clientId;
	private Integer boardIndex;
	private Integer layoutType;
	/**
	 * @return the templateId
	 */
	public Integer getTemplateId() {
		return templateId;
	}
	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
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
	public Integer getTemplateType() {
		return templateType;
	}
	/**
	 * @param templateType the templateType to set
	 */
	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}
	/**
	 * @return the clientId
	 */
	public Integer getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the boardIndex
	 */
	public Integer getBoardIndex() {
		return boardIndex;
	}
	/**
	 * @param boardIndex the boardIndex to set
	 */
	public void setBoardIndex(Integer boardIndex) {
		this.boardIndex = boardIndex;
	}
	/**
	 * @return the layoutType
	 */
	public Integer getLayoutType() {
		return layoutType;
	}
	/**
	 * @param layoutType the layoutType to set
	 */
	public void setLayoutType(Integer layoutType) {
		this.layoutType = layoutType;
	}
	
	
}