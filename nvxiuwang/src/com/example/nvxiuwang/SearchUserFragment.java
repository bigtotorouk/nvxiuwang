package com.example.nvxiuwang;

import java.util.Map;

import com.example.nvxiuwang.adapter.UserModelAdapter;
import com.example.nvxiuwang.bean.UserModel;
import com.example.nvxiuwang.bean.UserModelResponse;
import com.example.nvxiuwang.xlist.BasicPageResponse;
import com.example.nvxiuwang.xlist.PageGridFragment;
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
public class SearchUserFragment extends PageGridFragment<UserModel> {
	
	public String content = "";
	
	@Override
	public void initParams() {
		// TODO Auto-generated method stub
		url = Constants.SERVICE_URL+"api/main/search.html";
		_L_PAGE = "renum";
		_L_PAGE_SIZE = "num";
		pageSize = 20;
		
		_pageAdapter = new UserModelAdapter(SearchUserFragment.this.getActivity());
	}
	
	@Override
	public void updateEnv(Map<String, Object> paramMaps) {
		paramMaps.put("keyword", content);
		paramMaps.put("type", "1");
		super.updateEnv(paramMaps);
	}
	
	public void search(String content){
		this.content = content;
		load(true, false);
	}

	@Override
	public View onCreatePageView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_user_search, container, false);
		return view;
	}

	@Override
	public BasicPageResponse<UserModel> parseResponse(String content) {
		Gson gson = new Gson();
		return gson.fromJson(content, UserModelResponse.class);
	}
}