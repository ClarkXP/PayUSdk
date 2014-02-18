package com.payu.payusdk.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map.Entry;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.payu.payusdk.R;
import com.payu.payusdk.model.ALUColumns;

public class HttpRequest extends AsyncTask<Void, Boolean, Boolean> implements
		ALUColumns {

	private static final String PAYU_URL = "https://secure.payu.ru";

	static final int POST_POST_ORDER_REQ = 1;
	private static final String POST_POST_ORDER_URL = "/order/alu.php";

	private static final int STATUS_SUCCESS_CODE = 200;

	private static final String STATUS_SUCCESS = "SUCCESS";
	private static final String STATUS_FAILED = "FAILED";
	private static final String STATUS_INPUT_ERROR = "INPUT_ERROR";

	private static final String RETURN_CODE_AUTHORIZED = "AUTHORIZED";
	private static final String RETURN_CODE_3DS_ENROLLED = "3DS_ENROLLED";
	private static final String RETURN_CODE_ALREADY_AUTHORIZED = "ALREADY_AUTHORIZED";
	private static final String RETURN_CODE_AUTHORIZATION_FAILED = "AUTHORIZATION_FAILED";
	private static final String RETURN_CODE_INVALID_CUSTOMER_INFO = "INVALID_CUSTOMER_INFO";
	private static final String RETURN_CODE_INVALID_PAYMENT_INFO = "INVALID_PAYMENT_INFO";
	private static final String RETURN_CODE_INVALID_ACCOUNT = "INVALID_ACCOUNT";
	private static final String INVALID_PAYMENT_METHOD_CODE = "INVALID_PAYMENT_METHOD_CODE";
	private static final String RETURN_CODE_INVALID_CURRENCY = "INVALID_CURRENCY";
	private static final String RETURN_CODE_REQUEST_EXPIRED = "REQUEST_EXPIRED";
	private static final String RETURN_CODE_HASH_MISMATCH = "HASH_MISMATCH";

	private static final String ENCODING_TYPE = "HmacSHA1";

	private static final Integer REQ_TYPE_POST = 1;
	@SuppressWarnings("unused")
	private static final Integer REQ_TYPE_GET = 0;

	private final Context mCtx;
	private String response;
	private Toast toast;
	private Callback callback;
	private RequestCallback<Void, Boolean> requestCallback;

	public String status;
	public String returnMessage;

	public HttpRequest(Context ctx, Callback callback) {
		mCtx = ctx;
		this.callback = callback;
	}

	public HttpRequest(Fragment fragment, Callback callback) {
		this(fragment.getActivity(), callback);
	}

	public HttpRequest(Context ctx) {
		mCtx = ctx;
	}

	public HttpRequest(Fragment fragment) {
		this(fragment.getActivity());
	}

	private String makeRequest(int typeOfRequest, Object data) {

		try {
			switch (typeOfRequest) {
			case POST_POST_ORDER_REQ:
				response = GetJSONString(POST_POST_ORDER_URL, REQ_TYPE_POST,
						data);
				break;
			}

			helper.WriteDebug(response);

			status = getXMLFieldValue(response, STATUS);
			returnMessage = getXMLFieldValue(response, RETURN_MESSAGE);

			if (!status.equals(STATUS_SUCCESS)) {
				response = null;
			}

		} catch (Exception e) {
			helper.WriteDebug(e.toString());
			response = null;
		}

		return response;
	}

	public HttpRequest postOrder(final PurchaseBuilder data,
			final String secretKey) {

		requestCallback = new RequestCallback<Void, Boolean>() {

			@Override
			public Boolean doInBackground(Void... args) {

				MultipartEntityBuilder builder = MultipartEntityBuilder
						.create();
				builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
				builder.addTextBody(ORDER_HASH,
						encodeDataString(data.build(), secretKey));

				for (Entry<String, String> entry : data.getData().entrySet()) {
					builder.addTextBody(entry.getKey(), entry.getValue());
				}

				try {

					String data = makeRequest(POST_POST_ORDER_REQ,
							builder.build());
					if (data == null) {
						return false;
					}
				} catch (Exception e) {
					helper.WriteDebug(e.toString());
					return false;
				}

				return true;
			}
		};

		return this;
	}

	private String encodeDataString(String data, String secretKey) {
		SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(),
				ENCODING_TYPE);

		Mac mac = null;
		try {
			mac = Mac.getInstance(ENCODING_TYPE);
			mac.init(keySpec);
		} catch (NoSuchAlgorithmException e) {
			helper.WriteError(e.toString());
		} catch (InvalidKeyException e) {
			helper.WriteError(e.toString());
		}

		byte[] result = mac.doFinal(data.getBytes());

		try {
			return new String(new Base64().encode(result), HTTP.UTF_8);
			// return Base64.encodeToString(result, Base64.URL_SAFE);
		} catch (UnsupportedEncodingException e) {
			helper.WriteError(e.toString());
		}

		return "";
	}

	private String GetJSONString(String url, int type, Object data)
			throws IOException {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();

		url = PAYU_URL + url;

		if (type == REQ_TYPE_POST) {

			HttpPost httpPost = new HttpPost(url);

			try {
				httpPost.setEntity((HttpEntity) data);
				HttpResponse response = client.execute(httpPost);
				int statusCode = response.getStatusLine().getStatusCode();

				if (statusCode == STATUS_SUCCESS_CODE) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					helper.WriteInfo("Can't make HTTPPost: " + url
							+ "\nstatuscode = " + statusCode);
				}
			} catch (IOException e) {
				helper.WriteError(e.toString());
				status = mCtx.getString(R.string.error);
				returnMessage = mCtx.getString(R.string.cannotConnectServer);
			}

		} else {

			HttpGet httpGet = new HttpGet(url);
			if (data != null) {
				url += data;
			}

			try {
				HttpResponse response = client.execute(httpGet);
				int statusCode = response.getStatusLine().getStatusCode();

				if (statusCode == STATUS_SUCCESS_CODE) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					helper.WriteInfo("Can't make HTTPGet: " + url
							+ "\nstatuscode = " + statusCode);
				}
			} catch (IOException e) {
				helper.WriteError(e.toString());
				status = mCtx.getString(R.string.error);
				returnMessage = mCtx.getString(R.string.cannotConnectServer);
			}

		}

		return builder.toString();
	}

	private String getXMLFieldValue(String data, String field) {

		if (data.indexOf(field) != -1) {
			int start = data.indexOf(field) + field.length() + 1;
			int end = data.indexOf(field, start) - 2;

			return data.substring(start, end);
		} else {
			return null;
		}

	}

	private void showToast(final String message) {

		((Activity) mCtx).runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (toast != null) {
					toast.setText(message);
					toast.show();
				} else {
					toast = Toast.makeText(mCtx, message, Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
	}

	public interface Callback {
		public void onSuccess();

		public void onUpdate(int progress);

		public void onError();
	}

	private interface RequestCallback<A extends Object, B extends Object> {

		public B doInBackground(A... args);
	}

	@Override
	protected void onPreExecute() {
		((Activity) mCtx).setProgressBarIndeterminateVisibility(true);
		super.onPreExecute();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		if (requestCallback != null) {
			return (Boolean) requestCallback.doInBackground(params);
		} else {
			throw new NoRequestCallbackException();
		}
	}

	@Override
	protected void onPostExecute(Boolean result) {
		((Activity) mCtx).setProgressBarIndeterminateVisibility(false);
		try {
			if (callback != null) {
				if (result) {
					callback.onSuccess();
				} else {
					callback.onError();
				}
			}
		} catch (IllegalStateException e) {

		}

		super.onPostExecute(result);
	}

	class NoRequestCallbackException extends RuntimeException {
		private static final long serialVersionUID = 618062735722934925L;
	}
}
