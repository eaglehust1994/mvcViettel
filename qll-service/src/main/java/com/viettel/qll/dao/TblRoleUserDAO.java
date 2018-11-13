package com.viettel.qll.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.MappingException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.TblQltsCongNoVatTuBO;
import com.viettel.qll.bo.TblRoleUserBO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblRoleUserDTO;
import com.viettel.qll.dto.TblRolesDTO;
import com.viettel.qll.dto.TblUsersDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author hailh10
 */
@Repository("tblRoleUserDAO")
public class TblRoleUserDAO extends BaseFWDAOImpl<TblRoleUserBO, Long> {

	public TblRoleUserDAO() {
		this.model = new TblRoleUserBO();
	}

	public TblRoleUserDAO(Session session) {
		this.session = session;
	}

	public List<TblRoleUserDTO> checkExist(TblRoleUserDTO obj) {
		boolean check = false;
		StringBuilder sql = new StringBuilder("Select TBL_ROLES_ID tblRolesId,TBL_USERS_ID tblUsersId "
				+ " from TBL_ROLE_USER  where TBL_ROLES_ID=:tblRolesId and TBL_USERS_ID =:tblUsersId");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("tblRolesId", new LongType());
		query.addScalar("tblUsersId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(TblRoleUserDTO.class));
		query.setParameter("tblUsersId", obj.getTblUsersId());
		query.setParameter("tblRolesId", obj.getTblRolesId());

		return query.list();
	}

	public List<TblRoleUserDTO> getAllData() {
		boolean check = false;
		StringBuilder sql = new StringBuilder("Select distinct TBL_USERS_ID tblUsersId " + " from TBL_ROLE_USER ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("tblUsersId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(TblRoleUserDTO.class));
		return query.list();
	}

	public List<TblUsersDTO> getAllData1() {
		StringBuilder sql = new StringBuilder(
				"Select  TBL_USERS_ID tblUsersId " + " from TBL_USERS  where PHONG_BAN='Ban Giám đốc Cty'");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("tblUsersId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(TblUsersDTO.class));
		return query.list();
	}

	public List<TblUsersDTO> getAllData11(){
		StringBuilder sql = new StringBuilder("select distinct xx.TBL_USERS_ID tblUsersId from TBL_ROLE_USER xx  "
				+ "join TBL_USERS yy on  xx.TBL_USERS_ID=yy.TBL_USERS_ID "
				+ "where xx.TBL_ROLES_ID!=3 and yy.USER_CODE in ('053776','005297','181774','047454','183339',"
				+ "'185710','005632','005714','051794','052053','067335','080410','097057','119968','128112',"
				+ "'141194','056298','116734','128330','064459','020798','007065','109638','116869','069248',"
				+ "'190217','069701','068081','068757','023897','083891','008426','006667','079036','183036',"
				+ "'224534','024899','161834','117898','007919','058366','068758','089679','107656','073346',"
				+ "'064785','223142','223139','081102','004775','069741','005546','204874','036915','072532',"
				+ "'008658','043965','088027','111320','60526','003135','083387','066963','071569','179354',"
				+ "'066984','069207','063269','124269','109138','076918','125195','181323','066952','057068',"
				+ "'070261','079949','000898','049936','079982','204857','088356','009299','073683','064547',"
				+ "'081770','006242','071578','105297','032512','072974','070010','007591','069324','068816',"
				+ "'D00207778','204903','D00176230','060631','056708','188521','109878','081971','081974',"
				+ "'048435','184179','037154','059184''069865','006283','001619','069293','010298','060559',"
				+ "'069390','061033','075833','061115','121395','017279','014597','069591','060923','016079',"
				+ "'037012','064222','088576','070107','119705','174554','018119','189173','007997','004553',"
				+ "'068362','086536','023158','066577','015966','131468','007725','070177','033472','077010','119137','106063','069339'");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("tblUsersId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(TblUsersDTO.class));
		return query.list();
	}

	public Long deleteObj(TblRoleUserDTO obj) {
		try {
			String sql2 = "delete  from TBL_ROLE_USER  where TBL_ROLES_ID=:tblRolesId and TBL_USERS_ID =:tblUsersId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("tblUsersId", obj.getTblUsersId());
			query2.setParameter("tblRolesId", obj.getTblRolesId());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

	@Transactional
	public String saveList1(List<TblRoleUserBO> obj) {
		try {
			for (TblRoleUserBO item : obj) {
				getSession().save(item);
				System.out.println("ok");
			}

		} catch (SecurityException ex) {
			return ex.getMessage();
		} catch (MappingException e) {
			return e.getMessage();
		}
		return "SUCCESS";
	}

}
