package com.jms.dboard.manage.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

@Alias("missingPeopleResInfoVO")
public class MissingPeopleResInfoVO {
	private int totalCount;
	private int evetDuraion;
	private List<MissingPeopleInfoVO> list;
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the evetDuraion
	 */
	public int getEvetDuraion() {
		return evetDuraion;
	}
	/**
	 * @param evetDuraion the evetDuraion to set
	 */
	public void setEvetDuraion(int evetDuraion) {
		this.evetDuraion = evetDuraion;
	}
	/**
	 * @return the list
	 */
	public List<MissingPeopleInfoVO> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<MissingPeopleInfoVO> list) {
		this.list = list;
	}	

}
