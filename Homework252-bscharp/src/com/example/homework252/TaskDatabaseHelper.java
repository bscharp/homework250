package com.example.homework252;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class implements DB access methods for a SQListe database 
 * table called "Tasks".
 * @author bob
 *
 */
public class TaskDatabaseHelper extends SQLiteOpenHelper {
	
	static final String DB_NAME = "myTasks.sqlite";
	static final int VERSION = 1;
	static final String TAG = "TaskDatabaseHelper";
	
	public TaskDatabaseHelper( Context context)
	{
		super(context, DB_NAME, null, VERSION );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table tasks " +
		           "(id integer primary key autoincrement, task_description varchar(100))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public long insertTask( TaskData data )
	{
		ContentValues cv = new ContentValues();
		cv.put("task_description", data.getTaskDescription());
		long newId = getWritableDatabase().insert("tasks", null, cv);
		return newId;
	}
	
	public Object[] selectTasks()
	{
		String selectSql = "Select * from tasks";
		Log.d(TAG, "SQL is: " + selectSql);
		Cursor resultCursor = getReadableDatabase().rawQuery(selectSql, null);
		Log.d(TAG, "Number of rows returned is: " + resultCursor.getCount());
		
		Object[] resultArray = new Object[resultCursor.getCount()];
		TaskData task;
		int arrayIndex = 0;
		while ( resultCursor.moveToNext())
		{
			task = new TaskData();
			task.setTaskNumber(resultCursor.getLong(0));
			task.setTaskDescription(resultCursor.getString(1));
			resultArray[arrayIndex++] = task;
		}
		return resultArray;
	}
	
	public TaskData selectSingleTask(long key)
	{
		TaskData task = null;
		
		String selectSql = "Select * from tasks where id = " + key;
		Log.d(TAG, "SQL is: " + selectSql);
		Cursor resultCursor = getReadableDatabase().rawQuery(selectSql, null);
		
		while ( resultCursor.moveToNext())
		{
			task = new TaskData();
			task.setTaskNumber(resultCursor.getLong(0));
			task.setTaskDescription(resultCursor.getString(1));
		}
		
		return task;
	}
	
	public void deleteTask( long key )
	{
		String deleteSql = "Delete from tasks where id = " + key;
		Log.d(TAG, "SQL is: " + deleteSql);
		
		try {
		   getWritableDatabase().execSQL(deleteSql);
		}
		catch(SQLException e)
		{
			Log.d(TAG, "SQLException is deleteTask: " + e.getMessage());
		}
	}  

}
