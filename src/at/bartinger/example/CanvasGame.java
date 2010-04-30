package at.bartinger.example;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import at.bartinger.candroid.Constants;
import at.bartinger.candroid.net.GlobalClient;


public class CanvasGame extends ListActivity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//example01View = new Example01(this, true, false, 1.5d, 500);
		  String[] examples = new String[] {
				  "Example01",
				  "Example02",
				  "Example03",
				  "Example04",
				  "Example05",
				  "Example06",
				  "Example07",
				  "Example08",
				  "Example09",
				  "Example10",
				  "Example11",
				  "Example12",
				  "Example13",
				  "Example14",
				  "Example15",
				  "Example16",
			  };

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, examples));
		
		getListView().setTextFilterEnabled(true);


		//setContentView(example01View);

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ExampleActivity.exampleNr=position+1;
		Intent exampleactivity = new Intent(this, ExampleActivity.class);
		startActivity(exampleactivity);
		super.onListItemClick(l, v, position, id);
	}



	@Override
	protected void onDestroy() {	
//		example01View.clearSecondRunnable();
//		example01View.stopDrawing();
//		example01View.recycle();
		super.onDestroy();

	}

}