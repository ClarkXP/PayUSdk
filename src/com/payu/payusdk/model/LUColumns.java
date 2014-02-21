package com.payu.payusdk.model;

public interface LUColumns {

	static final String[] HASH_REQUIRED = { "MERCHANT", "ORDER_DATE",
			"ORDER_REF", "ORDER_PNAME[]", "ORDER_PCODE[]", "ORDER_PINFO[]",
			"ORDER_PRICE[]", "ORDER_QTY[]", "ORDER_VAT[]", "ORDER_SHIPPING",
			"PRICES_CURRENCY" };

	static final String[] REQUEST_REQUIRED = { "MERCHANT", "ORDER_DATE",
			"ORDER_PNAME[]", "ORDER_PCODE[]", "ORDER_PRICE[]", "ORDER_QTY[]",
			"ORDER_VAT[]", "ORDER_SHIPPING", "PRICES_CURRENCY", "ORDER_HASH" };

	static final String MERCHANT = "MERCHANT";
	static final String ORDER_REF = "ORDER_REF";
	static final String ORDER_DATE = "ORDER_DATE";
	static final String PAY_METHOD = "PAY_METHOD";
	static final String LANGUAGE = "LANGUAGE";
	static final String ORDER_HASH = "ORDER_HASH";
	static final String TESTORDER = "TESTORDER";
	static final String DEBUG = "DEBUG";
	static final String BACK_REF = "BACK_REF";
	static final String ORDER_PNAME = "ORDER_PNAME[]";
	static final String ORDER_PCODE = "ORDER_PCODE[]";
	static final String ORDER_PRICE = "ORDER_PRICE[]";
	static final String ORDER_PRICE_TYPE = "ORDER_PRICE_TYPE[]";
	static final String ORDER_QTY = "ORDER_QTY[]";
	static final String ORDER_VAT = "ORDER_VAT[]";
	static final String ORDER_PGROUP = "ORDER_PGROUP[]";
	static final String PRICES_CURRENCY = "PRICES_CURRENCY";
	static final String DISCOUNT = "DISCOUNT";
	static final String DESTINATION_CITY = "DESTINATION_CITY";
	static final String DESTINATION_STATE = "DESTINATION_STATE";
	static final String DESTINATION_COUNTRY = "DESTINATION_COUNTRY";
	static final String BACK_REF_RESULT = "RESULT";
	static final String BACK_REF_3DSECURE = "3DSECURE";
	static final String BACK_REF_DATE = "DATE";
	static final String BACK_REF_CTRL = "CTRL";
	static final String BILL_LNAME = "BILL_LNAME";
	static final String BILL_FNAME = "BILL_FNAME";
	static final String BILL_EMAIL = "BILL_EMAIL";
	static final String BILL_PHONE = "BILL_PHONE";
	static final String BILL_COUNTRYCODE = "BILL_COUNTRYCODE";
	static final String ORDER_PINFO = "ORDER_PINFO[]";
	static final String ORDER_VER = "ORDER_VER[]";
	static final String ORDER_SHIPPING = "ORDER_SHIPPING";
	static final String BILL_FAX = "BILL_FAX";
	static final String BILL_ADDRESS = "BILL_ADDRESS";
	static final String BILL_ADDRESS2 = "BILL_ADDRESS2";
	static final String BILL_ZIPCODE = "BILL_ZIPCODE";
	static final String BILL_CITY = "BILL_CITY";
	static final String BILL_STATE = "BILL_STATE";
	static final String DELIVERY_LNAME = "DELIVERY_LNAME";
	static final String DELIVERY_FNAME = "DELIVERY_FNAME";
	static final String DELIVERY_PHONE = "DELIVERY_PHONE";
	static final String DELIVERY_COMPANY = "DELIVERY_COMPANY";
	static final String DELIVERY_ADDRESS = "DELIVERY_ADDRESS";
	static final String DELIVERY_ADDRESS2 = "DELIVERY_ADDRESS2";
	static final String DELIVERY_ZIPCODE = "DELIVERY_ZIPCODE";
	static final String DELIVERY_CITY = "DELIVERY_CITY";
	static final String DELIVERY_STATE = "DELIVERY_STATE";
	static final String DELIVERY_COUNTRYCODE = "DELIVERY_COUNTRYCODE";
}
