package com.jms.dboard.manage.vo;

import org.apache.ibatis.type.Alias;

@Alias("weatherInfoVO")
public class WeatherInfoVO {
	String baseDate;
	String baseTime;
	String category;
	String pop; // 강수확률	%
	String pty;	//강수형태	코드값
	String pcp;	//1시간 강수량	범주 (1 mm)
	String reh;	//습도	%
	String sno;	//1시간 신적설	범주(1 cm)
	String sky;	//하늘상태	코드값
	String tmp;	//1시간 기온	℃
	String tmn;	//일 최저기온	℃
	String tmx;	//일 최고기온	℃
	String uuu;	//풍속(동서성분)	m/s
	String vvv;	//풍속(남북성분)	m/s
	String wav;	//파고	M
	String vec;	//풍향	deg
	String wsd;	//풍속	m/s
	String fcstTime;
	/**
	 * @return the baseDate
	 */
	public String getBaseDate() {
		return baseDate;
	}
	/**
	 * @param baseDate the baseDate to set
	 */
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}
	/**
	 * @return the baseTime
	 */
	public String getBaseTime() {
		return baseTime;
	}
	/**
	 * @param baseTime the baseTime to set
	 */
	public void setBaseTime(String baseTime) {
		this.baseTime = baseTime;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the pop
	 */
	public String getPop() {
		return pop;
	}
	/**
	 * @param pop the pop to set
	 */
	public void setPop(String pop) {
		this.pop = pop;
	}
	/**
	 * @return the pty
	 */
	public String getPty() {
		return pty;
	}
	/**
	 * @param pty the pty to set
	 */
	public void setPty(String pty) {
		this.pty = pty;
	}
	/**
	 * @return the pcp
	 */
	public String getPcp() {
		return pcp;
	}
	/**
	 * @param pcp the pcp to set
	 */
	public void setPcp(String pcp) {
		this.pcp = pcp;
	}
	/**
	 * @return the reh
	 */
	public String getReh() {
		return reh;
	}
	/**
	 * @param reh the reh to set
	 */
	public void setReh(String reh) {
		this.reh = reh;
	}
	/**
	 * @return the sno
	 */
	public String getSno() {
		return sno;
	}
	/**
	 * @param sno the sno to set
	 */
	public void setSno(String sno) {
		this.sno = sno;
	}
	/**
	 * @return the sky
	 */
	public String getSky() {
		return sky;
	}
	/**
	 * @param sky the sky to set
	 */
	public void setSky(String sky) {
		this.sky = sky;
	}
	/**
	 * @return the tmp
	 */
	public String getTmp() {
		return tmp;
	}
	/**
	 * @param tmp the tmp to set
	 */
	public void setTmp(String tmp) {
		this.tmp = tmp;
	}
	/**
	 * @return the tmn
	 */
	public String getTmn() {
		return tmn;
	}
	/**
	 * @param tmn the tmn to set
	 */
	public void setTmn(String tmn) {
		this.tmn = tmn;
	}
	/**
	 * @return the tmx
	 */
	public String getTmx() {
		return tmx;
	}
	/**
	 * @param tmx the tmx to set
	 */
	public void setTmx(String tmx) {
		this.tmx = tmx;
	}
	/**
	 * @return the uuu
	 */
	public String getUuu() {
		return uuu;
	}
	/**
	 * @param uuu the uuu to set
	 */
	public void setUuu(String uuu) {
		this.uuu = uuu;
	}
	/**
	 * @return the vvv
	 */
	public String getVvv() {
		return vvv;
	}
	/**
	 * @param vvv the vvv to set
	 */
	public void setVvv(String vvv) {
		this.vvv = vvv;
	}
	/**
	 * @return the wav
	 */
	public String getWav() {
		return wav;
	}
	/**
	 * @param wav the wav to set
	 */
	public void setWav(String wav) {
		this.wav = wav;
	}
	/**
	 * @return the vec
	 */
	public String getVec() {
		return vec;
	}
	/**
	 * @param vec the vec to set
	 */
	public void setVec(String vec) {
		this.vec = vec;
	}
	/**
	 * @return the wsd
	 */
	public String getWsd() {
		return wsd;
	}
	/**
	 * @param wsd the wsd to set
	 */
	public void setWsd(String wsd) {
		this.wsd = wsd;
	}
	/**
	 * @return the fcstTime
	 */
	public String getFcstTime() {
		return fcstTime;
	}
	/**
	 * @param fcstTime the fcstTime to set
	 */
	public void setFcstTime(String fcstTime) {
		this.fcstTime = fcstTime;
	}

	
}
