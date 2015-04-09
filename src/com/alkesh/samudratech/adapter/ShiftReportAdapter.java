package com.alkesh.samudratech.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alkesh.samudratech.R;

public class ShiftReportAdapter extends BaseAdapter {

	 private Context mContext;
	 private List<ShiftReport> mReportList;
	 LayoutInflater mLayoutInflater;
	 
	 public ShiftReportAdapter(Context mContext,List<ShiftReport> mReportList){
		 this.mContext=mContext;
		 this.mReportList=mReportList;		 
		 mLayoutInflater=LayoutInflater.from(mContext);
	 }
	 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mReportList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mReportList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder mViewHolder;
		
		if(convertView == null){
			convertView=(View)mLayoutInflater.inflate(R.layout.layout_shift_report_item,parent,false);     	
			mViewHolder=new ViewHolder();
			
			mViewHolder.productIdTV=(TextView)convertView.findViewById(R.id.productIdShiftReportItemTextView);
			mViewHolder.quantityTV=(TextView)convertView.findViewById(R.id.quantityShiftReportItemTextView);
					
			convertView.setTag(mViewHolder);
			convertView.setId(position);
			
			
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
		
		
        ShiftReport mReport=(ShiftReport)getItem(position);
        
		mViewHolder.productIdTV.setText(mReport.getItemId());
		mViewHolder.quantityTV.setText(mReport.getQuantity());
		
		return convertView;
	}

	public class ViewHolder{
		TextView productIdTV;
		TextView quantityTV;
	}
}
