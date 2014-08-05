package com.nyul.androidgroupapp3.dialogue;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogueMaker {
	private final Context context;

	public DialogueMaker(Context context){
		this.context = context;
	}

	public void createMessageDialogue(String title, String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setMessage(message)
			   .setTitle(title)
			   .setPositiveButton("Ok", new OkListener());					   
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void createItemsDialogue(String title, String message, String ... items){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
				
		builder.setTitle(title)
			   .setPositiveButton("Ok", new OkListener())
			   .setMultiChoiceItems(items, null, new MultiChoiceListener());					   
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	class OkListener implements DialogInterface.OnClickListener {
   		public void onClick(DialogInterface dialog, int id) {
   			// Respond to OK click
   		}
    }
	
	class MultiChoiceListener implements DialogInterface.OnMultiChoiceClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			// TODO Auto-generated method stub
			
		}		
	}
	
}