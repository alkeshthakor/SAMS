package com.alkesh.samudratech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class SupplyChainActivity extends Activity implements OnClickListener{

	TextView mBoothDeliveryTextView;
	TextView mRouteSummaryTextView;
	TextView mStockSummaryTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_supply_chain);
		
		setTitleBar();
		mBoothDeliveryTextView=(TextView)findViewById(R.id.supply_booth_delivery_tv);
		mRouteSummaryTextView=(TextView)findViewById(R.id.supply_route_summary_tv);
		mStockSummaryTextView=(TextView)findViewById(R.id.supply_stock_summary_tv);

		
		mBoothDeliveryTextView.setOnClickListener(this);
		mRouteSummaryTextView.setOnClickListener(this);
		mStockSummaryTextView.setOnClickListener(this);
	}
	private void setTitleBar(){
		TextView titleBarTextView=(TextView)findViewById(R.id.textView_titlebar);
		titleBarTextView.setText(getResources().getString(R.string.lbl_supplay_chain));
		
		ImageButton backImageButton=(ImageButton)findViewById(R.id.back_button_titlebar);
		backImageButton.setOnClickListener(this);	
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.supply_booth_delivery_tv:
			break;
		case R.id.supply_route_summary_tv:
			break;
		case R.id.supply_stock_summary_tv:
			break;
		case R.id.back_button_titlebar:
			finish();
			break;

			
		}
	}

}
