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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alkesh.samudratech.adapter.DocReport;
import com.alkesh.samudratech.adapter.LabReport;
import com.alkesh.samudratech.adapter.LabReportAdapter;
import com.alkesh.samudratech.util.ConnectionDetector;
import com.alkesh.samudratech.util.Constant;
import com.alkesh.samudratech.util.Pref;
import com.alkesh.samudratech.util.ServerConnector;

public class LabReportActivity extends Activity {

	 private TextView mIdTextView;
	 private TextView mAreaTextView;
	 private TextView mExitTextView;
	    
	private ListView labReportListView;
	
	private ProgressDialog mProgressDialog;
    ConnectionDetector cd;
    ServerConnector connector;
    Context mContext;
    
    
    private List<LabReport> mLabReportList;

    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lab_report);
		
		mContext=this;		
		cd=new ConnectionDetector(mContext);
		connector=new ServerConnector();
		mProgressDialog=new ProgressDialog(LabReportActivity.this);
		mProgressDialog.setMessage("Data loading...");
		mProgressDialog.setIndeterminate(false);
		
		
		mIdTextView=(TextView)findViewById(R.id.boothIdTVLab);
		mAreaTextView=(TextView)findViewById(R.id.boothAreaTVLab);
		mExitTextView=(TextView)findViewById(R.id.exit_textViewLab);
		labReportListView=(ListView)findViewById(R.id.labReportListView);
		
		mIdTextView.setText(Pref.getValue(Constant.PREF_BOOTH_ID,""));
		mAreaTextView.setText(Pref.getValue(Constant.PREF_BOOTH_NAME,""));
		
		mExitTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
				
		String fromDate=getIntent().getStringExtra("fromdate");
		String toDate=getIntent().getStringExtra("todate");
		
	    if(fromDate!=null&&toDate!=null){
	    	new LoadLabReportTask().execute("fromDate="+fromDate+"&toDate="+toDate);
	    }
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lab_report, menu);
		return true;
	}
	
	
	 private class LoadLabReportTask extends AsyncTask<String,Void,JSONObject>{

	    	@Override
	    	protected void onPreExecute() {
	    		// TODO Auto-generated method stub
	    		super.onPreExecute();
	    		mProgressDialog.show();
	    	}
	    	
			@Override
			protected JSONObject doInBackground(String... params) {
				// TODO Auto-generated method stub
				JSONObject resObj=connector.getServerResponse(Constant.HOST+Constant.LAB_REPORT_SERVICE+params[0]);
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
							mLabReportList=new ArrayList<LabReport>();
							for(int i=0;i<values.length();i++){
								LabReport mLabReport=new LabReport();
								
								String date=values.getJSONObject(i).getString("dumpDate");
								String[] temp=date.split(" ");
								mLabReport.setmDumpDate(temp[0]);
								mLabReport.setmShift(values.getJSONObject(i).getString("shift"));
								mLabReport.setmSoCode(values.getJSONObject(i).getString("socCode"));
								mLabReport.setmSocName(values.getJSONObject(i).getString("socName"));
								
								mLabReport.setmFat(values.getJSONObject(i).getString("fat"));
								mLabReport.setmNewFat(values.getJSONObject(i).getString("newFat"));
								mLabReport.setmLr(values.getJSONObject(i).getString("lr"));
								mLabReport.setmNewLr(values.getJSONObject(i).getString("newlr"));
								
								mLabReport.setmSnf(values.getJSONObject(i).getString("snf"));
								mLabReport.setmNewSnf(values.getJSONObject(i).getString("newsnf"));
								mLabReport.setmCntCode(values.getJSONObject(i).getString("cntcode"));
								
								mLabReportList.add(mLabReport);
								
							}
							
							LabReportAdapter mLabReportAdapter=new LabReportAdapter(getApplicationContext(), mLabReportList);
							labReportListView.setAdapter(mLabReportAdapter);
							
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
