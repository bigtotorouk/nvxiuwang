package com.example.nvxiuwang;

import java.util.ArrayList;

import com.totoro.freelancer.sdk.view.CustomViewPager;
import com.totoro.freelancer.sdk.view.PopSpinner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchManagerFragment extends Fragment{
	private ArrayList<Fragment> mList = new ArrayList<Fragment>();
	private CustomViewPager viewPager;
	private SearchPagerAdapter mAdapter;
	
	private EditText editContent;
	private Button btnSearch;
	private PopSpinner popSpinner;
	
	private static int position = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_search_manager, container, false);
		viewPager = (CustomViewPager)view.findViewById(R.id.view_pager);
		editContent = (EditText)view.findViewById(R.id.content);
		btnSearch = (Button)view.findViewById(R.id.search);
		btnSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String content = editContent.getText().toString().trim();
				if(TextUtils.isEmpty(content)){
					Toast.makeText(getActivity(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
				}else{
					invokeSearch(content);
				}
			}
		});
		TextView typeView = (TextView)view.findViewById(R.id.type);
		popSpinner = new PopSpinner(getActivity(), typeView);
		
		
		mList.add(new SearchUserFragment());
		mList.add(new SearchImageFragment());
		mAdapter = new SearchPagerAdapter(getChildFragmentManager());
		viewPager.setAdapter(mAdapter);
		viewPager.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				toggle(0);
			}
		}, 100);
		return view;
	}
	
	private void invokeSearch(String content){
		if(popSpinner.type.equals("0")){
			if(position != 0){
				toggle(0);
			}
		}else{
			if(position != 1){
				toggle(1);
			}
		}
		if(position == 0){
			SearchUserFragment userFrag = (SearchUserFragment)mList.get(position);
			userFrag.search(content);
		}else if(position == 1){
			SearchImageFragment imageFrag = (SearchImageFragment)mList.get(position);
			imageFrag.search(popSpinner.type, content);
		}
	}
	
	private void toggle(int position) {
		this.position = position;
		viewPager.setCurrentItem(position, false);
	}
	
	
	
	class SearchPagerAdapter extends FragmentPagerAdapter {

		FragmentManager fm;

		public SearchPagerAdapter(FragmentManager fragmentmanager) {
			super(fragmentmanager);
			fm = fragmentmanager;
		}

		@Override
		public int getCount() {
			return mList == null ? 0 : mList.size();
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = (mList == null || mList.size() == 0) ? null
					: mList.get(position);
			return fragment;
		}

	}
}
