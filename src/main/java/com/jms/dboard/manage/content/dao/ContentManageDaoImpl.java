package com.jms.dboard.manage.content.dao;

import java.util.List;
import java.util.Map;

import com.jms.dboard.manage.vo.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "contentManageDao")
public class ContentManageDaoImpl implements ContentManageDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int deleteContClients(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("contentManage.deleteContClients", param);
	}

	@Override
	public int saveContClients(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("contentManage.saveContClients", param);
	}

	@Override
	public List<Map<String,Object>> getClientAnnounceBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("contentManage.selectAnnounceBoardList", param);
	}
	
	
	@Override
	public Map<String,Object> getClientPromoBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectPromoBoardTypeList", param);
	}
	
	public List<Map<String,Object>> getClientPromotionBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("promoBoard.selectPromoBoardList", param);
	}

	@Override
	public List<Map<String, Object>> getContentInfoList(ContentInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("contentManage.selectContentInfoList", param);
	}

	@Override
	public int getTotalContentsList(ContentInfoVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectTotalContentsList", param);
	}

	@Override
	public int getTotalClientAnnounceBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectTotalClientAnnounceBoardInfo", param);
	}

	@Override
	public int getTotalClientPromoBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectTotalClientPromoBoardInfo", param);
	}
	
	@Override
	public int getCountUrgentContents(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectCountUrgentContents", param);
	}

	@Override
	public Map<String, Object> getImageBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectImageBoardInfo", param);
	}
	
	@Override
	public int saveContClientsPromo(Map<String,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("contentManage.saveContClientsPromo", param);
	}
	
	public List<Map<String,Object>> getClientEventBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("contentManage.selectEventBoardData", param);
	}
	@Override
	public List<Map<String,Object>> getClientNewsletterBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("contentManage.selectClientNewsletterBoardInfo", param);
	}
	
	@Override
	public Map<String,Object> getClientJuminBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectJuminBoardTypeList", param);
	}
	
	@Override
	public int getTotalClientJuminBoardInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectTotalClientJuminBoardInfo", param);
	}

	@Override
	public List<Map<String,Object>> getClientNoticeInfo(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("contentManage.selectClientNoticeInfo", param);
	}
	
	@Override
	public ReserveRoomInfoVO getReserveBoardData(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectReserveBoardData", param);
	}

	@Override
	public DownloadBoardResultVO getDownloadBoardList(ClientBoardDataReqVO param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("contentManage.selectDownloadBoardList", param);
	}


}
