package com.yfwl.yfgp.service;

import java.util.List;

import com.yfwl.yfgp.model.FuturesShares;

public interface FuturesSharesService {
	public Integer insertFuturesShares(FuturesShares futuresShares);
	
	/**
	 *  获取云南白药的最后一条数据
	 * @return
	 */
	
	public FuturesShares getLastSameName();
	/**
	 * 获取国金证券的最后一条数据
	 * @return
	 */
	public FuturesShares getLastSameName2();
	
	/**
	 *  查询所有股票的记录
	 * @return
	 */
	public List<FuturesShares> getAllShares();
	
	//查询所有期货的记录
	public List<FuturesShares> getAllFutures();
	
	
}
