/**
 *  Copyright 2010 Bartl Dominic

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package at.bartinger.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class CanvasGame extends Activity {


	String[] examples;
	AlertDialog.Builder dialog;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.examplebuttons);
		dialog = new AlertDialog.Builder(this);
		((Button)findViewById(R.id.e01)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(1);

			}
		});
		
		((Button)findViewById(R.id.e02)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(2);

			}
		});
		
		((Button)findViewById(R.id.e03)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(3);

			}
		});
		
		((Button)findViewById(R.id.e04)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(4);

			}
		});
		
		((Button)findViewById(R.id.e05)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(5);

			}
		});
		
		((Button)findViewById(R.id.e06)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(6);

			}
		});
		
		
		((Button)findViewById(R.id.e07)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(7);

			}
		});
		
		((Button)findViewById(R.id.e08)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(8);

			}
		});
		
		((Button)findViewById(R.id.e09)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(9);

			}
		});
		
		((Button)findViewById(R.id.e10)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(10);

			}
		});
		
		((Button)findViewById(R.id.about)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				dialog.setIcon(R.drawable.icon);
				dialog.setTitle("Candroid Game Engine");
				dialog.setMessage("Candroid is an opensource Canvas Based Game Engine. \n" +
						"To read more about it go to the Google Code page and search for Candroid. \n" +
						"Read the ’Getting Started’ and the Tutorials to write your own game! \n" +
						"Have fun!");
				dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}  
				});
				dialog.setNegativeButton("Google Code", new DialogInterface.OnClickListener() {  
					public void onClick(DialogInterface dialog, int whichButton) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://code.google.com/p/candroidengine/")));
					}  
				});
				dialog.create().show();

			}
		});
	}

	private void runExample(int number){
		ExampleActivity.exampleNr=number;
		Intent exampleactivity = new Intent(this, ExampleActivity.class);
		startActivity(exampleactivity);

	}

	


}