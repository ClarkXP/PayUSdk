package com.payu.payusdk.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.payu.payusdk.R;
import com.payu.payusdk.view.PurchaseDialog;

public class PayU {

	private FragmentActivity activity;

	public PayU(FragmentActivity activity) {
		this.activity = activity;
	}

	public void showPurchaseFragment(PurchaseBuilder purchase, String secretKey) {
		PurchaseDialog newFragment = new PurchaseDialog();
		Bundle bundle = new Bundle();
		bundle.putString(PurchaseDialog.DATA, purchase.build());
		bundle.putString(PurchaseDialog.PRICE, purchase.getPurchasePrice());
		bundle.putString(PurchaseDialog.SECRET_KEY, secretKey);
		newFragment.setArguments(bundle);
		newFragment.show(activity.getSupportFragmentManager(),
				activity.getString(R.string.tag));
	}
}
