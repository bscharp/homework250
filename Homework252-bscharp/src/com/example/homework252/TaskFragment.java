package com.example.homework252;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * Display a fragment with an EditData text field.
 * @author bob
 *
 */
public class TaskFragment extends Fragment {
	
	String TAG = "TaskFragment";
	TaskData item;
	EditText editTask;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// is Fragment displayed on handset or tablet
		Configuration config = getResources().getConfiguration();
		if (config.smallestScreenWidthDp >= 600)
		{
			
		}
		
		// user on handset device starts TaskActivity with an intent
		else
		{
		   long taskNumber = getActivity().getIntent().getLongExtra("TaskNumber", 0);
		   Log.d( TAG, "Intent parameter TaskNumber had a value of " + taskNumber);
		   item = TaskListData.getTask(taskNumber);
		   if ( item == null ) Log.d(TAG, "item object in onCreate() is null");
		}
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_task, container, false);
		
		editTask = (EditText) view.findViewById(R.id.taskEditText);
		
		// is Fragment displayed on handset or tablet
		Configuration config = getResources().getConfiguration();
		if (config.smallestScreenWidthDp >= 600)
		{
			editTask.setInputType(InputType.TYPE_NULL);		
		}
		
		// displaying empty editTask
		if ( item == null )
		{
			Log.d(TAG, "item object in onCreateView() is null");
			
			String hint = getActivity().getIntent().getStringExtra("newItemHint");
			if (hint != null) 
			{
			   editTask.setHint(R.string.task_editText_hint);
			   editTask.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			
			editTask.setOnEditorActionListener(new OnEditorActionListener() {
			    @Override
			    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			    {
			    	Log.d(TAG, "onEditorAction listener was called; action id value is " + actionId);
			    	if (event != null)
			    	{
			    		Log.d(TAG, "KeyEvent key code value is " + event.getKeyCode());
			    		Log.d(TAG, "KeyEvent repeat count value is " + event.getRepeatCount());
			    		Log.d(TAG, "KeyEvent flag value is " + event.getFlags());
			    		Log.d(TAG, "KeyEvent action value is " + event.getAction());
			    	}
			    	
			        boolean handled = false;
			        if (actionId == EditorInfo.IME_ACTION_DONE || 
			        		
			        	// Apparently, virtual devices return KeyEvents rather than actionIds
			        	(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
			        	 event.getAction() == KeyEvent.ACTION_DOWN)    ) 
			        {
			        	TaskListData.openTaskListData( getActivity().getApplicationContext());
						TaskListData.addTaskToList(v.getText().toString());
			            handled = true;
			            getActivity().finish();
			        }
			        return handled;
			    }
			});
		}
		else
		{
		    editTask.setText(item.getTaskDescription());
		}
		
		
		
		return view;
	}
	
	public TaskData getItem() {
		return item;
	}

	public void setItem(TaskData item) {
		this.item = item;
	}
	
	public void removeDisplayedItem()
	{
		editTask.setText("");
		item = null;
	}

}
