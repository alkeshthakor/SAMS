package com.alkesh.samudratech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.alkesh.samudratech.adapter.AdminMenuAdapter;

public class AdminActivity extends Activity implements OnClickListener{
	 String menuList[];
     ListView adminMenuListView;
	 private AdminMenuAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_admin);
		setTitleBar();
		
		menuList=getResources().getStringArray(R.array.admin_menu_array);
		adminMenuListView=(ListView)findViewById(R.id.admin_menu_listView);
		
		mAdapter=new AdminMenuAdapter(getApplicationContext(), menuList);
		adminMenuListView.setAdapter(mAdapter);
		
		adminMenuListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				if(menuList[position].equalsIgnoreCase("Master")){
					Intent masterIntent=new Intent(getApplicationContext(),MasterMenuActivity.class);
					startActivity(masterIntent);
				}else if(menuList[position].equalsIgnoreCase("Daily Activities")){
					Intent masterIntent=new Intent(getApplicationContext(),DailyActivity.class);
					startActivity(masterIntent);
				}else if(menuList[position].equalsIgnoreCase("User management")){
					
				}else if(menuList[position].equalsIgnoreCase("Dbf generation")){
					
				}else if(menuList[position].equalsIgnoreCase("Reports")){
					Intent masterIntent=new Intent(getApplicationContext(),AdminSubReportActivity.class);
					masterIntent.putExtra("report",menuList[position]);
					startActivity(masterIntent);	
				}else if(menuList[position].equalsIgnoreCase("Additional sales")){
					
				}else if(menuList[position].equalsIgnoreCase("Receipts")){
					
				}else if(menuList[position].equalsIgnoreCase("Maintenance")){
					
				}else if(menuList[position].equalsIgnoreCase("Deposit DR CR")){
					
				}
			}
		});
		
	}

	private void setTitleBar(){
		TextView titleBarTextView=(TextView)findViewById(R.id.textView_titlebar);
		titleBarTextView.setText(getResources().getString(R.string.lbl_admin));
		
		ImageButton backImageButton=(ImageButton)findViewById(R.id.back_button_titlebar);
		backImageButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.back_button_titlebar:
			finish();
			break;
		}
	}


}
