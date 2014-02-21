package com.payu.payusdk.controller;

import org.apache.http.util.EncodingUtils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.payu.payusdk.R;
import com.payu.payusdk.view.PurchaseDialog;
import com.payu.payusdk.view.PurchaseView;

public class PayU {

	private FragmentActivity activity;

	public PayU(FragmentActivity activity) {
		this.activity = activity;
	}

	public void showPurchaseFragment(ALUPurchaseBuilder purchase,
			String secretKey) {
		PurchaseDialog newFragment = new PurchaseDialog();
		Bundle bundle = new Bundle();
		bundle.putParcelable(PurchaseDialog.DATA, purchase);
		bundle.putString(PurchaseDialog.SECRET_KEY, secretKey);
		newFragment.setArguments(bundle);
		newFragment.show(activity.getSupportFragmentManager(),
				activity.getString(R.string.tag));
	}

	public PurchaseView getPurchaseView(LUPurchaseBuilder purchase,
			String secretKey) {
		PurchaseView view = new PurchaseView(activity);
		view.postUrl(HttpRequest.PAYU_LU_URL,
				EncodingUtils.getBytes(purchase.build(secretKey), "BASE64"));
		return view;
	}
}
