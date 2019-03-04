package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowRouteDeal;

public interface FlowRouteDealMapper {
    List<FlowRouteDeal> selectFlowRouteDealList(String routeId);

	FlowRouteDeal selectByPrimaryKey(String id);

	int insertSelective(FlowRouteDeal record);

    int updateByPrimaryKeySelective(FlowRouteDeal record);

	int deleteByPrimaryKey(FlowRouteDeal frd);

	int deleteByRouteId(String id);
}