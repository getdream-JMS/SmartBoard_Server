package com.jms.dboard.manage.vo;

import org.apache.ibatis.type.Alias;

@Alias("newsBoardDetailsVO")
public class NewsBoardDetailsVO {
	int contentId;
    int contIndex;
    String fileName;
    String ttsInfo;
	/**
	 * @return the contentId
	 */
	public int getContentId() {
		return contentId;
	}
	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	/**
	 * @return the contIndex
	 */
	public int getContIndex() {
		return contIndex;
	}
	/**
	 * @param contIndex the contIndex to set
	 */
	public void setContIndex(int contIndex) {
		this.contIndex = contIndex;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the ttsInfo
	 */
	public String getTtsInfo() {
		return ttsInfo;
	}
	/**
	 * @param ttsInfo the ttsInfo to set
	 */
	public void setTtsInfo(String ttsInfo) {
		this.ttsInfo = ttsInfo;
	}
    
}
