/**
 *
 */
package com.crm.product.bean;


/**
 * @author Hungdt
 * Edited Description: Add alias field
 */
public class ProductEntry {

    private long productId = 0;
    private String alias = "";
    private String title = "";
    private String productType = "";
    private String SKU = "";
    private String partNumber = "";
    private double price = 0;

    private boolean termOfUse = false;
    private int termPeriod = 0;
    private String termUnit = "";

    private boolean subscription = false;
    private String subscriptionUnit = "";
    private int subscriptionPeriod = 0;
    private int gracePeriod = 0;
    private String graceUnit = "";

    private int[] subscriberTypes = new int[0];
    private String[] availCOS = new String[0];
    private String[] availStatus = new String[0];
    private double minBalance = 0;
    private int minExpirationDays = 0;
    private double maxBalance = 0;
    private int maxExpirationDays = 0;

    private String postpaidSKU = "";

    private long[] upgradeProducts = new long[0];
    private long[] blacklistProducts = new long[0];

    private String accumulator = "";
    private String offerName = "";
    private String offerGroup = "";

    private String circleName = "";
    private String circleGroup = "";
    private String circleProvider = "";

    private int maxMembers = 0;
    private String memberType = "";

    private boolean auditEnable = false;

    private int status = 0;

    private long merchantId = 0;

    private int opId = 0;

    private String host = "";

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isTermOfUse() {
        return termOfUse;
    }

    public void setTermOfUse(boolean termOfUse) {
        this.termOfUse = termOfUse;
    }

    public int getTermPeriod() {
        return termPeriod;
    }

    public void setTermPeriod(int termPeriod) {
        this.termPeriod = termPeriod;
    }

    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public String getSubscriptionUnit() {
        return subscriptionUnit;
    }

    public void setSubscriptionUnit(String subscriptionUnit) {
        this.subscriptionUnit = subscriptionUnit;
    }

    public int getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public void setSubscriptionPeriod(int subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public String getGraceUnit() {
        return graceUnit;
    }

    public void setGraceUnit(String graceUnit) {
        this.graceUnit = graceUnit;
    }

    public int[] getSubscriberTypes() {
        return subscriberTypes;
    }

    public void setSubscriberTypes(int[] subscriberTypes) {
        this.subscriberTypes = subscriberTypes;
    }

    public String[] getAvailCOS() {
        return availCOS;
    }

    public void setAvailCOS(String[] availCOS) {
        this.availCOS = availCOS;
    }

    public String[] getAvailStatus() {
        return availStatus;
    }

    public void setAvailStatus(String[] availStatus) {
        this.availStatus = availStatus;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public int getMinExpirationDays() {
        return minExpirationDays;
    }

    public void setMinExpirationDays(int minExpirationDays) {
        this.minExpirationDays = minExpirationDays;
    }

    public double getMaxBalance() {
        return maxBalance;
    }

    public void setMaxBalance(double maxBalance) {
        this.maxBalance = maxBalance;
    }

    public int getMaxExpirationDays() {
        return maxExpirationDays;
    }

    public void setMaxExpirationDays(int maxExpirationDays) {
        this.maxExpirationDays = maxExpirationDays;
    }

    public String getPostpaidSKU() {
        return postpaidSKU;
    }

    public void setPostpaidSKU(String postpaidSKU) {
        this.postpaidSKU = postpaidSKU;
    }

    public long[] getUpgradeProducts() {
        return upgradeProducts;
    }

    public void setUpgradeProducts(long[] upgradeProducts) {
        this.upgradeProducts = upgradeProducts;
    }

    public long[] getBlacklistProducts() {
        return blacklistProducts;
    }

    public void setBlacklistProducts(long[] blacklistProducts) {
        this.blacklistProducts = blacklistProducts;
    }

    public String getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(String accumulator) {
        this.accumulator = accumulator;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferGroup() {
        return offerGroup;
    }

    public void setOfferGroup(String offerGroup) {
        this.offerGroup = offerGroup;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleGroup() {
        return circleGroup;
    }

    public void setCircleGroup(String circleGroup) {
        this.circleGroup = circleGroup;
    }

    public String getCircleProvider() {
        return circleProvider;
    }

    public void setCircleProvider(String circleProvider) {
        this.circleProvider = circleProvider;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public boolean isAuditEnable() {
        return auditEnable;
    }

    public void setAuditEnable(boolean auditEnable) {
        this.auditEnable = auditEnable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

}
