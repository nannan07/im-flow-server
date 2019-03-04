package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.model.BusGuideRoute;
import com.allmsi.flow.model.OperationMenu;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ovo.FlowRouteOVo;
import com.allmsi.flow.service.FlowRouteService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.flow.service.RouteDealRuleEngine;
import com.allmsi.sys.util.StrUtil;

@Service
public class RouteDealRuleEngineImpl implements RouteDealRuleEngine {

	@Resource
	private FlowUserService flowUserService;

	@Resource
	private FlowRouteService flowRouteService;

	@Override
	public List<FlowRouteOVo> guideRoute(String flowCode, String nodeId, String query, String userId) {
		List<FlowRouteOVo> list = new ArrayList<FlowRouteOVo>();
		list.addAll(flowRouteService.selectRouteListByPreNode(flowCode, nodeId));// 获取到路由列表信息
		return list;
	}

	@Override
	public BusGuideRoute ruleJudgment(List<FlowRouteOVo> frList, String userId, String nodeId, String query) {
		BusGuideRoute busGuideRoute = new BusGuideRoute();
		List<FlowRouteOVo> selectedRouteList = frList;
		if (StrUtil.isEmpty(query)) {
			String strQuery = getQueryRule(frList);// 判断是否有查询条件
			if (StrUtil.isEmpty(strQuery)) {// 不需要查询条件
				busGuideRoute.setType(OperationMenu.ROUTE);
				busGuideRoute.setFlowRouteList(selectedRouteList);
				return busGuideRoute;// 多条路由
			} else {// 需要查询条件
				if (StrUtil.isEmpty(query)) {// 如果没有传入query
					busGuideRoute.setType(OperationMenu.QUERY);
					busGuideRoute.setMsg(strQuery);
					return busGuideRoute;// 返回给mis
				}
			}
		}
		selectedRouteList = queryRuleJudgment(frList, query);
	
		if (selectedRouteList == null || selectedRouteList.size() == 0) {// 没有查询到路由返回错误
			busGuideRoute.setType(OperationMenu.ERROR);
			busGuideRoute.setMsg("error");
		}
		if (selectedRouteList.size() == 1) {
			busGuideRoute = getFlowDealModelList(selectedRouteList.get(0), userId);
		}
		if (selectedRouteList.size() > 1) {
			busGuideRoute.setType(OperationMenu.ROUTE);
			busGuideRoute.setFlowRouteList(selectedRouteList);
		}
		return busGuideRoute;
	}

	private String getQueryRule(List<FlowRouteOVo> frList) {
		String strQuery = "";
		for (FlowRouteOVo fr : frList) {
			if (StrUtil.notEmpty(fr.getQuery())) {
				String[] kv = fr.getQuery().split(":");
				if (kv.length == 2) {
					strQuery += kv[0] + ";";
				}
			}
		}
		if (strQuery.endsWith(";")) {
			strQuery = strQuery.substring(0, strQuery.length() - 1);
		}
		return strQuery;
	}

	private List<FlowRouteOVo> queryRuleJudgment(List<FlowRouteOVo> frList, String query) {
		List<FlowRouteOVo> queryRouteList = new ArrayList<FlowRouteOVo>();
		Map<String, String> queryMap = new HashMap<>();
		if (StrUtil.notEmpty(query)) {
			queryMap = parseQuery2Map(query);
		}

		for (FlowRouteOVo fr : frList) {
			if (StrUtil.notEmpty(fr.getQuery())) {
				String[] kv = fr.getQuery().split(":");
				String key = kv[0];
				String value = kv[1];
				String queryValue = queryMap.get(key);
				if (queryMap.containsKey(key) && value.contains(queryValue)) {
					queryRouteList.add(fr);
				}
			}
		}

		return queryRouteList;
	}

	private Map<String, String> parseQuery2Map(String query) {
		Map<String, String> map = new HashMap<>();
		if (StrUtil.notEmpty(query)) {
			String[] strs = query.split(";");
			for (String s : strs) {
				String[] kv = s.split(":");
				if (kv.length == 2) {
					map.put(kv[0], kv[1]);
				}
			}
		}
		return map;
	}

	@Override
	public FlowRouteOVo getFlowRoute(String routeId) {
		return flowRouteService.selectRouteInfo(routeId);
	}

	@Override
	public BusGuideRoute getFlowDealModelList(FlowRouteOVo flowRouteOVo, String userId) {
		BusGuideRoute busGuideRoute = new BusGuideRoute();
		String routeDealType = flowRouteOVo.getRouteDealType();
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();// 路由处理人
		if(StrUtil.notEmpty(routeDealType)) {
			switch (routeDealType) {
			case "01":// 本部门
				List<FlowUserModel> ulist = flowUserService.deptUserByUser(userId);
				for (FlowUserModel flowUserModel : ulist) {
					list.add(
							new FlowUserModel(flowUserModel.getUserId(), flowUserModel.getName(), flowUserModel.getType()));
				}
				break;
			case "02":// 大部门
				List<FlowUserModel> auList = flowUserService.allDeptUserByUser(userId);
				for (FlowUserModel flowUserModel : auList) {
					list.add(
							new FlowUserModel(flowUserModel.getUserId(), flowUserModel.getName(), flowUserModel.getType()));
				}
				break;
			case "11":// 发起人
				list.add(new FlowUserModel(userId, "发起人", "01"));
				busGuideRoute.setFlag(true);
				break;
			}
		}
		
		busGuideRoute.setType(OperationMenu.USER);
		busGuideRoute.setRouteUserList(list);
		List<FlowRouteOVo> flowRouteList = new ArrayList<FlowRouteOVo>();
		flowRouteList.add(flowRouteOVo);
		busGuideRoute.setFlowRouteList(flowRouteList);
		return busGuideRoute;
	}
}
