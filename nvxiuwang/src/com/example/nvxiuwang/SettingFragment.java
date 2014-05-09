package com.example.nvxiuwang;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.example.nvxiuwang.dialog.AccountRegisterDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SettingFragment extends Fragment implements OnClickListener {
	private EditText editUser, editPassword;
	private View layoutLogin, layoutProfile;
	
	private TextView txtName, txtStatus;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_setting, container, false);
		editUser = (EditText)view.findViewById(R.id.edit_username);
		editPassword = (EditText)view.findViewById(R.id.password);
		view.findViewById(R.id.btn_login).setOnClickListener(this);
		view.findViewById(R.id.btn_register).setOnClickListener(this);
		
		layoutLogin = view.findViewById(R.id.layout_login);
		layoutProfile = view.findViewById(R.id.layout_profile);
		txtName = (TextView)view.findViewById(R.id.username);
		txtStatus = (TextView)view.findViewById(R.id.status);
		
		if(Constants.USER_ID.equals("")){
			layoutLogin.setVisibility(View.VISIBLE);
			layoutProfile.setVisibility(View.GONE);
		}else{
			layoutLogin.setVisibility(View.GONE);
			layoutProfile.setVisibility(View.VISIBLE);
			txtName.setText(Constants.USER_NAME);
			txtStatus.setText("已登录");
		}
		return view;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			login();
			break;
		case R.id.btn_register:
			new AccountRegisterDialog(SettingFragment.this.getActivity());
			break;
		default:
			break;
		}
		
	}
	
	public void login(){
		final String username = editUser.getText().toString();
		final String password = editPassword.getText().toString();
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
		client.post(Constants.SERVICE_URL+"api/user/login", params, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				try {
					JSONObject object = new JSONObject(content);
					if(object.getString("state").equals("0")){
						Toast.makeText(SettingFragment.this.getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
						JSONObject data = object.getJSONObject("data");
						Constants.USER_ID = data.getString("sid");
						Constants.USER_NAME = username;
						layoutLogin.setVisibility(View.GONE);
						layoutProfile.setVisibility(View.VISIBLE);
						txtName.setText(username);
						txtStatus.setText("已登录");
						SharedPreferences prefs = getActivity().getSharedPreferences(MainActivity.DB_FILE, Context.MODE_PRIVATE);
						prefs.edit().putString("name", username).putString("password", password).commit();
						((MainActivity)getActivity()).updateTab(R.id.radio_button0);
					}else{
						Toast.makeText(SettingFragment.this.getActivity(),object.getString("msg") , Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
