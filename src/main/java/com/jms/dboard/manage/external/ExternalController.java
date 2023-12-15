package com.jms.dboard.manage.external;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

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
import com.jms.dboard.manage.clients.service.ClientsManageService;
import com.jms.dboard.manage.code.service.CodeManageService;
import com.jms.dboard.manage.content.service.ContentManageService;
import com.jms.dboard.manage.menu.service.MenuService;
import com.jms.dboard.manage.scheduler.service.SchedulerManageService;
import com.jms.dboard.manage.tonghap.service.TonghapInterfaceService;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientLocationInfoResultVO;
import com.jms.dboard.manage.vo.ClientLocationReqInfoVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.TonghapEventReqInfoVO;
import com.jms.dboard.common.vo.CommonCodeVO;

/**
 * 외부 연동 API 관련 컨트롤러
 *
 * @author shmoon
 */
@RequestMapping(value = "/cms")
@RestController
public class ExternalController {

	@Autowired
	ContentManageService contentManageService;
	
	@Autowired
	ClientsManageService clientManageService;
	
	@Autowired
	TonghapInterfaceService tonghapIfService;
	
	@Autowired
	SchedulerManageService schedulerManageService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	CodeManageService codeManageService;
	
	/**
	 * 클라이언트 게시판 데이타 리스트  조회 
	 * @return 
	 */
	@RequestMapping(value = "/client/board", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientBoardData(@ModelAttribute ClientBoardDataReqVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("=========================================");
//			System.out.println("client id : "+ param.getClientId());
//			System.out.println("board Index : "+ param.getBoardIndex());
			Map<String,Object> resultMap = contentManageService.getContentsInfoByBoard(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientBoardData()
	/**
	 * 스마트 정보 알림판 위치 정보  조회 
	 * @return 
	 */
	@RequestMapping(value = "/ext/i0010", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientLocation(@RequestBody ClientLocationReqInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("id : "+param.getId());
//			System.out.println("nm : "+param.getNm());
//			System.out.println("adr : "+param.getAdr());
//			
			ClientLocationInfoResultVO resultMap = tonghapIfService.getClientLocation(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientLocation()
	
	/**
	 * 긴급 메시지 저장
	 * @return 
	 */
	@RequestMapping(value = "/ext/i0020", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getTonghapEventInfo(@RequestBody TonghapEventReqInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			Map<String,Object> resultMap = tonghapIfService.getTonghapEventInfo(param);
			
//			System.out.println("getEvetDuration : "+param.getEvetDuration());
//			System.out.println("getOutbPosNm : "+param.getOutbPosNm());
//			System.out.println("getOutbPosX : "+param.getOutbPosX());
//			System.out.println("getOutbPosY : "+param.getOutbPosY());
//			System.out.println("getProcSt : "+param.getProcSt());
//			System.out.println("getStatEvetCntn : "+param.getStatEvetCntn());
//			System.out.println("getStatEvetNm : "+param.getStatEvetId());
//			System.out.println("getStatEvetNm : "+param.getStatEvetNm());
//			System.out.println("getStatEvetOutbDtm : "+param.getStatEvetOutbDtm());
//			System.out.println("getuSvcOutbId : "+param.getuSvcOutbId());
			resultJson = tonghapIfService.saveEmegencyInfo(param);
//			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getTonghapEventInfo()
	
	
	/**
	 * 긴급 메시지 호출 (클라이언트)
	 * @return 
	 */
	@RequestMapping(value = "/client/alarm", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getTonghapEventInfoClient(@RequestParam ("alarmId") int alarmId, HttpServletRequest request, HttpServletResponse response)
	{
		
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultJson = tonghapIfService.getTonghapEventInfoClient(alarmId);
//			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getTonghapEventInfoClient()
	
	/**
	 * 긴급 메시지 호출 (CMS)
	 * @return 
	 */
	@RequestMapping(value = "/event", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getTonghapEventInfoCMS(@RequestParam("pageNum") int pageNum,
			@RequestParam("pageCount") int pageCount,
			@RequestParam("sortingCol") String sortingCol,
			@RequestParam("sortingType") String sortingType, HttpServletRequest request, HttpServletResponse response)
	{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		HttpHeaders headers = new HttpHeaders();
		try {
			BaseSearchVO paramMap = new BaseSearchVO();
			paramMap.setPageNum(pageNum);
			paramMap.setPageCount(pageCount);
			paramMap.setSortingCol(sortingCol);
			paramMap.setSortingType(sortingType);
			
			resultMap = tonghapIfService.getTonghapEventInfoCMS(paramMap);
//			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);
			return new ResponseEntity<>(resultMap.toString(), headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getTonghapEventInfo()
	
	/**
	 * 날씨 정보 조회
	 * @return 
	 */
	@RequestMapping(value = "/client/weather", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getWeatherInfo(HttpServletRequest request, HttpServletResponse response)
	{
		
		Map<String,Object> resultMap = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			resultMap = schedulerManageService.getClientWeatherInfo();
//			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap = new HashMap<String,Object>();
			resultMap.put("result", 500);
			resultMap.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultMap, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getWeatherInfo()
	
	/**
	 *  플랫폼 날씨 정보 조회
	 * @return 
	 */
	@RequestMapping(value = "/client/weather1", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getWeatherInfo1(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			{"time":"2021-05-07T07:50:13.539+00:00","temperature":17.5,"airQuality":557.0,"rainfall":0.0}
			
			resultJson = new JSONObject();
			resultJson.put("temperature", 17.5);
			resultJson.put("airQuality", 557.0);
			resultJson.put("rainfall", 0.0);
			
//			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getWeatherInfo()
	
	/**
	 * 게시판 홈메뉴 컨텐츠 조회 
	 * @return 
	 */
	@RequestMapping(value = "/client/board/main", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientMainBoarData(@ModelAttribute ClientBoardDataReqVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("=========================================");
//			System.out.println("client id : "+ param.getClientId());
//			System.out.println("board Index : "+ param.getBoardIndex());
			Map<String,Object> resultMap = contentManageService.getContentsInfoByMainBoard(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientMainBoarData()
	
	/**
	 * 게시판 홈메뉴 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/client/board/menu", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientMenuData(@ModelAttribute ClientsInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("=========================================");
//			System.out.println("client id : "+ param.getClientId());
//			System.out.println("board Index : "+ param.getBoardIndex());
			Map<String,Object> resultMap = menuService.getClientMenuInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientMainBoarData()
	
	/**
	 * 게시판 공지사항 데이타 조회 
	 * @return 
	 */
	@RequestMapping(value = "/client/board/notice", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientNoticeData(@ModelAttribute ClientBoardDataReqVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("=========================================");
//			System.out.println("client id : "+ param.getClientId());
//			System.out.println("board Index : "+ param.getBoardIndex());
			Map<String,Object> resultMap = contentManageService.getClientNoticeInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientNoticeData()
	
	/**
	 * 게시판 공지사항 데이타 조회 
	 * @return 
	 */
	@RequestMapping(value = "/client/event", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientEventData(@ModelAttribute ClientBoardDataReqVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("=========================================");
//			System.out.println("client id : "+ param.getClientId());
//			System.out.println("board Index : "+ param.getBoardIndex());
			Map<String,Object> resultMap = contentManageService.getClientEventData(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientNoticeData()
	
	@RequestMapping(value = "/client/external", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getExternalPage(@ModelAttribute ClientBoardDataReqVO param, HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("iframe 내 페이지 요청");
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String,Object> resultMap = menuService.getExternalMenuPage(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	/**
	 * 게시판 터치 타이암웃 설정 데이타 조회 
	 * @return 
	 */
	@RequestMapping(value = "/client/code", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getTemplateConfig(@ModelAttribute CommonCodeVO param, HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			Map<String,Object> resultMap = codeManageService.getTouchTimeoutConfig(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getTemplateConfig()
	
	/**
	 * 스마트 액자 게시판 초기 페이지 리스트 조회 
	 * @return 
	 */
	@RequestMapping(value = "/client/smartframe/list", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientSmartFramePageList(@ModelAttribute ClientBoardsInfoVO param, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("=========================================");
//			System.out.println("client id : "+ param.getClientId());
//			System.out.println("board Index : "+ param.getBoardIndex());
			Map<String,Object> resultMap = clientManageService.getClientSmartFramePageList(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientBoardData()
	
	/**
	 * 클라이언트 정보 조회
	 * @return 
	 */
	@RequestMapping(value = "/client/{clientId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientInfo(@PathVariable int clientId, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("=========================================");
//			System.out.println("client id : "+ param.getClientId());
//			System.out.println("board Index : "+ param.getBoardIndex());
			Map<String,Object> resultMap = clientManageService.getClientInfo(clientId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientInfo()
	
	/**
	 * 클라이언트 보드 리스트 조회
	 * @return 
	 */
	@RequestMapping(value = "/client/board/list/{clientId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getClientBoardList(@PathVariable int clientId, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("=========================================");
//			System.out.println("client id : "+ param.getClientId());
//			System.out.println("board Index : "+ param.getBoardIndex());
			Map<String,Object> resultMap = clientManageService.selectClientBoardsList(clientId);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getClientBoardList()
	
	/**
	 * client > 회의실 예약 데이타 조회 
	 * @return 
	 */
	@RequestMapping(value = "/client/reserve/{clientId}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getReserveBoardData(@PathVariable int clientId, HttpServletRequest request, HttpServletResponse response)
	{
		
//		System.out.println(param.getClientId());
//		System.out.println(param.getBoardIndex());
//		System.out.println(param.getResolutionWidth());
//		System.out.println(param.getResolutionHeight());
		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("=========================================");
//			System.out.println("client id : "+ param.getClientId());
//			System.out.println("board Index : "+ param.getBoardIndex());
			ClientBoardDataReqVO param = new ClientBoardDataReqVO();
			param.setClientId(clientId);
			Map<String,Object> resultMap = contentManageService.getReserveBoardData(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getReserveBoardData()

	/**
	 * 소스 정보 및 파일 압축 경로 전달
	 * 정보 요청시 전달 할 파일 압축 진행
	 * @return
	 */
	@RequestMapping(value = "/client/download", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> getDownloadInfo(@ModelAttribute Map<String,Object> param, HttpServletRequest request, HttpServletResponse response)
	{

		JSONObject resultJson = null;
		HttpHeaders headers = new HttpHeaders();
		try {
//			System.out.println("id : "+param.getId());
//			System.out.println("nm : "+param.getNm());
//			System.out.println("adr : "+param.getAdr());
//
			Map<String,Object> resultMap = contentManageService.getDownloadInfo(param);
			return new ResponseEntity<>(resultMap, headers, HttpStatus.OK);


		} catch (Exception e) {
			e.printStackTrace();
			resultJson = new JSONObject();
			resultJson.put("result", 500);
			resultJson.put("resultMsg", e.getMessage());
			return new ResponseEntity<>(resultJson.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} // getDownloadInfo()
}
