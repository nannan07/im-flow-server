package com.allmsi.flow.model.external;

import java.util.List;
import java.util.Map;

public class FlowUserAuthIdSort {

	//Key----authType
	//Value-----authDealIds
	Map<String,List<String>> authMap;

	public Map<String, List<String>> getAuthMap() {
		return authMap;
	}

	public void setAuthMap(Map<String, List<String>> authMap) {
		this.authMap = authMap;
	}
	
}
