package com.example.nvxiuwang.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.nvxiuwang.Constants;
import com.example.nvxiuwang.DisplayImageActivity;
import com.example.nvxiuwang.R;
import com.example.nvxiuwang.UserImagesActivity;
import com.example.nvxiuwang.bean.ImageModel;
import com.example.nvxiuwang.dialog.ImageDisplayDialog;
import com.example.nvxiuwang.simple.imageloader.ImageLoader;
import com.example.nvxiuwang.xlist.PageAdapter;
import com.totoro.freelancer.sdk.view.SquareImageView;

public class ImageModelAdapter extends PageAdapter<ImageModel> implements OnClickListener {
	private Context context;
	ImageLoader imageLoader;
	
	public ImageModelAdapter(Context context){
		this.context = context;
		imageLoader = new ImageLoader(this.context);
	}
	
	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		if(convertView==null){
			holder = new ViewHolder();
			LayoutInflater inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflate.inflate(R.layout.item_image , null);
			holder.img = (SquareImageView)convertView.findViewById(R.id.img);
			holder.icon = (ImageView)convertView.findViewById(R.id.icon);
			holder.author = (TextView)convertView.findViewById(R.id.name);
			holder.time = (TextView)convertView.findViewById(R.id.time);
			holder.desc = (TextView)convertView.findViewById(R.id.desc);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		ImageModel model = objects.get(position);
		String big_url = Constants.IMAGE_MIDDLE+model.getMiddle_pic();
		imageLoader.DisplayImage(big_url, holder.img);
		imageLoader.DisplayImage(Constants.IMAGE_AVATAR+model.getThumb_avatar(), holder.icon);
		holder.author.setText(model.getUser_name());
		holder.time.setText(model.getCreate_time());
		holder.desc.setText(model.getText());
		
		holder.img.setOnClickListener(this);
		holder.img.setTag(model);
		holder.icon.setOnClickListener(this);
		holder.icon.setTag(model);
		return convertView;
	}
	
	static class ViewHolder{
		SquareImageView img;	//author of the beat
		ImageView icon;
		TextView author;
		TextView time;
		TextView desc;
	}

	@Override
	public void onClick(View v) {
		ImageModel model = (ImageModel)v.getTag();
		if(v.getId() == R.id.img){
			//new ImageDisplayDialog(context, url);
			Intent imageIntent = new Intent(context, DisplayImageActivity.class);
			Bundle mBundle = new Bundle();  
	        mBundle.putSerializable("model",model);  
	        imageIntent.putExtras(mBundle);  
			context.startActivity(imageIntent);
		}else if(v.getId() == R.id.icon){
			Intent userIntent = new Intent(context, UserImagesActivity.class);
			Bundle mBundle = new Bundle();  
	        mBundle.putString("uid",model.getUid());  
	        mBundle.putString("name",model.getUser_name());
	        userIntent.putExtras(mBundle);  
			context.startActivity(userIntent);
		}
		
	}

}
