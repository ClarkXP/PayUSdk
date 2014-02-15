package com.payu.payusdk.model;

public interface ALUColumns {

	// -------------------------Required Parameters List---------

	// Order details
	static final String MERCHANT = "MERCHANT";
	static final String ORDER_REF = "ORDER_REF";
	static final String ORDER_DATE = "ORDER_DATE";
	static final String PAY_METHOD = "PAY_METHOD";
	static final String LANGUAGE = "LANGUAGE";
	static final String ORDER_HASH = "ORDER_HASH";
	static final String BACK_REF = "BACK_REF";

	// Product details
	static final String ORDER_PNAME_$ = "ORDER_PNAME[%1$d]";
	static final String ORDER_PCODE_$ = "ORDER_PCODE[%1$d]";
	static final String ORDER_PRICE_$ = "ORDER_PRICE[%1$d]";
	static final String ORDER_QTY_$ = "ORDER_QTY[%1$d]";
	static final String PRICES_CURRENCY = "PRICES_CURRENCY";

	// Billing Details
	static final String BILL_LNAME = "BILL_LNAME";
	static final String BILL_FNAME = "BILL_FNAME";
	static final String BILL_EMAIL = "BILL_EMAIL";
	static final String BILL_PHONE = "BILL_PHONE";
	static final String BILL_COUNTRYCODE = "BILL_COUNTRYCODE";

	// Card Details
	static final String CC_NUMBER = "CC_NUMBER";
	static final String EXP_MONTH = "EXP_MONTH";
	static final String EXP_YEAR = "EXP_YEAR";
	static final String CC_CVV = "CC_CVV";
	static final String CC_OWNER = "CC_OWNER";

	// -------------------------Optional Parameters List---------

	// Order Details
	static final String ORDER_PINFO_$ = "ORDER_PINFO[%1$d]";
	static final String ORDER_VER_$ = "ORDER_VER[%1$d]";
	static final String SELECTED_INSTALLMENTS_NUMBER = "SELECTED_INSTALLMENTS_NUMBER";
	static final String CARD_PROGRAM_NAME = "CARD_PROGRAM_NAME";
	static final String ORDER_TIMEOUT = "ORDER_TIMEOUT";

	// Billing Details
	static final String BILL_FAX = "BILL_FAX";
	static final String BILL_ADDRESS = "BILL_ADDRESS";
	static final String BILL_ADDRESS2 = "BILL_ADDRESS2";
	static final String BILL_ZIPCODE = "BILL_ZIPCODE";
	static final String BILL_CITY = "BILL_CITY";
	static final String BILL_STATE = "BILL_STATE";

	// Delivery Details
	static final String DELIVERY_LNAME = "DELIVERY_LNAME";
	static final String DELIVERY_FNAME = "DELIVERY_FNAME";
	static final String DELIVERY_EMAIL = "DELIVERY_EMAIL";
	static final String DELIVERY_PHONE = "DELIVERY_PHONE";
	static final String DELIVERY_COMPANY = "DELIVERY_COMPANY";
	static final String DELIVERY_ADDRESS = "DELIVERY_ADDRESS";
	static final String DELIVERY_ADDRESS2 = "DELIVERY_ADDRESS2";
	static final String DELIVERY_ZIPCODE = "DELIVERY_ZIPCODE";
	static final String DELIVERY_CITY = "DELIVERY_CITY";
	static final String DELIVERY_STATE = "DELIVERY_STATE";
	static final String DELIVERY_COUNTRYCODE = "DELIVERY_COUNTRYCODE";

	// Other Details
	static final String CLIENT_IP = "CLIENT_IP";
	static final String CLIENT_TIME = "CLIENT_TIME";

	// Response
	static final String REFNO = "refno";
	static final String ALIAS = "alias";
	static final String STATUS = "status";
	static final String RETURN_CODE = "return_code";
	static final String RETURN_MESSAGE = "return_message";
	static final String DATE = "date";
	static final String URL_3DS = "url_3ds";
	static final String HASH = "hash";
}
