package com.jms.dboard.manage.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.jms.dboard.common.vo.BaseSearchVO;

/**
 * 통합 플랫폼 스마트 정보 알림판 정보 조회 결과 정보 
 * */

@Alias("clientLocationInfoResultVO")
public class ClientLocationInfoResultVO {	
	int totalCount;
	int resultCode;
    String errorString;
    
    List<Map<String,Object>>  datas;

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
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the errorString
	 */
	public String getErrorString() {
		return errorString;
	}

	/**
	 * @param errorString the errorString to set
	 */
	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	/**
	 * @return the datas
	 */
	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}
    
	
}
