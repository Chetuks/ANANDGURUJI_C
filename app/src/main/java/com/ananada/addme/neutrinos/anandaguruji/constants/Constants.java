package com.ananada.addme.neutrinos.anandaguruji.constants;

/**
 * Created by mahiti on 4/6/15.
 */
public class Constants {
    public static final String PROJECT ="YPO";
    public static final String CLASSPARSER ="Parser";
    public static final int SPLASHSCREEN_DELAY=2000;
    public  static final int databaseversion =5;

    public final static String dbname="YPO.db";
    public static final String APPLICATION_PREFERENCE ="YPO_PREFERENCE";
    public static final String USER_PROFILE = "user_profile";
    public static final String USER_NAME = "user_name";
    public static final String EVENT_ID ="event_id";

    /**
     * List of preferences values used through app
     * **/
    /* Boolean values */
    public static final String LOGIN_SUCCESS="loginEnabled";
    /* String values */
    public static final String USER_ID="userid";
    public static final String Event_Enable="Event_Enable";
    public static final String FIRSTTIME="first_Time";
    public static final String EventAttendStatus="AttendEnable";
    public static final String EventCheckInStatus="AttendCheckinEnable";
    public static final String EventRateEvent="AttendRateEnable";
    public static final String E_Status="status";
    public static final String E_position="e_pos";
    public static final String mem_url="mem_url";
    public static final java.lang.String EVENT_NAME = "eventName";
    public static final java.lang.String EVENT_DATE = "eventDate";


    /***
     *  @preference constants for feeds
     */
    public static final String LOGIN_RESULT="loginResult";

    public static final String FCM_TOKEN_REG = "FCM_Token_Reg";
    public static final String FCM_TOKEN = "FCM_Token";
    public static final String getFormMemberList="getMenberList";
    public static final String getFormSpouseList="getSpouseList";
    public static final String SPOUSE_ID = "Spouse_id";

    private Constants()
    {

    }
}
