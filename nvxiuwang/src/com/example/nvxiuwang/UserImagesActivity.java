package com.example.nvxiuwang;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.nvxiuwang.adapter.ImageModelAdapter;
import com.example.nvxiuwang.bean.ImageModel;
import com.example.nvxiuwang.bean.ImageModelResponse;
import com.example.nvxiuwang.xlist.BasicPageResponse;
import com.example.nvxiuwang.xlist.PageListActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 某个用户分享的图片列表
 * @author bing
 *
 */
public class UserImagesActivity extends PageListActivity<ImageModel> {
	private String uid = "";
	private View btnFavor;
	
	@Override
	public void initParams() {
		url = Constants.SERVICE_URL+"api/user/userpic";
		_L_PAGE = "renum";
		_L_PAGE_SIZE = "num";
		pageSize = 10;
		
		_pageAdapter = new ImageModelAdapter(UserImagesActivity.this);
	}
	
	@Override
	public void updateEnv(Map<String, Object> paramMaps) {
		paramMaps.put("uid", uid);
		super.updateEnv(paramMaps);
	}

	@Override
	public void onCreatePageView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_user_image);
		uid =  getIntent().getStringExtra("uid");
		String name =  getIntent().getStringExtra("name");
		((TextView)findViewById(R.id.title)).setText(name+"的分享");
		btnFavor = findViewById(R.id.favor);
		btnFavor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(Constants.USER_ID.equals("")){
					Toast.makeText(UserImagesActivity.this, "收藏用户必须先登录", Toast.LENGTH_SHORT).show();
					return;
				}
				AsyncHttpClient client = new AsyncHttpClient();
				String url = Constants.SERVICE_URL+"api/user/collectuser"+"?uid="+uid+"&sid="+Constants.USER_ID;
				client.get(url, new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(String content) {
						try {
							JSONObject object = new JSONObject(content);
							if(object.getString("state").equals("0")){
								
								if(btnFavor.isSelected()){
									Toast.makeText(UserImagesActivity.this, "取消收藏该成功", Toast.LENGTH_SHORT).show();	
								}else{
									Toast.makeText(UserImagesActivity.this, "收藏该用户成功", Toast.LENGTH_SHORT).show();
								}
								btnFavor.setSelected(!btnFavor.isSelected());
								//UserImagesActivity.this.finish();
							}else{
								Toast.makeText(UserImagesActivity.this,object.getString("msg") , Toast.LENGTH_SHORT).show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		});
		updateData();
	}
	
	private void updateData(){
		AsyncHttpClient client = new AsyncHttpClient();
		String url = Constants.SERVICE_URL+"api/user/checkcollect"+"?uid="+uid+"&sid="+Constants.USER_ID;
		client.get(url, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				Log.d(Constants.LOG, "checkcollect "+content);
				try {
					JSONObject object = new JSONObject(content);
					if(object.getString("state").equals("0")){
						if(object.getString("data").equals("0")){
							btnFavor.setSelected(false);	
						}else{
							btnFavor.setSelected(true);	
						}
					}else{
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		load(true, false);
		super.onResume();
	}

	@Override
	public BasicPageResponse<ImageModel> parseResponse(String content) {
		Gson gson = new Gson();
		return gson.fromJson(content, ImageModelResponse.class);
	}
	
}
