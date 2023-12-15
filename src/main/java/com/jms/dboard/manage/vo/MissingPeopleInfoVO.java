package com.jms.dboard.manage.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("missingPeopleInfoVO")
public class MissingPeopleInfoVO extends BaseSearchVO {
	private int id;
	private  String name;
	private  int age;
	private String gender;
	private String lostType;
	private String lostTime;
	private String lostLocation;
	private String lookLike;
	private String photoName;
	private String contentPath;
	private String originFile;
	private String postingStart;
	private String postingEnd;
	private String endYn;
	private String createDate;
	private String updateDate;
	private List<Map<String,Object>> connects;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the lostType
	 */
	public String getLostType() {
		return lostType;
	}
	/**
	 * @param lostType the lostType to set
	 */
	public void setLostType(String lostType) {
		this.lostType = lostType;
	}
	
	/**
	 * @return the lostTime
	 */
	public String getLostTime() {
		return lostTime;
	}
	/**
	 * @param lostTime the lostTime to set
	 */
	public void setLostTime(String lostTime) {
		this.lostTime = lostTime;
	}
	/**
	 * @return the lostLocation
	 */
	public String getLostLocation() {
		return lostLocation;
	}
	/**
	 * @param lostLocation the lostLocation to set
	 */
	public void setLostLocation(String lostLocation) {
		this.lostLocation = lostLocation;
	}
	/**
	 * @return the lookLike
	 */
	public String getLookLike() {
		return lookLike;
	}
	/**
	 * @param lookLike the lookLike to set
	 */
	public void setLookLike(String lookLike) {
		this.lookLike = lookLike;
	}
	/**
	 * @return the photoName
	 */
	public String getPhotoName() {
		return photoName;
	}
	/**
	 * @param photoName the photoName to set
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
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
	 * @return the endYn
	 */
	public String getEndYn() {
		return endYn;
	}
	/**
	 * @param endYn the endYn to set
	 */
	public void setEndYn(String endYn) {
		this.endYn = endYn;
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
	/**
	 * @return the connects
	 */
	public List<Map<String, Object>> getConnects() {
		return connects;
	}
	/**
	 * @param connects the connects to set
	 */
	public void setConnects(List<Map<String, Object>> connects) {
		this.connects = connects;
	}
	
}
