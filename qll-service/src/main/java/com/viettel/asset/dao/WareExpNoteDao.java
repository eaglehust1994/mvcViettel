package com.viettel.asset.dao;

import org.springframework.stereotype.Repository;
import com.viettel.asset.bo.WareExpNote;

@Repository("wareExpNoteDao")
public class WareExpNoteDao extends HibernateDao<WareExpNote, Long> {

}
