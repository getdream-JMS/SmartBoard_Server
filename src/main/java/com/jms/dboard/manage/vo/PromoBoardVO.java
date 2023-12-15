package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("promoBoardVO")
public class PromoBoardVO extends BaseSearchVO {
	int contentId;
    String title;
    String organId;
    String contentPath;
    String contentName;
    String postingStart;
    String postingEnd;
    String contentInfo;
    String repeatType;    
    int priority;
    String delExpired;
    String tags;
    List<String> client;
    String createDate;
    String updateDate;
    MultipartFile file;
    String publisher;
    String originFile;
    String contentType;
    String boardType;
    int intervalTime;
    int layoutType;
    String urgentYn;
    String videoScale;
    String useMain;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
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
	 * @return the contentPath
	 */
	public String getContentPath() {
		return contentPath;
	}
	/**
	 * @param contentPath the contentPath to set
	 */
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	/**
	 * @return the contentName
	 */
	public String getContentName() {
		return contentName;
	}
	/**
	 * @param contentName the contentName to set
	 */
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	/**
	 * @return the postingStart
	 */
	public String getPostingStart() {
		return postingStart;
	}
	/**
	 * @param postingStart the postingStart to set
	 */
	public void setPostingStart(String postingStart) {
		this.postingStart = postingStart;
	}
	/**
	 * @return the postingEnd
	 */
	public String getPostingEnd() {
		return postingEnd;
	}
	/**
	 * @param postingEnd the postingEnd to set
	 */
	public void setPostingEnd(String postingEnd) {
		this.postingEnd = postingEnd;
	}
	/**
	 * @return the contentInfo
	 */
	public String getContentInfo() {
		return contentInfo;
	}
	/**
	 * @param contentInfo the contentInfo to set
	 */
	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
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
	 * @return the repeatType
	 */
	public String getRepeatType() {
		return repeatType;
	}
	/**
	 * @param repeatType the repeatType to set
	 */
	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return the delExpired
	 */
	public String getDelExpired() {
		return delExpired;
	}
	/**
	 * @param delExpired the delExpired to set
	 */
	public void setDelExpired(String delExpired) {
		this.delExpired = delExpired;
	}
	
	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	

	/**
	 * @return the client
	 */
	public List<String> getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(List<String> client) {
		this.client = client;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the originFile
	 */
	public String getOriginFile() {
		return originFile;
	}
	/**
	 * @param originFile the originFile to set
	 */
	public void setOriginFile(String originFile) {
		this.originFile = originFile;
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
	 * @return the intervalTime
	 */
	public int getIntervalTime() {
		return intervalTime;
	}
	/**
	 * @param intervalTime the intervalTime to set
	 */
	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
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
	 * @return the urgentYn
	 */
	public String getUrgentYn() {
		return urgentYn;
	}
	/**
	 * @param urgentYn the urgentYn to set
	 */
	public void setUrgentYn(String urgentYn) {
		this.urgentYn = urgentYn;
	}
	/**
	 * @return the videoScale
	 */
	public String getVideoScale() {
		return videoScale;
	}
	/**
	 * @param videoScale the videoScale to set
	 */
	public void setVideoScale(String videoScale) {
		this.videoScale = videoScale;
	}
	public String getUseMain() {
		return useMain;
	}
	public void setUseMain(String useMain) {
		this.useMain = useMain;
	}
	
	
}
