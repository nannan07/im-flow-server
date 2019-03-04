package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowRouteDealIVo;

public class FlowRouteDeal {
	private String id;

	private String routeId;

	private String routeDealType;

	private String query;

	private Date cTime;

	private String cUserId;

	private Date uTime;

	private String uUserId;

	private Boolean del;

	public FlowRouteDeal() {

	}

	public FlowRouteDeal(FlowRouteDealIVo flowRouteDealIVo) {
		if (flowRouteDealIVo != null) {
			this.id = flowRouteDealIVo.getId();
			this.routeId = flowRouteDealIVo.getRouteId();
			this.routeDealType = flowRouteDealIVo.getRouteDealType();
			this.query = flowRouteDealIVo.getQuery();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId == null ? null : routeId.trim();
	}

	public String getRouteDealType() {
		return routeDealType;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setRouteDealType(String routeDealType) {
		this.routeDealType = routeDealType == null ? null : routeDealType.trim();
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId == null ? null : cUserId.trim();
	}

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public String getuUserId() {
		return uUserId;
	}

	public void setuUserId(String uUserId) {
		this.uUserId = uUserId == null ? null : uUserId.trim();
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}
}