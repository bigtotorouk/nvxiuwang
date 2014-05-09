package com.example.nvxiuwang;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
	public static String DB_FILE = "com.nvxiuwang";
	private final String[] TAGS = { "hot", "search", "favorite", "group",
			"setting" };
	private final Class<?>[] CLASSES = { HotFragment.class,
			SearchManagerFragment.class, FavoriteFragment.class, GroupFragment.class,
			SettingFragment.class };
	private int position = 0;
	private Fragment mContent;
	private TextView tab0, tab1, tab2, tab3, tab4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tab0 =(TextView) findViewById(R.id.radio_button0); tab0.setOnClickListener(this);
		tab1 =(TextView) findViewById(R.id.radio_button1); tab1.setOnClickListener(this);
		tab2 =(TextView) findViewById(R.id.radio_button2); tab2.setOnClickListener(this);
		tab3 =(TextView) findViewById(R.id.radio_button3); tab3.setOnClickListener(this);
		tab4 =(TextView) findViewById(R.id.radio_button4); tab4.setOnClickListener(this);
		
		updateTab(R.id.radio_button0);
		
		login();
	}
	
	public void togglePage(int position) {
		this.position = position;
		if (position < 0 || position > TAGS.length) {
			Toast.makeText(this, "跳转界面发成错误 in togglePage.", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		Fragment fragment = getSupportFragmentManager().findFragmentByTag(
				TAGS[position]);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		if (mContent != null) {
			ft.detach(mContent);
		}
		if (fragment == null) {
			fragment = Fragment.instantiate(this, CLASSES[position].getName());
			ft.add(R.id.content, fragment, TAGS[position]);
		} else {
			ft.attach(fragment);
		}
		mContent = fragment;
		ft.commit();
		getSupportFragmentManager().executePendingTransactions();
		
	}

	/**
	 * startActivity(new Intent(this, AccountActivity.class));
	 * overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	 */
	public void updateTab(int id){
		switch (id) {
		case R.id.radio_button0:
			tab0.setSelected(true);
			tab1.setSelected(false);
			tab2.setSelected(false);
			tab3.setSelected(false);
			tab4.setSelected(false);
			togglePage(position = 0);
			break;
		case R.id.radio_button1:
			tab0.setSelected(false);
			tab1.setSelected(true);
			tab2.setSelected(false);
			tab3.setSelected(false);
			tab4.setSelected(false);
			togglePage(position = 1);
			break;
		case R.id.radio_button2:
			tab0.setSelected(false);
			tab1.setSelected(false);
			tab2.setSelected(true);
			tab3.setSelected(false);
			tab4.setSelected(false);
			togglePage(position = 2);
			break;
		case R.id.radio_button3:
			tab0.setSelected(false);
			tab1.setSelected(false);
			tab2.setSelected(false);
			tab3.setSelected(true);
			tab4.setSelected(false);
			togglePage(position = 3);
			break;
		case R.id.radio_button4:
			tab0.setSelected(false);
			tab1.setSelected(false);
			tab2.setSelected(false);
			tab3.setSelected(false);
			tab4.setSelected(true);
			togglePage(position = 4);
			break;
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.radio_button0:
		case R.id.radio_button1:
		case R.id.radio_button2:
		case R.id.radio_button3:
		case R.id.radio_button4:
			updateTab(v.getId());
			break;

		default:
			break;
		}
		
	}
	
	private void login(){
		SharedPreferences prefs = this.getSharedPreferences(DB_FILE, Context.MODE_PRIVATE);
		final String username = prefs.getString("name", "");
		String password = prefs.getString("password", "");
		if(username.equals("")||password.equals("")){
			return;
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
						Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
						JSONObject data = object.getJSONObject("data");
						Constants.USER_ID = data.getString("sid");
						Constants.USER_NAME = username;
					}else{
						Toast.makeText(MainActivity.this,object.getString("msg") , Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
