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


public class FavoriteFragment extends PageListFragment<ImageModel> {

	@Override
	public void initParams(){
		url = "http://52pic.com/api/man/new";
		_L_PAGE = "renum";
		_L_PAGE_SIZE = "num";
		pageSize = 10;
		
		_pageAdapter = new ImageModelAdapter(FavoriteFragment.this.getActivity());
	}
	
	@Override
	public View onCreatePageView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_favorite, container, false);
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
