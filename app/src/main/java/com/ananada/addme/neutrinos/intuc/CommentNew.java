package com.ananada.addme.neutrinos.intuc;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ananada.addme.neutrinos.intuc.Model.Events;
import com.ananada.addme.neutrinos.intuc.Model.MemberDetails;
import com.ananada.addme.neutrinos.intuc.constants.Constants;
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
import java.util.Objects;

public class CommentNew extends AppCompatActivity {
    EditText cmnt;
    private Button submit;
    String formattedDate;
    String macaddress;
    int userid;
    int uploadid;
    public Events detailsEvent;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_new);
        //toolbar.setTitle("Comment");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        uploadid = bundle.getInt("uploadid");
        userid = bundle.getInt("userid");
        Logger.logD("userid", "" + userid);
        Logger.logD("uploadid", "" + uploadid);
        init();
        callApiForCommentList(detailsEvent);
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
        macaddress = getDeviceId();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        formattedDate = df.format(c);
        Log.d("currentDateTimeString", formattedDate);
    }

    private void clearAllTheFileds() {
        cmnt.setText("");
    }

    private void callApiForCommentList(Events detailsEvent) {
        callServerForCommentListApi(userid, uploadid);
    }

    private void callApiForCommentListupdated(Events detailsEvent) {
        callServerForCommentListApi(userid,uploadid);
    }

    public String getDeviceId() {

        return Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    private boolean checkValidation() {
        if (cmnt.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Comment is mandatory", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void callServerApi(String text, final Events detailsEvent) {
        String URL = "http://216.98.9.235:8180/api/jsonws/addMe-portlet.comments/Store-comments/comment/" + text + "/userid/" + userid + "/uploadid/" + uploadid + "/createddate/" + formattedDate + "/status/save";
        Log.v("savecomments", "result is" + URL);
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
                                callApiForCommentListupdated(detailsEvent);
                            } else {
                                // TODO
                                Toast.makeText(CommentNew.this, "No response from server", Toast.LENGTH_SHORT).show();
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
        Volley.newRequestQueue(CommentNew.this).add(postRequest);
    }

    private void callServerForCommentListApi(int userId, int eventid) {
        String URL = "http://216.98.9.235:8180/api/jsonws/addMe-portlet.comments/Store-comments/-comment/userid/" + userId + "/uploadid/" + eventid + "/-createddate/status/retrieve";
        Log.v("comment", "result" + URL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // ProgressUtils.CancelProgress(progressDialog);
                        try {
                            Logger.logD("commentResponse", "->" + response);
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
        Volley.newRequestQueue(CommentNew.this).add(postRequest);
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
        Picasso.get()
                .load("isdgfrhsdgfhdf")
                .placeholder(R.drawable.profile_image)
                .error(R.drawable.profile_image)
                .into(userCommentProfilePicture);
        commentsChatDynamicLayout.addView(commentslistviewchildView);
    }

    private void init() {
        cmnt = findViewById(R.id.cmnt_txt);
        submit = findViewById(R.id.submit_btn);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
