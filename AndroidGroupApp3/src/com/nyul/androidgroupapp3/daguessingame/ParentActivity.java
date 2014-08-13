package com.nyul.androidgroupapp3.daguessingame;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public abstract class ParentActivity extends Activity
		implements View.OnClickListener, CreateNdefMessageCallback{
	
    NfcAdapter mNfcAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        // Register callback
        mNfcAdapter.setNdefPushMessageCallback(this, this);
	}
	
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
    	String message = ((EditText) findViewById(R.id.editText1)).getText().toString();
    	
        String text = (message);
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { NdefRecord.createMime(
                        "application/com.nyul.androidgroupapp3;", text.getBytes())
         /**
          * The Android Application Record (AAR) is commented out. When a device
          * receives a push with an AAR in it, the application specified in the AAR
          * is guaranteed to run. The AAR overrides the tag dispatch system.
          * You can add it back in to guarantee that this
          * activity starts when receiving a beamed message. For now, this code
          * uses the tag dispatch system.
          */
          //,NdefRecord.createApplicationRecord("com.example.android.beam")
        });
        return msg;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }
    
    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
    	EditText textView = (EditText) findViewById(R.id.editText1);
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        textView.setText(new String(msg.getRecords()[0].getPayload()));
               
    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View v) {
		final int id = v.getId();

		switch (id) {
		case R.id.encrypt_1:
			onClickButton1(v);
			break;
		case R.id.decrypt_1:
			onClickButton3(v);
			break;
			
		case R.id.encrypt_2:
			onClickButton2(v);
			break;
		case R.id.decrypt_2:
			onClickButton4(v);
			break;
		}
	}

	protected abstract void onClickButton1(View v);
	protected abstract void onClickButton2(View v);
	protected abstract void onClickButton3(View v);
	protected abstract void onClickButton4(View v);

	
	public void log(String message){
		Log.e(this.toString(), message);
	}

}