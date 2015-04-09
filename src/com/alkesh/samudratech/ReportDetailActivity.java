package com.alkesh.samudratech;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alkesh.samudratech.adapter.Report;
import com.alkesh.samudratech.adapter.ReportAdapter;
import com.alkesh.samudratech.adapter.ShiftReport;
import com.alkesh.samudratech.adapter.ShiftReportAdapter;
import com.alkesh.samudratech.util.ConnectionDetector;
import com.alkesh.samudratech.util.Constant;
import com.alkesh.samudratech.util.Pref;
import com.alkesh.samudratech.util.ServerConnector;

public class ReportDetailActivity extends Activity {

	 private TextView mIdTextView;
	 private TextView mAreaTextView;
	 private TextView mDateTextView;
	 private TextView mShiftTextView;
	 
	 private ListView mReportListView;
	 private List<ShiftReport> mReportList;

	
	 private ProgressDialog mProgressDialog;
     ConnectionDetector cd;
     ServerConnector connector;
     Context mContext;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_report_detail);
		
		mContext=this;		
		cd=new ConnectionDetector(mContext);
		connector=new ServerConnector();
		mProgressDialog=new ProgressDialog(ReportDetailActivity.this);
		mProgressDialog.setMessage("Data loading...");
		mProgressDialog.setIndeterminate(false);
		
		
		mIdTextView = (TextView)findViewById(R.id.boothIdReportDetail);
		mAreaTextView = (TextView)findViewById(R.id.boothAreaReportDetail);
		mDateTextView = (TextView)findViewById(R.id.dateReportDetail);
		mShiftTextView = (TextView)findViewById(R.id.shiftReportDetail);
		mReportListView = (ListView)findViewById(R.id.shiftReportDetailListView);
		
		String boothId=Pref.getValue(Constant.PREF_BOOTH_ID,"");
		
		if(!boothId.equalsIgnoreCase("")){
			mIdTextView.setText(boothId);
			mAreaTextView.setText(Pref.getValue(Constant.PREF_BOOTH_NAME,""));
			
			if(getIntent()!=null){
				String date=getIntent().getStringExtra("date");
				String shift=getIntent().getStringExtra("shift");
				if(date!=null){
					mDateTextView.setText(date);
				}		
				if(shift.equalsIgnoreCase("0")){
					mShiftTextView.setText(mContext.getResources().getString(R.string.lbl_shift_morning));					
				}else{
					mShiftTextView.setText(mContext.getResources().getString(R.string.lbl_shift_night));
				}
				
				if(date!=null&&shift!=null){							
					String params="boothId="+boothId+"&date="+date+"&shift="+shift;
					new LoadReportTask().execute(params);
				}
			}
		}
	}
	
	
	
	 private class LoadReportTask extends AsyncTask<String,Void,JSONObject>{

	    	@Override
	    	protected void onPreExecute() {
	    		// TODO Auto-generated method stub
	    		super.onPreExecute();
	    		mProgressDialog.show();
	    	}
	    	
			@Override
			protected JSONObject doInBackground(String... params) {
				// TODO Auto-generated method stub
				JSONObject resObj=connector.getServerResponse(Constant.HOST+Constant.REPORT_DETAIL_SERVICE+params[0]);
				return resObj;
			}
	    	
			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				mProgressDialog.dismiss();
				
				if(result!=null){
					try {
						boolean status=result.getBoolean("status");
						if(status == true){							
							JSONArray values=result.getJSONArray("values");
							mReportList=new ArrayList<ShiftReport>();
							for(int i=0;i<values.length();i++){
								ShiftReport mReport=new ShiftReport();
								mReport.setItemId(values.getJSONObject(i).getString("itemId"));
								mReport.setQuantity(values.getJSONObject(i).getString("qty"));
								mReportList.add(mReport);
							}
							
							ShiftReportAdapter mShiftReportAdapter=new ShiftReportAdapter(mContext, mReportList);
							mReportListView.setAdapter(mShiftReportAdapter);
						}else{
							Toast.makeText(mContext,"No data found",Toast.LENGTH_LONG).show();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
	    }
	 
	 
	
}
