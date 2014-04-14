package com.example.nvxiuwang.xlist;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class PageAdapter<T> extends BaseAdapter {
	protected List<T> objects = new ArrayList<T>();
	
	public void update(List<T> objects, boolean isAppend){
		if(!isAppend){
			this.objects.clear();
			this.objects.addAll(objects);
		}else{
			this.objects.addAll(objects);
		}
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objects.size();
	}

	@Override
	public Object getItem(int arg0) {
		return objects.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		return getItemView(position, convertView, parent);
	}
	
	public abstract View getItemView(int position, View convertView, ViewGroup parent);
	
}
