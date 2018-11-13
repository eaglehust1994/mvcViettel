/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;


import com.viettel.erp.bo.QualityCableMeaResultBO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("qualityCableMeaResultDAO")
public class QualityCableMeaResultDAO extends BaseFWDAOImpl<QualityCableMeaResultBO, Long> {

	public QualityCableMeaResultDAO() {
		this.model = new QualityCableMeaResultBO();
	}

	public QualityCableMeaResultDAO(Session session) {
		this.session = session;
	}
	
	

//	public Boolean insertListQualityResult(List<QualityCableMeaResultBO> listObj) {
//		Session session = getSession();
//		Transaction tx = session.beginTransaction();
//		session.doWork(new Work() {
//
//			@Override
//			public void execute(Connection conn) throws SQLException {
//				// TODO Auto-generated method stub
//				PreparedStatement pstmt = null;
//				try {
//					String sqlInsert = "insert into QUALITY_CABLE_MEA_RESULT "
//							+ "(objectChecking,length,attenuationLength,attenuationDegree,attenuationSum,attenuationAverage,note,qualityCableMeaReportId)"
//							+ " values (?,?,?,?,?,?,?,?) ";
//					pstmt = conn.prepareStatement(sqlInsert);
//					int i = 0;
//					for (QualityCableMeaResultBO qlBO : listObj) {
//						pstmt.setString(1, qlBO.getObjectChecking());
//						pstmt.setLong(2, qlBO.getLength());
//						pstmt.setLong(3, qlBO.getAttenuationLength());
//						pstmt.setLong(4, qlBO.getAttenuationDegree());
//						pstmt.setLong(5, qlBO.getAttenuationSum());
//						pstmt.setLong(6, qlBO.getAttenuationAverage());
//						pstmt.setString(7, qlBO.getNote());
//						pstmt.setLong(8, qlBO.getQualityCableMeaReportId());
//						pstmt.addBatch();
//
//						// 20 : JDBC batch size
//						if (i % 2 == 0) {
//							pstmt.executeBatch();
//						}
//						i++;
//					}
//					pstmt.executeBatch();
//				}catch (Exception e) {
//					tx.rollback();
//				} finally {
//					pstmt.close();
//				}
//			}
//
//		});
//		tx.commit();
//		session.close();
//		return true;
//	}
	
}
