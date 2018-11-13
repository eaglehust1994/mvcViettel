package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblQlCvPtkFileBO;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TblQlCvPtkFileDTO;


import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import org.hibernate.type.DateType;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("tblQlCvPtkFileDAO")
public class TblQlCvPtkFileDAO extends BaseFWDAOImpl<TblQlCvPtkFileBO, Long> {

    public TblQlCvPtkFileDAO() {
        this.model = new TblQlCvPtkFileBO();
    }

    public TblQlCvPtkFileDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<TblQlCvPtkFileDTO> doSearch(TblQlCvPtkFileDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder("select ");
		stringBuilder.append(" T1.TBL_QL_CV_PTK_FILE_ID tblQlCvPtkFileId ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.PATH_FILE pathFile ");
		stringBuilder.append(",T1.ID_CV_PTK idCvPtk ");
		stringBuilder.append(",T1.FILE_TYPE fileType ");
		stringBuilder.append(",T1.CREATED_AT createdAt ");
		stringBuilder.append(",T1.CREATED_BY createdBy ");
		stringBuilder.append(",T1.UPDATED_AT updatedAt ");
		stringBuilder.append(",T1.UPDATED_BY updatedBy ");
    	stringBuilder.append("FROM  TBL_QL_CV_PTK_FILE T1 join TBL_QL_CV_PTK T2 on T2.TBL_QL_CV_PTK_ID=T1.ID_CV_PTK ");  

    	stringBuilder.append(" WHERE 1=1 ");	
    	
		if (obj.getIdCvPtk() != null) {
			stringBuilder.append(" AND T1.ID_CV_PTK =:idCvPtk ");
		}

		stringBuilder.append(" order by T1.TBL_QL_CV_PTK_FILE_ID desc ");
		
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
			query.addScalar("tblQlCvPtkFileId", new LongType());
			query.addScalar("name", new StringType());
			query.addScalar("pathFile", new StringType());
			query.addScalar("idCvPtk", new LongType());
			query.addScalar("fileType", new StringType());
			query.addScalar("createdAt", new DateType());
			query.addScalar("createdBy", new StringType());
			query.addScalar("updatedAt", new DateType());
			query.addScalar("updatedBy", new StringType());
			query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkFileDTO.class));
			
			query.setParameter("idCvPtk", obj.getIdCvPtk());
			
			return query.list();
		
	}  
	 public Long deleteObj(Long id){
		 try{
			 
			 String sql2 = "delete from TBL_QL_CV_PTK_FILE  where TBL_QL_CV_PTK_FILE_ID=:tblQlCvPtkFileId ";
				SQLQuery query2 = getSession().createSQLQuery(sql2);
				query2.setParameter("tblQlCvPtkFileId", id);
				query2.executeUpdate();
			 return 1L;
		 }catch(Exception e) {
				e.getMessage();
				return 0L;
			}
	 }
	
}
