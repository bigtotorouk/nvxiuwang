package com.example.nvxiuwang;

import java.util.List;

import net.tsz.afinal.FinalDb;

import com.example.nvxiuwang.adapter.ImageModelAdapter;
import com.example.nvxiuwang.adapter.ImageModelGridAdapter;
import com.example.nvxiuwang.bean.ImageModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;


public class FavoriteFragment extends Fragment {
	private GridView mListView;
	private ImageModelGridAdapter pageAdapter;
	FinalDb db;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_favorite, container, false);
		pageAdapter = new ImageModelGridAdapter(FavoriteFragment.this.getActivity());
		mListView = (GridView)view.findViewById(R.id.pull_refresh_list);
		mListView.setAdapter(pageAdapter);
		loadData();
		return view;
	}
	
	public void loadData(){
		db = FinalDb.create(FavoriteFragment.this.getActivity());
		List<ImageModel> objects = db.findAll(ImageModel.class);
		pageAdapter.update(objects, false);
		
	}
	
}
