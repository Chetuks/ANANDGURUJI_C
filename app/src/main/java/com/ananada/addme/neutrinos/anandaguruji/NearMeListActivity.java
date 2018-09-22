package com.ananada.addme.neutrinos.anandaguruji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NearMeListActivity extends AppCompatActivity {

    private static final String TAG = "NearMeListActivity";
    private ListView myListView;
    List<NearMe> myNearMeList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_me_list);
        myListView= findViewById(R.id.near_me_listview);
      //  callNearMeApiFromServer();
        String tempResponse="{\n" +
                "  \"message\": \"urls generated successfully\",\n" +
                "  \"response\": 2,\n" +
                "  \"nearMeList\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"ATM\",\n" +
                "      \"image\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Branch\",\n" +
                "      \"image\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"Dinner\",\n" +
                "      \"image\": \"\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        parceResponse(tempResponse);


    }

    private void callNearMeApiFromServer() {
        //String url = "http://216.98.9.235:8080/api/jsonws/addMe-portlet.device/save-and-update-users-details-remotely/username/"+userName.getText().toString()+"/mobile/"+phoneNumber.getText().toString()+"/placename/"+cityName.getText().toString()+"/deviceaddress/"+deviceId+"/devicelocation/"+location+"/status/Activated/devicename/"+deviceName+"/device-type/"+deviceType+"/apk-type/54058/token/"+firebaseTokenId+"/language/"+"English";
      //  String url = "http://216.98.9.235:8180/api/jsonws/addMe-portlet.device/save-and-update-users-details-remotely/username/"+userName.getText().toString()+"/mobile/"+phoneNumber.getText().toString()+"/placename/"+cityName.getText().toString()+"/deviceaddress/"+deviceId+"/devicelocation/"+location+"/status/Activated/devicename/"+deviceName+"/device-type/"+deviceType+"/apk-type/20826/token/"+firebaseTokenId+"/language/"+"English";
       String url ="http://216.98.9.235:8080/api/jsonws/addMe-portlet.device/save-and-update-users-details-remotely/username/text/mobile/text/placename/text/deviceaddress/text/devicelocation/text/status/Activated/devicename/text/device-type/text/apk-type/text/token/text/language/text";
        String convertedURL =url.replace(" ", "%20");
        Logger.logV("Location"," convertedURL "+convertedURL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, convertedURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.logV(TAG,response);
                        String tempResponse="{\n" +
                                "  \"message\": \"urls generated successfully\",\n" +
                                "  \"response\": 2,\n" +
                                "  \"nearMeList\": [\n" +
                                "    {\n" +
                                "      \"id\": 1,\n" +
                                "      \"name\": \"ATM\",\n" +
                                "      \"image\": \"\"\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"id\": 2,\n" +
                                "      \"name\": \"Branch\",\n" +
                                "      \"image\": \"\"\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"id\": 3,\n" +
                                "      \"name\": \"Dinner\",\n" +
                                "      \"image\": \"\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}";
                        // TODO change the below line to response
                        parceResponse(tempResponse);
                      //  parceResponse(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Logger.logV("Location"," ERROR "+error);

                    }
                });
//        RetryPolicy retryPolicy = new Re
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void parceResponse(String tempResponse) {

        try {
            JSONObject jsonObject= new JSONObject(tempResponse);
            int getResonseStatus= jsonObject.getInt("response");
            if (getResonseStatus==2){
                JSONArray jsonArray= jsonObject.getJSONArray("nearMeList");

              for(int i=0;i<jsonArray.length();i++){
                 JSONObject jsonObject1= jsonArray.getJSONObject(i);
                  NearMe nearMe= new NearMe(jsonObject1.getInt("id"),jsonObject1.getString("name"),jsonObject1.getString("image"));
                  myNearMeList.add(nearMe);
              }

                MyBaseAdapter myBaseAdapter= new MyBaseAdapter(NearMeListActivity.this,myNearMeList);
                myListView.setAdapter(myBaseAdapter);


            }else{
                //TODO display one toast
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
