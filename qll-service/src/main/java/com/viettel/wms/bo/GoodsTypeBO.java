/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.GoodsTypeDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity
@Table(name = "CAT_OWNER.GOODS_TYPE")
/**
 *
 * @author: TuanNB
 * @version: 1.0
 * @since: 1.0
 */
public class GoodsTypeBO extends BaseFWModelImpl {
     

private java.lang.Long goodsTypeId;
private java.lang.String code;
private java.lang.String name;
private java.lang.String status;

public GoodsTypeBO() {
        setColId("goodsTypeId");
        setColName("goodsTypeId");
        setUniqueColumn(new String[]{"goodsTypeId"});
}


@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.GOODS_TYPE_SEQ")
            }
    )
@Column(name = "GOODS_TYPE_ID", length = 22)
public java.lang.Long getGoodsTypeId() {
	return goodsTypeId;
}
public void setGoodsTypeId(java.lang.Long goodsTypeId) {
	this.goodsTypeId = goodsTypeId;
}

@Column(name = "CODE", length = 50)
public java.lang.String getCode() {
	return code;
}
public void setCode(java.lang.String code) {
	this.code = code;
}

@Column(name = "NAME", length = 200)
public java.lang.String getName() {
	return name;
}
public void setName(java.lang.String name) {
	this.name = name;
}

@Column(name = "STATUS", length = 2)
public java.lang.String getStatus() {
	return status;
}
public void setStatus(java.lang.String status) {
	this.status = status;
}


	@Override
    public GoodsTypeDTO toDTO() {
        GoodsTypeDTO goodsTypeDTO = new GoodsTypeDTO();
        goodsTypeDTO.setGoodsTypeId(this.goodsTypeId);
        goodsTypeDTO.setCode(this.code);
        goodsTypeDTO.setName(this.name);
        goodsTypeDTO.setStatus(this.status);
        return goodsTypeDTO;
    }
}
