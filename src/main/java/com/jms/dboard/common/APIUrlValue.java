package com.jms.dboard.common;

import org.springframework.stereotype.Component;

@Component
public class APIUrlValue {

	    public static final String TR_CTRL_LIVE_URL = "/transcoder/live";    
	    public static final String TR_CTRL_VOD_URL = "/transcoder/work";
	    public static final String TR_STATUS_VOD_URL = "/transcoder/work/status";
	    public static final String TR_STATUS_LIVE_URL = "/transcoder/live/status";
	    public static final String TR_CLEAR_VOD_URL = "/transcoder/work/clear";
	    public static final String TR_CLEAR_LIVE_URL = "/transcoder/live/clear";
	    
  
	    

		public static String getTrCtrlLiveUrl() {
			return TR_CTRL_LIVE_URL;
		}

		public static String getTrCtrlVodUrl() {
			return TR_CTRL_VOD_URL;
		}
	
}
