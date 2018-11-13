package com.viettel.qll.dto;

import com.viettel.qll.bo.TaskBO;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TASKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDTO extends QllBaseDTO<TaskBO> {

	private java.lang.Long taskId;
	private java.lang.Long idTaskGroup;
	private java.lang.Long status;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date endTime;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date endTimeFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date endTimeTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date startTime;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date startTimeFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date startTimeTo;
	private java.lang.Long createTaskCycle;
	private java.lang.String noteTask;
	private String taskGroupName,taskGroupContent,department,employeeCode;
	private Long departmentId;
	private int start;
	private int maxResult;
	
	
	
	
	

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getTaskGroupName() {
		return taskGroupName;
	}

	public void setTaskGroupName(String taskGroupName) {
		this.taskGroupName = taskGroupName;
	}

	public String getTaskGroupContent() {
		return taskGroupContent;
	}

	public void setTaskGroupContent(String taskGroupContent) {
		this.taskGroupContent = taskGroupContent;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
    public TaskBO toModel() {
        TaskBO taskBO = new TaskBO();
        taskBO.setTaskId(this.taskId);
        taskBO.setIdTaskGroup(this.idTaskGroup);
        taskBO.setStatus(this.status);
        taskBO.setEndTime(this.endTime);
        taskBO.setStartTime(this.startTime);
        taskBO.setCreateTaskCycle(this.createTaskCycle);
        taskBO.setNoteTask(this.noteTask);
        return taskBO;
    }
    
    @Override
     public Long getFWModelId() {
        return taskId;
    }
   
    @Override
    public String catchName() {
        return getTaskId().toString();
    }
	
	@JsonProperty("taskId")
    public java.lang.Long getTaskId(){
		return taskId;
    }
	
    public void setTaskId(java.lang.Long taskId){
		this.taskId = taskId;
    }	
	
	@JsonProperty("idTaskGroup")
    public java.lang.Long getIdTaskGroup(){
		return idTaskGroup;
    }
	
    public void setIdTaskGroup(java.lang.Long idTaskGroup){
		this.idTaskGroup = idTaskGroup;
    }	
	
	@JsonProperty("status")
    public java.lang.Long getStatus(){
		return status;
    }
	
    public void setStatus(java.lang.Long status){
		this.status = status;
    }	
	
	@JsonProperty("endTime")
    public java.util.Date getEndTime(){
		return endTime;
    }
	
    public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
    }	
	
	public java.util.Date getEndTimeFrom() {
    	return endTimeFrom;
    }
	
    public void setEndTimeFrom(java.util.Date endTimeFrom) {
    	this.endTimeFrom = endTimeFrom;
    }
	
	public java.util.Date getEndTimeTo() {
    	return endTimeTo;
    }
	
    public void setEndTimeTo(java.util.Date endTimeTo) {
    	this.endTimeTo = endTimeTo;
    }
	
	@JsonProperty("startTime")
    public java.util.Date getStartTime(){
		return startTime;
    }
	
    public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
    }	
	
	public java.util.Date getStartTimeFrom() {
    	return startTimeFrom;
    }
	
    public void setStartTimeFrom(java.util.Date startTimeFrom) {
    	this.startTimeFrom = startTimeFrom;
    }
	
	public java.util.Date getStartTimeTo() {
    	return startTimeTo;
    }
	
    public void setStartTimeTo(java.util.Date startTimeTo) {
    	this.startTimeTo = startTimeTo;
    }
	
	@JsonProperty("createTaskCycle")
    public java.lang.Long getCreateTaskCycle(){
		return createTaskCycle;
    }
	
    public void setCreateTaskCycle(java.lang.Long createTaskCycle){
		this.createTaskCycle = createTaskCycle;
    }	
	
	@JsonProperty("noteTask")
    public java.lang.String getNoteTask(){
		return noteTask;
    }
	
    public void setNoteTask(java.lang.String noteTask){
		this.noteTask = noteTask;
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
