package com.example.nvxiuwang;

import java.util.Map;

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

/**
 * 图片搜索结果页面
 * @author bing
 *
 */
public class SearchImageFragment extends PageListFragment<ImageModel> {
	
	private String type = ""; // 1 图片， 2 美图
	public String content = "";
	
	@Override
	public void initParams() {
		// TODO Auto-generated method stub
		url = Constants.SERVICE_URL+"api/main/search.html";
		_L_PAGE = "renum";
		_L_PAGE_SIZE = "num";
		pageSize = 20;
		
		_pageAdapter = new ImageModelAdapter(SearchImageFragment.this.getActivity());
	}
	
	@Override
	public void updateEnv(Map<String, Object> paramMaps) {
		paramMaps.put("keyword", content);
		paramMaps.put("type", type);
		super.updateEnv(paramMaps);
	}
	
	public void search(String type, String conent){
		this.type = type;
		this.content = conent;
		load(true, false);
	}

	@Override
	public View onCreatePageView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_image_search, container, false);
		return view;
	}

	@Override
	public BasicPageResponse<ImageModel> parseResponse(String content) {
		Gson gson = new Gson();
		return gson.fromJson(content, ImageModelResponse.class);
	}
}