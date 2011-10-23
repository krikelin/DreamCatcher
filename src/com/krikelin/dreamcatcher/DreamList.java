package com.krikelin.dreamcatcher;

import com.krikelin.dreamcatcher.localdb.DBDreamManager;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;

public class DreamList extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dream_list);
		Cursor cursor = null;  // Cursor for the listadapter
		/** Get the latest dreams.
		 * If a week parameter is set, show the current week, otherwise
		 * show dreams according to other parameters
		 **/
		 // Get according to mode
		if(getIntent().hasExtra("mode"))
		{
			String mode = getIntent().getStringExtra("mode");
			
			if(mode == "week")
			{
				// Get query
				cursor = (new DBDreamManager(this)).getDreamsByWeek(getIntent().getIntExtra("week",1));
				
			}
			if(mode == "tag")
			{
				cursor = (new DBDreamManager(this)).getDreamsByTags(getIntent().getStringArrayExtra("tag"),0,10);
			}
			if(mode == "default")
			{
				cursor =  (new DBDreamManager(this)).getDreams(0,10);
			}
		}
		else
		{
			cursor =  (new DBDreamManager(this)).getDreams(0,10);
		}
		if(cursor!=null)
		{
			setListAdapter(new DreamListAdapter(this,cursor));
		}
	}

}
