package com.alkesh.samudratech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.alkesh.samudratech.adapter.AdminMenuAdapter;

public class DailyActivity extends Activity implements OnClickListener{

	String menuList[];
    ListView dailyMenuListView;
	private AdminMenuAdapter mAdapter;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_daily);
		
		setTitleBar();
		
		menuList=getResources().getStringArray(R.array.daily_menu_array);
		dailyMenuListView=(ListView)findViewById(R.id.daily_menu_listView);
		
		mAdapter=new AdminMenuAdapter(getApplicationContext(), menuList);
		dailyMenuListView.setAdapter(mAdapter);
		
		
		dailyMenuListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				if(menuList[position].equalsIgnoreCase("Morning Report")){
					Intent masterIntent=new Intent(getApplicationContext(),ShiftReportActivity.class);
					masterIntent.putExtra("report",menuList[position]);
					startActivity(masterIntent);
					
				}else if(menuList[position].equalsIgnoreCase("Evening Report")){
					Intent masterIntent=new Intent(getApplicationContext(),ShiftReportActivity.class);
					masterIntent.putExtra("report",menuList[position]);
					startActivity(masterIntent);
				}
			}
		});
	}

	private void setTitleBar(){
		TextView titleBarTextView=(TextView)findViewById(R.id.textView_titlebar);
		titleBarTextView.setText(getResources().getString(R.string.lbl_daily_activity));
		
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
