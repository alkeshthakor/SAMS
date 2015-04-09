package com.alkesh.samudratech;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo.DetailedState;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alkesh.samudratech.adapter.Report;
import com.alkesh.samudratech.adapter.ReportAdapter;
import com.alkesh.samudratech.util.ConnectionDetector;
import com.alkesh.samudratech.util.Constant;
import com.alkesh.samudratech.util.Pref;
import com.alkesh.samudratech.util.ServerConnector;

public class ReportActivity extends Activity {

	 private TextView mIdTextView;
	 private TextView mAreaTextView;
	    
	 private ListView mReportListView;
	
	private ProgressDialog mProgressDialog;
    ConnectionDetector cd;
    ServerConnector connector;
    Context mContext;
    
    
    private List<Report> mReportList;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_report);
				
		mContext=this;		
		cd=new ConnectionDetector(mContext);
		connector=new ServerConnector();
		mProgressDialog=new ProgressDialog(ReportActivity.this);
		mProgressDialog.setMessage("Data loading...");
		mProgressDialog.setIndeterminate(false);
		
		
		mIdTextView = (TextView)findViewById(R.id.boothIdReport);
		mAreaTextView = (TextView)findViewById(R.id.boothAreaReport);
		mReportListView = (ListView)findViewById(R.id.shiftReportListView);
		
		
		String boothId=Pref.getValue(Constant.PREF_BOOTH_ID,"");
		
		if(!boothId.equalsIgnoreCase("")){
			mIdTextView.setText(boothId);
			mAreaTextView.setText(Pref.getValue(Constant.PREF_BOOTH_NAME,""));
			new LoadReportTask().execute(boothId);
			
		}
		
		mReportListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent detailReportIntent=new Intent(getApplicationContext(),ReportDetailActivity.class);
				detailReportIntent.putExtra("date",mReportList.get(position).getReportDate());
				detailReportIntent.putExtra("shift",mReportList.get(position).getShift());
				startActivity(detailReportIntent);
				
			}
			
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report, menu);
		return true;
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
				JSONObject resObj=connector.getServerResponse(Constant.HOST+Constant.REPORT_LIST_SERVICE+params[0]);
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
							mReportList=new ArrayList<Report>();
							for(int i=0;i<values.length();i++){
								Report mReport=new Report();
								mReport.setReportDate(values.getJSONObject(i).getString("reportDate"));
								mReport.setShift(values.getJSONObject(i).getString("shift"));
								mReportList.add(mReport);
							}
							ReportAdapter mReportAdapter=new ReportAdapter(mContext, mReportList);
							//LabReportAdapter mLabReportAdapter=new LabReportAdapter(getApplicationContext(), mReportList);
							mReportListView.setAdapter(mReportAdapter);
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
