package com.example.nvxiuwang;

import java.util.Map;

import com.example.nvxiuwang.adapter.UserModelAdapter;
import com.example.nvxiuwang.bean.UserModel;
import com.example.nvxiuwang.bean.UserModelResponse;
import com.example.nvxiuwang.xlist.BasicPageResponse;
import com.example.nvxiuwang.xlist.PageGridFragment;
import com.google.gson.Gson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class GroupFragment extends PageGridFragment<UserModel> {
	
	private EditText editContent;
	private Button btnSearch;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_search, container, false);
		
		editContent = (EditText)view.findViewById(R.id.content);
		btnSearch = (Button)view.findViewById(R.id.search);
		btnSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				load(true, false);
			}
		});
		return view;
	}

	@Override
	public void initParams() {
		// TODO Auto-generated method stub
		url = "http://52pic.com/api/man/ulist";
		_L_PAGE = "renum";
		_L_PAGE_SIZE = "num";
		pageSize = 20;
		
		_pageAdapter = new UserModelAdapter(GroupFragment.this.getActivity());
	}
	
	@Override
	public void updateEnv(Map<String, Object> paramMaps) {
		String content = editContent.getText().toString().trim();
		paramMaps.put("key", content);
		super.updateEnv(paramMaps);
	}

	@Override
	public View onCreatePageView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_search, container, false);
		return view;
	}

	@Override
	public BasicPageResponse<UserModel> parseResponse(String content) {
		Gson gson = new Gson();
		return gson.fromJson(content, UserModelResponse.class);
	}
}