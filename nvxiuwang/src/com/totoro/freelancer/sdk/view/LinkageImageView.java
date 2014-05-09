package com.totoro.freelancer.sdk.view;

import java.io.InputStream;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

public class LinkageImageView{
	private Context context;
	ProgressDialog pd;
	boolean isLoading = true;

	public void setUrl(ImageView imageView, String url) {
		context = imageView.getContext(); 
		pd = new ProgressDialog(imageView.getContext());
		pd.setMessage("加载图片...");
		isLoading = true;
		new DownloadImageTask(imageView).execute(url);
	}
	
	public boolean isLoading(){
		return isLoading;
	}

	class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd.show();
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mIcon11;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			pd.dismiss();
			if(result!=null){
				bmImage.setImageBitmap(result);
			}else{
				Toast.makeText(context, "加载图片失败", Toast.LENGTH_SHORT).show();
			}
			isLoading = false;
		}
	}

}
