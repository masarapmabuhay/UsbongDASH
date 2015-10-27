/*
 * Copyright 2012 Michael Syson
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package usbong.android.dash;

import usbong.android.utils.UsbongUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * This is Usbong's Main Menu activity. 
 */
public class UsbongMainActivity extends AppCompatActivity
{	
	private Button startButton;
	private Button conditionsOfUseButton;
	private Button aboutButton;
	private Button settingsButton;
	private Button exitButton;
			
	private static UsbongMainActivity instance;
				
	public static String timeStamp;
	
	private static Activity myActivityInstance;
	
//	private static Date startTime;	
	
	protected UsbongDecisionTreeEngineActivity myUsbongDecisionTreeEngineActivity;
	protected SettingsActivity mySettingsActivity;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);

        //added by Mike, 27 Sept. 2015
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        myActivityInstance = this;
        
        //added by Mike, 25 Sept. 2015
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Usbong Engine");

//    	if (instance==null) { //comment this out, since the app seems to retain the instance even after we do a finish to GameActivity to close the app...
	        setContentView(R.layout.main);	        
	        instance = this;
//	    	startTime = new Date();
	    	
	        reset();
			UsbongUtils.initUsbongConfigFile(); //added by Mike, 30 April 2015

	        initMainMenuScreen();
    }
    
    public static UsbongMainActivity getInstance() {
    	return instance;
    }
    
    //added by Mike, April 14, 2015
    @Override
	public void onBackPressed() {
    	//Reference: http://stackoverflow.com/questions/11495188/how-to-put-application-to-background
    	//; last accessed: 14 April 2015, answer by: JavaCoderEx
    	Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);    
    }
    
    /*
     * Initialize this activity
     */
    public void init()
    {    	
    }
    
    public void initMainMenuScreen()
    {
    	startButton = (Button)findViewById(R.id.start_button);
    	conditionsOfUseButton = (Button)findViewById(R.id.conditions_of_use_button);
    	aboutButton = (Button)findViewById(R.id.about_button);
    	settingsButton = (Button)findViewById(R.id.settings_button);
    	exitButton = (Button)findViewById(R.id.exit_button);    	

		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {				
//				myUsbongDecisionTreeEngineActivity = new UsbongDecisionTreeEngineActivity();
				reset(); //generate new timestamp
				Intent toUsbongDecisionTreeEngineActivityIntent = new Intent().setClass(UsbongMainActivity.getInstance(), UsbongDecisionTreeEngineActivity.class);
				toUsbongDecisionTreeEngineActivityIntent.putExtra("currScreen","0"); //make currScreen=0; meaning very first screen				
				startActivity(toUsbongDecisionTreeEngineActivityIntent);				
			}
    	});


    	conditionsOfUseButton.setOnClickListener(new OnClickListener() {
    		@Override
			public void onClick(View v) {				
/*
    			TextView tv = (TextView) UsbongUtils.applyTagsInView(UsbongMainActivity.getInstance(),
    	    														 new TextView(UsbongMainActivity.getInstance()), 
    	    														 UsbongUtils.IS_TEXTVIEW,     	    														 
    	    														 UsbongUtils.readTextFileInAssetsFolder(UsbongMainActivity.getInstance(),"instructions.txt")); //don't add a '/', otherwise the file would not be found    	    	
*/
/*
    			TextView tv = new TextView(UsbongMainActivity.getInstance());
    			tv.setText(UsbongUtils.readTextFileInAssetsFolder(UsbongMainActivity.this,"instructions.txt"));
    			ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -2);
    			LinearLayout layout = new LinearLayout(new Context(), params);
    			layout.addView(tv, params);
    			tv.setBackgroundResource(R.layout.dialog);
*/    			
//    	    	tv.setTextSize((getResources().getDimension(R.dimen.textsize)));

    	    	AlertDialog.Builder prompt = new AlertDialog.Builder(UsbongMainActivity.this);
    	    	prompt.setTitle("Conditions of Use");
//    	    	prompt.setView(getLayoutInflater().inflate(R.layout.dialog, null));
/*
    	    	prompt.setView(layout); 
*/    	    	
//				prompt.setView(tv); 
				prompt.setMessage(UsbongUtils.readTextFileInAssetsFolder(UsbongMainActivity.this,"conditions_of_use.txt")); //don't add a '/', otherwise the file would not be found
    	    	prompt.setInverseBackgroundForced(true);
				prompt.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				AlertDialog alert = prompt.create();
				alert.show();
			}
    	});

    	aboutButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
/*
				TextView tv = (TextView) UsbongUtils.applyTagsInView(UsbongMainActivity.getInstance(),
						 new TextView(UsbongMainActivity.getInstance()), 
						 UsbongUtils.IS_TEXTVIEW,     	    														 
						 UsbongUtils.readTextFileInAssetsFolder(UsbongMainActivity.getInstance(),"credits.txt")); //don't add a '/', otherwise the file would not be found    	    	
    	    	tv.setTextSize((getResources().getDimension(R.dimen.textsize)));
*/
				AlertDialog.Builder prompt = new AlertDialog.Builder(UsbongMainActivity.this);
				prompt.setTitle("About");
//				prompt.setView(tv);
				prompt.setMessage(UsbongUtils.readTextFileInAssetsFolder(UsbongMainActivity.this,"credits.txt")); //don't add a '/', otherwise the file would not be found
				prompt.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				prompt.show();
			}
    	});

    	settingsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {				
//				mySettingsActivity = new SettingsActivity();
				
				Intent toSettingsActivityIntent = new Intent().setClass(UsbongMainActivity.getInstance(), SettingsActivity.class);
				startActivity(toSettingsActivityIntent);				
			}
    	});

    	exitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(UsbongMainActivity.this).setTitle("Exiting application...")
				.setMessage("Are you sure you want to exit application?")
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				})
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {						
						finish();
					}
				}).show();
			}
    	});
    }
  
    public void reset() {
    	UsbongUtils.generateDateTimeStamp(); //create a new timestamp for this "New Entry"
    }
/*
    private void showStatusDialog(String status)
	{
		AlertDialog.Builder prompt = new AlertDialog.Builder(UsbongMainActivity.this);
		prompt.setTitle("Notification");
		prompt.setMessage(status);
		prompt.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		prompt.show();
	}
*/
    //added by Mike, 25 Sept. 2015
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.title_menu, menu);
		return super.onCreateOptionsMenu(menu); 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{		
		switch(item.getItemId())
		{
			case(R.id.settings):
		    	new AlertDialog.Builder(UsbongMainActivity.this).setTitle("Settings")
				.setMessage("Automatic voice-over narration:")
//				.setView(requiredFieldAlertStringTextView)
		    	.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						UsbongUtils.isInAutoVoiceOverNarration=true;
					}
		    	})
			    .setNegativeButton("Turn Off", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						UsbongUtils.isInAutoVoiceOverNarration=false;
					}
				}).show();
				return true;
			case(R.id.about):
		    	new AlertDialog.Builder(UsbongMainActivity.this).setTitle("About")
				.setMessage(UsbongUtils.readTextFileInAssetsFolder(UsbongMainActivity.this,"credits.txt")) //don't add a '/', otherwise the file would not be found
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

}