package com.yfwl.yfgp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfwl.yfgp.dao1.FuturesSharesMapper;
import com.yfwl.yfgp.model.FuturesShares;
import com.yfwl.yfgp.service.FuturesSharesService;

@Service
public class FuturesSharesServiceImpl implements FuturesSharesService {
	@Autowired
	FuturesSharesMapper futuresSharesMapper;

	@Override
	public Integer insertFuturesShares(FuturesShares futuresShares) {
		// TODO Auto-generated method stub
		return futuresSharesMapper.insertFuturesShares(futuresShares);
	}
	/**
	 * 获取云南白药的最后一条数据
	 */
	@Override
	public FuturesShares getLastSameName() {
		// TODO Auto-generated method stub
		return futuresSharesMapper.getLastSameName();
	}
	/**
	 * 获取国金证券的最后一条数据
	 */
	@Override
	public FuturesShares getLastSameName2() {
		// TODO Auto-generated method stub
		return futuresSharesMapper.getLastSameName2();
	}
	@Override
	public List<FuturesShares> getAllShares() {
		// TODO Auto-generated method stub
		return futuresSharesMapper.getAllShares();
	}
	@Override
	public List<FuturesShares> getAllFutures() {
		// TODO Auto-generated method stub
		return futuresSharesMapper.getAllFutures();
	}


}
