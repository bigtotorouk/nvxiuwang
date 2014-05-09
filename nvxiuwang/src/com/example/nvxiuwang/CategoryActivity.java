package com.example.nvxiuwang;

import android.os.Bundle;

import com.example.nvxiuwang.bean.ImageModel;
import com.example.nvxiuwang.xlist.PageListActivity_back;

public class CategoryActivity extends PageListActivity_back<ImageModel> {

	@Override
	public void onCreateView(Bundle savedInstanceState) {
		setContentView(R.id.pull_refresh_list);
	}

}
