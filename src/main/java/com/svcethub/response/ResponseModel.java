package com.svcethub.response;

import java.util.Map;
import java.util.Optional;

public class ResponseModel {
	
	private Long status ;
	private String message;
	private Optional<Object> payLoad;
	
	private Optional<Map<String, Object>> mapPayload;
	
	public ResponseModel() {
		super();
	}
	public ResponseModel(Long status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ResponseModel(Long status, String message, Optional<Object> payLoad) {
		super();
		this.status = status;
		this.message = message;
		this.payLoad = payLoad;
	}
	
	/*
	 * public ResponseModel(Long status, String message, Optional<Map<String,
	 * Object>> mapPayload) { super(); this.status = status; this.message = message;
	 * this.mapPayload = mapPayload; }
	 */
	
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getPayLoad() {
		return payLoad;
	}
	public Optional<Map<String, Object>> getMapPayload() {
		return mapPayload;
	}
	public void setMapPayload(Optional<Map<String, Object>> mapPayload) {
		this.mapPayload = mapPayload;
	}
	public void setPayLoad(Optional<Object> payLoad) {
		this.payLoad = payLoad;
	}
}
