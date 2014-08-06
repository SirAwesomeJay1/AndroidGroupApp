package com.nyul.androidgroupapp3;

import static java.lang.Thread.sleep;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.nyul.androidgroupapp3.dialogue.DialogueMaker;
import com.nyul.androidgroupapp3.dialogue.SelectionListener;


public class MainActivity extends ParentActivity{
	DialogueMaker dialogueMaker = new DialogueMaker(this);	
	
	protected void onClickButton1(View v) {
		final String[] items = { "Item1", "Item2", "Item3", "Item4", "Item5" };
		final SelectionListener listener = dialogueMaker.options("Title", "Message", items);
		final TextView tV = (TextView) findViewById(R.id.textView1);				
		
		Thread t = new Thread(new Runnable() {			
			@Override
			public void run() {
				Looper.prepare();
				while( ! listener.wasOkPressed() ){
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						log(e.toString());
					}
				}
				
				for(final String s : listener.getCheckedOptionsList()){
					tV.post(new Runnable() {						
						@Override
						public void run() {
							tV.setText(tV.getText() + "\n" + s);							
						}
					});
				}
			}
		});
		
		t.start();
	}
	
	protected void onClickButton2(View v) {
		dialogueMaker.message("The Title", "The Message");
	}

}