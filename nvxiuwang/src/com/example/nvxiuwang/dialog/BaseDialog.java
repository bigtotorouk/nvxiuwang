package com.example.nvxiuwang.dialog;
import com.example.nvxiuwang.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

/**
 * a base class to operate a module using a dialog. it has three mode: get, put and post.
 * 'get' mark means that you browser a object using dialog; 'put' mark means that you edit
 * a object using dialog; 'post' mark means that you create a object using dialog.
 * you could try the following code to avoid opening a dialog twice:
 * 
 * if(baseDialog == null ||(baseDialog != null && !baseDialog.isShowing())) {
 *	baseDialog = new BaseDialog....
 *  }
 *  
 *  
 * @author bing
 * 
 */
public abstract class BaseDialog<T> {
    public static int MODE_GET = 0; /* DISPLAY A MODULE OBJECT */
    public static int MODE_PUT = 1; /* EDIT A MODULE OBJECT */
    public static int MODE_POST = 2; /* NEW A OBJECT MODULE */
    int mode = MODE_POST; /* default value */

    Context context;
    Dialog dialog;
    T object;

    /**
     * 
     * @param context
     * @param layout
     * @param mode
     */
    public BaseDialog(Context context, T obejct, int layout, int mode) {
	this.context = context;
	this.mode = mode;
	this.object = obejct;
	dialog = new Dialog(context, R.style.dialog);
	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	dialog.setContentView(layout);
	
	initView(dialog);
	
	bindData(obejct);
	if (this.mode == MODE_GET) {
	    adjustGetView();
	} else if (this.mode == MODE_PUT) {
	    adjustPutView();
	} else if (this.mode == MODE_POST) {
	    adjustPostView();
	}
    }

    public abstract void initView(Dialog dialog);
    public abstract void bindData(T obejct);
    
    public void adjustGetView() {
    }

    public void adjustPutView() {
    }

    public void adjustPostView() {
    }

    
    public boolean isShowing() {
	if (dialog != null) {
	    return dialog.isShowing();
	}
	return false;
    }
}
