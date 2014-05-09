package com.example.nvxiuwang;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import net.tsz.afinal.FinalDb;

import com.example.nvxiuwang.adapter.ImageModelAdapter;
import com.example.nvxiuwang.bean.ImageModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.totoro.freelancer.sdk.util.DownloadTask;
import com.totoro.freelancer.sdk.view.LinkageImageView;
import com.totoro.freelancer.sdk.view.TouchImageView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class DisplayImageActivity extends Activity implements OnClickListener {
	private ImageModel model;
	private TouchImageView imageView;
	private LinkageImageView linkageImageView;
	private View btnLike;
	private FinalDb db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.image_display);
		super.onCreate(savedInstanceState);
		model = (ImageModel) getIntent().getSerializableExtra("model");
		imageView = (TouchImageView)this.findViewById(R.id.img);
		findViewById(R.id.delete).setOnClickListener(this);
		findViewById(R.id.favor).setOnClickListener(this);
		findViewById(R.id.save).setOnClickListener(this);
		//imageView.setOnClickListener(this);
		btnLike = findViewById(R.id.like);
		btnLike.setOnClickListener(this);
		
		db = FinalDb.create(this);
		initData();
		linkageImageView = new LinkageImageView();
		linkageImageView.setUrl(imageView, Constants.IMAGE_BIG+model.getBig_pic());
		
		
	}
	
	public void initData(){
		
		List<ImageModel> objects = db.findAllByWhere(ImageModel.class, " id=\"" + model.getId() + "\"");
		if(objects != null && objects.size()>0){
			findViewById(R.id.favor).setSelected(true);
		}else{
			findViewById(R.id.favor).setSelected(false);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.delete:
		case R.id.img:
			finish();
			break;
		case R.id.like:
			if(Constants.USER_ID.equals("")){
				Toast.makeText(DisplayImageActivity.this, "收藏用户必须先登录", Toast.LENGTH_SHORT).show();
				return;
			}
			String uid = model.getUid();
			AsyncHttpClient client = new AsyncHttpClient();
			String url = Constants.SERVICE_URL+"api/user/xincreate"+"?uid="+uid+"&sid="+Constants.USER_ID;
			client.get(url, new AsyncHttpResponseHandler(){
				@Override
				public void onSuccess(String content) {
					try {
						JSONObject object = new JSONObject(content);
						if(object.getString("state").equals("0")){
							Toast.makeText(DisplayImageActivity.this, "添加爱心成功", Toast.LENGTH_SHORT).show();
							//UserImagesActivity.this.finish();
							btnLike.setSelected(true);
						}else{
							Toast.makeText(DisplayImageActivity.this,object.getString("msg") , Toast.LENGTH_SHORT).show();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		case R.id.save:
			if(!v.isSelected()){
				DownloadTask task = new DownloadTask(this);
				task.execute(Constants.IMAGE_BIG+model.getBig_pic());
				//Toast.makeText(this, "下载成功", Toast.LENGTH_SHORT).show();
				v.setSelected(!v.isSelected());
			}else{
				Toast.makeText(this, "已下载", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.favor:
			if(!v.isSelected()){
				v.setSelected(!v.isSelected());
				db.save(model);
				Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		
	}
}
