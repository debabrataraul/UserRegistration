package com.incture.userregistration.data;

import java.util.Arrays;
import java.util.List;

/**
 * Created by debabrataraul on 11/24/2016.
 */

public class FeedItemProvider {

    public static  List<FeedItem> readFeeds(){

       /* FeedItem(String activityType,String date, String year, String activityHeading, String userName,
                String activityDetails, String noOfComments, byte[] userProfilePic, byte[] attachedImage) {*/
        //byte [] img2=
        return Arrays.asList(
                new FeedItem("1","12 Nov","2013","John Davis created a task ","John Davis",
                        "Please create the required bill of materials for our CAPEX",null,null,null),
                new FeedItem("2","21 Nov","2013","John Davis commented on this ","Sarah Jones",
                        "Kindly book the tickets for my UK trip starting 5th Jan","2 comments",null,null),
                new FeedItem("3","29 Oct","2013","John Davis added an attachment","Sarah Jones",
                        "Kindly book the tickets for my UK trip starting 5th Jan",null,null,null),
                new FeedItem("1","12 Nov","2013","John Davis created a task ","John Davis",
                "Please create the required bill of materials for our CAPEX",null,null,null),
                new FeedItem("2","21 Nov","2013","John Davis commented on this ","Sarah Jones",
                        "Kindly book the tickets for my UK trip starting 5th Jan","5 comments",null,null),
                new FeedItem("3","29 Oct","2013","John Davis added an attachment","Sarah Jones",
                        "Kindly book the tickets for my UK trip starting 5th Jan",null,null,null)
        );

    }
}
