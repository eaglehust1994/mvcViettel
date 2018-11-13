package com.viettel.asset.dao;

import org.springframework.stereotype.Repository;
import com.viettel.asset.bo.CatMerchandise;

@Repository("catMerchandiseDao")
public class CatMerchandiseDao extends HibernateDao<CatMerchandise, Long> {

}
