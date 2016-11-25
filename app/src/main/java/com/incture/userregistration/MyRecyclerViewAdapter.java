package com.incture.userregistration;

/**
 * Created by debabrataraul on 11/24/2016.
 */

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.incture.userregistration.data.FeedItem;
//import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Nilanchala Panigrahy on 10/25/16.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<FeedItem> feedItemList;
    private Context mContext;

    public MyRecyclerViewAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activities_recycler_view, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final FeedItem feedItem = feedItemList.get(i);

        customViewHolder.dateTV.setText(feedItem.getDate());
        customViewHolder.yearTV.setText(feedItem.getYear());
        customViewHolder.activityHeadingTV.setText(feedItem.getActivityHeading());
        customViewHolder.activityDetailsTV.setText(feedItem.getActivityDetails());
        customViewHolder.noOfCommentsTV.setText(feedItem.getNoOfComments());
        if(feedItem.getNoOfComments()!=null){
            customViewHolder.noOfCommentsTV.setVisibility(View.VISIBLE);
        }
        if(feedItem.getDate().equals("29 Oct")){
            customViewHolder.attachedImageIMRV.setVisibility(View.VISIBLE);
        }
        if(feedItem.getActivityType().equals("1")){
            customViewHolder.activityTypeIMRV.setImageResource(R.drawable.ic_send_white_24dp);
            customViewHolder.activityTypeIMRV.setBackgroundResource(R.drawable.circular_view_task);
        }else if(feedItem.getActivityType().equals("2")){
            customViewHolder.activityTypeIMRV.setImageResource(R.drawable.ic_message_white_24dp);
            customViewHolder.activityTypeIMRV.setBackgroundResource(R.drawable.circular_view_comment);
        } else if(feedItem.getActivityType().equals("3")){
            customViewHolder.activityTypeIMRV.setImageResource(R.drawable.ic_photo_white_24dp);
            customViewHolder.activityTypeIMRV.setBackgroundResource(R.drawable.circular_view_attachment);
        }

        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.debabrata);

        customViewHolder.userProfilePicIMRV.setImageResource(R.drawable.profile_pic1);

    }

    @Override
    public int getItemCount() {
        return feedItemList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView activityTypeIMRV,userProfilePicIMRV,attachedImageIMRV;
        protected TextView dateTV,yearTV,activityHeadingTV,userNameTV,activityDetailsTV,noOfCommentsTV;

        public CustomViewHolder(View view) {
            super(view);
            // this.imageView = (ImageView) view.findViewById(R.id.profilePic);
            this.dateTV = (TextView) view.findViewById(R.id.dateRV);
            this.yearTV= (TextView) view.findViewById(R.id.yearRV);
            this.activityHeadingTV=(TextView) view.findViewById(R.id.activityHeadingRV);
            this.userNameTV= (TextView) view.findViewById(R.id.userNameRV);
            this.activityDetailsTV= (TextView) view.findViewById(R.id.activityDetailsRV);
            this.noOfCommentsTV= (TextView) view.findViewById(R.id.noOfCommentsRV);

            this.activityTypeIMRV= (ImageView) view.findViewById(R.id.activityTypeIMRV);
            this.userProfilePicIMRV= (ImageView) view.findViewById(R.id.userProfilePicIMRV);
            this.attachedImageIMRV= (ImageView) view.findViewById(R.id.attachedImageIMRV);

        }
    }


    /*public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 12;

        paint.setAntiAlias(true);
        canvas.drawARGB(5, 5, 5, 5);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }*/

}
