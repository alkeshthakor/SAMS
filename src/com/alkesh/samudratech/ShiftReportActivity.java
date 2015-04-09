package com.alkesh.samudratech;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.alkesh.samudratech.adapter.AdminMenuAdapter;

public class ShiftReportActivity extends Activity implements OnClickListener{

	private String menuList[];
    private ListView shiftMenuListView;
	private AdminMenuAdapter mAdapter;
	private String reportName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_shift_report);
	    if(getIntent()!=null)
	    	reportName=getIntent().getStringExtra("report");
	    
		setTitleBar();
		
		menuList=getResources().getStringArray(R.array.shift_report_menu_array);
		shiftMenuListView=(ListView)findViewById(R.id.shift_menu_listView);
		
		mAdapter=new AdminMenuAdapter(getApplicationContext(), menuList);
		shiftMenuListView.setAdapter(mAdapter);
	}

	private void setTitleBar(){
		TextView titleBarTextView=(TextView)findViewById(R.id.textView_titlebar);
		titleBarTextView.setText(reportName);
		
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
