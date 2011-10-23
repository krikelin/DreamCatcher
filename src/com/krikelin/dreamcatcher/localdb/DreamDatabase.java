package com.krikelin.dreamcatcher.localdb;

import com.krikelin.dreamcatcher.R;
import com.krikelin.dreamcatcher.R.string;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DreamDatabase extends SQLiteOpenHelper {
	Context context;
	public static final String DBNAME = "dreamdb";
	public static final int VERSION = 2;
	public DreamDatabase(Context context) {
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
		this.context=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(this.context.getString(R.string.query_create_dream_table));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
