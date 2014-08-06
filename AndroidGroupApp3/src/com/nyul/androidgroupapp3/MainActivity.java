package com.nyul.androidgroupapp3;

import static java.lang.Thread.sleep;

import java.util.Locale;

import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nyul.androidgroupapp3.dialogue.DialogueMaker;
import com.nyul.androidgroupapp3.dialogue.SelectionListener;


public class MainActivity extends ParentActivity{
	DialogueMaker dialogueMaker = new DialogueMaker(this);	
	
	protected void onClickButton1(View v) {
		EditText editText = (EditText) findViewById(R.id.editText1);

		String message = editText.getText().toString();
		editText.setText(encrypt(message));
	}
	
	protected void onClickButton2(View v) {
		EditText editText = (EditText) findViewById(R.id.editText1);

		String message = editText.getText().toString();
		editText.setText(encrypt2(message));

	}
	
	@Override
	protected void onClickButton3(View v) {
		EditText editText = (EditText) findViewById(R.id.editText1);

		String message = editText.getText().toString();
		editText.setText(decrypt(message));

	}
protected void onClickButton4(View v) {
	EditText editText = (EditText) findViewById(R.id.editText1);

	String message = editText.getText().toString();
	editText.setText(decrypt2(message));
	}
	
class SelectedOptionsHandler implements Runnable{
		private final SelectionListener listener;
		private final TextView tV = (TextView) findViewById(R.id.editText1);
		
		public SelectedOptionsHandler(SelectionListener listener) {
			this.listener = listener;
		}

		@Override
		public void run() {
			for(int i = 0; i < 3; i++){			
				tV.setText("PIE" + tV.getText() + i);
			}
		}
		
	}
	
	class MultiOptionsHandler implements Runnable{
		private final SelectionListener listener;
		private final TextView tV = (TextView) findViewById(R.id.editText1);				
		
		public MultiOptionsHandler(SelectionListener listener) {
			this.listener = listener; 
		}

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
			
			tV.post(new SelectedOptionsHandler(listener));			
		}
		
	}

	public static String encrypt(String message) {
		char[] a = message.toCharArray();
		
		for(int i=0; i < a.length; i++){
			a[i] = (char)( a[i] + 1);
		}
		
		return new String(a);
	}
	public static String encrypt2(String message) {
		char[] a = message.toCharArray();
		
		for(int i=0; i < a.length; i++){
			a[i] = (char)( a[i] + 2);
		}
		
		return new String(a);
	}
	public static String decrypt(String message) {
		char[] a = message.toCharArray();
		for(int i=0; i < a.length; i++){
			a[i] = (char)( a[i] - 1);
		}
		
		return new String(a);
	}
	public static String decrypt2(String message) {
		char[] a = message.toCharArray();
		for(int i=0; i < a.length; i++){
			a[i] = (char)( a[i] - 2);
		}
		
		return new String(a);
	}

	


}