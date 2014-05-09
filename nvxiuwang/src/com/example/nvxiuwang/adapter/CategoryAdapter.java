package com.example.nvxiuwang.adapter;

import java.util.List;

import com.example.nvxiuwang.HotFragment;
import com.example.nvxiuwang.R;
import com.example.nvxiuwang.bean.CategoryModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
	private HotFragment context;
	private List<CategoryModel> categories;
	public CategoryAdapter(HotFragment context, List<CategoryModel> categories){
		this.context = context;
		this.categories = categories;
	}
	
	public void update(List<CategoryModel> categories){
		this.categories = categories;
		notifyDataSetInvalidated();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categories.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		LayoutInflater inflate = (LayoutInflater)context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflate.inflate(R.layout.item_category , null);
		TextView btn = (TextView)convertView.findViewById(R.id.category);
		btn.setText(categories.get(position).getName());
		
		return convertView;
	}

}
