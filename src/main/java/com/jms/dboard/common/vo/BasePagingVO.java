package com.jms.dboard.common.vo;

import java.util.List;

/**
 *  페이징  조건
 */
public class BasePagingVO
{
	private int pageNum;
	private int pageCount;
	private int startRowNum;
	private String userId;


	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}
	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return this.pageCount;
	}
	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}	
	/**
	 * @return the startRowNum
	 */
	public int getStartRowNum() {
		return (this.startRowNum < 0)?0:this.startRowNum;
	}
	/**
	 * @param startRowNum the startRowNum to set
	 */
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}	
	
} // BasePagingVO
