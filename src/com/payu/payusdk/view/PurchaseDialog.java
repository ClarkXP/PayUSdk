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
import android.widget.TextView;

public class PurchaseDialog extends DialogFragment implements OnClickListener {

	private View contentView;

	public static String TITLE = "purchaseTitle";
	public static String SUBTITLE = "purchaseSubtitle";
	public static String PRICE = "purchasePrice";
	public static String DATA = "purchaseData";
	public static String SECRET_KEY = "secretKey";
	private Bundle extras;
	private HttpRequest request;

	private AlertDialog dialog;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		contentView = LayoutInflater.from(getActivity()).inflate(
				R.layout.purchase_dialog_layout, null);

		contentView.findViewById(R.id.buyBtn).setOnClickListener(this);
		contentView.findViewById(R.id.successBtn).setOnClickListener(this);
		contentView.findViewById(R.id.errorBtn).setOnClickListener(this);

		dialog = new AlertDialog.Builder(getActivity()).setView(contentView)
				.create();

		return dialog;
	}

	@Override
	public void onResume() {
		extras = getArguments();
		((TextView) contentView.findViewById(R.id.price)).setText(extras
				.getString(PRICE));
		super.onResume();
	}

	@Override
	public void onClick(final View view) {

		int id = view.getId();

		if (id == R.id.buyBtn) {
			contentView.findViewById(R.id.progressBar).setVisibility(
					View.VISIBLE);
			contentView.findViewById(R.id.content).setVisibility(View.GONE);
			dialog.setCancelable(false);
			request = new HttpRequest(this, new Callback() {

				@Override
				public void onUpdate(int progress) {

				}

				@Override
				public void onSuccess() {
					contentView.findViewById(R.id.progressBar).setVisibility(
							View.GONE);
					contentView.findViewById(R.id.success).setVisibility(
							View.VISIBLE);
					((TextView) contentView.findViewById(R.id.successTitle))
							.setText(request.status);
					dialog.setCancelable(true);
				}

				@Override
				public void onError() {
					contentView.findViewById(R.id.progressBar).setVisibility(
							View.GONE);
					contentView.findViewById(R.id.error).setVisibility(
							View.VISIBLE);
					((TextView) contentView.findViewById(R.id.errorTitle))
							.setText(request.status);
					((TextView) contentView.findViewById(R.id.errorMessage))
							.setText(request.returnMessage);
					dialog.setCancelable(true);

				}
			}).postOrder(extras.getString(DATA), extras.getString(SECRET_KEY));
			request.execute();
		} else if (id == R.id.successBtn) {
			dismiss();
		} else if (id == R.id.errorBtn) {
			dismiss();
		}
	}
}