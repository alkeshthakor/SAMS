package com.alkesh.samudratech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends Activity implements OnClickListener{

	TextView mNewOrderTextView;
	TextView mReportTextView;
	
	TextView mBoothTextView;
	TextView mSupplyChainTextView;
	TextView mAdminTextView;
	TextView mUtilitesChainTextView;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_home);
		
//		mNewOrderTextView=(TextView)findViewById(R.id.newOrder_textView);
//		mReportTextView=(TextView)findViewById(R.id.report_textView);
		
//		mNewOrderTextView.setOnClickListener(this);
//		mReportTextView.setOnClickListener(this);
		

		mBoothTextView=(TextView)findViewById(R.id.home_booth_tv);
		mSupplyChainTextView=(TextView)findViewById(R.id.home_supplay_chain_tv);
		mAdminTextView=(TextView)findViewById(R.id.home_admin_tv);
		mUtilitesChainTextView=(TextView)findViewById(R.id.home_utilites_tv);

		mBoothTextView.setOnClickListener(this);
		mSupplyChainTextView.setOnClickListener(this);
		mAdminTextView.setOnClickListener(this);
		mUtilitesChainTextView.setOnClickListener(this);
		
		setTitleBar();
		
	}

	private void setTitleBar(){
		//TextView titleBarTextView=(TextView)findViewById(R.id.textView_titlebar);
		ImageButton backImageButton=(ImageButton)findViewById(R.id.back_button_titlebar);
		backImageButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.home_booth_tv:
			Intent productIntent=new Intent(getApplicationContext(),BoothActivity.class);
			startActivity(productIntent);
			break;
		case R.id.home_supplay_chain_tv:
			Intent supplyIntent=new Intent(getApplicationContext(),SupplyChainActivity.class);
			startActivity(supplyIntent);
			break;
		case R.id.home_admin_tv:
			Intent adminIntent=new Intent(getApplicationContext(),AdminActivity.class);
			startActivity(adminIntent);
			break;
		case R.id.home_utilites_tv:
			break;
		case R.id.back_button_titlebar:
			finish();
			break;
			
		}
	}

}
