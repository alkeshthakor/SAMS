package com.alkesh.samudratech.adapter;

import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.alkesh.samudratech.R;

public class ProductListAdapter extends BaseAdapter {

	 private Context mContext;
	 private List<Product> productList;
	 LayoutInflater mLayoutInflater;
	 public int[] unitData;
	 
	 public ProductListAdapter(Context mContext,List<Product> productList){
		 this.mContext=mContext;
		 this.productList=productList;
		 this.unitData=new int[productList.size()];
		 
		 mLayoutInflater=LayoutInflater.from(mContext);
		 
	 }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		for(int i=0;i<productList.size();i++){
			unitData[i]=productList.get(i).unit;
		}
		return productList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return productList.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder mViewHolder;
		
		if(convertView == null){
			convertView=(View)mLayoutInflater.inflate(R.layout.layout_product_item,parent,false);     	
			mViewHolder=new ViewHolder();
			//mViewHolder.productCodeTV=(TextView)convertView.findViewById(R.id.productCodeTextView);
			//mViewHolder.productRateTV=(TextView)convertView.findViewById(R.id.productRateTextView);
			mViewHolder.productNameTV=(TextView)convertView.findViewById(R.id.productNameTextView);
			//mViewHolder.totalTV=(TextView)convertView.findViewById(R.id.TotalTextView);
			mViewHolder.quantityET=(EditText)convertView.findViewById(R.id.quantityEditText);
			mViewHolder.quantityET.setId(position);
			
			mViewHolder.quantityET.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					String unitText=mViewHolder.quantityET.getText().toString();
					if(!"".equals(unitText)){
						int unit=Integer.parseInt(unitText);
						//float amount=unit*productList.get(position).quantityNo*productList.get(position).rate;
						//mViewHolder.totalTV.setText(amount+"");
						productList.get(position).setUnit(unit);
						unitData[position]=unit;
					}
				}
			});
			
			convertView.setTag(mViewHolder);
			convertView.setId(position);
			
			
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
		
		Product mProduct=(Product)getItem(position);
		
		Log.d("Data: ","Position: "+position+"Unit: "+mProduct.unit+" View Position"+convertView.getId());
		
		//float total=mProduct.quantityNo*mProduct.rate*mProduct.unit;
		
		//mViewHolder.productCodeTV.setText(mProduct.productId+"");
		//mViewHolder.productRateTV.setText(mProduct.rate+"");
		mViewHolder.productNameTV.setText(mProduct.productName);
		mViewHolder.quantityET.setText(unitData[position]+"");
		
		//mViewHolder.totalTV.setText(total+"");
		
		return convertView;
	}

	public class ViewHolder{
		//TextView productCodeTV;
		//TextView productRateTV;
		TextView productNameTV;
		//TextView totalTV;
		
		EditText quantityET;
	}
	
}
