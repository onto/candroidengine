package at.bartinger.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import at.bartinger.candroid.CandroidActivity;


public class ExampleActivity extends CandroidActivity{

	public static int exampleNr = 0;
	private String startLabel = "This is an example about: \n\n";
	private String[] messages;
	AlertDialog.Builder dialog;
	private Context context;
	
	private Example01View view01;
	private Example02View view02;
	private Example03View view03;
	private Example04View view04;
	private Example05View view05;
	private Example06View view06;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.context = this;
		
		dialog = new AlertDialog.Builder(this);
		
		messages = new String[] {
				"Basics, Textures and Sprites",
				"Animations",
				"Backgrounds Part 1",
				"Backgrounds Part 2",
				"SFX and Backgroundmusic",
				"Text and Fonts",
				"TouchEvent",
				"TrackballEvent",
				"Accelerometer",
				"D-Pad",
				"nothing yet",
				"nothing yet",
				"nothing yet",
				"nothing yet",
				"nothing yet",
				"nothing yet",			
		};
		
		dialog.setIcon(R.drawable.icon);
		dialog.setTitle("Candroid Game Engine");  
		dialog.setMessage(startLabel + messages[exampleNr-1]);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {
				switch(exampleNr){

				case 1:
					
					view01 = new Example01View(context, false);
					setContentView(view01);
					break;

				case 2:
					view02 = new Example02View(context, false);
					setContentView(view02);
					break;

				case 3:
					view03 = new Example03View(context, false);
					setContentView(view03);
					break;

				case 4:
					view04 = new Example04View(context, false);
					setContentView(view04);
					break;

				case 5:
					view05 = new Example05View(context, false);
					setContentView(view05);
					break;

				case 6:
					view06 = new Example06View(context, false);
					setContentView(view06);
					break;

				case 7:

					break;

				case 8:

					break;

				case 9:

					break;

				case 10:

					break;

				case 11:

					break;

				case 12:

					break;

				case 13:

					break;

				case 14:

					break;

				case 15:

					break;
					
				case 16:

					break;

				}
			}  
		});
		dialog.create().show();
		
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onDestroy() {
		switch(exampleNr){

		case 1:
			view01.clearSecondRunnable();
			view01.stopDrawing();
			view01.recycle();
			break;

		case 2:
			view02.clearSecondRunnable();
			view02.stopDrawing();
			view02.recycle();
			break;

		case 3:

			break;

		case 4:
			setContentView(new Example04View(context, false));
			break;

		case 5:
			view05.clearSecondRunnable();
			view05.stopDrawing();
			view05.recycle();

			break;

		case 6:

			break;

		case 7:

			break;

		case 8:

			break;

		case 9:

			break;

		case 10:

			break;

		case 11:

			break;

		case 12:

			break;

		case 13:

			break;

		case 14:

			break;

		case 15:

			break;
			
		case 16:

			break;

		}
		
		super.onDestroy();
	}
	
	
	@Override
	protected void onResume() {
		
		super.onResume();
	}



}
