package com.epam.esm.db;

public final class DAOConstants {

    private DAOConstants() {
    }

    public static final String CERTIFICATE_TABLE = "gift_certificate";
    //gift_certificate table columns
    public static final String GC_ID = "id";
    public static final String GC_NAME = "name";
    public static final String GC_DESCRIPTION = "description";
    public static final String GC_PRICE = "price";
    public static final String GC_CREATE_DATE = "create_date";
    public static final String GC_LAST_UPDATE_DATE = "last_date_date";
    public static final String GC_DURATION = "duration";
    //tag
    public static final String TAG_TABLE = "tag";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    //certificate tags table
    public static final String CERTIFICATE_TAG_TABLE = "gift_certificate_has_tag";
    public static final String CT_CERTIFICATE_ID = "gift_certificate_id";
    public static final String CT_TAG_ID = "tag_id";
    //user
    public static final String USER_TABLE = "user";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_DOB = "date_of_birth";
    //order
    public static final String ORDER_TABLE = "order";
    public static final String ORDER_ID = "id";
    public static final String ORDER_TIME = "purchase_date";
    public static final String ORDER_USER_ID = "user_id";
    //order details
    public static final String ORDER_DETAILS_TABLE = "order_details";


}
