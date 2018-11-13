package com.viettel.qll.dto;

import com.viettel.qll.bo.TaskGroupBO;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TASK_GROUPBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskGroupDTO extends QllBaseDTO<TaskGroupBO> {

	private java.lang.Long taskGroupId;
	private java.lang.String taskGroupName;
	private java.lang.String taskGroupContent;
	private java.lang.String department;
	private java.lang.Long periodic;
	private java.lang.Long endTaskGroup;
	private java.lang.Long startTaskGroup;
	private java.lang.Long warningTaskGroup;
	private java.lang.Long warningCycle;
	private java.lang.String warningEmail;
	private java.lang.Long departmentId;
	private String autoDept;
	
	
	public String getAutoDept() {
		return autoDept;
	}

	public void setAutoDept(String autoDept) {
		this.autoDept = autoDept;
	}

	private int start;
	private int maxResult;

    @Override
    public TaskGroupBO toModel() {
        TaskGroupBO taskGroupBO = new TaskGroupBO();
        taskGroupBO.setTaskGroupId(this.taskGroupId);
        taskGroupBO.setTaskGroupName(this.taskGroupName);
        taskGroupBO.setTaskGroupContent(this.taskGroupContent);
        taskGroupBO.setDepartment(this.department);
        taskGroupBO.setPeriodic(this.periodic);
        taskGroupBO.setEndTaskGroup(this.endTaskGroup);
        taskGroupBO.setStartTaskGroup(this.startTaskGroup);
        taskGroupBO.setWarningTaskGroup(this.warningTaskGroup);
        taskGroupBO.setWarningCycle(this.warningCycle);
        taskGroupBO.setWarningEmail(this.warningEmail);
        taskGroupBO.setDepartmentId(this.departmentId);
        return taskGroupBO;
    }

    @Override
     public Long getFWModelId() {
        return taskGroupId;
    }
   
    @Override
    public String catchName() {
        return getTaskGroupId().toString();
    }
	
	@JsonProperty("taskGroupId")
    public java.lang.Long getTaskGroupId(){
		return taskGroupId;
    }
	
    public void setTaskGroupId(java.lang.Long taskGroupId){
		this.taskGroupId = taskGroupId;
    }	
	
	@JsonProperty("taskGroupName")
    public java.lang.String getTaskGroupName(){
		return taskGroupName;
    }
	
    public void setTaskGroupName(java.lang.String taskGroupName){
		this.taskGroupName = taskGroupName;
    }	
	
	@JsonProperty("taskGroupContent")
    public java.lang.String getTaskGroupContent(){
		return taskGroupContent;
    }
	
    public void setTaskGroupContent(java.lang.String taskGroupContent){
		this.taskGroupContent = taskGroupContent;
    }	
	
	@JsonProperty("department")
    public java.lang.String getDepartment(){
		return department;
    }
	
    public void setDepartment(java.lang.String department){
		this.department = department;
    }	
	
	@JsonProperty("periodic")
    public java.lang.Long getPeriodic(){
		return periodic;
    }
	
    public void setPeriodic(java.lang.Long periodic){
		this.periodic = periodic;
    }	
	
	@JsonProperty("endTaskGroup")
    public java.lang.Long getEndTaskGroup(){
		return endTaskGroup;
    }
	
    public void setEndTaskGroup(java.lang.Long endTaskGroup){
		this.endTaskGroup = endTaskGroup;
    }	
	
	@JsonProperty("startTaskGroup")
    public java.lang.Long getStartTaskGroup(){
		return startTaskGroup;
    }
	
    public void setStartTaskGroup(java.lang.Long startTaskGroup){
		this.startTaskGroup = startTaskGroup;
    }	
	
	@JsonProperty("warningTaskGroup")
    public java.lang.Long getWarningTaskGroup(){
		return warningTaskGroup;
    }
	
    public void setWarningTaskGroup(java.lang.Long warningTaskGroup){
		this.warningTaskGroup = warningTaskGroup;
    }	
	
	@JsonProperty("warningCycle")
    public java.lang.Long getWarningCycle(){
		return warningCycle;
    }
	
    public void setWarningCycle(java.lang.Long warningCycle){
		this.warningCycle = warningCycle;
    }	
	
	@JsonProperty("warningEmail")
    public java.lang.String getWarningEmail(){
		return warningEmail;
    }
	
    public void setWarningEmail(java.lang.String warningEmail){
		this.warningEmail = warningEmail;
    }
    
    @JsonProperty("departmentId")
	public java.lang.Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(java.lang.Long departmentId) {
		this.departmentId = departmentId;
	}

	@JsonProperty("start")
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JsonProperty("maxResult")
	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	

}
