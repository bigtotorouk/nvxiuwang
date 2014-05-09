package com.example.nvxiuwang.dialog;

import com.example.nvxiuwang.R;
import com.totoro.freelancer.sdk.view.LinkageImageView;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class ImageDisplayDialog implements OnClickListener {
	private Dialog dialog;
	private ImageView image;
	private LinkageImageView linkageImageView;
	public ImageDisplayDialog(Context context,String url){
		dialog = new Dialog(context);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(LayoutInflater.from(context).inflate(R.layout.image_display, null));
		
		image = (ImageView)dialog.findViewById(R.id.img);
		image.setOnClickListener(this);
		dialog.findViewById(R.id.delete).setOnClickListener(this);
		linkageImageView = new LinkageImageView();
		linkageImageView.setUrl(image, url);
		dialog.show();
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.delete){
			dialog.dismiss();
		}else if(v.getId() == R.id.img){
			if(!linkageImageView.isLoading()){
				dialog.dismiss();
			}
		}
	}
}
