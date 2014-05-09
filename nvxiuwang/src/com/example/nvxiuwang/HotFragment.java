package com.example.nvxiuwang;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.nvxiuwang.adapter.CategoryAdapter;
import com.example.nvxiuwang.adapter.ImageModelAdapter;
import com.example.nvxiuwang.bean.CategoryModel;
import com.example.nvxiuwang.bean.CategoryModelResponse;
import com.example.nvxiuwang.bean.ImageModel;
import com.example.nvxiuwang.bean.ImageModelResponse;
import com.example.nvxiuwang.xlist.BasicPageResponse;
import com.example.nvxiuwang.xlist.PageListFragment;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


public class HotFragment extends PageListFragment<ImageModel> implements OnClickListener {
	private GridView gridView;
	private CategoryAdapter categoryAdapter;
	private List<CategoryModel> categorList = new ArrayList<CategoryModel>();
	private Button tab1, tab2;
	private TextView hot1, hot2, category1, category2;
	
	int category = 0;// 0 代表hot累， 1代表非hot累
	private String type = "";
	private final String category_url1 = Constants.SERVICE_URL+"api/man/gettype";
	private final String category_url2 = Constants.SERVICE_URL+"api/meitu/gettype";
	private String category_url = category_url1;
	private final String url1 = Constants.SERVICE_URL+"api/man/searchpic";
	private final String url2 = Constants.SERVICE_URL+"api/meitu/meitu";
	
	@Override
	public void initParams(){
		url = Constants.SERVICE_URL+"api/man/new";
		_L_PAGE = "renum";
		_L_PAGE_SIZE = "num";
		pageSize = 10;
		
		_pageAdapter = new ImageModelAdapter(HotFragment.this.getActivity());
	}
	
	@Override
	public void updateEnv(Map<String, Object> paramMaps) {
		if(category == 0){
			if(paramMaps.containsKey("type")){
				paramMaps.remove("type");
			}
		}else if(category == 1){
			paramMaps.put("type", type);
		}
		
		super.updateEnv(paramMaps);
	}
	
	@Override
	public View onCreatePageView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_hot, container, false);
		tab1 =  (Button)view.findViewById(R.id.tab1);
		tab2 =  (Button)view.findViewById(R.id.tab2);
		tab1.setOnClickListener(this); 	tab2.setOnClickListener(this);
		hot1 = (TextView)view.findViewById(R.id.hot1);
		hot2 = (TextView)view.findViewById(R.id.hot2);
		category1 = (TextView)view.findViewById(R.id.category1);
		category2 = (TextView)view.findViewById(R.id.category2);
		hot1.setOnClickListener(this);	category1.setOnClickListener(this);
		hot2.setOnClickListener(this);	category2.setOnClickListener(this);
		
		gridView = (GridView)view.findViewById(R.id.gridview);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				CategoryModel model = categorList.get(arg2);
				type = model.getId();
				gridView.setVisibility(View.GONE);
				mPullRefreshListView.setVisibility(View.VISIBLE);
				category = 1;
				load(true, false);
			}
		});
		categoryAdapter = new CategoryAdapter(this, categorList);
		gridView.setAdapter(categoryAdapter);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// 默认tab1
		tab1.setSelected(true);
		hot1.setSelected(false);
		category1.setSelected(false);
		tab2.setSelected(false);
		hot2.setSelected(false);
		category2.setSelected(false);
		gridView.setVisibility(View.GONE);
		mPullRefreshListView.setVisibility(View.VISIBLE);
		url = Constants.SERVICE_URL+"api/man/new";
		category = 0;
		load(true, false);
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public BasicPageResponse<ImageModel> parseResponse(String content) {
		Gson gson = new Gson();
		return gson.fromJson(content, ImageModelResponse.class);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.tab1:
			tab1.setSelected(true);
			hot1.setSelected(false);
			category1.setSelected(false);
			tab2.setSelected(false);
			hot2.setSelected(false);
			category2.setSelected(false);
			gridView.setVisibility(View.GONE);
			mPullRefreshListView.setVisibility(View.VISIBLE);
			url = Constants.SERVICE_URL+"api/man/new";
			category = 0;
			load(true, false);
			break;
		case R.id.tab2:
			tab1.setSelected(false);
			hot1.setSelected(false);
			category1.setSelected(false);
			tab2.setSelected(true);
			hot2.setSelected(false);
			category2.setSelected(false);
			gridView.setVisibility(View.GONE);
			mPullRefreshListView.setVisibility(View.VISIBLE);
			url = Constants.SERVICE_URL+"api/meitu/new";
			category = 0;
			load(true, false);
			break;
		case R.id.hot1:
			tab1.setSelected(false);
			hot1.setSelected(true);
			category1.setSelected(false);
			tab2.setSelected(false);
			hot2.setSelected(false);
			category2.setSelected(false);
			gridView.setVisibility(View.GONE);
			mPullRefreshListView.setVisibility(View.VISIBLE);
			url = Constants.SERVICE_URL+"api/man/hot";
			category = 0;
			load(true, false);
			
			break;
		case R.id.hot2:
			tab1.setSelected(false);
			hot1.setSelected(false);
			category1.setSelected(false);
			tab2.setSelected(false);
			hot2.setSelected(true);
			category2.setSelected(false);
			gridView.setVisibility(View.GONE);
			mPullRefreshListView.setVisibility(View.VISIBLE);
			url = Constants.SERVICE_URL+"api/meitu/hot";
			category = 0;
			load(true, false);
			break;
		case R.id.category1:
			tab1.setSelected(false);
			hot1.setSelected(false);
			category1.setSelected(true);
			tab2.setSelected(false);
			hot2.setSelected(false);
			category2.setSelected(false);
			gridView.setVisibility(View.VISIBLE);
			mPullRefreshListView.setVisibility(View.GONE);
			category_url = category_url1;
			url = url1;
			category = 1;
			loadCategory();
			break;
		case R.id.category2:
			tab1.setSelected(false);
			hot1.setSelected(false);
			category1.setSelected(false);
			tab2.setSelected(false);
			hot2.setSelected(false);
			category2.setSelected(true);
			gridView.setVisibility(View.VISIBLE);
			mPullRefreshListView.setVisibility(View.GONE);
			category_url = category_url2;
			url = url2;
			category = 1;
			loadCategory();
			break;
		default:
			break;
		}
		
	}
	
	private void loadCategory(){
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(category_url, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				Gson gson = new Gson();
				CategoryModelResponse response = gson.fromJson(content, CategoryModelResponse.class);
				if(response!=null&&response.getState().equals("0")){
					categorList = response.getData();
					categoryAdapter.update(categorList);
				}
				
			}
		});
	}
	
	
}
