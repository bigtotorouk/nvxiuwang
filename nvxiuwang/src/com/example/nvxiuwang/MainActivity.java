package com.example.nvxiuwang;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {

	private final String[] TAGS = { "hot", "search", "favorite", "group",
			"setting" };
	private final Class<?>[] CLASSES = { HotFragment.class,
			SearchFragment.class, FavoriteFragment.class, GroupFragment.class,
			SettingFragment.class };
	private int position = 0;
	private Fragment mContent;
	
	private RadioGroup tabs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabs = (RadioGroup) findViewById(R.id.tabs);
		tabs.setOnCheckedChangeListener(this);
		
		tabs.check(R.id.radio_button0);//togglePage(0);
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
	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch (arg1) {
		case R.id.radio_button0:
			togglePage(position = 0);
			break;
		case R.id.radio_button1:
			togglePage(position = 1);
			break;
		case R.id.radio_button2:
			togglePage(position = 2);
			break;
		case R.id.radio_button3:
			togglePage(position = 3);
			break;
		case R.id.radio_button4:
			togglePage(position = 4);
			break;
		default:
			break;
		}
		
	}

}
