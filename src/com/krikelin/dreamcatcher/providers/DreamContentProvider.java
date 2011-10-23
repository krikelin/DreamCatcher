package com.krikelin.dreamcatcher.providers;

import java.util.Date;
import java.util.HashMap;

import com.krikelin.dreamcatcher.DreamManager;
import com.krikelin.dreamcatcher.localdb.DBDreamManager;
import com.krikelin.dreamcatcher.localdb.DreamDatabase;
import com.krikelin.dreamcatcher.providers.Dream.Dreams;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class DreamContentProvider extends ContentProvider {
	public static final String AUTHORITY = "com.krikelin.dreamcatcher.providers.DreamContentProvider";

	private static final String DREAMS_TABLE_NAME = "dreams";
	private static final int DREAMS = 1;
	private static HashMap<String, String> notesProjectionMap;
	private static UriMatcher sUriMatcher;
	private Context mContext;
	public DreamContentProvider(Context context){
		mManager = new DBDreamManager(context);
		this.mContext = context;
	}
	DreamManager mManager;
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		if(sUriMatcher.match(uri) == DREAMS)
		{
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			db.delete(DREAMS_TABLE_NAME, selection, selectionArgs);
			db.close();
		}
		else
		{
			throw new IllegalArgumentException("Unknown URI " + uri);

		}
		mContext.getContentResolver().notifyChange(uri, null);

		return 1;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		if(sUriMatcher.match(uri) == DREAMS)
			return Dream.Dreams.CONTENT_TYPE;
		else
		{
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		int rowID = mManager.addDream(new Date(values.getAsString(Dreams.TIME)), values.getAsInteger(Dreams.PART), values.getAsString(Dreams.TITLE), values.getAsString(Dreams.DESCRIPTION), values.getAsString(Dreams.TAGS), values.getAsBoolean(Dreams.EROTIC));
		return ContentUris.withAppendedId(uri, rowID);
		
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new DreamDatabase(mContext);
		return true;
	}
	SQLiteOpenHelper dbHelper;
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
		sqb.setTables(DREAMS_TABLE_NAME);
		sqb.setProjectionMap(notesProjectionMap);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c = sqb.query(db, projection, selection, selectionArgs, null,null,null);
		db.close(); // Close the database
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, DREAMS_TABLE_NAME, DREAMS); 
 
        notesProjectionMap = new HashMap<String, String>();
        notesProjectionMap.put(Dreams.DREAM_ID, Dreams.DREAM_ID);
        notesProjectionMap.put(Dreams.TITLE, Dreams.TITLE);
        notesProjectionMap.put(Dreams.DESCRIPTION, Dreams.DESCRIPTION);

    }

}
