package com.jms.dboard.common.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 *  검색 조건
 */
@Alias("baseSearchVO")
public class BaseSearchVO
{
	private int pageNum;
	private int pageCount;
	
	private String order;
	private String orderTarget;
	private int startRowNum;
	private String userId;
	private String startDate;
	private String endDate;
	private String sortingCol;
	private String sortingType;
	private List<String> searchContentType;
	private Boolean isManualPaging = null;
	private String roleType;

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
		if(getIsManualPaging() == null || !this.isManualPaging)this.startRowNum = (this.pageNum - 1) * this.pageCount;		
		return (this.startRowNum < 0)?0:this.startRowNum;
//		return startRowNum;
	}
	/**
	 * @param startRowNum the startRowNum to set
	 */
	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}
	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * @return the orderTarget
	 */
	public String getOrderTarget() {
		return orderTarget;
	}
	/**
	 * @param orderTarget the orderTarget to set
	 */
	public void setOrderTarget(String orderTarget) {
		this.orderTarget = orderTarget;
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
	
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @return the sortingCol
	 */
	public String getSortingCol() {
		return sortingCol;
	}
	/**
	 * @param sortingCol the sortingCol to set
	 */
	public void setSortingCol(String sortingCol) {
		this.sortingCol = sortingCol;
	}
	/**
	 * @return the sortingType
	 */
	public String getSortingType() {
		return sortingType;
	}
	/**
	 * @param sortingType the sortingType to set
	 */
	public void setSortingType(String sortingType) {
		this.sortingType = sortingType;
	}
	/**
	 * @return the searchContentType
	 */
	public List<String> getSearchContentType() {
		return searchContentType;
	}
	/**
	 * @param searchContentType the searchContentType to set
	 */
	public void setSearchContentType(List<String> searchContentType) {
		this.searchContentType = searchContentType;
	}
	/**
	 * @return the isManualPaging
	 */
	public Boolean getIsManualPaging() {
		return isManualPaging;
	}
	/**
	 * @param isManualPaging the isManualPaging to set
	 */
	public void setIsManualPaging(Boolean isManualPaging) {
		this.isManualPaging = isManualPaging;
	}
	/**
	 * @return the roleType
	 */
	public String getRoleType() {
		return roleType;
	}
	/**
	 * @param roleType the roleType to set
	 */
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
} // BaseSearchVO
