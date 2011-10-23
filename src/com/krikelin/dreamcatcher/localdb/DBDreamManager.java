package com.krikelin.dreamcatcher.localdb;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.krikelin.dreamcatcher.DreamManager;

public class DBDreamManager extends DreamManager {
	SQLiteDatabase mDatabase;
	Context mContext;
	public   DBDreamManager(Context context)
	{
		mContext=context;
		mDatabase = (new DreamDatabase(mContext)).getWritableDatabase();
	}
	
	@Override
	public Cursor getDreams(int page,int count) {
		// TODO Auto-generated method stub
		int _page = (page*count);
		Cursor d = mDatabase.rawQuery("SELECT * FROM dream ORDER BY time DESC LIMIT "+_page+","+String.valueOf(count+_page),null);
		d.moveToFirst();
		mDatabase.close(); 
		return d;
	}

	@Override
	public Cursor getDreamsByTime(Date time) {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	public int addDream(Date date, int part, String title, String desc,
			String tags, boolean erotic) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put("part",part);
		cv.put("title",title);
		cv.put("desc",desc);
		cv.put("tags",tags);
		cv.put("sex",erotic);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		
		cv.put("time",dateFormat.format(date));
		long result = mDatabase.insertOrThrow("dream",null,cv);
		
		
		mDatabase.close();
		return (int)result;
	
	}

	@Override
	public boolean removeDreamWithId(int id) {
		// TODO Auto-generated method stub
		mDatabase.rawQuery("DELETE FROM dream WHERE _id="+id,null);
		mDatabase.close();
		return true;
	}

	@Override
	public void create(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cursor getDreamsByWeek(int weekID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor getDreamsByTags(String[] tags,int page,int count) {
		// TODO Auto-generated method stub
		String _tags = "";
		int _page = (page*count);
		for(String i : tags)
		{
			_tags+=("tag LIKE '%"+i+"%' AND");
			 
		}
		Cursor d = mDatabase.rawQuery("SELECT * FROM dream WHERE "+_tags+" TRUE ORDER BY time DESC LIMIT "+_page+","+String.valueOf(count+_page),null);
		d.moveToFirst();
		mDatabase.close(); 
		return null;
	}

}
