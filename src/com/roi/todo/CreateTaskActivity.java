package com.roi.todo;

import com.roi.todo.DatePickerFragment.MyOnDateSetListener;
import com.roi.todo.R;
import com.roi.todo.TimePickerFragment.MyOnTimeSetListener;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

@TargetApi(11)
public class CreateTaskActivity extends Activity implements MyOnTimeSetListener, MyOnDateSetListener
{
	public final static String TASK_ID = "com.roi.todo.TASK_ID";
	public final static String TASK_DESCRIPTION = "com.roi.todo.TASK_DESCRIPTION";
	
	public final static String YEAR = "com.roi.todo.YEAR";
	public final static String MONTH = "com.roi.todo.MONTH";
	public final static String DAY = "com.roi.todo.DAY";
	public final static String HOUR_OF_DAY = "com.roi.todo.HOUR_OF_DAY";
	public final static String MINUTE = "com.roi.todo.MINUTE";
	
	private static int year;
	private static int month;
	private static int day;
	private static int hourOfDay;
	private static int minute;
	
	private static boolean isTimeSelected = false; 
	private static boolean isDateSelected = false; 
	
	@Override
	public void onCreate(Bundle savedInstanceStase)
	{
		super.onCreate(savedInstanceStase);
        setContentView(R.layout.create_task_layout);
	}
	
	public void addNewTask(View view)
    {
		Intent intent = new Intent(this, MainActivity.class);
		
		EditText taskDescription = (EditText) findViewById(R.id.task_description);
		String taskDescriptionStr = taskDescription.getText().toString();
		
		if (taskDescriptionStr.isEmpty())
		{
			setResult(RESULT_CANCELED,intent);
		}
		else
		{
			if (isTimeSelected && isDateSelected)
			{
				intent.putExtra(YEAR, String.valueOf(year));
				intent.putExtra(MONTH, String.valueOf(month));
				intent.putExtra(DAY, String.valueOf(day));
				intent.putExtra(HOUR_OF_DAY, String.valueOf(hourOfDay));
				intent.putExtra(MINUTE, String.valueOf(minute));
				
				isTimeSelected = false;
				isDateSelected = false;
			}
			
			intent.putExtra(TASK_DESCRIPTION, taskDescriptionStr);
			
			setResult(RESULT_OK,intent);
		}
		
		finish();
    }
	
	public void showTimePickerDialog(View v)
	{
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");
	}
	
	public void showDatePickerDialog(View v) 
	{
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}

	public void myOnTimeSet(int hourOfDay, int minute) 
	{
		CreateTaskActivity.hourOfDay = hourOfDay;
		CreateTaskActivity.minute = minute;
		isTimeSelected = true;
	}

	public void myOnDateSet(int year, int month, int day) 
	{
		CreateTaskActivity.year = year;
		CreateTaskActivity.month = month;
		CreateTaskActivity.day = day;
		isDateSelected = true;
	}
}
