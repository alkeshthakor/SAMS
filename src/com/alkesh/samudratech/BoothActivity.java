package com.alkesh.samudratech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class BoothActivity extends Activity implements OnClickListener{

	TextView mOrderPlaceTextView;
	TextView mPreviousOrderTextView;
	TextView mOnlinePaymentTextView;
	TextView mAccountTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_booth);
		setTitleBar();
		mOrderPlaceTextView=(TextView)findViewById(R.id.booth_order_place_tv);
		mPreviousOrderTextView=(TextView)findViewById(R.id.booth_previous_order_tv);
		mOnlinePaymentTextView=(TextView)findViewById(R.id.booth_online_payment_tv);
		mAccountTextView=(TextView)findViewById(R.id.booth_account_tv);

		
		mOrderPlaceTextView.setOnClickListener(this);
		mPreviousOrderTextView.setOnClickListener(this);
		mOnlinePaymentTextView.setOnClickListener(this);
		mAccountTextView.setOnClickListener(this);
		
		
	}

	private void setTitleBar(){
		TextView titleBarTextView=(TextView)findViewById(R.id.textView_titlebar);
		titleBarTextView.setText(getResources().getString(R.string.lbl_booth));
		
		ImageButton backImageButton=(ImageButton)findViewById(R.id.back_button_titlebar);
		backImageButton.setOnClickListener(this);
		
	}

	
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.booth_order_place_tv:
			Intent productIntent=new Intent(getApplicationContext(),ProductListActivity.class);
			productIntent.putExtra("name",getResources().getString(R.string.lbl_order_place));
			startActivity(productIntent);
			break;
		case R.id.booth_previous_order_tv:
			Intent productIntent1=new Intent(getApplicationContext(),ProductListActivity.class);
			productIntent1.putExtra("name",getResources().getString(R.string.lbl_previous_order));
			startActivity(productIntent1);
			break;
		case R.id.booth_online_payment_tv:
//			Intent productIntent=new Intent(getApplicationContext(),ProductListActivity.class);
//			startActivity(productIntent);
			break;
		case R.id.booth_account_tv:
//			Intent productIntent=new Intent(getApplicationContext(),ProductListActivity.class);
//			startActivity(productIntent);
			break;
		case R.id.back_button_titlebar:
			finish();
			break;

			
		}
	}


}
