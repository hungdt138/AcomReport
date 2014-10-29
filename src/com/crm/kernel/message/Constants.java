/**
 *
 */
package com.crm.kernel.message;

/**
 * @author ThangPV
 *
 */
public class Constants {

    // log level
    public final static String LOG_LEVEL_OFF = "off";
    public final static String LOG_LEVEL_DEBUG = "debug";
    public final static String LOG_LEVEL_ERROR = "error";
    public final static String LOG_LEVEL_FATAL = "fatal";
    public final static String LOG_LEVEL_INFO = "info";
    public final static String LOG_LEVEL_TRACE = "trace";
    public final static String LOG_LEVEL_WARN = "warn";

    // Id
    public final static long DEFAULT_ID = 0;
    public final static int DEFAULT_PENDING_TIME = 60;
    public final static int DEFAULT_TIMEOUT = 30000;

    // Id
    public final static int QUEUE_MODE_MANUAL = 0;
    public final static int QUEUE_MODE_CONSUMER = 1;
    public final static int QUEUE_MODE_PRODUCER = 2;

    public final static int QUEUE_CONNECTION_SHARING = 0;
    public final static int QUEUE_CONNECTION_DEDICATED = 1;

    // product type
    public final static String PRODUCT_BUNDLE = "bundle";
    public final static String PRODUCT_SINGLE = "single";
    public final static String PRODUCT_CHARGING = "charging";

    // Channel
    public final static String CHANNEL_SMS = "SMS";
    public final static String CHANNEL_WEB = "web";
    public final static String CHANNEL_CORE = "core";

    /**
     * Balance state Active (CCWS)
     */
    public final static String BALANCE_STATE_ACTIVE = "Active";
    /**
     * Balance state Suspend (S1) (CCWS)
     */
    public final static String BALANCE_STATE_SUSPEND_S1 = "Suspended(S1)";
    /**
     * Balance state Disable (S2) (CCWS)
     */
    public final static String BALANCE_STATE_SUSPEND_S2 = "Disabled(S2)";
    /**
     * Balance state Retired (S3) (CCWS)
     */
    public final static String BALANCE_STATE_RETIRED_S3 = "Retired(S3)";
    /**
     * Balance state Idle (CCWS)
     */
    public final static String BALANCE_STATE_IDLE = "Idle";
    /**
     * Balance state Deleted (CCWS)
     */
    public final static String BALANCE_STATE_DELETED = "Deleted";

    // discount
    public final static String DISCOUNT_FREE_OF_CHARGE = "free";
    public final static String DISCOUNT_RATE = "rate";
    public final static String DISCOUNT_FIX_VALUE = "fix";

    // status
    public final static int SERVICE_STATUS_APPROVED = 0;
    public final static int SERVICE_STATUS_DENIED = 4;
    public final static int SERVICE_STATUS_UNREGISTER = 8;

    // Action
    public final static String ACTION_ADD_MEMBER = "add-member";

    public static final String ACTION_REGISTER_AFTER_18H = "register-after-18h";

    public final static String ACTION_REMOVE_MEMBER = "remove-member";

    public final static String ACTION_TOPUP = "topup";

    public final static String ACTION_UPGRADE = "upsell";

    public final static String ACTION_REGISTER = "register";

    public final static String ACTION_UNREGISTER = "unregister";

    public final static String ACTION_CANCEL = "cancel";

    public final static String ACTION_USER_REACTIVE = "reactive";

    public final static String ACTION_SUPPLIER_REACTIVE = "reactive";

    public final static String ACTION_USER_DEACTIVE = "deactive";

    public final static String ACTION_SUPPLIER_DEACTIVE = "deactive";

    public final static String ACTION_SUPPLIER_BARRING = "barring";

    public final static String ACTION_RECONCILE = "reconcile";

    public final static String ACTION_SUBSCRIPTION = "subscription";

    public final static String ACTION_CHARGING = "charging";

    public final static String ACTION_CHANGE_ISDN = "isdn";

    public final static String ACTION_CHANGE_IMSI = "imsi";

    public final static String ACTION_CHANGE_PROFILE = "profile";

    public final static String ACTION_RANK = "ranking";

    public final static String ACTION_ROLLBACK = "rollback";

    public final static String ACTION_ADVERTISING = "advertising";

    public final static String ACTION_CONFIRM = "confirm";

    public final static String ACTION_RENEW = "renew";
    public final static String ACTION_AUTORENEW = "autorenew";
    public final static String ACTION_UNRENEW = "unrenew";

    public final static String ACTION_USAGE = "usage";

    public final static String ACTION_BET = "bet";

    // order constants
    public static final int ORDER_STATUS_APPROVED = 0;

    public static final int ORDER_STATUS_PENDING = 1;

    public static final int ORDER_STATUS_DRAFT = 2;

    public static final int ORDER_STATUS_EXPIRED = 3;

    public static final int ORDER_STATUS_DENIED = 4;

    public static final int ORDER_STATUS_ANY = -1;

    // subscriber type constants
    public static final int ALL_SUB_TYPE = 0;

    public static final int PREPAID_SUB_TYPE = 1;

    public static final int POSTPAID_SUB_TYPE = 2;

    public static final int UNKNOW_SUB_TYPE = -1;

    // status constants
    public static final int USER_ACTIVE_STATUS = 1;

    public static final int USER_BARRING_STATUS = 2;

    public static final int USER_CANCEL_STATUS = 3;

    public static final int SUPPLIER_UNKNOW_STATUS = -1;

    public static final int SUPPLIER_ACTIVE_STATUS = 1;

    public static final int SUPPLIER_BARRING_STATUS = 2;

    public static final int SUPPLIER_CANCEL_STATUS = 3;

    // ISDN
    public final static String COUNTRY_CODE = "84";

    public final static String DOMESTIC_CODE = "0";

    // Language
    public final static String DEFAULT_LANGUAGE = "vni";

    // COLUMN
    public final static String PRIMARY_ID_FIELD = "$primaryId$";
    public final static String COMPANY_ID_FIELD = "$companyId$";
    public final static String GROUP_ID_FIELD = "$groupId$";
    public final static String USER_ID_FIELD = "$userId$";
    public final static String USER_NAME_FIELD = "$userName$";
    public final static String CREATE_DATE_FIELD = "$createDate$";
    public final static String MODIFIED_DATE_FIELD = "$modifiedDate$";
    public final static String ERROR_FIELD = "$error$";

    public final static int PRIMARY_ID_COLUMN = 1000;
    public final static int COMPANY_ID_COLUMN = 1010;
    public final static int GROUP_ID_COLUMN = 1020;
    public final static int USER_ID_COLUMN = 1030;
    public final static int USER_NAME_COLUMN = 1040;
    public final static int CREATE_DATE_COLUMN = 1050;
    public final static int MODIFIED_DATE_COLUMN = 1060;
    public final static int ERROR_COLUMN = 1070;

    // BIND
    public final static int BIND_ACTION_NONE = 0;
    public final static int BIND_ACTION_SUCCESS = 1;
    public final static int BIND_ACTION_EXPORT = 2;
    public final static int BIND_ACTION_ERROR = 3;
    public final static int BIND_ACTION_BYPASS = 4;

    // Audit
    public final static String AUDIT_SUBSCRIBER = "00";
    public final static String AUDIT_SUB_PRODUCT = "10";
    public final static String AUDIT_SUB_ORDER = "20";

    // Associate type
    public final static String ASSOCIATE_CROSS_SELL = "cross-sell";
    public final static String ASSOCIATE_UP_SELL = "up-sell";
    public final static String ASSOCIATE_BLACK_LIST = "black-list";

    // Command
    public final static String PROVISIONING_SMSC = "SMSC";
    public final static String COMMAND_SEND_SMS = "SMSC.SEND_SMS";

    public final static String PROVISIONING_MT = "MT";
    public final static String COMMAND_SEND_MT = "MT.SEND_SMS";

    // Response
    public final static String SUCCESS = "success";

    public final static String ERROR = "error";

    public final static String WRONG_ANSWER = "wrong";
    public final static String CORRECT_ANSWER = "correct";

    public final static String UPGRADING = "upgrading";

    public static final String EPOS_COS_CHANGED = "in-past-cos-registed";
    public static final String EPOS_COS_CANCELED = "in-past-cos-canceled";

    // NamTA
    public final static String REQUEST_PREFIX = "request.";
    public final static String RESPONSE_PREFIX = "response.";

    public final static String ERROR_RESOURCE_BUSY = "resource-busy";
    public final static String ERROR_INVALID_REQUEST = "invalid-request";
    public final static String ERROR_INVALID_SYNTAX = "invalid-syntax";
    public final static String ERROR_OVER_TRANSACTION_LIMIT = "over-limit";
    public final static String ERROR_CREATE_ORDER_FAIL = "create-order-fail";

    public final static String ERROR_PRODUCT_NOT_FOUND = "unknow-product";
    public final static String ERROR_ROUTE_NOT_FOUND = "unknow-route";
    public final static String ERROR_CAMPAIGN_NOT_FOUND = "unknow-campaign";
    public final static String ERROR_SEGMENT_NOT_FOUND = "unknow-segment";
    public final static String ERROR_RANK_NOT_FOUND = "unknow-rank";
    public final static String ERROR_PROVISIONING_NOT_FOUND = "unknow-provisioning";
    public final static String ERROR_COMMAND_NOT_FOUND = "unknow-command";
    public final static String ERROR_ORDER_NOT_FOUND = "unknow-order";
    public final static String ERROR_SUBSCRIBER_NOT_FOUND = "unknow-subscriber";
    public final static String ERROR_SUBSCRIPTION_NOT_FOUND = "unknow-subscription";
    public final static String ERROR_MEMBER_NOT_FOUND = "unknow-member";
    public final static String ERROR_GROUP_NOT_FOUND = "unknow-group";
    public final static String ERROR_BALANCE_NOT_FOUND = "unknow-balance";
    public final static String ERROR_PROCESS_CLASS = "unknow-class";
    public final static String ERROR_PROCESS_METHOD = "unknow-method";
    public final static String ERROR_UNSUPPORT = "unsupport";
    public static final String ERROR_INVALID_PROMOTION = "invalid-promotion";

    public final static String ERROR_UNSUPPORT_PREPAID = "unsupport-prepaid";
    public final static String ERROR_UNSUPPORT_POSTPAID = "unsupport-postpaid";

    public final static String ERROR_DUPLICATED = "duplicated";
    public final static String ERROR_REGISTERED = "registered";
    public final static String ERROR_UNREGISTERED = "unregistered";
    public final static String ERROR_EXPIRED = "expired";
    public final static String ERROR_BLACKLIST_PRODUCT = "blacklist-product";

    public final static String ERROR_KEYWORD = "unknow-keyword";
    public final static String ERROR_OUT_OF_TIME = "out-of-time";

    public final static String ERROR_CONNECTION = "disconnected";
    public final static String ERROR_TIMEOUT = "timeout";
    public final static String ERROR_RECURSIVE_COMMAND = "recursive-command";
    public final static String ERROR_DENIED_COS = "denied-cos";
    public final static String ERROR_DENIED_STATUS = "denied-status";
    public final static String ERROR_DENIED_SUBSCRIBER_TYPE = "denied-subscriber-type";
    public final static String ERROR_NOT_ENOUGH_MONEY = "not-enought-money";
    public final static String ERROR_BALANCE_TOO_LARGE = "balance-too-large";
    public final static String ERROR_EXPIRE_TOO_LARGE = "expire-too-large";
    public final static String ERROR_BALANCE_TOO_SMALL = "balance-too-small";
    public final static String ERROR_INVALID_OWNER = "invalid-owner";
    public final static String ERROR_INVALID_DELIVER = "invalid-deliver";
    public final static String ERROR_OVER_MEMBER_LIMITATION = "over-max-member";
    public final static String ERROR_EXSITED_MEMBER = "existed-member";
    public final static String ERROR_INVALID_ACTIVE_DATE = "invalid-active-date";
    public final static String ERROR_INVALID_PARAMETER = "invalid-parameter";

    public final static String ERROR_INVALID_CONFIRM = "invalid-confirm";
    public final static String ERROR_OVER_CONFIRM = "over-confirm";

    public final static String ERROR_MERCHANT_NOT_FOUND = "unknow-mechant";
    public final static String ERROR_AGENT_NOT_FOUND = "unknow-agent";
    // DATE
    public final static String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    // Balances
    public final static String PARM_OFFER_NAME = "offer.name";
    public final static String PARM_OFFER_DURATION = "offer.duration";
    public final static String PARM_OFFER_DURATION_VALUE = "364";
    public final static String PARM_MAX_MEMBER = "max-member";

    // VB Error response
    public final static String ERROR_NOTIFY_OVER = "notify-over";

    // Command cost
    public final static String COST_LOW = "low";
    public final static String COST_MEDIUM = "medium";
    public final static String COST_HIGH = "high";
    public final static int COST_LOW_VALUE = 500;
    public final static int COST_HIGH_VALUE = 1000;

    // Lucky sim product keyword
    public final static String INVALID_RECHARGE_NOT_USED = "invalid-recharge-not-used";
    public final static String INVALID_RECHARGE_USED = "invalid-recharge-used";

    // Insert table product keyword
    public final static String VERIFY_KEYWORD = "true";

    // Charggw tariff_plan
    public final static String ERROR_TARIFF_NOT_FOUND = "unknow-tariff";

    public final static int CONTENT_CODE_MOBILE_ORIGINATED = 100;
    public final static int CONTENT_CODE_MOBILE_ORIGINATED_SMS = 102;
    public final static int CONTENT_CODE_MOBILE_TERMINATED_SMS = 103;
    public final static int CONTENT_CODE_CALL_FOWARD = 104;
    public final static int CONTENT_CODE_MMS_ORIGINATED = 120;
    public final static int CONTENT_CODE_MMS_TERMINATED = 121;
    public final static int CONTENT_CODE_DATA = 122;
    public final static int CONTENT_CODE_MMS_FOWARDED = 125;

    // Upgrade SMS User Keyword
    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_INACTIVE = 0;
    public static final int STATUS_PROCESSING = 2;
    public static final int STATUS_CANCEL = 3;

    // cgw type
    public final static String CGW_ACTION_ONLINE = "online";
    public final static String CGW_ACTION_OFFLINE = "offline";
    public final static String CGW_STATUS_D = "D";
    public final static String CGW_STATUS_U = "U";

    // campaign status
    public final static int CAMPAIGN_STATUS_APPROVED = 1;
    public final static int CAMPAIGN_STATUS_DENIED = 0;

    /**
     * Status OTP Violet
     */
    public static final int INVALID_REQUEST_ID_407 = 407;
    public static final int OK_200 = 200;
    public static final int BAD_400 = 400;
    public static final int UNAUTHORIZED_401 = 401;
    public static final int INVALID_402 = 402;
    public static final int FORBIDDEN_403 = 403;
    public static final int GET_OTP_ERROR_404 = 404;
    public static final int METHOD_NOT_ALLOWED_405 = 405;
    public static final int NOT_ACCEPTABLE_406 = 406;
    public static final int INVALID_ACTION_408 = 408;
    public static final int NOT_SUCCESS_503 = 503;

    // List OID
    public static final int MOBIFONE = 1;
    public static final int VINAPHONE = 2;
    public static final int VIETTEL = 3;
    public static final int VIETNAMOBILE = 4;

    public final static String ACTION_ANSWER = "answer";

    public final static String ACTION_TRUE_ANSWER = "true-answer";

    public final static String ACTION_WRONG_ANSWER = "wrong-answer";

    public final static String ACTION_ORVER_QUESTION = "most-question";

    public final static String ACTION_ORVER_QUESTION_DAY = "over-question-day";

    public final static String ACTION_ORVER_QUESTION_DAY_REG = "over-question-day-register";

    public final static String ROLE_ADMIN = "admin";
    public final static String ROLE_CP = "cp";
    public final static String ROLE_TELCO = "telco";

    public final static int PAGE_SIZE = 30;
}
