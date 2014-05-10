package com.example.homework252;


/**
 * A data entity class for a database row in the "Tasks" table.
 * @author bob
 *
 */
public class TaskData {
	
	long taskNumber;
	
	String taskDescription;
	
	public TaskData()
	{
		taskNumber = 0;
		taskDescription = "";
	}

	public TaskData(long taskNumber, String taskDescription) {
		super();
		this.taskNumber = taskNumber;
		this.taskDescription = taskDescription;
	}

	public long getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(long taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	
	@Override
	public String toString()
	{
		return taskDescription;
	}

}
