package com.alkesh.samudratech.adapter;

import java.util.List;

import com.alkesh.samudratech.R;
import com.alkesh.samudratech.adapter.DocReportAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LabReportAdapter extends BaseAdapter {

	 private Context mContext;
	 private List<LabReport> labReportList;
	 LayoutInflater mLayoutInflater;
	 
	 public LabReportAdapter(Context mContext,List<LabReport> labReportList){
		 this.mContext=mContext;
		 this.labReportList=labReportList;		 
		 mLayoutInflater=LayoutInflater.from(mContext);
	 }
	 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return labReportList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return labReportList.get(position);
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
			convertView=(View)mLayoutInflater.inflate(R.layout.layout_lab_report,parent,false);     	
			mViewHolder=new ViewHolder();
			
			mViewHolder.cntCodeTV=(TextView)convertView.findViewById(R.id.cntcodeLabTextView);
			mViewHolder.shiftTV=(TextView)convertView.findViewById(R.id.shiftLabTextView);
			mViewHolder.dumpdateTV=(TextView)convertView.findViewById(R.id.dateLabTextView);
			
			mViewHolder.socCodeTV=(TextView)convertView.findViewById(R.id.socCodeLabTextView);
			mViewHolder.socNameTV=(TextView)convertView.findViewById(R.id.socNameLabTextView);

			mViewHolder.ftNewTV=(TextView)convertView.findViewById(R.id.fatNewLabTV);
			mViewHolder.ftOldTV=(TextView)convertView.findViewById(R.id.fatOldLabTV);
			
			mViewHolder.lrNewTV=(TextView)convertView.findViewById(R.id.lrNewLabTV);
			mViewHolder.lrOldTV=(TextView)convertView.findViewById(R.id.lrOldLabTV);
			
			mViewHolder.snfNewTV=(TextView)convertView.findViewById(R.id.snfNewLabTV);
			mViewHolder.snfOldTV=(TextView)convertView.findViewById(R.id.snfOldLabTV);
		
			convertView.setTag(mViewHolder);
			convertView.setId(position);
			
			
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
		
		LabReport mLabReport=(LabReport)getItem(position);
		
		mViewHolder.cntCodeTV.setText(mLabReport.getmCntCode());
		mViewHolder.shiftTV.setText(mLabReport.getmShift());
		mViewHolder.dumpdateTV.setText(mLabReport.getmDumpDate());
		
		mViewHolder.socCodeTV.setText(mLabReport.getmSoCode());
		mViewHolder.socNameTV.setText(mLabReport.getmSocName());
		
		mViewHolder.ftOldTV.setText(mLabReport.getmFat());
		mViewHolder.ftNewTV.setText(mLabReport.getmNewFat());
		
		mViewHolder.lrOldTV.setText(mLabReport.getmLr());
		mViewHolder.lrNewTV.setText(mLabReport.getmNewLr());

		mViewHolder.snfOldTV.setText(mLabReport.getmSnf());
		mViewHolder.snfNewTV.setText(mLabReport.getmNewSnf());
		
		return convertView;
	}

	public class ViewHolder{
		
		TextView cntCodeTV;
		TextView shiftTV;
		TextView dumpdateTV;
		
		TextView socCodeTV;
		TextView socNameTV;
		
		TextView ftOldTV;
		TextView ftNewTV;
		TextView lrOldTV;
		TextView lrNewTV;
		TextView snfOldTV;
		TextView snfNewTV;
		
		
//		TextView cntCodeTV;
//		TextView shiftTV;
//		TextView dumpdateTV;
//		
//		TextView socCodeOldTV;
//		TextView socCodeNewTV;
//		TextView socNameOldTV;
//		TextView socNameNewTV;
//		TextView typeOldTV;
//		TextView typeNewTV;
//		TextView weightOldTV;
//		TextView weightNewTV;
//		TextView didOldTV;
//		TextView didNewTV;
	}
}
