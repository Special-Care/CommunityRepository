package com.nsusoft.community.entity;

public class Notification {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.ID
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.NOTIFIER
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    private Long notifier;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.RECEIVER
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    private Long receiver;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.OUTER_ID
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    private Long outerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.TYPE
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.STATUS
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.NOTIFICATION_NAME
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    private String notificationName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column NOTIFICATION.OUTER_TITLE
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    private String outerTitle;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.ID
     *
     * @return the value of NOTIFICATION.ID
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.ID
     *
     * @param id the value for NOTIFICATION.ID
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.NOTIFIER
     *
     * @return the value of NOTIFICATION.NOTIFIER
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public Long getNotifier() {
        return notifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.NOTIFIER
     *
     * @param notifier the value for NOTIFICATION.NOTIFIER
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public void setNotifier(Long notifier) {
        this.notifier = notifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.RECEIVER
     *
     * @return the value of NOTIFICATION.RECEIVER
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public Long getReceiver() {
        return receiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.RECEIVER
     *
     * @param receiver the value for NOTIFICATION.RECEIVER
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.OUTER_ID
     *
     * @return the value of NOTIFICATION.OUTER_ID
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public Long getOuterId() {
        return outerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.OUTER_ID
     *
     * @param outerId the value for NOTIFICATION.OUTER_ID
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public void setOuterId(Long outerId) {
        this.outerId = outerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.TYPE
     *
     * @return the value of NOTIFICATION.TYPE
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.TYPE
     *
     * @param type the value for NOTIFICATION.TYPE
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.GMT_CREATE
     *
     * @return the value of NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.GMT_CREATE
     *
     * @param gmtCreate the value for NOTIFICATION.GMT_CREATE
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.STATUS
     *
     * @return the value of NOTIFICATION.STATUS
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.STATUS
     *
     * @param status the value for NOTIFICATION.STATUS
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.NOTIFICATION_NAME
     *
     * @return the value of NOTIFICATION.NOTIFICATION_NAME
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public String getNotificationName() {
        return notificationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.NOTIFICATION_NAME
     *
     * @param notificationName the value for NOTIFICATION.NOTIFICATION_NAME
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName == null ? null : notificationName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column NOTIFICATION.OUTER_TITLE
     *
     * @return the value of NOTIFICATION.OUTER_TITLE
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public String getOuterTitle() {
        return outerTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column NOTIFICATION.OUTER_TITLE
     *
     * @param outerTitle the value for NOTIFICATION.OUTER_TITLE
     *
     * @mbg.generated Sat Apr 04 17:42:27 CST 2020
     */
    public void setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle == null ? null : outerTitle.trim();
    }
}