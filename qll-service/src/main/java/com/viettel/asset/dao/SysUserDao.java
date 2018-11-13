package com.viettel.asset.dao;

import org.springframework.stereotype.Repository;

import com.viettel.asset.bo.SysUser;

@Repository("sysUserDao")
public class SysUserDao extends HibernateDao<SysUser, Long> {
	
}
