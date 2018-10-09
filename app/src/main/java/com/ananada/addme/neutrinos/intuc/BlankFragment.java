package com.ananada.addme.neutrinos.intuc;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ananada.addme.neutrinos.intuc.FeedModels.EventListingFeed;
import com.ananada.addme.neutrinos.intuc.Feeds.Parser;
import com.ananada.addme.neutrinos.intuc.Model.Events;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class BlankFragment extends android.support.v4.app.Fragment {
    View root;
    Context activity;
    private String deviceId = "";
    private EventListingFeed ceResultEvents;
    private EventListingAdapter eventAdapter;
    private static final String TAG = "EventPastEvents";
    ImageView spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_blank, container, false);
        spinner = root.findViewById(R.id.progressBar1);
        spinner.bringToFront();
        Glide.with(Objects.requireNonNull(getActivity()).getApplicationContext()).load(R.drawable.spinnernew).asGif().into(spinner);
        spinner.setVisibility(View.VISIBLE);
        if (Utils.checkNetworkConnection(getActivity().getApplicationContext())) {
            deviceId = Utils.getDeviceId((Activity) activity);
            Logger.logD("devid", "dfg" + deviceId);
            callServerApi(getActivity());
            // likeapi();
        } else {
            Utils.displayToast(getActivity().getApplicationContext(), getResources().getString(R.string.NO_NETWORK));
        }
        return root;
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

        String URL = "http://216.98.9.235:8180/api/jsonws/addMe-portlet.comments/Generate-contents/macaddress/" + deviceId + "/appuniqueid/20829";
        Log.v("generatecontents", "api" + URL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // ProgressUtils.CancelProgress(progressDialog);
                        try {
                            Logger.logD("responseGeneratecontents", "" + response);
                            setResults(response);
                            spinner.setVisibility(View.GONE);
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

    private void setDataToAdapter(List<Events> listEvents) {
        ListView eventsList = (ListView) root.findViewById(R.id.event_list);
        LinearLayout noEventLayout = (LinearLayout) root.findViewById(R.id.no_events);
        if (!listEvents.isEmpty()) {
            eventAdapter = new EventListingAdapter(getActivity(), getContext(), listEvents);
            eventsList.setVisibility(View.VISIBLE);
            noEventLayout.setVisibility(View.GONE);
            eventsList.setAdapter(eventAdapter);
            eventAdapter.notifyDataSetChanged();
        } else {
            eventsList.setVisibility(View.GONE);
            noEventLayout.setVisibility(View.VISIBLE);
        }
    }

   /* @Override
    public void onFragmentInteraction(Uri uri) {

    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
