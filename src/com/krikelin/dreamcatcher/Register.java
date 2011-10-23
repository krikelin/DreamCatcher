package com.krikelin.dreamcatcher;

import java.util.Date;
import com.krikelin.dreamcatcher.localdb.*;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
public class Register extends Activity {
	public class CProcess extends AsyncTask<String,String,Integer>
	{

		@Override
		protected Integer doInBackground(String... params) {
			// TODO Auto-generated method stub
			/**
			 * Get data
			 */
			String tags = getEditText(R.id.edTags);
			String title = getEditText(R.id.edTitle);
			String desc = getEditText(R.id.edDescription);
			int part = 1;
			try
			{
				part = Integer.valueOf(getEditText(R.id.edPart));
			}
			catch(Exception e)
			{
				part = -1;
			}
			boolean erotic = getCheckBox(R.id.edErotic);
			Date date = getDate(R.id.edDate);
			/** 
			 * Add to the database
			 */
			DBDreamManager dbm = new DBDreamManager(Register.this);
			
			int i = dbm.addDream(date, part,title, desc, tags, erotic);
			return i;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Register.this.mProgressDialog.cancel();
			
		}
		
	};
	public ProgressDialog mProgressDialog;
	/**
	 * 
	 * @param resId the int of the resource
	 * @return Date the value of the resource
	 */
	public Date getDate(int resId)
	{
		
		DatePicker datePicker = (DatePicker)findViewById(resId);
		return new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth());
		
	}
	/**
	 * 
	 * @param resId the int of the resource
	 * @return boolean the value of the resource
	 */
	public boolean getCheckBox(int resId)
	{
		return ((CheckBox)findViewById(resId)).isChecked();
	}
	/**
	 * 
	 * @param resId the int of the resource
	 * @return boolean the value of the resource
	 */
	public String getEditText(int resId)
	{
		return ((EditText)findViewById(resId)).getText().toString();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_dream);
		((Button)findViewById(R.id.btnSave)).setOnClickListener(new Button.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mProgressDialog = ProgressDialog.show(Register.this,"", "Adding the dream");
				new CProcess().execute((String[])null);
			}
			
		});
	}

}
