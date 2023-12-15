package com.jms.dboard.manage.code.service;

import java.util.Map;
import com.jms.dboard.common.vo.CommonCodeVO;

public interface CodeManageService {
	/**Resolution 조회  */
	public Map<String,Object> getResolutionList (Map<String,Object> param);
	
	/**클라이언트에서 코드 정보 조회*/
	public Map<String,Object> getTouchTimeoutConfig (CommonCodeVO param);
}
