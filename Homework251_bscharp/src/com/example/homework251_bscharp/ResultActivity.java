package com.example.homework251_bscharp;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
	
	public static final String USER_EMAIL = "ResultActivit.user_email";
	public static final String USER_PASSWORD = "ResultActivity.user_password";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        
        TextView tv1 = (TextView) findViewById(R.id.textView1);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv1.setText( getIntent().getStringExtra(USER_EMAIL));
        tv2.setText( getIntent().getStringExtra(USER_PASSWORD));
	}

}
