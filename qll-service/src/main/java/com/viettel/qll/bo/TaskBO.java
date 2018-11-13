package com.viettel.qll.bo;

import com.viettel.qll.dto.TaskDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TaskBO")
@Table(name = "TASK")
/**
 *
 * @author: hailh10
 */
public class TaskBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TASK_SEQ") })
	@Column(name = "TASK_ID", length = 22)
	private java.lang.Long taskId;
	@Column(name = "ID_TASK_GROUP", length = 22)
	private java.lang.Long idTaskGroup;
	@Column(name = "STATUS", length = 22)
	private java.lang.Long status;
	@Column(name = "END_TIME", length = 7)
	private java.util.Date endTime;
	@Column(name = "START_TIME", length = 7)
	private java.util.Date startTime;
	@Column(name = "CREATE_TASK_CYCLE", length = 22)
	private java.lang.Long createTaskCycle;
	@Column(name = "NOTE_TASK", length = 4000)
	private java.lang.String noteTask;

	
	public java.lang.Long getTaskId(){
		return taskId;
	}
	
	public void setTaskId(java.lang.Long taskId)
	{
		this.taskId = taskId;
	}
	
	public java.lang.Long getIdTaskGroup(){
		return idTaskGroup;
	}
	
	public void setIdTaskGroup(java.lang.Long idTaskGroup)
	{
		this.idTaskGroup = idTaskGroup;
	}
	
	public java.lang.Long getStatus(){
		return status;
	}
	
	public void setStatus(java.lang.Long status)
	{
		this.status = status;
	}
	
	public java.util.Date getEndTime(){
		return endTime;
	}
	
	public void setEndTime(java.util.Date endTime)
	{
		this.endTime = endTime;
	}
	
	public java.util.Date getStartTime(){
		return startTime;
	}
	
	public void setStartTime(java.util.Date startTime)
	{
		this.startTime = startTime;
	}
	
	public java.lang.Long getCreateTaskCycle(){
		return createTaskCycle;
	}
	
	public void setCreateTaskCycle(java.lang.Long createTaskCycle)
	{
		this.createTaskCycle = createTaskCycle;
	}
	
	public java.lang.String getNoteTask(){
		return noteTask;
	}
	
	public void setNoteTask(java.lang.String noteTask)
	{
		this.noteTask = noteTask;
	}
   
    @Override
    public TaskDTO toDTO() {
        TaskDTO taskDTO = new TaskDTO(); 
        taskDTO.setTaskId(this.taskId);		
        taskDTO.setIdTaskGroup(this.idTaskGroup);		
        taskDTO.setStatus(this.status);		
        taskDTO.setEndTime(this.endTime);		
        taskDTO.setStartTime(this.startTime);		
        taskDTO.setCreateTaskCycle(this.createTaskCycle);		
        taskDTO.setNoteTask(this.noteTask);		
        return taskDTO;
    }
}
