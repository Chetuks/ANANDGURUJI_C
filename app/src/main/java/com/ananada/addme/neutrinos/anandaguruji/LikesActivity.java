package com.ananada.addme.neutrinos.anandaguruji;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.ananada.addme.neutrinos.anandaguruji.Model.Events;
import com.ananada.addme.neutrinos.anandaguruji.Model.MemberDetails;
import com.ananada.addme.neutrinos.anandaguruji.constants.Constants;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LikesActivity extends AppCompatActivity {
    public static final String EVENTBEAN = "userSelectedBean";
    VideoView video;
    String video_url = "http://file2.video9.in/english/movie/2014/x-men-_days_of_future_past/X-Men-%20Days%20of%20Future%20Past%20Trailer%20-%20[Webmusic.IN].3gp";
    ProgressDialog pd;
    EditText cmnt;
    private Button submit;
    MediaController mediaC;
    String formattedDate;
    public Events detailsEvent;
    private SharedPrefManager sharedPrefManager;
    private com.like.LikeButton likebtn;
    public String uploadid;
    String macaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);
        detailsEvent = (Events) getIntent().getSerializableExtra(EVENTBEAN);
        Logger.logD("eventcheck",""+detailsEvent);
        uploadid = detailsEvent.getEvent_id();
        Logger.logD("uplllll",""+uploadid);
        if (detailsEvent != null && !detailsEvent.getUrl().equals(""))
            video_url = detailsEvent.getUrl();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPrefManager = new SharedPrefManager(LikesActivity.this);
        video = (VideoView) findViewById(R.id.video_view);
        pd = new ProgressDialog(LikesActivity.this);
        pd.setMessage("Buffering video please wait...");
        pd.setCancelable(false);
        pd.show();

        Uri uri = Uri.parse(video_url);
        video.setVideoURI(uri);
        video.start();
        mediaC = new MediaController(this);
        mediaC.setAnchorView(video);
        video.setMediaController(mediaC);
        init();
        callApiForCommentList(detailsEvent);
        likestatus();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    try {
                        String text = cmnt.getText().toString();
                        clearAllTheFileds();
                        // JSONObject jsonObject = createCommentJsonObject(text, detailsEvent);
                        callServerApi(text, detailsEvent);
                        //     setDataToAdapter(text);
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        macaddress=getDeviceId();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //close the progress dialog when buffering is done
                pd.dismiss();
            }
        });

       likebtn.setOnLikeListener(new OnLikeListener() {
           @Override
           public void liked(LikeButton likeButton) {
               likeTrueapi(macaddress,detailsEvent.getEvent_id(),true,"save");
           }

           @Override
           public void unLiked(LikeButton likeButton) {
               likeTrueapi(macaddress,detailsEvent.getEvent_id(),false,"save");
           }
       });
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        formattedDate = df.format(c);
        Log.d("currentDateTimeString", formattedDate);
    }

    private void likestatus() {
        String url="http://216.98.9.235:8180/api/jsonws/addMe-portlet.likes/Store-retrieve-likes/macaddress/"+getDeviceId()+"/appuniqueid/20826/uploadid/"+detailsEvent.getEvent_id()+"/-like/status/retrieve";
        Log.v("theresultoflikestatus", "the result is" + url);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // ProgressUtils.CancelProgress(progressDialog);
                        try {
                            Logger.logD("LikestatusResponse", "->" + response);
                            JSONObject jsonObject=new JSONObject(response);
                            Logger.logD("statusboolean",""+jsonObject);
                            Boolean status=jsonObject.getBoolean("Liked");
                            Logger.logD("getjkshdjk",""+status);
                            if(status){
                                likebtn.setLiked(true);
                            }else{
                                likebtn.setLiked(false);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.v("the result of like", "the result is" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(LikesActivity.this).add(postRequest);
    }


    private void likeTrueapi(String macaddress,String uploadid,boolean liked,String status) {
        String url="http://216.98.9.235:8180/api/jsonws/addMe-portlet.likes/Store-retrieve-likes/macaddress/"+macaddress+"/appuniqueid/20826/uploadid/"+uploadid+"/like/"+liked+"/status/"+status;
        Log.v("the result of like", "the result is" + url);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // ProgressUtils.CancelProgress(progressDialog);
                        try {
                            Logger.logD("Like Response list", "->" + response);
                            //parceAndSetCommentAdapter(response);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.v("the result of like", "the result is" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(LikesActivity.this).add(postRequest);
    }

    private void callApiForCommentList(Events detailsEvent) {
        callServerForCommentListApi(detailsEvent.getUser_id(), detailsEvent.getEvent_id());
    }

    private JSONObject createCommentJsonObject(String text, Events detailsEvent) {
        JSONObject jsonObject = new JSONObject();
        try {

            JSONArray jsonArray = new JSONArray();
            JSONObject createUserJsonObject = new JSONObject();
            createUserJsonObject.put("comment", text);
            createUserJsonObject.put("user_id", String.valueOf(detailsEvent.getUser_id()));
            createUserJsonObject.put("uploaded_id", String.valueOf(detailsEvent.getEvent_id()));
            createUserJsonObject.put("createddate", String.valueOf(formattedDate));
            jsonArray.put(createUserJsonObject);
            jsonObject.put("ct", jsonArray);
            Logger.logD("JsonObject", "to comment api" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * @param text
     * @param detailsEvent
     */
    private void callServerApi(String text, final Events detailsEvent) {
        String URL = "http://216.98.9.235:8180/api/jsonws/addMe-portlet.comments/Store-comments/comment/" + text + "/userid/" + detailsEvent.getUser_id() + "/uploadid/" + detailsEvent.getEvent_id() + "/createddate/" + formattedDate + "/status/save";
        Log.v("the result is", "the result is" + URL);
        String convertedURL = URL.replace(" ", "%20");
        StringRequest postRequest = new StringRequest(Request.Method.POST, convertedURL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // ProgressUtils.CancelProgress(progressDialog);
                        try {
                            Logger.logD("COMMENT RESPONSE", "->" + response);
                            JSONObject jsonObject1 = new JSONObject(response);
                            if (jsonObject1.getInt("response") == 2) {
                                callApiForCommentList(detailsEvent);
                            } else {
                                // TODO
                                Toast.makeText(LikesActivity.this,"No response from server",Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.v("the result is", "the result is" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(LikesActivity.this).add(postRequest);
    }


    private void callServerForCommentListApi(int userId, String eventid) {
        String URL = "http://216.98.9.235:8180/api/jsonws/addMe-portlet.comments/Store-comments/-comment/userid/" + userId + "/uploadid/" + eventid + "/-createddate/status/retrieve";
        Log.v("the result is", "the result is" + URL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // ProgressUtils.CancelProgress(progressDialog);
                        try {
                            Logger.logD("COMMENT Response list", "->" + response);
                            parceAndSetCommentAdapter(response);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.v("the result is", "the result is" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(LikesActivity.this).add(postRequest);
    }

    private void parceAndSetCommentAdapter(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("ct");
            List<MemberDetails> list = new ArrayList<MemberDetails>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {

                    MemberDetails mobj = new MemberDetails();
                    JSONObject objCommentListing = jsonArray.getJSONObject(i);
                    mobj.setMember_id(objCommentListing.getString("k1"));
                    mobj.setMember_name(objCommentListing.getString("k6"));
                    mobj.setComment_id(objCommentListing.getString("k2"));
                    mobj.setImage_url(objCommentListing.getString("k7"));
                    mobj.setDesignation(objCommentListing.getString("k8"));
                    mobj.setCompany_name(objCommentListing.getString("k9"));
                    mobj.setComment(objCommentListing.getString("k3").trim());
                    mobj.setLike_status(objCommentListing.getString("k5"));
                    mobj.setLike(objCommentListing.getString("k4"));
                    if (objCommentListing.has("k10"))
                        mobj.setReply_count(objCommentListing.getString("k10"));
                    else {
                        mobj.setReply_count("");
                    }
                    if (objCommentListing.has("phone"))
                        mobj.setNum(objCommentListing.getString("phone"));
                    if (objCommentListing.has("k11"))
                        mobj.setMessage_status(objCommentListing.getString("k11"));
                    list.add(mobj);

                } catch (Exception e) {
                    Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
                }
            }
            LinearLayout commentsChatDynamicLayout = (LinearLayout) findViewById(R.id.dymanic_comments_holder);
            commentsChatDynamicLayout.removeAllViews();
            commentsChatDynamicLayout.setVisibility(View.VISIBLE);
            for (int j = 0; j < list.size(); j++) {
                commentsChatDynamicLayout.setVisibility(View.VISIBLE);
                setDataToAdapter(list.get(j), commentsChatDynamicLayout);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setResults(String response) {
        //  setDataToAdapter("JHVJHVJHV");
    }


    /**
     * clearing the edidtext field
     */
    private void clearAllTheFileds() {
        cmnt.setText("");
    }

    /**
     * @return validating the data fields
     */
    private boolean checkValidation() {
        if (cmnt.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Comment is mandatory", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String getDeviceId() {

        return Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
    private void setDataToAdapter(MemberDetails memberDetails, LinearLayout commentsChatDynamicLayout) {

        commentsChatDynamicLayout.setVisibility(View.VISIBLE);
        View commentslistviewchildView = getLayoutInflater().inflate(R.layout.comments_page, commentsChatDynamicLayout, false);//child.xml
        ImageView userCommentProfilePicture = commentslistviewchildView.findViewById(R.id.profile_image);
        TextView commentUserName = commentslistviewchildView.findViewById(R.id.comment_user_name);
        TextView commentmessage = commentslistviewchildView.findViewById(R.id.user_message);
        TextView repliesmessage = commentslistviewchildView.findViewById(R.id.replies);
        TextView likesCount = commentslistviewchildView.findViewById(R.id.comment_likes);
        commentmessage.setText(memberDetails.getComment());
        commentUserName.setText(memberDetails.getMember_name());
        Picasso.with(this)
                .load("isdgfrhsdgfhdf")
                .placeholder(R.drawable.profile_image)
                .error(R.drawable.profile_image)
                .into(userCommentProfilePicture);
        commentsChatDynamicLayout.addView(commentslistviewchildView);
    }

    private void init() {
        cmnt = findViewById(R.id.cmnt_txt);
        submit = findViewById(R.id.submit_btn);
        likebtn = findViewById(R.id.star_button);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            Intent intent=new Intent(this,CommentsActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

