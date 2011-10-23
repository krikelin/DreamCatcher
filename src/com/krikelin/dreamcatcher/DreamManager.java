package com.krikelin.dreamcatcher;

import java.util.Date;

import android.content.Context;
import android.database.Cursor;

/**
 * Abstract class for managing dreams
 * @author Alexander
 *
 */
public abstract class DreamManager {
	public static final int ID_FAILED = -1;
	public static final boolean FAILED = false;
	public static final boolean SUCSES = true;
	
	/**
	 * Creates an new dream manager
	 * @param context the context for the creation
	 */
	public abstract void create(Context context);
	/**
	 * 
	 * @return Cursor a list of dreams
	 */
	public abstract Cursor getDreams(int page, int count);
	
	public abstract Cursor getDreamsByTags(String[] tags,int page,int count);
	
	
	/**
	 * Get dreams for the particular time
	 * @param time
	 * @return
	 */
	public abstract Cursor getDreamsByTime(Date time);
	
	/**
	 * Add an dream
	 * @param date The date of the dream
	 * @param part The part of the dream
	 * @param title Title of the Dreams
	 * @param desc Description of the dream
	 * @param tags Tags of the dream
	 * @param erotic indicates if the dream were erotic
	 * @return int id of the new dream if sucess, -1 if failed
	 */
	public abstract int addDream(Date date,int part,String title,String desc,String tags,boolean erotic);
	
	/**
	 * Removes an dream by the particular ID
	 * @param id the id of the dream
	 * @return boolean if the operation was sucessfull or not
	 */
	public abstract boolean removeDreamWithId(int id);
	/**
	 * Get dreams from an particualr week of the year
	 * @param weekID the week
	 * @return the dream
	 */
	public abstract Cursor getDreamsByWeek(int weekID);
	
	
}
