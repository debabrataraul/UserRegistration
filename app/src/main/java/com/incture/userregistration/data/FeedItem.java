package com.incture.userregistration.data;

/**
 * Created by debabrataraul on 11/24/2016.
 */

public class FeedItem {
    private String activityType;
    private String date;
    private String year;
    private String activityHeading;
    private String userName;
    private String activityDetails;
    private String noOfComments;
    private byte [] userProfilePic;
    private byte [] attachedImage;
    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getActivityHeading() {
        return activityHeading;
    }

    public void setActivityHeading(String activityHeading) {
        this.activityHeading = activityHeading;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(String activityDetails) {
        this.activityDetails = activityDetails;
    }

    public String getNoOfComments() {
        return noOfComments;
    }

    public void setNoOfComments(String noOfComments) {
        this.noOfComments = noOfComments;
    }

    public byte[] getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(byte[] userProfilePic) {
        this.userProfilePic = userProfilePic;
    }

    public byte[] getAttachedImage() {
        return attachedImage;
    }

    public void setAttachedImage(byte[] attachedImage) {
        this.attachedImage = attachedImage;
    }

    public FeedItem(String activityType,String date, String year, String activityHeading, String userName,
                    String activityDetails, String noOfComments, byte[] userProfilePic, byte[] attachedImage) {
        this.activityType=activityType;
        this.date = date;
        this.year = year;
        this.activityHeading = activityHeading;
        this.userName = userName;
        this.activityDetails = activityDetails;
        this.noOfComments = noOfComments;
        this.userProfilePic = userProfilePic;
        this.attachedImage = attachedImage;
    }
}
