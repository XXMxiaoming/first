package com.yfwl.yfgp.service;

import java.util.List;

import com.yfwl.yfgp.model.Revenue;

public interface RevenueService {

	Integer insertRevenue(Integer userid);
	Integer deleteRevenue(Integer userid);
	List<Revenue> getAllRevenue();
	Integer updateRevenue(Revenue revenue);
	Integer insertRevenue2(Revenue revenue);
	Revenue selectRevenue(Integer userid);
	Integer updateRevenue2(Revenue revenue);
	
	Revenue selectRevenue2();
	}
