package com.example.homework251_bscharp;

import java.io.IOException;
import java.io.InputStream;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	EditText emailEditText;
	EditText passwordEditText;
	Button signIn;
	
	String emailText;
	String passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // hide the action bar
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        
        // get references to the screen components
        emailEditText = (EditText) findViewById(R.id.editText1);
        passwordEditText = (EditText) findViewById(R.id.editText2);
        signIn = (Button) findViewById(R.id.button1);
        
        // create a listener for the button
        signIn.setOnClickListener( new View.OnClickListener() {
          @Override
          public void onClick(View v )
          {
        	  
        	// retrieve values from the text components  
        	emailText = emailEditText.getText().toString();
        	passwordText = passwordEditText.getText().toString();
        	
        	// check for value in the email field
        	if (emailText == null | emailText.length() == 0)
        	{
        		Toast.makeText(MainActivity.this, 
        				      "Please enter your email address", 
        				      Toast.LENGTH_SHORT).show();
        	}
        	
        	// check for proper email address
        	else if ( emailText.indexOf('@') == -1 )
        	{
        		Toast.makeText(MainActivity.this, 
  				      "Please enter a valid email address", 
  				      Toast.LENGTH_SHORT).show();
        	}
        	
        	// check for value in the password field
        	else if (passwordText == null | passwordText.length() == 0)
        	{
        		Toast.makeText(MainActivity.this, 
  				      "Please enter your password", 
  				      Toast.LENGTH_SHORT).show();
        	}
        	
        	// send text field values to another activity
        	else 
        	{
        	   Intent i = new Intent(MainActivity.this, ResultActivity.class);
        	   i.putExtra(ResultActivity.USER_EMAIL, emailText);
        	   i.putExtra(ResultActivity.USER_PASSWORD, passwordText);
        	   startActivity(i);
        	}
        	
          }
        });
        
        AssetManager assetManager = this.getAssets();
        
        try {

            // Get an image as an asset
            InputStream is = assetManager.open("logo-NOLOGO.png");
            Bitmap bm = BitmapFactory.decodeStream(is);

            ImageView iv0 = (ImageView) this.findViewById(R.id.imageView0);
            iv0.setImageBitmap(bm);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
