package com.example.nvxiuwang.xlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.nvxiuwang.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
/**
 * 
 * @author bing
 *
 * @param <T>
 */
public abstract class PageListFragment<T> extends Fragment {
	public String url = "";
	public static String _L_PAGE = "page";
	public static String _L_PAGE_SIZE = "pageSize";
	public int page = 1, pageSize = 20;  // 默认其实页从1开始
	private Map<String, Object> paramMaps = new HashMap<String, Object>();; 
	FinalHttp finalHttp = new FinalHttp(); 
	
	private PullToRefreshListView mPullRefreshListView;
	protected PageAdapter<T> _pageAdapter;
	
	public abstract void initParams();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view  = onCreatePageView(inflater, container, savedInstanceState);
		initParams();
		updateEnv(paramMaps);
		createListView(view);
		return view;
	}
	
	public abstract View onCreatePageView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState);
	
	/**
	 * update the params
	 */
	public void updateEnv(Map<String, Object> paramMaps){}
	
	/**
	 * 每次请求检查本地参数环境
	 * @return
	 */
	public String checkEnv(){
		try {
			if(page<1){
				throw new Exception("value of page should be more than 0");
			}
			if(pageSize<5){
				throw new Exception("value of pageSize should be more than 5");
			}
			if(url.equals("")){
				throw new Exception("value of url should be initialization");
			}
			if(_pageAdapter == null){
				throw new Exception("value of _pageAdapter should be initialization");
			}
			StringBuilder sb = new StringBuilder(url);
			sb.append("?"+_L_PAGE+"="+page+"&"+_L_PAGE_SIZE+"="+pageSize);
			for (Map.Entry<String, Object> entry : paramMaps.entrySet()) {
				sb.append("&");
				sb.append(entry.getKey() + "=" + entry.getValue());
			}
			return sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	private void createListView(View view){
		mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		mPullRefreshListView.setMode(Mode.BOTH);
		mPullRefreshListView.setAdapter(_pageAdapter);
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(PageListFragment.this.getActivity(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				if (refreshView.isHeaderShown()){ 
					page = 1;
				}else{
					
				}
				load(true, false);
			}
		});

		/*// Add an end-of-list listener
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				Toast.makeText(PageListFragment.this.getActivity(), "End of List!", Toast.LENGTH_SHORT).show();
			}
		});*/
	}
	public abstract BasicPageResponse<T> parseResponse(String content);
	
	public void load(boolean update, boolean loadMore){
		updateEnv(paramMaps);
		finalHttp.get(checkEnv(), new AjaxCallBack<String>(){
			@Override
			public void onSuccess(String t) {
				Log.d("bigtotoro", "result" +t);
				BasicPageResponse<T> response = parseResponse(t);
				if(response.getState().equals("0")){
					List<T> data = response.getData();
					if(page == 1){
						_pageAdapter.update(data, false);
					}else{
						_pageAdapter.update(data, true);
					}
					page++;
				}else{
					Toast.makeText(getActivity(), response.getMsg(), Toast.LENGTH_SHORT).show();
				}
				mPullRefreshListView.onRefreshComplete();
			}
			
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				Toast.makeText(PageListFragment.this.getActivity(), strMsg, Toast.LENGTH_SHORT).show();
				//super.onFailure(t, errorNo, strMsg);
				mPullRefreshListView.onRefreshComplete();
			}
		    
		});
	}
}
