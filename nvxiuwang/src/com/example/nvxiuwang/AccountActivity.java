package com.example.nvxiuwang;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.nvxiuwang.dialog.AccountRegisterDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class AccountActivity extends Activity implements OnClickListener {
	private EditText editUser, editPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		editUser = (EditText)findViewById(R.id.username);
		editPassword = (EditText)findViewById(R.id.password);
		findViewById(R.id.back).setOnClickListener(this);
		findViewById(R.id.btn_login).setOnClickListener(this);
		findViewById(R.id.btn_register).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			AccountActivity.this.finish();
			break;
		case R.id.btn_login:
			login();
			break;
		case R.id.btn_register:
			new AccountRegisterDialog(AccountActivity.this);
			break;
		default:
			break;
		}
	}
	
	public void login(){
		String username = editUser.getText().toString();
		String password = editPassword.getText().toString();
		if(TextUtils.isEmpty(username)){
			editUser.setError("请输入用户名");
			editUser.requestFocus();
		}
		if(TextUtils.isEmpty(password)){
			editPassword.setError("请输入密码");
			editPassword.requestFocus();
		}
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
	    params.put("username", username);
	    params.put("password", password);
		client.post("http://52pic.com/api/user/login", params, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				try {
					JSONObject object = new JSONObject(content);
					if(object.getString("state").equals("0")){
						Toast.makeText(AccountActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
						AccountActivity.this.finish();
					}else{
						Toast.makeText(AccountActivity.this,object.getString("msg") , Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
}
