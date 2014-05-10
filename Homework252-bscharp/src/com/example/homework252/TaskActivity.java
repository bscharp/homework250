package com.example.homework252;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Screen will always host one fragment, the TaskFragment.
 * @author bob
 *
 */
public class TaskActivity extends Activity {
	
	String TAG = "TaskActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task, menu);
		return true;
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_delete:
            	deleteItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    void deleteItem()
    {
    	Log.d( TAG, "Delete action button is tapped ");
    	TaskFragment fragment = (TaskFragment) getFragmentManager().findFragmentById(R.id.taskFragment);
    	TaskData item = fragment.getItem();
    	TaskListData.deleteTaskFromList(item.getTaskNumber());
    	finish();
    }

}
