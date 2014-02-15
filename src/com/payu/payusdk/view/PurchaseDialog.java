package com.payu.payusdk.view;

import com.payu.payusdk.R;
import com.payu.payusdk.controller.HttpRequest;
import com.payu.payusdk.controller.HttpRequest.Callback;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class PurchaseDialog extends DialogFragment implements OnClickListener {

	private View view;

	public static String TITLE = "purchaseTitle";
	public static String SUBTITLE = "purchaseSubtitle";
	public static String SECRET_KEY = "secretKey";

	private AlertDialog dialog;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		view = LayoutInflater.from(getActivity()).inflate(
				R.layout.purchase_dialog_layout, null);

		view.findViewById(R.id.buyBtn).setOnClickListener(this);

		dialog = new AlertDialog.Builder(getActivity()).setView(view).create();

		return dialog;
	}

	@Override
	public void onClick(View view) {

		int id = view.getId();

		if (id == R.id.buyBtn) {
			new HttpRequest(this, new Callback() {

				@Override
				public void onUpdate(int progress) {

				}

				@Override
				public void onSuccess() {

				}

				@Override
				public void onError() {

				}
			}).postOrder("", "").execute();
		}
	}
}
