package com.jms.dboard.manage.vo;


import java.util.Map;
import org.apache.ibatis.type.Alias;


/**
 * 솔루션 기본 정보 
 * */
@Alias("helpInfoVO")
public class HelpInfoVO{
	
	private String solutionId;
	private String product;
	private String version;
	private String compVersionAdmin ;
	private String compVersionClient;
	private String companyName;
	private String companyAddress;
	private String companyRights;
	private String companyId;
	
	private Map<String,Object> connect;
	/**
	 * @return the solutionId
	 */
	public String getSolutionId() {
		return solutionId;
	}
	/**
	 * @param solutionId the solutionId to set
	 */
	public void setSolutionId(String solutionId) {
		this.solutionId = solutionId;
	}
	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the compVersionAdmin
	 */
	public String getCompVersionAdmin() {
		return compVersionAdmin;
	}
	/**
	 * @param compVersionAdmin the compVersionAdmin to set
	 */
	public void setCompVersionAdmin(String compVersionAdmin) {
		this.compVersionAdmin = compVersionAdmin;
	}
	/**
	 * @return the compVersionClient
	 */
	public String getCompVersionClient() {
		return compVersionClient;
	}
	/**
	 * @param compVersionClient the compVersionClient to set
	 */
	public void setCompVersionClient(String compVersionClient) {
		this.compVersionClient = compVersionClient;
	}
	/**
	 * @return the connect
	 */
	public Map<String, Object> getConnect() {
		return connect;
	}
	/**
	 * @param connect the connect to set
	 */
	public void setConnect(Map<String, Object> connect) {
		this.connect = connect;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}
	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	/**
	 * @return the companyRights
	 */
	public String getCompanyRights() {
		return companyRights;
	}
	/**
	 * @param companyRights the companyRights to set
	 */
	public void setCompanyRights(String companyRights) {
		this.companyRights = companyRights;
	}
	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
