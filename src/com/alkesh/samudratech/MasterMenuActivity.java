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

public class MasterMenuActivity extends Activity implements OnClickListener {
	 String menuList[];
     ListView masterMenuListView;
	 private AdminMenuAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_master_menu);
		
		setTitleBar();
		
		menuList=getResources().getStringArray(R.array.master_menu_sub_array);
		masterMenuListView=(ListView)findViewById(R.id.master_menu_listView);
		
		mAdapter=new AdminMenuAdapter(getApplicationContext(), menuList);
		masterMenuListView.setAdapter(mAdapter);
		
		
	}

	private void setTitleBar(){
		TextView titleBarTextView=(TextView)findViewById(R.id.textView_titlebar);
		titleBarTextView.setText(getResources().getString(R.string.lbl_master));
		
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
