package com.allmsi.flow.service;

import com.allmsi.flow.model.BusGuideNode;
import com.allmsi.flow.model.BusGuideRoute;

public interface NodeDealRuleEngine {

	BusGuideNode guideNode(BusGuideRoute busGuideRoute);
}
