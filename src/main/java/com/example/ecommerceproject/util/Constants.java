package com.example.ecommerceproject.util;

public class Constants {
    public static final String pathImage = "image/";
    public static final long ACCESS_TOKEN_EXPIRATION = 10 * 20 * 1000;
    public static final long REFRESH_TOKEN_EXPIRATION = 60 * 60 * 1000;
    public static final String JWT_SECRET = "nguyenconguan";

    public static final class ORDER_STATUS {
        public static final Integer INCART = 0;
        public static final Integer CHECKEDOUT = 1;
        public static final Integer REVIEWED = 2;
    };

    public static final class RECEIPT_STATUS {
        public static final Integer CHECKEDOUT = 1;
        public static final Integer INSHIPPING = 2;
        public static final Integer RECEIVED = 3;
        public static final Integer CANCEL = 4;
    }



    public static final class ADMIN_APPROVAL_ACTION {
        public static final Integer ACCEPT = 1;
        public static final Integer REFUSE = 0;
    }
}
