package com.example.nvxiuwang.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.nvxiuwang.Constants;
import com.example.nvxiuwang.R;
import com.example.nvxiuwang.UserImagesActivity;
import com.example.nvxiuwang.bean.UserModel;
import com.example.nvxiuwang.simple.imageloader.ImageLoader;
import com.example.nvxiuwang.xlist.PageAdapter;
import com.totoro.freelancer.sdk.view.SquareImageView;

public class UserModelAdapter extends PageAdapter<UserModel> implements OnClickListener {
	private Context context;
	ImageLoader imageLoader;
	public UserModelAdapter(Context context){
		this.context = context;
		imageLoader = new ImageLoader(this.context);
	}
	
	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		if(convertView==null){
			holder = new ViewHolder();
			LayoutInflater inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflate.inflate(R.layout.item_user, null);
			holder.img = (SquareImageView)convertView.findViewById(R.id.icon);
			holder.name = (TextView)convertView.findViewById(R.id.name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		UserModel user = objects.get(position);
		imageLoader.DisplayImage(Constants.IMAGE_AVATAR+user.getThumb_avatar(), holder.img);
		holder.name.setText(user.getUser_name()+"");
		
		holder.img.setOnClickListener(this);
		holder.img.setTag(user);
		return convertView;
	}
	static class ViewHolder{
		SquareImageView img;	//author of the beat
		TextView name;
	}
	@Override
	public void onClick(View v) {
		UserModel user = (UserModel)v.getTag();
		if(v.getId() == R.id.icon){
			Intent userIntent = new Intent(context, UserImagesActivity.class);
			Bundle mBundle = new Bundle();  
	        mBundle.putString("uid",user.getId());  
	        mBundle.putString("name",user.getUser_name());
	        userIntent.putExtras(mBundle);  
			context.startActivity(userIntent);
		}
		
	}
}
