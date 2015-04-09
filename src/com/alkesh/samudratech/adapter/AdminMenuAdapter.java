package com.alkesh.samudratech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alkesh.samudratech.R;

public class AdminMenuAdapter extends BaseAdapter {

	 private Context mContext;
	 private String[] menuList;
	 
	 LayoutInflater mLayoutInflater;
	 
	 private int[] colors = new int[] { 0x30FF0000, 0x300000FF };
	 
	 public AdminMenuAdapter(Context mContext,String[] menuList){
		 this.mContext=mContext;
		 this.menuList=menuList;		 
		 mLayoutInflater=LayoutInflater.from(mContext);
	 }
	 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return menuList.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return menuList[position];
	}
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder mViewHolder;
		if(convertView == null){
			convertView=(View)mLayoutInflater.inflate(R.layout.layout_admin_menu,parent,false);     	
			mViewHolder=new ViewHolder();
			mViewHolder.menuTV=(TextView)convertView.findViewById(R.id.admin_menu_item_tv);
			
			convertView.setTag(mViewHolder);
			convertView.setId(position);
			
			 int colorPos = position % colors.length;
			 convertView.setBackgroundColor(colors[colorPos]);
		      
		
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
		
		mViewHolder.menuTV.setText(getItem(position).toString());
		
		return convertView;
	}
	
   public class ViewHolder{
		TextView menuTV;
	}

}
