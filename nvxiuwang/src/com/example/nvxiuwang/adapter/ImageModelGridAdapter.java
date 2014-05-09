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
import com.example.nvxiuwang.adapter.UserModelAdapter.ViewHolder;
import com.example.nvxiuwang.bean.ImageModel;
import com.example.nvxiuwang.dialog.ImageDisplayDialog;
import com.example.nvxiuwang.simple.imageloader.ImageLoader;
import com.example.nvxiuwang.xlist.PageAdapter;
import com.totoro.freelancer.sdk.view.SquareImageView;

public class ImageModelGridAdapter extends PageAdapter<ImageModel> implements OnClickListener {
	private Context context;
	ImageLoader imageLoader;
	
	public ImageModelGridAdapter(Context context){
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
		ImageModel model = objects.get(position);
		String middle_url = Constants.IMAGE_MIDDLE+model.getMiddle_pic();
		imageLoader.DisplayImage(middle_url, holder.img);
		holder.name.setText(model.getText());
		
		holder.img.setOnClickListener(this);
		holder.img.setTag(model);
		return convertView;
	}
	
	static class ViewHolder{
		SquareImageView img;	//author of the beat
		TextView name;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.icon){
			ImageModel model = (ImageModel)v.getTag();
			//new ImageDisplayDialog(context, url);
			Intent imageIntent = new Intent(context, DisplayImageActivity.class);
			Bundle mBundle = new Bundle();  
	        mBundle.putSerializable("model",model);  
	        imageIntent.putExtras(mBundle);  
			context.startActivity(imageIntent);
		}
		
	}

}
