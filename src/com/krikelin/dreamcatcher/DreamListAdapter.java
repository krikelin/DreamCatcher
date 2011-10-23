package com.krikelin.dreamcatcher;

import android.app.Service;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class DreamListAdapter extends CursorAdapter {

	public DreamListAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater LI = (LayoutInflater)context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		View view = LI.inflate(R.layout.dream_entry,null);
		/**
		 * Set text
		 */
		String tags = cursor.getString(cursor.getColumnIndex("tags"));
		String desc = cursor.getString(cursor.getColumnIndex("desc"));
		String date = cursor.getString(cursor.getColumnIndex("time"));
		String title = cursor.getString(cursor.getColumnIndex("title"));
		((TextView)view.findViewById(R.id.tvTags)).setText(tags);
		
		((TextView)view.findViewById(R.id.tvDescription)).setText(desc);
		((TextView)view.findViewById(R.id.tvDate)).setText(date);
		((TextView)view.findViewById(R.id.tvTitle)).setText(title);
		return view;
	}

}
