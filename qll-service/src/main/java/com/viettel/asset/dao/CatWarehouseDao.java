package com.viettel.asset.dao;

import org.springframework.stereotype.Repository;
import com.viettel.asset.bo.CatWarehouse;

@Repository("catWarehouseDao")
public class CatWarehouseDao extends HibernateDao<CatWarehouse, Long> {

}
