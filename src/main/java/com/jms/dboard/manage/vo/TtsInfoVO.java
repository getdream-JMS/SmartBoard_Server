package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("ttsInfoVO")
public class TtsInfoVO {
	int contentId;
    String ttsInfo;
    int contIndex;
    int depth;
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
}
