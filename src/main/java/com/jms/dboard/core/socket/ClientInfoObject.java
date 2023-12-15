package com.jms.dboard.core.socket;

public class ClientInfoObject {
	private int clientId;
	private int boardIndex;
	private String activeYn;
	private String message;

	public ClientInfoObject() {
	}
	public ClientInfoObject(int clientId, int boardIndex,String activeYn, String message) {
		super();
		this.clientId = clientId;
		this.boardIndex = boardIndex;
		this.activeYn = activeYn;
		this.message = message;
	}
	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * @return the boardIndex
	 */
	public int getBoardIndex() {
		return boardIndex;
	}
	/**
	 * @param boardIndex the boardIndex to set
	 */
	public void setBoardIndex(int boardIndex) {
		this.boardIndex = boardIndex;
	}
	
	/**
	 * @return the activeYn
	 */
	public String getActiveYn() {
		return activeYn;
	}
	/**
	 * @param activeYn the activeYn to set
	 */
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
