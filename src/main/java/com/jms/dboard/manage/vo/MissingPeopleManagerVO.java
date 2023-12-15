package com.jms.dboard.manage.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("missingPeopleManagerVO")
public class MissingPeopleManagerVO extends BaseSearchVO {
	private int id;
	private  String name;
	private  int age;
	private String lostType;
	private String lostLocation;
	private String lookLike;
	private String photoPath;
	private String postingStart;
	private String postingEnd;
	private String endYn;
	private List<Map<String,Object>> connect;

	
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
	 * @return the photoPath
	 */
	public String getPhotoPath() {
		return photoPath;
	}
	/**
	 * @param photoPath the photoPath to set
	 */
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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
	 * @return the connect
	 */
	public List<Map<String, Object>> getConnect() {
		return connect;
	}
	/**
	 * @param connet the connect to set
	 */
	public void setConnect(List<Map<String, Object>> connect) {
		this.connect = connect;
	}	
    
    
}
