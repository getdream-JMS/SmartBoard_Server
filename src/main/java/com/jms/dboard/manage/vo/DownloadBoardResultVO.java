package com.jms.dboard.manage.vo;

import org.apache.ibatis.type.Alias;

import java.util.List;
import java.util.Map;

@Alias("downloadBoardResultVO")
public class DownloadBoardResultVO {
    List<Map<String,Object>> notice = null;
    List<Map<String,Object>> promo = null;

    public List<Map<String, Object>> getNotice() {
        return notice;
    }

    public void setNotice(List<Map<String, Object>> notice) {
        this.notice = notice;
    }

    public List<Map<String, Object>> getPromo() {
        return promo;
    }

    public void setPromo(List<Map<String, Object>> promo) {
        this.promo = promo;
    }
}
