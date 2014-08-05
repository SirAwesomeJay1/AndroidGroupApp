package com.nyul.androidgroupapp3;

import com.nyul.androidgroupapp3.dialogue.DialogueMaker;

import android.view.View;


public class MainActivity extends ParentActivity{
	DialogueMaker dialogue = new DialogueMaker(this);	
	
	protected void onClickButton1(View v) {
		String[] items = { "Item1", "Item2", "Item3", "Item4", "Item5" };
		dialogue.createItemsDialogue("Title", "Message", items); 
	}
	
	protected void onClickButton2(View v) {
		dialogue.createMessageDialogue("The Title", "The Message");
	}

}