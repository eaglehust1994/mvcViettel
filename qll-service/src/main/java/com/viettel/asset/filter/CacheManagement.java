package com.viettel.asset.filter;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheManagement {
	private static CacheManagement instance;
	public static synchronized CacheManagement getInstance(){
                     if(instance==null){
                            instance=new CacheManagement();
                    
                }
		return instance;
	}
	
	public void putCache(String jsessionI,Object o){
		Cache cache = CacheManager.getInstance().getCache("CacheName");
		CacheManager cm =CacheManager.getInstance();
		cm.addCache("CacheName");

		Element element = new Element(jsessionI, o, Boolean.FALSE, (int)600000, 0);
		cache.put(element);
	}
	
	public Object getCache(String jsessionI){
		Cache cache = CacheManager.getInstance().getCache("CacheName");
		Element elem = cache.get(jsessionI);
		if (elem == null) 
		{
			return null;
		}
		if (elem.isExpired())
		{
			return null;
		}

		return elem.getObjectValue();
		
	}
	
//	public 
	
}
