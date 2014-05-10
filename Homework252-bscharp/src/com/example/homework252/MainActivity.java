package com.example.homework252;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 *  The activity for the main screen. The main screen will host 2 fragments
 *  when displayed on a tablet device and one fragment on a handset device.
 * @author bob
 *
 */
public class MainActivity extends Activity {
	
	String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar bar = getActionBar();
        bar.setSubtitle("'To-Do' Task List");
        
        // is Activity displayed on handset or tablet
		Configuration config = getResources().getConfiguration();
		if (config.smallestScreenWidthDp >= 600)
		{
			TaskFragment newTaskFragment = new TaskFragment();
			TaskListFragment newListFragment = new TaskListFragment();
			FragmentManager fm = getFragmentManager();
			fm.beginTransaction()
			   .add(R.id.listFragmentContainer, newListFragment)
			   .add(R.id.detailFragmentContainer, newTaskFragment)
			   .commit();
		}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add:
            	addItem();
                return true;
            case R.id.action_delete:
            	deleteItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    
    void addItem()
    {
    	// send user to the task activity screen
    	Log.d( TAG, "Add action button is tapped ");
    	Intent taskIntent = new Intent(this, TaskActivity.class);
    	taskIntent.putExtra("newItemHint", "Please enter task description");
 	    startActivity(taskIntent);
    }
    
    void deleteItem()
    {
    	Log.d( TAG, "Delete action button is tapped ");
    	
        // is Activity displayed on handset or tablet
		Configuration config = getResources().getConfiguration();
		if (config.smallestScreenWidthDp >= 600)
		{
			TaskFragment detailFragment = (TaskFragment) getFragmentManager().findFragmentById(R.id.detailFragmentContainer);
	    	TaskData item = detailFragment.getItem();
	    	if ( item == null )
	    	{
	    		// prompt user to place task in the detail fragment
	    		Toast.makeText(MainActivity.this, 
	  			      "Please select a task for deletion", 
	  			      Toast.LENGTH_SHORT).show();
	    	}
	    	else
	    	{
	    	   TaskListData.deleteTaskFromList(item.getTaskNumber());
	    	   detailFragment.removeDisplayedItem();
	    	   
	    	   TaskListFragment listFragment = (TaskListFragment) getFragmentManager()
	    			                                              .findFragmentById(R.id.listFragmentContainer);
	    	   ArrayList<TaskData> taskList = TaskListData.getList();
	    	   ArrayAdapter<TaskData> adapter = new ArrayAdapter<TaskData>( this,
                                                                            android.R.layout.simple_list_item_1,
                                                                            taskList);
	    	   listFragment.setListAdapter(adapter);
	    	}
		}
		
		// send user on handset device to Task Actvity screen
		// (by selecting a task)
		else
		{
    	    Toast.makeText(MainActivity.this, 
			      "Please select a task for deletion", 
			      Toast.LENGTH_SHORT).show();
		}
    }
    
}
