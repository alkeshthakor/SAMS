package com.alkesh.samudratech.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alkesh.samudratech.R;

public class ReportAdapter extends BaseAdapter {

	 private Context mContext;
	 private List<Report> mReportList;
	 LayoutInflater mLayoutInflater;
	 
	 public ReportAdapter(Context mContext,List<Report> mReportList){
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
			convertView=(View)mLayoutInflater.inflate(R.layout.layout_report_item,parent,false);     	
			mViewHolder=new ViewHolder();
			
			mViewHolder.dateTV=(TextView)convertView.findViewById(R.id.dateReportItemTextView);
			mViewHolder.shiftTV=(TextView)convertView.findViewById(R.id.shiftReportItemTextView);
					
			convertView.setTag(mViewHolder);
			convertView.setId(position);
			
			
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
		
		
        Report mReport=(Report)getItem(position);
        
		mViewHolder.dateTV.setText(mReport.getReportDate());
		if(mReport.getShift().equalsIgnoreCase("0")){
			mViewHolder.shiftTV.setText(mContext.getResources().getString(R.string.lbl_shift_morning));
		}else{
			mViewHolder.shiftTV.setText(mContext.getResources().getString(R.string.lbl_shift_night));

		}
		
		return convertView;
	}

	public class ViewHolder{
		TextView dateTV;
		TextView shiftTV;
	}
}
