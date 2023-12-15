package com.jms.dboard.manage.help;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jms.dboard.common.vo.BaseSearchVO;
import com.jms.dboard.manage.content.service.ContentManageService;
import com.jms.dboard.manage.help.service.HelpManageService;
import com.jms.dboard.manage.tonghap.service.TonghapInterfaceService;
import com.jms.dboard.manage.vo.AnnounceBoardVO;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientLocationInfoResultVO;
import com.jms.dboard.manage.vo.ClientLocationReqInfoVO;
import com.jms.dboard.manage.vo.TonghapEventClientVO;
import com.jms.dboard.manage.vo.TonghapEventReqInfoVO;

/**
 * 관리자 페이지 솔루션 및 구청 및 기업체 기본 정보 제공
 *
 * @author shmoon
 */
@RequestMapping(value = "/cms")
@RestController
public class HelpInfoController {

	@Autowired
	HelpManageService helpManageService;
	
	/**
	 * 솔수션 및 업체 정보 조회
	 * @return 
	 */
	@RequestMapping(value = "/help", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getHelpInfo(@ModelAttribute ClientBoardDataReqVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = helpManageService.getHelpInfo();
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getHelpInfo()	
}
