package com.viettel.qll.bo;

import com.viettel.qll.dto.TaskGroupDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TaskGroupBO")
@Table(name = "TASK_GROUP")
/**
 *
 * @author: hailh10
 */
public class TaskGroupBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TASK_GROUP_SEQ") })
	@Column(name = "TASK_GROUP_ID", length = 22)
	private java.lang.Long taskGroupId;
	@Column(name = "TASK_GROUP_NAME", length = 3000)
	private java.lang.String taskGroupName;
	@Column(name = "TASK_GROUP_CONTENT", length = 4000)
	private java.lang.String taskGroupContent;
	@Column(name = "DEPARTMENT", length = 3000)
	private java.lang.String department;
	@Column(name = "PERIODIC", length = 22)
	private java.lang.Long periodic;
	@Column(name = "END_TASK_GROUP", length = 22)
	private java.lang.Long endTaskGroup;
	@Column(name = "START_TASK_GROUP", length = 22)
	private java.lang.Long startTaskGroup;
	@Column(name = "WARNING_TASK_GROUP", length = 22)
	private java.lang.Long warningTaskGroup;
	@Column(name = "WARNING_CYCLE", length = 22)
	private java.lang.Long warningCycle;
	@Column(name = "WARNING_EMAIL", length = 4000)
	private java.lang.String warningEmail;
	@Column(name = "DEPARTMENT_ID", length =22)
	private java.lang.Long departmentId;
	
	public java.lang.Long getTaskGroupId(){
		return taskGroupId;
	}
	
	public void setTaskGroupId(java.lang.Long taskGroupId)
	{
		this.taskGroupId = taskGroupId;
	}
	
	public java.lang.String getTaskGroupName(){
		return taskGroupName;
	}
	
	public void setTaskGroupName(java.lang.String taskGroupName)
	{
		this.taskGroupName = taskGroupName;
	}
	
	public java.lang.String getTaskGroupContent(){
		return taskGroupContent;
	}
	
	public void setTaskGroupContent(java.lang.String taskGroupContent)
	{
		this.taskGroupContent = taskGroupContent;
	}
	
	public java.lang.String getDepartment(){
		return department;
	}
	
	public void setDepartment(java.lang.String department)
	{
		this.department = department;
	}
	
	public java.lang.Long getPeriodic(){
		return periodic;
	}
	
	public void setPeriodic(java.lang.Long periodic)
	{
		this.periodic = periodic;
	}
	
	public java.lang.Long getEndTaskGroup(){
		return endTaskGroup;
	}
	
	public void setEndTaskGroup(java.lang.Long endTaskGroup)
	{
		this.endTaskGroup = endTaskGroup;
	}
	
	public java.lang.Long getStartTaskGroup(){
		return startTaskGroup;
	}
	
	public void setStartTaskGroup(java.lang.Long startTaskGroup)
	{
		this.startTaskGroup = startTaskGroup;
	}
	
	public java.lang.Long getWarningTaskGroup(){
		return warningTaskGroup;
	}
	
	public void setWarningTaskGroup(java.lang.Long warningTaskGroup)
	{
		this.warningTaskGroup = warningTaskGroup;
	}
	
	public java.lang.Long getWarningCycle(){
		return warningCycle;
	}
	
	public void setWarningCycle(java.lang.Long warningCycle)
	{
		this.warningCycle = warningCycle;
	}
	
	public java.lang.String getWarningEmail(){
		return warningEmail;
	}
	
	public void setWarningEmail(java.lang.String warningEmail)
	{
		this.warningEmail = warningEmail;
	}
   
    public java.lang.Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(java.lang.Long departmentId) {
		this.departmentId = departmentId;
	}

	@Override
    public TaskGroupDTO toDTO() {
        TaskGroupDTO taskGroupDTO = new TaskGroupDTO(); 
        taskGroupDTO.setTaskGroupId(this.taskGroupId);		
        taskGroupDTO.setTaskGroupName(this.taskGroupName);		
        taskGroupDTO.setTaskGroupContent(this.taskGroupContent);		
        taskGroupDTO.setDepartment(this.department);		
        taskGroupDTO.setPeriodic(this.periodic);		
        taskGroupDTO.setEndTaskGroup(this.endTaskGroup);		
        taskGroupDTO.setStartTaskGroup(this.startTaskGroup);		
        taskGroupDTO.setWarningTaskGroup(this.warningTaskGroup);		
        taskGroupDTO.setWarningCycle(this.warningCycle);		
        taskGroupDTO.setWarningEmail(this.warningEmail);
        taskGroupDTO.setDepartmentId(this.departmentId);
        return taskGroupDTO;
    }
}
