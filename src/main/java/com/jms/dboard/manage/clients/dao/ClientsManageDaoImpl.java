package com.jms.dboard.manage.clients.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.dboard.manage.vo.BoardTemplateInfoVO;
import com.jms.dboard.manage.vo.ClientBoardDataReqVO;
import com.jms.dboard.manage.vo.ClientBoardsInfoVO;
import com.jms.dboard.manage.vo.ClientScheduleVO;
import com.jms.dboard.manage.vo.ClientTemplateInfoVO;
import com.jms.dboard.manage.vo.ClientsInfoResultVO;
import com.jms.dboard.manage.vo.ClientsInfoVO;
import com.jms.dboard.manage.vo.CustomHolidayOffVO;

@Repository(value = "clientsMngDao")
public class ClientsManageDaoImpl implements ClientsManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ClientsInfoResultVO> getClientsInfoList(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("clientsMng.selectClientsInfoList", param);
	}
	
	@Override
	public ClientsInfoResultVO getClientsInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("clientsMng.selectClientsInfo", param);
	}
	
	@Override
	public Map<String,Object> getClientInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("clientsMng.selectClientInfo", param);
	}
	
	@Override
	public int saveClientsInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("clientsMng.saveClientsInfo", param);
	}

	
	@Override
	public int updateClientsInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("clientsMng.updateClientsInfo", param);
	}
	
	@Override
	public int deleteClientsInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteClientsInfoList", param);
	}

	
	@Override
	public int getTotalClientsInfoList(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("clientsMng.selectTotalClientsInfoList", param);
	}

	@Override
	public int deleteClientsInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteClientsInfo", param);
	}
	@Override
	public int getNextSeqClients () throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("clientsMng.selectNextSeqClients");
	}

	@Override
	public int saveClientBoardsInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("clientsMng.saveClientBoardsInfo", param);
	}
	
	@Override
	public int deleteClientsBoardInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteClientsBoardInfo", param);
	}
	
	@Override
	public int saveClientSchedule(List<ClientScheduleVO> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("clientsMng.saveClientSchedule", params);
	}

	@Override
	public int updateClientSchedule(List<ClientScheduleVO> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("clientsMng.updateClientScheduleTemp", params);
	}
	
	
	@Override
	public List<Map<String,Object>> getInitPage(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("clientsMng.selectInitPage",param);
	}
	
	@Override
	public List<Map<String,Object>> getInitSmartFramePage(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("clientsMng.selectInitSmartFramePage",param);
	}
	
	@Override
	public List<ClientsInfoResultVO> getOrganClientsInfoList(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("clientsMng.selectOrganClientsInfoList", param);
	}

	@Override
	public int deleteClientsBoardInfo(List<Integer> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteClientsBoardInfoList", param);
	}

	@Override
	public int deleteClientSchedule(List<Integer> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteClientSchedule", params);
	}

	@Override
	public ClientBoardsInfoVO getClientBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("clientsMng.selectClientBoardInfo", param);
	}

	@Override
	public ClientsInfoResultVO getClientHoliday(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("clientsMng.selectClientHoliday", param);
	}

	@Override
	public int updateClientsOperationStatus(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("clientsMng.updateClientsOperationStatus", param);
	}

	@Override
	public int deleteExceptSchedule() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteExceptSchedule");
	}

	@Override
	public int insertExceptSchedule(List<CustomHolidayOffVO> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("clientsMng.insertExceptSchedule", params);
	}

	@Override
	public Map<String, Object> getIsOffDay() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("clientsMng.selectIsOffDay");
	}

	@Override
	public int deleteClientsMonitoringInfo(List<Integer> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteClientsMonitoringInfo", params);
	}

	@Override
	public int updateClientBoardInfo(ClientBoardsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("clientsMng.updateClientBoardInfo", param);
	}
	
	@Override
	public int saveBoardTemplateInfo(List<BoardTemplateInfoVO> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("clientsMng.saveBoardTemplateInfo", param);
	}

	@Override
	public int saveClientTemplateInfo(List<ClientTemplateInfoVO> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("clientsMng.saveClientTemplateInfo",param);
		
	}
	
	@Override
	public Map<String, Object> selectClientTemplateCount(ClientsInfoVO param) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("clientsMng.selectClientTemplateCount",param);
	}

	@Override
	public int updateBoardTemplateInfo(List<BoardTemplateInfoVO> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("clientsMng.updateBoardTemplateInfoList", param);
	}
	
	@Override
	public int updateBoardTemplateInfo(BoardTemplateInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("clientsMng.updateBoardTemplateInfo", param);
	}
	
	@Override
	public int deleteBoardTemplateInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteBoardTemplateInfo",param);
		
	}
	@Override
	public int deleteClientsTemplate(List<Integer> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteClientsTemplate", params);
	}
	
	@Override
	public int deleteTemplateContents(List<Integer> params) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteTemplateContents", params);
	}

	@Override
	public List<Map<String, Object>> getClientSmartFramePageList(ClientBoardsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("clientsMng.selectClientSmartFramePageList", param);
	}

	@Override
	public List<BoardTemplateInfoVO> selectClientBoardsList(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("clientsMng.selectTemplateBoardsList", param);
	}

	@Override
	public int saveSmartFrameInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("clientsMng.insertSmartFrameInfo",param);
	}
	
	@Override
	public int deleteSmartFrameInfo(ClientsInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("clientsMng.deleteSmartFrameInfo",param);
	}
}
	