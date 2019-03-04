package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowRouteDeal;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowRouteDealOVo {

	private String id;

	private String routeId;

	private String routeDealType;

	private Date cTime;

	private FlowUserModel user;

	public FlowRouteDealOVo() {

	}

	public FlowRouteDealOVo(FlowRouteDeal frd) {
		if (frd != null) {
			this.id = frd.getId();
			this.routeId = frd.getRouteId();
			this.routeDealType = frd.getRouteDealType();
			this.cTime = frd.getcTime();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getRouteDealType() {
		return routeDealType;
	}

	public void setRouteDealType(String routeDealType) {
		this.routeDealType = routeDealType;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public FlowUserModel getUser() {
		return user;
	}

	public void setUser(FlowUserModel user) {
		this.user = user;
	}
}
