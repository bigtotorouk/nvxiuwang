package com.example.nvxiuwang;

import com.example.nvxiuwang.adapter.ImageModelAdapter;
import com.example.nvxiuwang.bean.ImageModel;
import com.example.nvxiuwang.bean.ImageModelResponse;
import com.example.nvxiuwang.xlist.BasicPageResponse;
import com.example.nvxiuwang.xlist.PageListFragment;
import com.google.gson.Gson;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HotFragment extends PageListFragment<ImageModel> {

	private Button tab1, tab2;
	
	@Override
	public void initParams(){
		url = "http://52pic.com/api/man/new";
		_L_PAGE = "renum";
		_L_PAGE_SIZE = "num";
		pageSize = 10;
		
		_pageAdapter = new ImageModelAdapter(HotFragment.this.getActivity());
	}
	
	@Override
	public View onCreatePageView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_hot, container, false);
		tab1 =  (Button)view.findViewById(R.id.tab1);
		tab2 =  (Button)view.findViewById(R.id.tab2);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		load(true, false);
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public BasicPageResponse<ImageModel> parseResponse(String content) {
		Gson gson = new Gson();
		return gson.fromJson(content, ImageModelResponse.class);
	}
	
	
}
