package com.alkesh.samudratech;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alkesh.samudratech.util.ConnectionDetector;
import com.alkesh.samudratech.util.Constant;
import com.alkesh.samudratech.util.Pref;
import com.alkesh.samudratech.util.ServerConnector;

public class LoginActivity extends Activity  {

	EditText userIdEditText;
	EditText passwordEditText;
	
	TextView loginTextView;
	
	String userIdList[]=new String[]{"1234","5678","9999","1111"};

	private ProgressDialog mProgressDialog;
    ConnectionDetector cd;
    ServerConnector connector;
    Context mContext;
    Resources rs;
    
    private CheckBox rememberMeCheckBox;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mContext=this;
		Constant.CONTEXT=this;
		
		cd=new ConnectionDetector(mContext);
		connector=new ServerConnector();
		rs=getResources();
		mProgressDialog=new ProgressDialog(LoginActivity.this);
		mProgressDialog.setMessage("Please wait...");
		mProgressDialog.setIndeterminate(false);
		
		
		userIdEditText=(EditText)findViewById(R.id.editText_userid);
		passwordEditText=(EditText)findViewById(R.id.editText_password);
	    
		loginTextView=(TextView)findViewById(R.id.login_textView);
		
		rememberMeCheckBox=(CheckBox)findViewById(R.id.rememberMeCheckBox);

		rememberMeCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				rememberMe();
			}
		});
		
		if(!Pref.getValue(Constant.PREF_USER_ID,"").equals("")){
			userIdEditText.setText(Pref.getValue(Constant.PREF_USER_ID,""));
			rememberMeCheckBox.setChecked(true);
		}else{
			rememberMeCheckBox.setChecked(false);
		}
		
		setTitleBar();
		
		loginTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if(userIdEditText.getText().length()>0&&passwordEditText.getText().length()>0){
					
					 if(cd.isConnectingToInternet()){
							String userID=userIdEditText.getText().toString();
							userID.trim();
							String password=passwordEditText.getText().toString();
							String urlString=Constant.HOST+Constant.LOGIN_SERVICE+"mobileno="+userID.trim()+"&password="+password.trim()+"&loginType=1";
							new UserLoginTask().execute(urlString);
							
						  }else{
							  Toast.makeText(mContext,rs.getString(R.string.net_not_available),Toast.LENGTH_SHORT).show();
							  return;
						  }
				}else{
					Toast.makeText(getApplicationContext(),"Please enter userid or password",Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
	
	private void setTitleBar(){
		//TextView titleBarTextView=(TextView)findViewById(R.id.textView_titlebar);
		ImageButton backImageButton=(ImageButton)findViewById(R.id.back_button_titlebar);
		backImageButton.setVisibility(View.INVISIBLE);
	}
	   private void rememberMe(){
		   if(rememberMeCheckBox.isChecked()){
			  Pref.setValue(Constant.PREF_USER_ID, userIdEditText.getText().toString());
			  Pref.setValue(Constant.PREF_PASSWORD, passwordEditText.getText().toString());
			}else{
				 Pref.setValue(Constant.PREF_USER_ID,"");
				 Pref.setValue(Constant.PREF_PASSWORD,"");
			}
	   }

	   
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		userIdEditText.setText("9909431965");
		passwordEditText.setText("dp1");

		
	}
	
	class UserLoginTask extends AsyncTask<String,Void,JSONObject>{
        @Override
        protected void onPreExecute() {
        	super.onPreExecute();
        	mProgressDialog.show();
        }
		@Override
		protected JSONObject doInBackground(String... param) {
			return connector.getServerResponse(param[0]);
			//return connector.getProductList(param[0]);
			
		}
		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			
			if(result!=null){
				try {
					Log.d("Data",result.toString());
					if(result.has("action")&&result.getBoolean("status")==true){
						try {
							JSONObject values=result.getJSONObject("values");
							if(values!=null){
								int boothid=values.getInt("boothId");
								Pref.setValue(Constant.PREF_BOOTH_ID,""+boothid);
								Pref.setValue(Constant.PREF_BOOTH_NAME,values.getString("boothName"));
								Pref.setValue(Constant.PREF_BOOTH_BALANCE,values.getString("openingDepo"));
								Intent homeIntent=new Intent(getApplicationContext(),HomeActivity.class);
								startActivity(homeIntent);	
							}
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
