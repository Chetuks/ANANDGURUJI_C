package com.ananada.addme.neutrinos.anandaguruji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ananada.addme.neutrinos.anandaguruji.FeedModels.EventListingFeed;
import com.ananada.addme.neutrinos.anandaguruji.Feeds.Parser;
import com.ananada.addme.neutrinos.anandaguruji.Model.Events;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsActivity extends AppCompatActivity {
    private static final String TAG = "EventPastEvents";
    public static final String EVENTBEANS = "userSelectedBean";
    private EventListingAdapter eventAdapter;
    private EventListingFeed ceResultEvents;
    private Activity activity;
    List<Events> listt = new ArrayList<>();
    private String deviceId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_comments);
        //setContentView(R.layout.activity_main_comments);
        activity = CommentsActivity.this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Utils.checkNetworkConnection(CommentsActivity.this.getApplicationContext())) {
            deviceId = Utils.getDeviceId(activity);
            Logger.logD("devid", "dfg" + deviceId);
            callServerApi(CommentsActivity.this);
            likeapi();
        } else {
            Utils.displayToast(CommentsActivity.this.getApplicationContext(), getResources().getString(R.string.NO_NETWORK));
        }
        //setResults();
    }

    private void likeapi() {
        Events detailsEvent;
        detailsEvent = (Events) getIntent().getSerializableExtra(EVENTBEANS);
        Logger.logD("detail", "" + detailsEvent);
        LikesActivity likesActivity = new LikesActivity();
        String upload = likesActivity.uploadid;
        Logger.logD("jkgfhdgf", "" + upload);
      /*  String macaddress=likesActivity.getDeviceId();
        Logger.logD("macaddress",""+macaddress);*/
        String url = "http://216.98.9.235:8180/api/jsonws/addMe-portlet.likes/Store-retrieve-likes/macaddress/" + deviceId + "/appuniqueid/20826/uploadid/" + upload + "/-like/status/retrieve";
        Log.v("the result of like", "the result is" + url);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // ProgressUtils.CancelProgress(progressDialog);
                        try {
                            Logger.logD("Likecount", "->" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            Logger.logD("statusboolean", "" + jsonObject);
                            String status = String.valueOf(jsonObject.getInt("count"));
                            Logger.logD("getjkshdjk", "" + status);
                            setlike();
                            //likeincrement.setText(status);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.v("the result of count", "the result is" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(activity).add(postRequest);
    }

    private void setlike() {
    }

    public void callServerApi(final Activity activity) {
       /* String staticResponse = "{\n" +
                "  \"url\": \"http://ypolive_testing.mahiti.org/uploads/events/medium/\",\n" +
                "  \"ce\": [\n" +
                "    {\n" +
                "      \"k1\": \"144\",\n" +
                "      \"k2\": \"TEST EVENT MAY08\",\n" +
                "      \"k3\": \"a13311a4a89251d163cbebe7edcc6969.jpeg\",\n" +
                "      \"k4\": \"08/05/18 02:00 PM\",\n" +
                "      \"k5\": \"bengaluru\",\n" +
                "      \"k6\": \"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\",\n" +
                "      \"k7\": 0,\n" +
                "      \"k8\": \"Members With Spouse \",\n" +
                "      \"k9\": 0,\n" +
                "      \"k10\": \"\",\n" +
                "      \"k11\": 0,\n" +
                "      \"k12\": 1,\n" +
                "      \"k13\": 1,\n" +
                "      \"k14\": \"test event\",\n" +
                "      \"k15\": \"08/05/2018 06:00 PM\",\n" +
                "      \"k16\": \"1\",\n" +
                "      \"k17\": \"2018-05-08 14:00:00\",\n" +
                "      \"k18\": \"\",\n" +
                "      \"k19\": \"Apparao Mallavarapu,Ishwar Subramanian\",\n" +
                "      \"k20\": 0,\n" +
                "      \"K21\": \"0\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"k1\": \"145\",\n" +
                "      \"k2\": \"TEST EVENT MAY\",\n" +
                "      \"k3\": \"ead3ac3e904e1d358929828d2d561da8.jpeg\",\n" +
                "      \"k4\": \"08/05/18 02:00 PM\",\n" +
                "      \"k5\": \"bengaluru\",\n" +
                "      \"k6\": \"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\",\n" +
                "      \"k7\": 0,\n" +
                "      \"k8\": \"Members With Spouse \",\n" +
                "      \"k9\": 0,\n" +
                "      \"k10\": \"\",\n" +
                "      \"k11\": 0,\n" +
                "      \"k12\": 1,\n" +
                "      \"k13\": 1,\n" +
                "      \"k14\": \"test event\",\n" +
                "      \"k15\": \"08/05/2018 06:00 PM\",\n" +
                "      \"k16\": \"1\",\n" +
                "      \"k17\": \"2018-05-08 14:00:00\",\n" +
                "      \"k18\": \"\",\n" +
                "      \"k19\": \"Apparao Mallavarapu,Ishwar Subramanian\",\n" +
                "      \"k20\": 0,\n" +
                "      \"K21\": \"0\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(staticResponse);
            ceResultEvents = (EventListingFeed) Parser.parseEventsList(jsonObject);
            if (ceResultEvents != null && ceResultEvents.getStatus() != null && ("1").equalsIgnoreCase(ceResultEvents.getStatus()) && ceResultEvents.getList_events() != null && !ceResultEvents.getList_events().isEmpty()) {
                setDataToAdapter(ceResultEvents.getList_events());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        String URL = "http://216.98.9.235:8180/api/jsonws/addMe-portlet.comments/Generate-contents/macaddress/" + deviceId + "/appuniqueid/20826";
        Log.v("Url", "generatecontents " + URL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // ProgressUtils.CancelProgress(progressDialog);
                        try {
                            Logger.logD("responseGeneratecontents", "" + response);
                            setResults(response);
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
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramsHeader = new HashMap<>();
                paramsHeader.put("un", "WYPO");
                paramsHeader.put("pw", "VD0+)&lrYlUiUcl^8%a~");
                return paramsHeader;
            }
        };
        try {
            postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(activity).add(postRequest);
        } catch (Exception e) {
            Logger.logE("exception", "call server api ", e);
            Utils.displayToast(activity, "Facing difficulty's please try again");

        }
    }

    private void setDataToAdapter(List<Events> listEvents) {
        ListView eventsList = (ListView) findViewById(R.id.event_list);
        LinearLayout noEventLayout = (LinearLayout) findViewById(R.id.no_events);
        if (!listEvents.isEmpty()) {
            eventAdapter = new EventListingAdapter(CommentsActivity.this, CommentsActivity.this, listEvents);
            eventsList.setVisibility(View.VISIBLE);
            noEventLayout.setVisibility(View.GONE);
            eventsList.setAdapter(eventAdapter);
            eventAdapter.notifyDataSetChanged();
        } else {
            eventsList.setVisibility(View.GONE);
            noEventLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * @param results
     * @throws Exception set the results and call parse class
     */
    public void setResults(String results) throws Exception {
        Log.d(TAG, "EventPastEvents server response" + results);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(results);
            ceResultEvents = (EventListingFeed) Parser.parseEventsList(jsonObject);
            Logger.logD("listresponse4", "" + jsonObject);
            Logger.logD("listresponse1", "" + ceResultEvents.getList_events());
            Logger.logD("listresponse3", "" + ceResultEvents);
            if (ceResultEvents != null && ceResultEvents.getStatus() != null && ("1").equalsIgnoreCase(ceResultEvents.getStatus()) && ceResultEvents.getList_events() != null && !ceResultEvents.getList_events().isEmpty()) {
                Logger.logD("listresponse2", "" + ceResultEvents.getList_events());
                setDataToAdapter(ceResultEvents.getList_events());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

