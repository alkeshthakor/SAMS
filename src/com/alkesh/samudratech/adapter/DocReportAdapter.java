package com.alkesh.samudratech.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alkesh.samudratech.R;

public class DocReportAdapter extends BaseAdapter {

	 private Context mContext;
	 private List<DocReport> docReportList;
	 LayoutInflater mLayoutInflater;
	 
	 public DocReportAdapter(Context mContext,List<DocReport> productList){
		 this.mContext=mContext;
		 this.docReportList=productList;		 
		 mLayoutInflater=LayoutInflater.from(mContext);
	 }
	 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return docReportList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return docReportList.get(position);
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
			convertView=(View)mLayoutInflater.inflate(R.layout.layout_doc_report,parent,false);     	
			mViewHolder=new ViewHolder();
			
			mViewHolder.docNoTV=(TextView)convertView.findViewById(R.id.docNoTextView);
			
			mViewHolder.cntCodeTV=(TextView)convertView.findViewById(R.id.cntcodeDocTextView);
			mViewHolder.shiftTV=(TextView)convertView.findViewById(R.id.shiftDocTextView);
			mViewHolder.dumpdateTV=(TextView)convertView.findViewById(R.id.dateDocTextView);
			
			mViewHolder.socCodeOldTV=(TextView)convertView.findViewById(R.id.socCodeOldDocTV);
			mViewHolder.socCodeNewTV=(TextView)convertView.findViewById(R.id.socCodeNewDocTV);
			
			mViewHolder.socNameOldTV=(TextView)convertView.findViewById(R.id.socNameOldDocTV);
			mViewHolder.socNameNewTV=(TextView)convertView.findViewById(R.id.socNameNewDocTV);
			
			mViewHolder.typeOldTV=(TextView)convertView.findViewById(R.id.typeOldDocTV);
			mViewHolder.typeNewTV=(TextView)convertView.findViewById(R.id.typeNewDocTV);
			mViewHolder.weightOldTV=(TextView)convertView.findViewById(R.id.weightOldDocTV);
			mViewHolder.weightNewTV=(TextView)convertView.findViewById(R.id.weightNewDocTV);
			mViewHolder.didOldTV=(TextView)convertView.findViewById(R.id.didOldDocTV);
			mViewHolder.didNewTV=(TextView)convertView.findViewById(R.id.didNewDocTV);
		
			convertView.setTag(mViewHolder);
			convertView.setId(position);
			
			
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
		
		DocReport mDocReport=(DocReport)getItem(position);
		
		mViewHolder.docNoTV.setText(mDocReport.getmDocNo());
		
		mViewHolder.cntCodeTV.setText(mDocReport.getmCntCode());
		mViewHolder.shiftTV.setText(mDocReport.getmShift());
		mViewHolder.dumpdateTV.setText(mDocReport.getmDumpDate());
		
		mViewHolder.socCodeOldTV.setText(mDocReport.getmSocCode());
		mViewHolder.socCodeNewTV.setText(mDocReport.getmNewSocCode());
		
		mViewHolder.socNameOldTV.setText(mDocReport.getmSocName());
		mViewHolder.socNameNewTV.setText(mDocReport.getmNewSocName());
		
		mViewHolder.typeOldTV.setText(mDocReport.getmType());
		mViewHolder.typeNewTV.setText(mDocReport.getmNewType());

		mViewHolder.weightOldTV.setText(mDocReport.getmWeight());
		mViewHolder.weightNewTV.setText(mDocReport.getmNewWeight());
		
		mViewHolder.didOldTV.setText(mDocReport.getmDid());
		mViewHolder.didNewTV.setText(mDocReport.getmNewDid());
		
		
		return convertView;
	}
	
	
	public class ViewHolder{
		
		TextView docNoTV;
		
		TextView cntCodeTV;
		TextView shiftTV;
		TextView dumpdateTV;
		
		TextView socCodeOldTV;
		TextView socCodeNewTV;
		TextView socNameOldTV;
		TextView socNameNewTV;
		TextView typeOldTV;
		TextView typeNewTV;
		TextView weightOldTV;
		TextView weightNewTV;
		TextView didOldTV;
		TextView didNewTV;
	}
	
	

}
