package com.jms.dboard.common.vo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import com.jms.dboard.common.utils.XMLBean;

/**
 *  공통 코드 
 */
public class CommonCodeVO
{
	private static final long serialVersionUID = -9192225481031017470L;
	
	private int id;
	private String codeGrp;
	private String codeName;
	private String code;
	private String codeInfo;
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
	 * @return the codeGrp
	 */
	public String getCodeGrp() {
		return codeGrp;
	}
	/**
	 * @param codeGrp the codeGrp to set
	 */
	public void setCodeGrp(String codeGrp) {
		this.codeGrp = codeGrp;
	}
	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		return codeName;
	}
	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the codeInfo
	 */
	public String getCodeInfo() {
		return codeInfo;
	}
	/**
	 * @param codeInfo the codeInfo to set
	 */
	public void setCodeInfo(String codeInfo) {
		this.codeInfo = codeInfo;
	}
	
} // CommonCodeVO
