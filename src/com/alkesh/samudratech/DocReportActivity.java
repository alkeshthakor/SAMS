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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alkesh.samudratech.adapter.DocReport;
import com.alkesh.samudratech.adapter.DocReportAdapter;
import com.alkesh.samudratech.util.ConnectionDetector;
import com.alkesh.samudratech.util.Constant;
import com.alkesh.samudratech.util.Pref;
import com.alkesh.samudratech.util.ServerConnector;

public class DocReportActivity extends Activity {

    private TextView mIdTextView;
    private TextView mAreaTextView;
    private TextView mExitTextView;
	    
	private ListView docReportListView;
	
	private ProgressDialog mProgressDialog;
    ConnectionDetector cd;
    ServerConnector connector;
    Context mContext;
    
    private List<DocReport> mDocReportList;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_doc_report);
		
		mContext=this;		
		cd=new ConnectionDetector(mContext);
		connector=new ServerConnector();
		mProgressDialog=new ProgressDialog(DocReportActivity.this);
		mProgressDialog.setMessage("Data loading...");
		mProgressDialog.setIndeterminate(false);
		
		
		mIdTextView=(TextView)findViewById(R.id.boothIdTVDoc);
		mAreaTextView=(TextView)findViewById(R.id.boothAreaTVDoc);
		mExitTextView=(TextView)findViewById(R.id.exit_textViewDoc);
		docReportListView=(ListView)findViewById(R.id.docReportListView);
		
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
	    	new LoadDocReportTask().execute("fromDate="+fromDate+"&toDate="+toDate);
	    }
		
		
		
	}

    private class LoadDocReportTask extends AsyncTask<String,Void,JSONObject>{

    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		mProgressDialog.show();
    	}
    	
		@Override
		protected JSONObject doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSONObject resObj=connector.getServerResponse(Constant.HOST+Constant.DOC_REPORT_SERVICE+params[0]);
			return resObj;
		}
    	
		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			if(result!=null){
				try {
					//String error=result.getString("errors");
					boolean status=result.getBoolean("status");
					if(status == true){
						
						JSONArray values=result.getJSONArray("values");
						mDocReportList=new ArrayList<DocReport>();
						for(int i=0;i<values.length();i++){
							DocReport mDocReport=new DocReport();
							
							String date=values.getJSONObject(i).getString("dumpDate");
							String[] temp=date.split(" ");
							mDocReport.setmDumpDate(temp[0]);
							mDocReport.setmShift(values.getJSONObject(i).getString("shift"));
							mDocReport.setmSocCode(values.getJSONObject(i).getString("soccode"));
							mDocReport.setmSocName(values.getJSONObject(i).getString("socname"));
							mDocReport.setmType(values.getJSONObject(i).getString("type"));
							mDocReport.setmWeight(values.getJSONObject(i).getString("weight"));
							mDocReport.setmDocNo(values.getJSONObject(i).getString("dockno"));
							mDocReport.setmDid(values.getJSONObject(i).getString("did"));
							
							mDocReport.setmNewSocCode(values.getJSONObject(i).getString("newsoccode"));
							mDocReport.setmNewSocName(values.getJSONObject(i).getString("newsocname"));
							mDocReport.setmNewType(values.getJSONObject(i).getString("newtype"));
							mDocReport.setmNewWeight(values.getJSONObject(i).getString("newweight"));
							mDocReport.setmNewDid(values.getJSONObject(i).getString("newdid"));
							mDocReport.setmCntCode(values.getJSONObject(i).getString("cntCode"));
							
							mDocReportList.add(mDocReport);
						}
						
						DocReportAdapter mDocReportAdapter=new DocReportAdapter(getApplicationContext(), mDocReportList);
						docReportListView.setAdapter(mDocReportAdapter);
						
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
