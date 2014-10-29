/**
 *
 */
package com.crm.subscriber.bean;

import java.io.Serializable;
import java.util.Date;

import com.crm.kernel.message.Constants;

/**
 * @author HungDT
 *
 */
public class SubscriberOrder implements Serializable {
    // PK fields -->

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long orderId = Constants.DEFAULT_ID;

    // Audit fields -->
    private long userId = Constants.DEFAULT_ID;
    private String userName = "";
    private Date createDate = null;
    private Date modifiedDate = null;

    // Other fields -->
    private long merchantId = Constants.DEFAULT_ID;
    private String orderType = "";
    private Date orderDate = null;
    private String orderNo = "";
    private Date cycleDate = null;

    private long subscriberId = Constants.DEFAULT_ID;
    private long subProductId = Constants.DEFAULT_ID;
    private int subscriberType = Constants.PREPAID_SUB_TYPE;
    private String isdn = "";
    private String shipTo = "";
    private long productId = Constants.DEFAULT_ID;

    private double offerPrice = 0;
    private double price = 0;
    private int quantity = 1;
    private double discount = 0;
    private double amount = 0;
    private double score = 0;
    private String currency = "";

    private int status = Constants.ORDER_STATUS_PENDING;
    private String cause = "";
    private String description = "";
    private int motype = 0;
    private String productTitle = "";
    private String merchant = "";
    private String channel = "";

    private String productAlias = "";

    private String serviceAddr = "";

    private String delivery_status = "";

    private int telcoId = 0;

    private int totalPage = 0;
    private int totalRecord = 0;
    private int count = 0;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Get the value of telcoId
     *
     * @return the value of telcoId
     */
    public int getTelcoId() {
        return telcoId;
    }

    /**
     * Set the value of telcoId
     *
     * @param telcoId new value of telcoId
     */
    public void setTelcoId(int telcoId) {
        this.telcoId = telcoId;
    }

    /**
     * Get the value of delivery_status
     *
     * @return the value of delivery_status
     */
    public String getDelivery_status() {
        return delivery_status;
    }

    /**
     * Set the value of delivery_status
     *
     * @param delivery_status new value of delivery_status
     */
    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    /**
     * Get the value of serviceAddr
     *
     * @return the value of serviceAddr
     */
    public String getServiceAddr() {
        return serviceAddr;
    }

    /**
     * Set the value of serviceAddr
     *
     * @param serviceAddr new value of serviceAddr
     */
    public void setServiceAddr(String serviceAddr) {
        this.serviceAddr = serviceAddr;
    }

    /**
     * Get the value of productAlias
     *
     * @return the value of productAlias
     */
    public String getProductAlias() {
        return productAlias;
    }

    /**
     * Set the value of productAlias
     *
     * @param productAlias new value of productAlias
     */
    public void setProductAlias(String productAlias) {
        this.productAlias = productAlias;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getMotype() {
        return motype;
    }

    public void setMotype(int motype) {
        this.motype = motype;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCycleDate() {
        return cycleDate;
    }

    public void setCycleDate(Date cycleDate) {
        this.cycleDate = cycleDate;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public long getSubProductId() {
        return subProductId;
    }

    public void setSubProductId(long subProductId) {
        this.subProductId = subProductId;
    }

    public int getSubscriberType() {
        return subscriberType;
    }

    public void setSubscriberType(int subscriberType) {
        this.subscriberType = subscriberType;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String destAddress) {
        this.shipTo = destAddress;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
