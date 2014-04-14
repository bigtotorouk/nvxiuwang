package com.example.nvxiuwang.dialog;


import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.nvxiuwang.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class AccountRegisterDialog implements OnClickListener {
	private Context context;
	private Dialog dialog;
	private EditText editUser, editPassword1, editPassword2;
	
	public AccountRegisterDialog(Context context){
		this.context = context;
		dialog = new Dialog(context, R.style.dialog);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_register);
		dialog.findViewById(R.id.btn_register).setOnClickListener(this);
		editUser = (EditText)dialog.findViewById(R.id.username);
		editPassword1 = (EditText)dialog.findViewById(R.id.password1);
		editPassword2 = (EditText)dialog.findViewById(R.id.password2);
		dialog.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			register();
			break;
		default:
			break;
		}
	}
	
	public void register(){
		String username = editUser.getText().toString();
		String password1 = editPassword1.getText().toString();
		String password2 = editPassword2.getText().toString();
		if(TextUtils.isEmpty(username)){
			editUser.setError("请输入用户名");
			editUser.requestFocus();
		}
		if(TextUtils.isEmpty(password1)){
			editPassword1.setError("请输入密码");
			editPassword1.requestFocus();
		}
		if(TextUtils.isEmpty(password2)){
			editPassword2.setError("请输入确认密码");
			editPassword2.requestFocus();
		}
		if(!password1.equals(password2)){
			editPassword2.setError("两次密码不一致");
			editPassword2.requestFocus();
		}
		
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
	    params.put("username", username);
	    params.put("password", password1);
		client.post("http://52pic.com/api/user/register", params, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				try {
					JSONObject object = new JSONObject(content);
					if(object.getString("state").equals("0")){
						Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}else{
						Toast.makeText(context,object.getString("msg") , Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/*public void register(){
		String username = editUser.getText().toString();
		String password1 = editPassword1.getText().toString();
		String password2 = editPassword2.getText().toString();
		if(TextUtils.isEmpty(username)){
			editUser.setError("请输入用户名");
			editUser.requestFocus();
		}
		if(TextUtils.isEmpty(password1)){
			editPassword1.setError("请输入密码");
			editPassword1.requestFocus();
		}
		if(TextUtils.isEmpty(password2)){
			editPassword2.setError("请输入确认密码");
			editPassword2.requestFocus();
		}
		if(!password1.equals(password2)){
			editPassword2.setError("两次密码不一致");
			editPassword2.requestFocus();
		}
		
		AsyncHttpClient client = new AsyncHttpClient();
		JSONObject jsonParams = new JSONObject();
        try {
        	jsonParams.put("username", username);
			jsonParams.put("password", password1);
			StringEntity entity = new StringEntity(jsonParams.toString());
	        client.post(context, "http://52pic.com/user/register", entity, "application/json",
	        		new AsyncHttpResponseHandler(){
	        	@Override
	        	public void onSuccess(String content) {
	        		try {
						JSONObject object = new JSONObject(content);
						if(object.getString("state").equals("0")){
							dialog.dismiss();
						}else{
							Toast.makeText(context, object.getString("msg"), Toast.LENGTH_SHORT).show();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
	        	}
	        	@Override
	        	public void onFailure(Throwable error, String content) {
	        		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	        	}
	        });
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}*/
}
