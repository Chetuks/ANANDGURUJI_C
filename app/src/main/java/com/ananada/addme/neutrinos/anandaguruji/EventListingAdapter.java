package com.ananada.addme.neutrinos.anandaguruji;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.ananada.addme.neutrinos.anandaguruji.Model.Events;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.ananada.addme.neutrinos.anandaguruji.LikesActivity.EVENTBEAN;

/**
 * Created by mahiti on 12/6/15.
 */
public class EventListingAdapter extends BaseAdapter {
    public static final String EVENTMODIFIEDDATE = "EVENTMODIFIEDDATE";
    Context context;
    List<Events> rowItems;
    private String userId;
    Activity activity;
    long _eventId;
    int apiCode;
    SharedPreferences myAppPreference;
    //PushingResultsInterface pushingResultsInterface = null;

    public EventListingAdapter(Activity activity, Context context, List<Events> items) {
        this.context = context;
        this.rowItems = items;
        this.activity = activity;
        this.userId = "";
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    /**
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Viewholder vh;
        final ProgressDialog pd;
        pd = new ProgressDialog(context);
        pd.setMessage("Buffering video please wait...");
        pd.setCancelable(true);
        pd.show();


        Events events = new Events();

        View layoutView = convertView;
        Log.e("position-->", "getView" + position);
        if (layoutView == null) {

            vh = new Viewholder();

            assert inflater != null;
            layoutView = inflater.inflate(R.layout.activity_comments, viewGroup, false);
            vh.eventHeadingTitle = (TextView) layoutView.findViewById(R.id.event_heading_tittle);
            vh.eventSubHeading = (TextView) layoutView.findViewById(R.id.event_subtittle);
            vh.eventday = (TextView) layoutView.findViewById(R.id.event_day);
            vh.eventMonth = (TextView) layoutView.findViewById(R.id.event_month);
            vh.eventTime = (TextView) layoutView.findViewById(R.id.event_time);
            vh.eventImage = (VideoView) layoutView.findViewById(R.id.event_image);
            vh.commentsLayout = (LinearLayout) layoutView.findViewById(R.id.comments);
            vh.likeincrement = (TextView) layoutView.findViewById(R.id.likeincrement);


            vh.eventImage.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //close the progress dialog when buffering is done
                    pd.dismiss();
                }
            });
            vh.commentsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, LikesActivity.class);
                    intent.putExtra(EVENTBEAN, rowItems.get(position));
                    activity.startActivity(intent);
                    activity.finish();
                }
            });

            vh.eventContainerLayout = (LinearLayout) layoutView.findViewById(R.id.event_image_linear);
            setContentToView(vh, position);
            layoutView.setTag(vh);
            final View finalLayoutView = layoutView;

            vh.eventContainerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Events selected_value = rowItems.get(position);
                    SharedPreferences.Editor editor= myAppPreference.edit();
                    Logger.logD("Date formate------>",rowItems.get(position).getEvent_date());
                    String changeDateFormate= String.valueOf(rowItems.get(position).getBadgeUpdateCount());
                    Logger.logD("Date formate------>",changeDateFormate);
                    editor.putString(EVENTMODIFIEDDATE,changeDateFormate);
                    editor.apply();
                    callApiForUpdateBadgeCount(selected_value);
                    OnFragmentInteractionListener mListener = (OnFragmentInteractionListener) context;
                    if (null != mListener) {

                        if (selected_value != null) {
                            mListener.onFragmentInteraction(selected_value, position, finalLayoutView, String.valueOf(apiCode));
                        }
                    }*/
                    Intent intent = new Intent(activity, LikesActivity.class);
                    intent.putExtra(EVENTBEAN, rowItems.get(position));
                    //Logger.logD("nameurl",""+events.getEvent_name());

                    activity.startActivity(intent);
                    activity.finish();
                }
            });
        } else {
            layoutView = convertView;
            vh = (Viewholder) layoutView.getTag();
        }
        return layoutView;
    }


    private void setContentToView(Viewholder vh, final int position) {
        MediaController mediaC;
        vh.eventHeadingTitle.setText(rowItems.get(position).getEvent_name());
        vh.likeincrement.setText(String.valueOf(rowItems.get(position).getLikes()));
        Logger.logD("checklikee", "" + rowItems.get(position).getLikes());
        switch (position) {
            case 0:
                //vh.likeincrement.setText(String.valueOf(rowItems.get(position).getLikes()));
                String video_url = rowItems.get(position).getUrl();/*"http://file2.video9.in/english/movie/2014/x-men-_days_of_future_past/X-Men-%20Days%20of%20Future%20Past%20Trailer%20-%20[Webmusic.IN].3gp";*/
                Uri uri = Uri.parse(video_url);
                Logger.logD("videourl", "" + rowItems.get(position).getUrl());
                vh.eventImage.setVideoURI(uri);
                vh.eventImage.getFitsSystemWindows();
                mediaC = new MediaController(context);
                mediaC.setAnchorView(vh.eventImage);
                vh.eventImage.setMediaController(mediaC);
                //vh.eventImage.start();

               /*Picasso.with(context)
                       .load(R.drawable.ag1)
                       .placeholder(R.drawable.ag1)
                       .fit()
                       .error(R.drawable.ag1)
                       .into(vh.eventImage);*/
                break;
            case 1:
                //vh.likeincrement.setText("2");
                video_url = rowItems.get(position).getUrl();//"http://file2.video9.in/english/movie/2014/x-men-_days_of_future_past/X-Men-%20Days%20of%20Future%20Past%20Trailer%20-%20[Webmusic.IN].3gp";
                uri = Uri.parse(video_url);
                Logger.logD("videourl", "" + rowItems.get(position).getUrl());
                vh.eventImage.setVideoURI(uri);
                vh.eventImage.getFitsSystemWindows();
                mediaC = new MediaController(context);
                mediaC.setAnchorView(vh.eventImage);
                vh.eventImage.setMediaController(mediaC);
               /*Picasso.with(context)
                       .load(R.drawable.ag2)
                       .fit()
                       .placeholder(R.drawable.ag2)
                       .error(R.drawable.ag2)
                       .into(vh.eventImage);*/
                break;
            case 2:
                //vh.likeincrement.setText("5");
                video_url = rowItems.get(position).getUrl();//"http://file2.video9.in/english/movie/2014/x-men-_days_of_future_past/X-Men-%20Days%20of%20Future%20Past%20Trailer%20-%20[Webmusic.IN].3gp";
                uri = Uri.parse(video_url);
                Logger.logD("videourl", "" + rowItems.get(position).getUrl());
                vh.eventImage.setVideoURI(uri);
                vh.eventImage.getFitsSystemWindows();
                mediaC = new MediaController(context);
                mediaC.setAnchorView(vh.eventImage);
                vh.eventImage.setMediaController(mediaC);
               /*Picasso.with(context)
                       .load(R.drawable.ag3)
                       .fit()
                       .placeholder(R.drawable.ag3)
                       .error(R.drawable.ag3)
                       .into(vh.eventImage);*/
                break;
            default:
                break;
        }

        if (rowItems.get(position).getEvent_date() != null) {
            String getDateInToDisplayFormate = getDateFormate(rowItems.get(position).getEvent_date());
            String[] getParceDate = getDateInToDisplayFormate.split("#");
            vh.eventday.setText(getParceDate[0]);
            vh.eventMonth.setText(getParceDate[1]);
            vh.eventTime.setText(getParceDate[2]);
        }
    }

    private String getDateFormate(String event_date) {
        String sendDateTemp = "";
        String[] parceDate = event_date.split(" ");
        String getTime = parceDate[1] + " " + parceDate[2];
        String[] getDayMonth = parceDate[0].split("/");
        String getDay = getDayMonth[0];
        String getMonthYear = Utils.getMonthWithYear(getDayMonth[1], getDayMonth[2]);
        return getDay + "#" + getMonthYear + "#" + getTime;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private class Viewholder {
        TextView eventHeadingTitle;
        TextView eventSubHeading;
        TextView eventday;
        TextView eventMonth;
        TextView eventTime;
        VideoView eventImage;
        LinearLayout eventContainerLayout;
        LinearLayout commentsLayout;
        TextView likeincrement;
    }
}