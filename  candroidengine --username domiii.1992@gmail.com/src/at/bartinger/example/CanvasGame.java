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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class CanvasGame extends Activity {


	String[] examples;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.examplebuttons);

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
		
		((Button)findViewById(R.id.e11)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(11);

			}
		});
		
		((Button)findViewById(R.id.e12)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(12);

			}
		});
		
		((Button)findViewById(R.id.e13)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(13);

			}
		});
		
		((Button)findViewById(R.id.e14)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(14);

			}
		});
		
		((Button)findViewById(R.id.e15)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(15);

			}
		});
		
		((Button)findViewById(R.id.e16)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				runExample(16);

			}
		});

		//		  examples = new String[] {
		//				  "Example01",
		//				  "Example02",
		//				  "Example03",
		//				  "Example04",
		//				  "Example05",
		//				  "Example06",
		//				  "Example07",
		//				  "Example08",
		//				  "Example09",
		//				  "Example10",
		//				  "Example11",
		//				  "Example12",
		//				  "Example13",
		//				  "Example14",
		//				  "Example15",
		//				  "Example16",
		//			  };
		//		  setListAdapter(new ArrayAdapter<String>(this,
		//					android.R.layout.simple_list_item_1, examples));
	}

	private void runExample(int number){
		ExampleActivity.exampleNr=number;
		Intent exampleactivity = new Intent(this, ExampleActivity.class);
		startActivity(exampleactivity);

	}

	//	@Override
	//	protected void onListItemClick(ListView l, View v, int position, long id) {
	//		ExampleActivity.exampleNr=position+1;
	//		Intent exampleactivity = new Intent(this, ExampleActivity.class);
	//		startActivity(exampleactivity);
	//		super.onListItemClick(l, v, position, id);
	//	}


}