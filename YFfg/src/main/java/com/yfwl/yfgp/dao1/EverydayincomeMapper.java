package com.yfwl.yfgp.dao1;

import java.util.List;

import com.yfwl.yfgp.model.Everydayincome;

public interface EverydayincomeMapper {
	
	
  public int insertEverydayincome(Everydayincome everydayincome);
  
  public List<Everydayincome> getAllEverydayincome();
  
  
  public int updateEverydayincome(Everydayincome everydayincome);
  
  
  public List<Everydayincome> getDisEverydayincome();
  
  public List<Everydayincome> getAllEverydayincomeByUserid(int userid);
  
  public Integer deleteEverydayincome(int userid);
}
