package com.example.homework252;


import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.util.Log;


/**
 * A DAO utility class for use with the "Tasks" database table.
 * @author bob
 *
 */
public class TaskListData {
	
	
	static String TAG = "TaskListData";
	
	//static HashMap taskList;
	static TaskDatabaseHelper taskList;
	
	static long taskSequenceNumber = 0;
	
	
	
	private TaskListData()
	{

	}
	
	public static boolean openTaskListData( Context context)
	{
		if (taskList == null) 
		{
			taskSequenceNumber = 1;
			//taskList = new HashMap();
			
			// substitute SQLite table for HashMap
			taskList = new TaskDatabaseHelper( context );
			
			// populate HashMap with some test data
			/*
			TaskData task = new TaskData( 1L, "Wash the car");
			taskList.put( 1L, task);
			task = new TaskData( 2L, "Feed the dog" );
			taskList.put( 2L, task);
			taskSequenceNumber = 3;
			*/
		}
		
		return true;
	}

	public static ArrayList getList() {
		//Object[] temp = taskList.values().toArray();
		
		// substitute SQLite table for HashMap
		Object[] temp = taskList.selectTasks();
		
		ArrayList<TaskData> tempList = new ArrayList();
		for (int i = 0; i < temp.length; i++)
		{
			tempList.add( (TaskData) temp[i] );
		}
		return tempList;
	}
	
	public static boolean addTaskToList(TaskData item)
	{
		//taskList.put(item.getTaskNumber(), item);
		
		// substitute SQLite table for HashMap
		taskList.insertTask(item);
		return true;
	}
	
	public static boolean addTaskToList(String taskDescription)
	{
		Log.d(TAG, "adding task to list");
		TaskData item = new TaskData();
		item.setTaskDescription(taskDescription);
		item.setTaskNumber(taskSequenceNumber++);
		//taskList.put(item.getTaskNumber(), item);
		
		// substitute SQLite table for HashMap
		taskList.insertTask(item);
		return true;
	}
	
	public static TaskData getTask( long taskNumber)
	{
		//return (TaskData) taskList.get(taskNumber);
		
		// substitute SQLite table for HashMap
		return taskList.selectSingleTask(taskNumber);
	}
	
	public static long getNextTaskNumber()
	{
		return taskSequenceNumber++;
	}
	
	public static boolean deleteTaskFromList( long taskNumber )
	{
		//taskList.remove( taskNumber );
		
		// substitute SQLite table for HashMap
		taskList.deleteTask(taskNumber);
		return true;
	}
	
	public static boolean closeTaskList()
	{
		// close the database connection or drop the table?
		return true;
	}

}
