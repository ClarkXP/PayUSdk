package com.payu.payusdk.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PurchaseView extends WebView {

	public PurchaseView(Context context) {
		super(context);
		init(context);
	}

	public PurchaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void init(Context context) {

		getSettings().setJavaScriptEnabled(true);
		getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		getSettings().setDomStorageEnabled(true);

		setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}
}
