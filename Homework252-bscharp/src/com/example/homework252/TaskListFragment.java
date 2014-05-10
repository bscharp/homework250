package com.example.homework252;

import java.util.ArrayList;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Displays a fragment that contains a list
 * @author bob
 *
 */
public class TaskListFragment extends ListFragment {
	
	ArrayList<TaskData> taskList;
	
	String TAG = "TaskListFragment";
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d(TAG, "entered onCreate method");
		
		TaskListData.openTaskListData( getActivity().getApplicationContext());
		taskList = TaskListData.getList();
		
		ArrayAdapter<TaskData> adapter = new ArrayAdapter<TaskData>( getActivity(),
				                              android.R.layout.simple_list_item_1,
				                              taskList);
		setListAdapter(adapter);

	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		
		View parentView = super.onCreateView(inflater, container, savedInstanceState);
		Log.d(TAG, "entered onCreateView method");
		return parentView;
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(TAG, "entered onPause method");
	}
	
	
	@Override
	public void onResume()
	{
		super.onResume();
		Log.d(TAG, "entered onResume method");
		
		taskList = TaskListData.getList();
		ArrayAdapter<TaskData> adapter = new ArrayAdapter<TaskData>( getActivity(),
                                                                     android.R.layout.simple_list_item_1,
                                                                     taskList);
        setListAdapter(adapter);
	}
	
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		TaskData item = (TaskData) getListAdapter().getItem(position);
		
		// is Fragment displayed on handset or tablet
		Configuration config = getResources().getConfiguration();
		if (config.smallestScreenWidthDp >= 600)
		{
			TaskFragment newTaskFragment = new TaskFragment();
			newTaskFragment.setItem(item);
			FragmentManager fm = getActivity().getFragmentManager();
			fm.beginTransaction().replace(R.id.detailFragmentContainer, newTaskFragment).commit();
		}
		else
		{
		   Intent taskIntent = new Intent(getActivity(), TaskActivity.class);
		   taskIntent.putExtra("TaskNumber", item.getTaskNumber());
		   startActivity(taskIntent);
		}
	}

}
