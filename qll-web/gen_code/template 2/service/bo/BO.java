package com.viettel.${projectCode}.bo;

import com.viettel.${projectCode}.dto.${tbl.tableNameJV}DTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name = "${tbl.tableName}")
/**
 *
 * @author: hailh10
 */
public class ${tbl.tableNameJV}BO extends BaseFWModelImpl {
     
	<#list  tbl.columnBOs as clm >
	<#if clm.isKey>
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "${tbl.tableName}_SEQ") })
	</#if>
	<#if clm.dataTypeJV =="String">	
	@Column(name = "${clm.columnName}", length = ${clm.stringLength})
	private ${clm.dataTypeJV} ${clm.columnVar};
	<#elseif clm.dataTypeJV =="Long" || clm.dataTypeJV =="Double">
	@Column(name = "${clm.columnName}", length = ${clm.precision})
	private ${clm.dataTypeJV} ${clm.columnVar};
	<#else>
	@Column(name = "${clm.columnName}", length = ${clm.lengthDis})
	private ${clm.dataTypeJV} ${clm.columnVar};
	</#if>
	</#list>

	<#list  tbl.columnBOs as clm >
	public ${clm.dataTypeJV} get${clm.columnNameJV}(){
		return ${clm.columnVar};
	}
	public void set${clm.columnNameJV}(${clm.dataTypeJV} ${clm.columnVar})
	{
		this.${clm.columnVar} = ${clm.columnVar};
	}
	</#list>
   
    @Override
    public ${tbl.tableNameJV}DTO toDTO() {
        ${tbl.tableNameJV}DTO ${tbl.tableNameVar}DTO = new ${tbl.tableNameJV}DTO(); 
       <#list tbl.columnBOs as clm>
        ${tbl.tableNameVar}DTO.set${clm.columnNameJV}(this.${clm.columnVar});		
       </#list>
        return ${tbl.tableNameVar}DTO;
    }
}
