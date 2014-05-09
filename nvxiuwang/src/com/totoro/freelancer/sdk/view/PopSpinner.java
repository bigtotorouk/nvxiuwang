package com.totoro.freelancer.sdk.view;

import com.example.nvxiuwang.R;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopSpinner implements OnClickListener {
	private Context context;
	PopupWindow popupWindow;
	private TextView textView;
	public String type = "0";
	public PopSpinner(Context context, TextView view) {
		this.context = context;
		textView = view;
		LayoutInflater inflater = LayoutInflater.from(PopSpinner.this.context);
		View layout = inflater.inflate(R.layout.pop_spinner, null);
		popupWindow = new PopupWindow(layout, 400, 250, true);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable()); // 响应返回键必须的语句
		Rect rc = new Rect();
		textView.getWindowVisibleDisplayFrame(rc);
		int[] xy = new int[2];
		textView.getLocationInWindow(xy);
		rc.offset(xy[0], xy[1]);
		layout.findViewById(R.id.item_0).setOnClickListener(this);
		layout.findViewById(R.id.item_1).setOnClickListener(this);
		layout.findViewById(R.id.item_2).setOnClickListener(this);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				popupWindow.showAsDropDown(textView);
				//popupWindow.showAsDropDown(anchor, xoff, yoff)
				//popupWindow.showAtLocation(textView, Gravity.BOTTOM, 0, 0);
			}
		});
		
		//初始化
		type = "0";
		textView.setText("用户");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.item_0:
			//用户
			type = "0";
			textView.setText("用户");
			popupWindow.dismiss();
			break;
		case R.id.item_1:
			//图片
			type = "1";
			textView.setText("图片");
			popupWindow.dismiss();
			break;
		case R.id.item_2:
			//美图
			type = "2";
			textView.setText("美图");
			popupWindow.dismiss();
			break;
		default:
			break;
		}
		if(popupWindow.isShowing()){
			popupWindow.dismiss();
		}
	}
}
