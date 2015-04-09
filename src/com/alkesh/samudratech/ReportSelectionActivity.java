package com.alkesh.samudratech;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.alkesh.samudratech.util.DatePickerFragment;

public class ReportSelectionActivity extends Activity implements OnClickListener{

	private RadioGroup mReportHistoryGroup;
	private EditText mFromDateEditText;
	private EditText mToDateEditText;
	
	private TextView generateReportButton;
	
	Calendar calender;

	private int mReportType=1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_report_selection);
		calender = Calendar.getInstance();
		mFromDateEditText=(EditText)findViewById(R.id.fromDateEditText);
		mToDateEditText=(EditText)findViewById(R.id.toDateEditText);
		generateReportButton=(TextView)findViewById(R.id.generateReport_textView);
		mReportHistoryGroup=(RadioGroup)findViewById(R.id.radioHistoryReportGroup);
		
		mReportHistoryGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == R.id.radioDocHistory){
					mReportType=0;
					//7 Toast.makeText(getApplicationContext(), "You select Doc History",Toast.LENGTH_SHORT).show();

				}else if(checkedId == R.id.radioReportHistory){
					mReportType=1;
					// Toast.makeText(getApplicationContext(), "You select Report History",Toast.LENGTH_SHORT).show();

				}
				
			}
		});
		
		mFromDateEditText.setOnClickListener(this);
		mToDateEditText.setOnClickListener(this);
		generateReportButton.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.fromDateEditText:
			selectFromDate();
			break;
		case R.id.toDateEditText:
			selectToDate();
			break;
		case R.id.generateReport_textView:
			Intent reportIntent;
			if(mReportType==0){
				reportIntent=new Intent(getApplicationContext(),DocReportActivity.class);
			}else{
				reportIntent=new Intent(getApplicationContext(),LabReportActivity.class);
			}
			reportIntent.putExtra("fromdate",mFromDateEditText.getText().toString());
			reportIntent.putExtra("todate",mToDateEditText.getText().toString());
			startActivity(reportIntent);
			break;
		}
	}

	
	private void selectFromDate(){
		DatePickerFragment date = new DatePickerFragment();
		  /**
		   * Set Up Current Date Into dialog
		   */
		 //Calendar calender = Calendar.getInstance();
		  Bundle args = new Bundle();
		  args.putInt("year", calender.get(Calendar.YEAR));
		  args.putInt("month", calender.get(Calendar.MONTH));
		  args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		  date.setArguments(args);
		  /**
		   * Set Call back to capture selected date
		   */
		  date.setCallBack(fromDateListener);
		  date.show(getFragmentManager(), "Date Picker");
	}
	
    private void selectToDate(){
    	  
    	 DatePickerFragment date = new DatePickerFragment();
		  /**
		   * Set Up Current Date Into dialog
		   */
		 //Calendar calender = Calendar.getInstance();
		  Bundle args = new Bundle();
		  args.putInt("year", calender.get(Calendar.YEAR));
		  args.putInt("month", calender.get(Calendar.MONTH));
		  args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		  date.setArguments(args);
		  /**
		   * Set Call back to capture selected date
		   */
		  date.setCallBack(toDateListener);
		  date.show(getFragmentManager(), "Date Picker");
		  
	}
    
    OnDateSetListener fromDateListener = new OnDateSetListener() {
		  @Override
		  public void onDateSet(DatePicker view, int year, int monthOfYear,
		    int dayOfMonth) {
		 
			  monthOfYear = monthOfYear + 1;
				String dateValue = "";
				
				if (monthOfYear < 10) {
					dateValue ="0" + monthOfYear;
				} else {
					dateValue = ""+ monthOfYear;
				}
				
				if (dayOfMonth < 10) {
					dateValue =dateValue+"-0" + dayOfMonth;
				} else {
					dateValue =dateValue+"-"+dayOfMonth;
				}
				
				String yearShort=""+year;
			    year =Integer.parseInt(yearShort.substring(2));
				dateValue = dateValue + "-" + year;
				
				mFromDateEditText.setText(dateValue);	
		  }
	};
	
	
	 OnDateSetListener toDateListener = new OnDateSetListener() {
		  @Override
		  public void onDateSet(DatePicker view, int year, int monthOfYear,
		    int dayOfMonth) {
		 
			    monthOfYear = monthOfYear + 1;
				String dateValue = "";
				
				if (monthOfYear < 10) {
					dateValue ="0" + monthOfYear;
				} else {
					dateValue = ""+ monthOfYear;
				}
				
				if (dayOfMonth < 10) {
					dateValue =dateValue+"-0" + dayOfMonth;
				} else {
					dateValue =dateValue+"-"+dayOfMonth;
				}
				
				String yearShort=""+year;
			    year =Integer.parseInt(yearShort.substring(2));
				dateValue = dateValue + "-" + year;
				mToDateEditText.setText(dateValue);
				
				//mDateEditText.setText(dateValue);	
		  }
	};
	
	
}
