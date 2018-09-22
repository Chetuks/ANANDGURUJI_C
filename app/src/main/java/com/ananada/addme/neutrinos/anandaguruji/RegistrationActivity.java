package com.ananada.addme.neutrinos.anandaguruji;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class RegistrationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "RegistrationApi";
    private EditText userName;
    private EditText phoneNumber;
    private TextView enterDetails;
    private EditText cityName;
    private Button btnRegistration;
    private Context context;
    private Spinner spinner;
    String[] languagesList = {"Select Language", "English","ಕನ್ನಡ "};
    SharedPrefManager prefManager;
    Configuration config;
    private String currentLanguage;
    private GPSTracker gpsTracker;
    private Activity activity;
    private String deviceId = "";
    private String location = "";
    private String deviceName = "";
    private String firebaseTokenId = "text";
    private String deviceType = "";
    private int value;
    private GoogleApiClient googleApiClient;
    private String date = "";
    private int year;
    private int day;
    private int month;
    private RadioGroup gender;
    private Button dateOfBirth;
    String getSelectedGender;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Spinner mobileSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
            } else {
                requestPermission();
            }
        }
        bindView();
        initPreferences();
        setDefaultLanguage(prefManager.getLanguage());
        turnGPSOn();
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dateOfBirth.setText("date of birth : " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                Logger.logD("date", dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                  RadioButton radioButton = (RadioButton) findViewById(checkedId);
                                                  getSelectedGender = radioButton.getText().toString();
                                              }
                                          }
        );
    }

    private void setDefaultLanguage(int position) {
        switch (position) {
            case 1:
                SetLocality.setLocale(this, "en");
                setTexts();
                break;
            case 2:
                SetLocality.setLocale(this, "kan");
                setTexts();
                break;
            default:
                SetLocality.setLocale(this, "en");
                // preferences.edit().putInt(Constants.SELECTEDLANGUAGE, position).apply();
                setTexts();
                break;
        }

    }

    private void setTexts() {
        userName.setHint(getResources().getString(R.string.name));
        cityName.setHint(getResources().getString(R.string.place));
        phoneNumber.setHint(getResources().getString(R.string.mobile_number));
        enterDetails.setText(getResources().getString(R.string.enter_your_details));
        btnRegistration.setText(getResources().getString(R.string.btnRegistrationText));
    }

    private void initPreferences() {
        context = this;
        activity = RegistrationActivity.this;
        prefManager = new SharedPrefManager(context);
        gpsTracker = new GPSTracker(context);
        FirebaseApp.initializeApp(context);
    }

    private void bindView() {
        userName = (EditText) findViewById(R.id.personName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        cityName = (EditText) findViewById(R.id.cityName);
        btnRegistration = (Button) findViewById(R.id.btnRegistartion);
        spinner = (Spinner) findViewById(R.id.spinner);
        enterDetails = (TextView) findViewById(R.id.enterDetails);
        gender = (RadioGroup) findViewById(R.id.gender_id);
        dateOfBirth = (Button) findViewById(R.id.dateofbirth);
        mobileSpinner = (Spinner) findViewById(R.id.mobile_spinner);
        //  setLanguage();
        setSpinnerListener();
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callRegistrationProcess();
            }
        });
    }

    private void setSpinnerListener() {
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languagesList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //noting to handle
                setDefaultLanguage(i);
                prefManager.saveLanguage(context, i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //noting to handle
            }
        });

        List<String> mySpinnerList = new ArrayList<String>();
        mySpinnerList.add("+91");
        mySpinnerList.add("+974");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mySpinnerList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mobileSpinner.setAdapter(dataAdapter);
    }

    private void callRegistrationProcess() {
        if (isAllFieldsValid()) {
            if (!Utils.checkNetworkConnection(context)) {
                Toast.makeText(context, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                return;
            } else {
                deviceId = Utils.getDeviceId(activity);
                location = Utils.getLocation(gpsTracker, activity);
                deviceName = Utils.getDeviceName();
                firebaseTokenId = FirebaseInstanceId.getInstance().getToken();
                deviceType = Utils.getDeviceDimention(activity);
                Logger.logV("Location", " Lat->Lon " + location);
                Logger.logV("Location", " device id " + deviceId);
                Logger.logV("Location", " firebaseTokenId " + firebaseTokenId);
                Logger.logV("Location", " firebaseTokenId " + firebaseTokenId);
                Logger.logV("Location", " device Name " + deviceName);
                Logger.logV("Location", " deviceType " + deviceType);
                callRegistrationApi();

            }
        }
    }

    private void callRegistrationApi() {
        Logger.logD("GetMobileSpinner",mobileSpinner.getSelectedItem().toString());
        int getSeletedLanguageId= getLanguage();
        // String getSeletedLanguageId= "1";
        Logger.logD("getuserSelectedLanguage",getSeletedLanguageId+"");

        String url = "http://216.98.9.235:8180/api/jsonws/addMe-portlet.device/save-and-update-users-details-remotely/username/"+userName.getText().toString()+"/mobile/"+mobileSpinner.getSelectedItem().toString()+phoneNumber.getText().toString()+"/placename/"+cityName.getText().toString()+
                "/deviceaddress/"+deviceId+"/devicelocation/"+location+"/status/Activated/devicename/"+deviceName+"/device-type/"
                +deviceType+"/apk-type/20826/token/"+firebaseTokenId+"/language/"+getSeletedLanguageId+"/gender/"+getSelectedGender+"/date-of-birth/"+date;
        String convertedURL = url.replace(" ", "%20");
        Logger.logV("Location", " convertedURL " + convertedURL);
        StringRequest postRequest = new StringRequest(Request.Method.POST, convertedURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.logV(TAG, response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (2 == (jsonObject.getInt("response")) || 1 == (jsonObject.getInt("response"))) {
                                prefManager.saveIsLoggedIn(context, true);
                                prefManager.saveLanguage(context, getLanguage());
                                prefManager.saveName(context,response);
                                Intent intent = new Intent(activity, DashBoardActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Logger.logV("Location", " ERROR " + error);

                    }
                });
        Volley.newRequestQueue(context).add(postRequest);
    }

    /**
     * @return
     */
    private int getLanguage() {
        switch (spinner.getSelectedItemPosition()){
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 1;
        }
    }

    private boolean isAllFieldsValid() {
        if (userName.getText().toString().isEmpty()) {
            Toast.makeText(context, getResources().getString(R.string.usernameMandatoryText), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phoneNumber.getText().toString().isEmpty()) {
            Toast.makeText(context, getResources().getString(R.string.phoneMandatoryText), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cityName.getText().toString().isEmpty()) {
            Toast.makeText(context, getResources().getString(R.string.cityMandatoryText), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkPermission() {
        int externalread = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int externalwrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int gpsACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int gpsACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int READ_PHONE_STATE = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int manageDocuments = ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_DOCUMENTS);

        if (externalread != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (externalwrite != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (gpsACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (gpsACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        if (READ_PHONE_STATE != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (manageDocuments != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestPermission() {
        List<String> list = new ArrayList<>();
        activity = RegistrationActivity.this;
        if (!ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
            list.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (!ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION))
            list.add(Manifest.permission.ACCESS_FINE_LOCATION);
        if (!ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION))
            list.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (!ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this, Manifest.permission.MANAGE_DOCUMENTS))
            list.add(Manifest.permission.MANAGE_DOCUMENTS);
        if (list.isEmpty())
            return;
        String[] stockArr = new String[list.size()];
        stockArr = list.toArray(stockArr);
        if (stockArr.length != 0) {
            ActivityCompat.requestPermissions(RegistrationActivity.this, stockArr, 1);
        }

    }

    private void turnGPSOn() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        //**************************
        builder.setAlwaysShow(true); //this is the key ingredient
        //**************************

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(RegistrationActivity.this, 1000);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        turnGPSOn();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
