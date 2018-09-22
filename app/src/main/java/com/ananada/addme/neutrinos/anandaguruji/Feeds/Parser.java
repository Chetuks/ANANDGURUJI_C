package com.ananada.addme.neutrinos.anandaguruji.Feeds;

import android.util.Log;

import com.ananada.addme.neutrinos.anandaguruji.FeedModels.AttendeesFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.BirthdaysAndAnniversariesFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.BoardFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.CommentListFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.EventListingFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.ForgotPassWord;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.GalleryDetailFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.GalleryListingFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.MemberListingFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.MemberbenifitsFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.NetworkMembers;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.NetworksFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.ProfileFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.SpeakerFeed;
import com.ananada.addme.neutrinos.anandaguruji.FeedModels.WhatsNewFeed;
import com.ananada.addme.neutrinos.anandaguruji.Logger;
import com.ananada.addme.neutrinos.anandaguruji.Model.Attendee;
import com.ananada.addme.neutrinos.anandaguruji.Model.EventGallery;
import com.ananada.addme.neutrinos.anandaguruji.Model.Events;
import com.ananada.addme.neutrinos.anandaguruji.Model.HomeDetails;
import com.ananada.addme.neutrinos.anandaguruji.Model.ListImages;
import com.ananada.addme.neutrinos.anandaguruji.Model.MemberBenifits;
import com.ananada.addme.neutrinos.anandaguruji.Model.MemberDetails;
import com.ananada.addme.neutrinos.anandaguruji.Model.NetWorks;
import com.ananada.addme.neutrinos.anandaguruji.Model.Options;
import com.ananada.addme.neutrinos.anandaguruji.Model.Questions;
import com.ananada.addme.neutrinos.anandaguruji.Model.Reminders;
import com.ananada.addme.neutrinos.anandaguruji.Model.Speaker;
import com.ananada.addme.neutrinos.anandaguruji.Utils;
import com.ananada.addme.neutrinos.anandaguruji.Whats_new;
import com.ananada.addme.neutrinos.anandaguruji.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mahiti on 17/6/15.
 */
public class Parser {

    private static WhatsNewFeed resultwhatsnew = null;
    static CommentListFeed resultcomment = null;
    static AttendeesFeed resultattendees = null;
    static EventListingFeed resulteventList = null;
   
    static SpeakerFeed resultspeakers = null;
    static GalleryListingFeed resultgallerylist = null;
    static GalleryDetailFeed resultgallerydetail = null;
    static ProfileFeed resultprofiledetail = null;
    static MemberListingFeed resultmemeberlisting = null;
    static BirthdaysAndAnniversariesFeed resultbirthdays = null;
    static MemberbenifitsFeed resultbenifits = null;
    static NetworksFeed resultnetworks = null;
    static BoardFeed resultboard = null;
    static NetworkMembers resultnetworkmembers = null;
    static HomeDetails resulthome = null;
    public static final String messageConstant = "message";
    public static final String msgConstant = "msg";
   public static List<NetWorks> parseNetworksList = new ArrayList<>();


    private Parser() {

    }

    public static Object parseForgot(JSONObject jsonObject) {

        ForgotPassWord resultForgot = new ForgotPassWord();
        if (jsonObject == null)
            return resultForgot;

        try {

            resultForgot = (ForgotPassWord) parseForgot(resultForgot, jsonObject);
            if ("success".equals(jsonObject.getString("status"))) {
                resultForgot.setStatus(jsonObject.getString("status"));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultForgot;

    }

    /**
     * @param resultForgot
     * @param jsonObject
     * @return
     */
    public static Object parseForgot(ForgotPassWord resultForgot, JSONObject jsonObject) {
        try {
            if (jsonObject.has(messageConstant)) {
                resultForgot.setMessage(jsonObject.getString(messageConstant));
            }
        } catch (JSONException e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultForgot;
    }

    /**
     * @param jsonObject
     * @return
     */
    public static Object parseWhatsNewList(JSONObject jsonObject) {

        resultwhatsnew = new WhatsNewFeed();
        if (jsonObject == null)
            return resultwhatsnew;

        try {
            if (jsonObject.has("url")) {
                resultwhatsnew.setStatus("1");
                resultwhatsnew.setUrl(jsonObject.getString("url"));
                JSONArray jsonArray = jsonObject.getJSONArray("wn");

                /**
                 * Calling getWhatsNewFunction method for reducing complexity
                 */
                resultwhatsnew.setList_whats_new(getWhatsNewFunction(jsonArray, jsonObject));
            } else {
                resultwhatsnew.setStatus("0");
                if (jsonObject.has(msgConstant))
                    resultwhatsnew.setMessage(jsonObject.getString(msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultwhatsnew;
    }

    /**
     * @param jsonArray
     * @param jsonObject
     * @return
     */
    public static List<Whats_new> getWhatsNewFunction(JSONArray jsonArray, JSONObject jsonObject) {
        List<Whats_new> list = new ArrayList<>();
        for (int i = 0; jsonArray != null && i < jsonArray.length(); i++) {
            try {
                Whats_new wobj = new Whats_new();
                JSONObject obj = jsonArray.getJSONObject(i);
                wobj.setS_status(Utils.checkData(obj,"s_status"));
                if (obj.has("s1")) {
                    wobj.setQuestions(parseWhatsNewSurvey(obj));
                    wobj.setSurveyId(obj.getInt("s1"));
                    wobj.setSurveyTitle(obj.getString("s2"));
                    wobj.setId(obj.getString("s1"));
                    wobj.setPoll_status(obj.getString("s4"));
                    wobj.setEvent_date(Utils.checkData(obj, "s6"));
                    if (Utils.checkData(obj, "s5").length() > 1)
                        wobj.setImg_url(jsonObject.getString("surl") + Utils.checkData(obj, "s5"));
                    wobj.setEvent_desc(Utils.checkData(obj, "s7"));
                    wobj.setSurveyStatus(Utils.checkIntegerData(obj, "s9"));
                    wobj.setSurveyResponseMessage(Utils.checkData(obj, "s10"));
                } else {

                    wobj.setUrl(jsonObject.getString("url"));
                    wobj.setId(obj.getString("k1"));
                    wobj.setTitle(obj.getString("k4"));
                    wobj.setEvent_date(Utils.checkData(obj, "k5"));
                    wobj.setEvent_desc(Utils.checkData(obj, "k6"));
                    wobj.setEvent_comment_count(Utils.checkData(obj, "k9"));
                    if (obj.has("k10"))
                     wobj.setUpdateBadgeCount(Utils.checkData(obj, "k10"));
                    wobj.setEvent_likes_count(Utils.checkData(obj, "k7"));
                    wobj.setLike_status(Utils.checkData(obj, "k8"));
                    wobj.setImg_url(Utils.checkData(obj, "k3"));
                    wobj.setType(Utils.checkData(obj, "k2"));
                    wobj.setQuestion_id(Utils.checkData(obj, "question_id"));
                    wobj.setS_status(Utils.checkData(obj, "s_status"));
                }
                list.add(wobj);
            } catch (Exception e) {
                Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
            }
        }
        return list;
    }

    /**
     * @param obj
     * @return
     */
    public static List<Questions> parseWhatsNewSurvey(JSONObject obj) {
        List<Questions> questionsList = new ArrayList<>();
        Log.d("", "parsing data");
        try {
            if (obj != null && obj.has("s1")) {
                JSONArray array = obj.getJSONArray("s3");

                for (int j = 0; j < array.length(); j++) {
                    JSONObject questionObj = array.getJSONObject(j);
                    Questions question = new Questions();
                    question.setQuestion_id(questionObj.getString("q1"));
                    question.setQuestion(questionObj.getString("q2"));
                    question.setPercentage(questionObj.getString("q8"));
                    question.setAverage(questionObj.getString("q9"));
                    /**
                     * Calling a method to parse options for reducing complexity
                     */
                    question.setOptions(parseOptionList(questionObj.getJSONArray("q4")));
                    questionsList.add(question);
                }
            }
        } catch (Exception e) {
            Log.d("", "", e);
        }
        return questionsList;
    }

    /**
     * @param optionsArray
     * @return
     */
    public static List<Options> parseOptionList(JSONArray optionsArray) {
        List<Options> optionsList = new ArrayList<>();
        try {
            for (int k = 0; k < optionsArray.length(); k++) {
                JSONObject optionsObj = optionsArray.getJSONObject(k);
                Options options = new Options();
                options.setOption_id(optionsObj.getString("o2"));
                options.setOption(optionsObj.getString("o1"));
                optionsList.add(options);
            }
        } catch (Exception e) {
            Log.d("", "", e);
        }
        return optionsList;
    }

    /**
     * @param jsonObject
     * @return
     */
    public static Object parseEventsList(JSONObject jsonObject) {

        resulteventList = new EventListingFeed();
        Logger.logD("checkjson",""+jsonObject);
        if (jsonObject == null)
            return resulteventList;
        try {
            if (jsonObject.has("ce")) {
                resulteventList.setStatus("1");
                /**
                 * Calling method to reduce complexity to parse json
                 */
                resulteventList.setList_events(parseEvents(jsonObject.getJSONArray("ce")));
            } else {
                resulteventList.setStatus("0");
                resulteventList.setMessage(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d("", "", e);
        }
        Log.d("eevnt list:", "eevnt list:" + resulteventList.getStatus()+"--"+ resulteventList.getMessage());
        Log.d("eevnt list 2:", "eevnt list 2:" + resulteventList.getList_events());
        return resulteventList;
    }

    /**

     * @param parseEventsJsonArray
     * @return
     */
    public static List<Events> parseEvents(JSONArray parseEventsJsonArray) {
        List<Events> eventsList = new ArrayList<>();
        for (int i = 0; i < parseEventsJsonArray.length(); i++) {
            try {
                Events eventsObj = new Events();
                JSONObject parseEventsObj = parseEventsJsonArray.getJSONObject(i);
                eventsObj = getPrase(eventsObj, parseEventsObj);
                eventsObj.setDay_status(parseEventsObj.getString("k12"));
                eventsObj.setSpekers(parseEventsObj.getString("k13"));
                eventsObj.setSname(parseEventsObj.getString("k14"));
                eventsObj.setSingleUserRating(parseEventsObj.getString("k18"));
                eventsObj.setDayChair(parseEventsObj.getString("k19"));
               // eventsObj.setLikes(parseEventsObj.getInt("k20"));
                eventsObj.setUrl(parseEventsObj.getString("k3"));
                eventsObj.setUser_id(parseEventsObj.getInt("k2"));
                eventsObj.setEvent_id(parseEventsObj.getString("k1"));
                eventsObj.setEvent_name(parseEventsObj.getString("k5"));
                eventsObj.setLikes(parseEventsObj.getString("k10"));
                if (parseEventsObj.has("K21"))
                    eventsObj.setUserEventLike(parseEventsObj.getString("K21"));
                if (i == 0)
                    eventsObj.setEvents("NEXT EVENT");
                else if (i == 1)
                    eventsObj.setEvents("UPCOMING EVENTS");
                else
                    eventsObj.setEvents("enable");

                eventsList.add(eventsObj);

            } catch (Exception e) {
                Log.d("", "", e);
            }
        }
        return eventsList;
    }

    /**
     * @param jsonObject
     * @return
     */
    public static Object parsePreviousEventsList(JSONObject jsonObject) {

        resulteventList = new EventListingFeed();
        if (jsonObject == null)
            return resulteventList;
        try {
            if (jsonObject.has("ce")) {
                resulteventList.setStatus("1");
                /**
                 * Calling method to reduce complexity to parse json
                 */
                resulteventList.setList_events(parsePreviousEvents( jsonObject.getJSONArray("ce")));
                Logger.logD("getlist",""+resulteventList.getList_events());
            } else {
                resulteventList.setStatus(jsonObject.getString("st"));
                resulteventList.setMessage(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resulteventList;

    }

    /**

     * @param jsonArray
     * @return
     */
    public static List<Events> parsePreviousEvents(JSONArray jsonArray) {
        List<Events> preEventsList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Events eobj = new Events();
                JSONObject obj = jsonArray.getJSONObject(i);
                eobj = getPrase(eobj, obj);
                eobj.setDay_status("1");
                eobj.setSpekers(obj.getString("k13"));
                eobj.setSname(obj.getString("k14"));
                if (i == 0)
                    eobj.setEvents("previous events");
                else
                    eobj.setEvents("disable");

                preEventsList.add(eobj);

            } catch (Exception e) {
                Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
            }
        }
        return preEventsList;
    }

    public static Events getPrase(Events eobj, JSONObject obj) {
        try {
            eobj.setEvent_id(obj.getString("k1"));
            eobj.setEvent_name(obj.getString("k2"));
            eobj.setImage_id(obj.getString("k3"));
            eobj.setEvent_date(obj.getString("k4"));
            eobj.setEvent_location(obj.getString("k5"));
            eobj.setDescription(obj.getString("k6"));
            eobj.setAttend_status(obj.getString("k7"));
            eobj.setAttendee_category(obj.getString("k8"));
            eobj.setRated_status(obj.getString("k9"));
            eobj.setRating(obj.getString("k10"));
            eobj.setCheckin_status(obj.getString("k11"));
            eobj.setBadgeUpdateCount(obj.getString("k17"));
            eobj.setCheckin_status(obj.getString("k11"));
            eobj.setDayChair(obj.getString("k19"));
            eobj.setLikes(obj.getString("k20"));
          //  eobj.setUserEventLike(obj.getString("K21"));
        } catch (JSONException e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return eobj;
    }

    /**
     * @param jsonObject
     * @return
     */
    public static Object parseGalleryList(JSONObject jsonObject) {

        resultgallerylist = new GalleryListingFeed();
        if (jsonObject == null) {
            return resultgallerylist;
        }
        try {
            if (jsonObject.has("url")) {
                resultgallerylist.setStatus("1");
                JSONArray jsonArray = jsonObject.getJSONArray("gl");
                List<EventGallery> list = getGalleryList(jsonObject, jsonArray);
                Collections.reverse(list);
                resultgallerylist.setList_gallery(list);

            } else {
                resultgallerylist.setStatus(jsonObject.getString("status"));
                resultgallerylist.setMessage(Utils.checkData(jsonObject, messageConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultgallerylist;
    }

    /**
     *
     * @param jsonObject Main Object
     * @param jsonArray inner array of the main object
     * @return
     */
    public static List<EventGallery> getGalleryList(JSONObject jsonObject, JSONArray jsonArray){
        List<EventGallery> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                EventGallery gobj = new EventGallery();
                JSONObject obj = jsonArray.getJSONObject(i);
                gobj.setEvent_id(obj.getString("k1"));
                gobj.setEvent_name(obj.getString("k2"));
                gobj.setEvent_date(Utils.checkData(obj, "k3"));
                gobj.setImage_url(Utils.checkData(jsonObject, "url"));
                // resultgallerylist.setFull_image(Utils.checkData(jsonObject, "url"));
                /**
                 * calling parseImages method to reduce complexity
                 */
                gobj.setList_images(parseImages(obj, jsonObject.getString("url")));
                list.add(gobj);
            } catch (Exception e) {
                Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
            }
        }
        return list;
    }

    /**
     * @param obj
     * @param url
     * @return
     */
    public static List<String> parseImages(JSONObject obj, String url) {
        List<String> image_list = new ArrayList<>();
        try {
            JSONArray imageArray = obj.getJSONArray("k4");
            for (int j = 0; j < imageArray.length(); j++) {
                String image = url + imageArray.getString(j);
                image_list.add(image);
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return image_list;
    }


    public static Object parseProfileDetail(JSONObject jsonObject) {

        resultprofiledetail = new ProfileFeed();

        if (jsonObject == null)
            return resultprofiledetail;
        try {
            if (jsonObject.has("url")) {
                resultprofiledetail.setStatus("1");
                resultprofiledetail.setUrl(jsonObject.getString("url"));
                resultprofiledetail.setUsername(jsonObject.getString("k5"));
                resultprofiledetail.setUser_email(jsonObject.getString("k13"));
                resultprofiledetail.setImageUrl(jsonObject.getString("k6"));
                resultprofiledetail.setMember_id(jsonObject.getString("k1"));
                resultprofiledetail.setUser_mid(jsonObject.getString("k2"));
                resultprofiledetail.setMinEvent(jsonObject.getString("k3"));
                resultprofiledetail.setAttended_events(jsonObject.getString("k4"));
                resultprofiledetail.setImageUrl(jsonObject.getString("k6"));
                resultprofiledetail.setProfileDesignation(jsonObject.getString("k7"));
                resultprofiledetail.setCompany_name(jsonObject.getString("k8"));
                resultprofiledetail.setJoined_date(jsonObject.getString("k9"));
                resultprofiledetail.setUser_status(jsonObject.getString("k10"));
                resultprofiledetail.setPhone_no(jsonObject.getString("k12"));
                resultprofiledetail.setDate_of_birth(jsonObject.getString("k14"));
                resultprofiledetail.setAnniversary_date(jsonObject.getString("k15"));
                resultprofiledetail.setSpouse_id(jsonObject.getString("k17"));
                resultprofiledetail.setSpouse_image(jsonObject.getString("k18"));
                resultprofiledetail.setSpouce_name(jsonObject.getString("k19"));
                resultprofiledetail.setSpouse_phone(jsonObject.getString("k20"));
                resultprofiledetail.setSpouse_email(jsonObject.getString("k21"));
                resultprofiledetail.setSpousedesignation(jsonObject.getString("k22"));
                resultprofiledetail.setCountry_code(jsonObject.getString("k11"));
                resultprofiledetail.setMemberShipImage(jsonObject.getString("k23"));
                resultprofiledetail.setTotal_events(jsonObject.getString("k24"));
               // resultprofiledetail.setProfileStar(jsonObject.getInt("k25"));
                resultprofiledetail.setGroupCount(String.valueOf(jsonObject.getInt("K26")));
                resultprofiledetail.setForumCount(String.valueOf(jsonObject.getString("K27")));
                Log.d("profile star", "profile star--" + resultprofiledetail.getProfileStar());
                Log.d("card image", "card image" + resultprofiledetail.getMemberShipImage());

                JSONArray json = jsonObject.getJSONArray("k16");
                List<Questions> list = new ArrayList<>();
                for (int i = 0; i < json.length(); i++) {
                    JSONObject j = json.getJSONObject(i);
                    Questions q = new Questions();
                    q.setAnswers(j.getString("q3"));
                    q.setQuestion(j.getString("q2"));
                    q.setQuestion_id(j.getString("q1"));
                    list.add(q);
                }
                JSONArray spouseJson = jsonObject.getJSONArray("K28");
                List<Questions> spouseList = new ArrayList<>();
                for (int i = 0; i < spouseJson.length(); i++) {
                    JSONObject j = spouseJson.getJSONObject(i);
                    Questions q = new Questions();
                    q.setAnswers(j.getString("q3"));
                    q.setQuestion(j.getString("q2"));
                    q.setQuestion_id(j.getString("q1"));
                    spouseList.add(q);
                }
                resultprofiledetail.setList_questions(list);
                resultprofiledetail.setSpouse_questions(spouseList);
                resultprofiledetail.setSpouseDataOfBirth(String.valueOf(jsonObject.getString("K29")));
                resultprofiledetail.setSpouseOrganisation(String.valueOf(jsonObject.getString("K30")));
                resultprofiledetail.setSpouseGroupCount(String.valueOf(jsonObject.getString("K31")));


            } else {
                resultprofiledetail.setStatus("0");
                resultprofiledetail.setMessage(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultprofiledetail;

    }

    /**
     * @param jsonObject
     * @return
     */
    public static Object parseBoardListing(JSONObject jsonObject) {

        resultboard = new BoardFeed();

        if (jsonObject == null)
            return resultboard;
        try {
            if (jsonObject.has("url")) {
                resultboard.setStatus("1");
                JSONArray jsonArray = jsonObject.getJSONArray("bm");
                List<MemberDetails> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        MemberDetails mobj = new MemberDetails();
                        JSONObject obj = jsonArray.getJSONObject(i);
                        mobj.setMember_id(obj.getString("k1"));
                        mobj.setMember_name(obj.getString("k2"));
                        mobj.setImage_url(jsonObject.getString("url") + obj.getString("k3"));
                        mobj.setDesignation(obj.getString("k4"));
                        mobj.setCompany_name(obj.getString("k6"));
                        mobj.setBoard_position(obj.getString("k7"));
                        if (obj.has("k8"))
                          mobj.setExcomType(obj.getInt("k8"));
                        mobj.setJoined_date(obj.getString("k5"));
                        mobj.setNum(Utils.nullCheck(obj, "phone"));
                        list.add(mobj);
                    } catch (Exception e) {
                        Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
                    }
                }
                resultboard.setList_memebers(list);
            } else {
                resultboard.setStatus("0");
                resultboard.setMessage(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultboard;
    }

    /**
     * @param jsonObjectCommentListing
     * @return
     */
    public static Object parseCommentListing(JSONObject jsonObjectCommentListing) {

        resultcomment = new CommentListFeed();


        if (jsonObjectCommentListing == null)
            return resultcomment;

        try {
            resultcomment.setStatus("1");
            JSONArray jsonArray = jsonObjectCommentListing.getJSONArray("ct");
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

            resultcomment.setList_comments(list);

        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultcomment;

    }

    public static Object parseCommentReplyListing(JSONObject jsonObject) {

        resultcomment = new CommentListFeed();

        if (jsonObject == null)
            return resultcomment;
        try {
            resultcomment.setStatus("1");
            JSONArray jsonArray = jsonObject.getJSONArray("rp");

            resultcomment.setList_comments(getCommentsList(jsonArray));

        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultcomment;
    }

    /**
     *
     * @param jsonArray
     * @return
     */
    public static List<MemberDetails> getCommentsList(JSONArray jsonArray){
        List<MemberDetails> list = new ArrayList<MemberDetails>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {

                MemberDetails mobj = new MemberDetails();
                JSONObject obj = jsonArray.getJSONObject(i);
                mobj.setMember_id(obj.getString("k1"));
                mobj.setMember_name(obj.getString("k6"));
                mobj.setComment_id(obj.getString("k2"));
                mobj.setImage_url(obj.getString("k7"));
                mobj.setDesignation(obj.getString("k8"));
                mobj.setCompany_name(obj.getString("k9"));
                mobj.setComment(obj.getString("k3").trim());
                mobj.setLike_status(obj.getString("k5"));
                mobj.setLike(obj.getString("k4"));
                if (obj.has("phone"))
                    mobj.setNum(obj.getString("phone"));
                list.add(mobj);

            } catch (Exception e) {
                Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
            }
        }
        return list;
    }

    public static Object parseImageListing(JSONObject jsonObject) {

        resultgallerydetail = new GalleryDetailFeed();

        if (jsonObject == null)
            return resultgallerydetail;
        try {
            resultgallerydetail.setStatus("1");
            JSONArray jsonArray = jsonObject.getJSONArray("k4");
            List<ListImages> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                ListImages obj = new ListImages();
                obj.setImg(jsonArray.getString(i));
                list.add(obj);
            }
            resultgallerydetail.setList(list);
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultgallerydetail;

    }

    public static Object parseAttendeesListing(JSONObject jsonObject) {

        resultattendees = new AttendeesFeed();

        if (jsonObject == null)
            return resultattendees;
        try {
            if ("success".equals(jsonObject.getString("status"))) {
                resultattendees.setStatus(jsonObject.getString("status"));
                JSONArray jsonArray = jsonObject.getJSONArray("attendees");


                resultattendees.setList_attendees(getAttendeeDetails(jsonArray));
            } else {
                resultattendees.setStatus(jsonObject.getString("status"));
                resultattendees.setMessage(Utils.checkData(jsonObject, messageConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultattendees;
    }

    /**
     *
     * @param jsonArray
     * @return
     */
    public static List<Attendee> getAttendeeDetails(JSONArray jsonArray){
        List<Attendee> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Attendee aobj = new Attendee();
                JSONObject obj = jsonArray.getJSONObject(i);
                aobj.setAttendee_id(obj.getString("attendee_id"));
                aobj.setAttendee_name(obj.getString("attendee_name"));
                aobj.setDesignation(obj.getString("designation"));
                aobj.setImage_url(obj.getString("image_url"));
                aobj.setCompany_name(obj.getString("company_name"));
                list.add(aobj);

            } catch (Exception e) {
                Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
            }
        }
        return list;
    }

    public static Object parseSpeakersListing(JSONObject jsonObject) {

        resultspeakers = new SpeakerFeed();

        if (jsonObject == null)
            return resultspeakers;
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("sp");
            List<Speaker> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {

                list = parseSpeaker(jsonArray, i, list);
            }
            resultspeakers.setList_speaker(list);

        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultspeakers;
    }

    /**
     * @param jsonArray
     * @param i
     * @param list
     * @return
     */
    public static List<Speaker> parseSpeaker(JSONArray jsonArray, int i, List<Speaker> list) {
        try {
            Speaker aobj = new Speaker();
            JSONObject obj = jsonArray.getJSONObject(i);
            aobj.setSpeaker_id(obj.getString("k1"));
            aobj.setSpeaker_name(obj.getString("k2"));
            aobj.setDesignation(obj.getString("k5"));
            aobj.setImage_url(obj.getString("k3"));
            aobj.setCompany_name(obj.getString("k4"));
            aobj.setDesc(obj.getString("k6"));
            list.add(aobj);
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return list;
    }

    /**
     * @param jsonObject
     * @return
     */
    public static Object parseMemberBenifitsListing(JSONObject jsonObject) {

        resultbenifits = new MemberbenifitsFeed();

        if (jsonObject == null)
            return resultbenifits;
        try {
            if (jsonObject.has("url")) {
                resultbenifits.setStatus("1");
                JSONArray jsonArray = jsonObject.getJSONArray("mb");
                List<MemberBenifits> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        MemberBenifits aobj = new MemberBenifits();
                        JSONObject obj = jsonArray.getJSONObject(i);
                        aobj.setId(obj.getString("k1"));
                        aobj.setName(obj.getString("k2"));
                        aobj.setDesc(obj.getString("k3"));
                        aobj.setImage(jsonObject.getString("url") + obj.getString("k4"));
                        aobj.setImage_url(jsonObject.getString("url"));
                        aobj.setCreated_date(obj.getString("k5"));
                        list.add(aobj);

                    } catch (Exception e) {
                        Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
                    }
                }
                resultbenifits.setList_benifits(list);
            } else {
                resultbenifits.setStatus("0");
                resultbenifits.setMessage(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultbenifits;

    }

    public static Object parseNetworksListing(JSONObject jsonObject) {

        resultnetworks = new NetworksFeed();

        if (jsonObject == null)
            return resultnetworks;
        try {
            if (jsonObject.has("url")) {
                resultnetworks.setStatus("1");
                JSONArray jsonArray = jsonObject.getJSONArray("gr");
                JSONArray jsonArrayStatus = jsonObject.getJSONArray("a_st");
                List<NetWorks> list = new ArrayList<>();
                list.clear();
                parseNetworksList.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    list = parseNetworks(jsonArray, i, jsonObject, jsonArrayStatus);
                }
                resultnetworks.setList_networks(list);

            } else {
                resultnetworks.setStatus("0");
                resultnetworks.setMessage(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultnetworks;
    }

    /**
     * @param jsonArray Feed data
     * @param i  position
     * @param jsonObject  json object
     * @param jsonArrayStatus  json status
     * @return network object list will be returned
     */
    public static List<NetWorks> parseNetworks(JSONArray jsonArray, int i, JSONObject jsonObject, JSONArray jsonArrayStatus) {
        try {
            NetWorks aobj = new NetWorks();
            JSONObject obj = jsonArray.getJSONObject(i);
            aobj.setId(obj.getString("k1"));
            aobj.setName(obj.getString("k2"));
            aobj.setImage(jsonObject.getString("url") + obj.getString("k4"));
            aobj.setImage_url(obj.getString("k4"));
            if (obj.has("k5"))
                aobj.setGetMemberStatus(obj.getInt("k5"));
            for (int k = 0; k < jsonArrayStatus.length(); k++) {
                JSONObject obj_status = jsonArrayStatus.getJSONObject(k);
                if (obj.getString("k1").trim().equals(obj_status.getString("s1"))) {
                    Logger.logD("a_st","a_st--"+obj_status.getString("s1"));
                    aobj.setStatus(obj_status.getString("s2"));
                    break;
                }
            }
            parseNetworksList.add(aobj);
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        Logger.logD("parseNetworks","parseNetworks--"+parseNetworksList.size());
        return parseNetworksList;
    }

    /**
     * @param jsonObject  json object
     * @return  object will be returning
     */
    public static Object parseHomeDetails(JSONObject jsonObject) {

        resulthome = new HomeDetails();
        if (jsonObject == null)
            return resulthome;
        try {
            if (jsonObject.has("url")) {
                resulthome.setSt("1");
                resulthome.setUrl(jsonObject.getString("url"));
                /**
                 * Calling parseAndFillReminders method to parse reminders
                 */
                resulthome.setRemind(parseAndFillReminders(jsonObject));
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("et");
                    if (jsonArray != null) {
                        resulthome.setEvent(eventParsingFunction(jsonArray));
                    }
                } catch (Exception e) {
                    Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
                }
            } else {
                resulthome.setSt(jsonObject.getString("0"));
                resulthome.setMsg(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resulthome;
    }

    /**
     * @param jsonObject // json object
     * @return // Reminders will be returning
     */
    public static Reminders parseAndFillReminders(JSONObject jsonObject) {
        Reminders rem = new Reminders();
        try {
            JSONObject jsonremind = jsonObject.getJSONObject("nt");
            if (jsonremind.has("dob"))
                rem.setRemind_dob(jsonObject.getString("url") + jsonremind.getString("dob"));
            else
                rem.setRemind_dob("");
            if (jsonremind.has("doa"))
                rem.setRemind_doa(jsonObject.getString("url") + jsonremind.getString("doa"));
            else
                rem.setRemind_doa("");
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return rem;
    }

    /**
     * @param jsonArray // json Array to parse
     * @return // Events will be returning
     */
    public static Events eventParsingFunction(JSONArray jsonArray) {
        Events eventObj = new Events();
        if (jsonArray.length() > 0) {
            for (int i = 0; i < 1; i++) {
                try {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    eventObj.setEvent_id(obj.getString("k1"));
                    eventObj.setEvent_name(obj.getString("k2"));
                    eventObj.setImage_id(obj.getString("k3"));
                    eventObj.setEvent_date(obj.getString("k4"));
                    eventObj.setEvent_location(obj.getString("k5"));
                    eventObj.setSname(obj.getString("k14"));
                } catch (Exception e) {
                    Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
                }
            }
        }
        return eventObj;
    }

    public static Object parseAllMembersList(JSONObject allMemberJsonObject) {

        resultmemeberlisting = new MemberListingFeed();
        if (allMemberJsonObject == null)
            return resultmemeberlisting;
        try {
            if (allMemberJsonObject.has("url")) {
                resultmemeberlisting.setStatus("1");
                JSONArray allMemJsonArray = allMemberJsonObject.getJSONArray("am");
                List<MemberDetails> list = new ArrayList<>();
                for (int i = 0; i < allMemJsonArray.length(); i++) {
                    try {
                        MemberDetails memberObj = new MemberDetails();
                        JSONObject allMemObj = allMemJsonArray.getJSONObject(i);
                        memberObj.setMember_id(allMemObj.getString("k1"));
                        memberObj.setUrl(allMemberJsonObject.getString("url"));
                        memberObj.setMember_name(allMemObj.getString("k2"));
                        memberObj.setImage_url(allMemberJsonObject.getString("url") + allMemObj.getString("k3"));
                        memberObj.setDesignation(allMemObj.getString("k4"));
                        memberObj.setStarStatus(allMemObj.getString("k7"));
                        if (allMemObj.has("k6"))
                            memberObj.setCompany_name(allMemObj.getString("k6"));
                        else
                            memberObj.setCompany_name("");
                        if (allMemObj.has("k5"))
                            memberObj.setJoined_date(allMemObj.getString("k5"));
                        else
                            memberObj.setJoined_date("");
                        if (allMemObj.has("phone"))
                            memberObj.setNum(allMemObj.getString("phone"));

                        list.add(memberObj);
                    } catch (Exception e) {
                        Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
                    }
                }
                resultmemeberlisting.setList_all(list);

            } else {
                resultmemeberlisting.setStatus("0");
                resultmemeberlisting.setMessage(Utils.checkData(allMemberJsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultmemeberlisting;
    }
    public static Object parseSpouceMembersList(JSONObject spouseJsonObject) {
        resultmemeberlisting = new MemberListingFeed();
        if (spouseJsonObject == null)
            return resultmemeberlisting;
        try {
            if (spouseJsonObject.has("url")) {
                resultmemeberlisting.setStatus("1");

                JSONArray jsonArray = spouseJsonObject.getJSONArray("sm");
                List<MemberDetails> listNew = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        MemberDetails memberobj = new MemberDetails();
                        JSONObject parceObj = jsonArray.getJSONObject(i);
                        memberobj.setMember_id(parceObj.getString("k1"));
                        memberobj.setUrl(spouseJsonObject.getString("url"));
                        memberobj.setMember_name(parceObj.getString("k2"));
                        memberobj.setImage_url(spouseJsonObject.getString("url") + parceObj.getString("k3"));
                        memberobj.setDesignation(parceObj.getString("k4"));
                        memberobj.setStarStatus(parceObj.getString("k7"));
                        if (parceObj.has("k6"))
                            memberobj.setCompany_name(parceObj.getString("k6"));
                        else
                            memberobj.setCompany_name("");
                        if (parceObj.has("k5"))
                            memberobj.setJoined_date(parceObj.getString("k5"));
                        else
                            memberobj.setJoined_date("");
                        if (parceObj.has("phone"))
                            memberobj.setNum(parceObj.getString("phone"));
                        listNew.add(memberobj);
                    } catch (Exception e) {
                        Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
                    }
                }
                resultmemeberlisting.setList_spouse(listNew);

            } else {
                resultmemeberlisting.setStatus("0");
                resultmemeberlisting.setMessage(Utils.checkData(spouseJsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultmemeberlisting;

    }

    public static Object parseNewMembersList(JSONObject jsonObject) {

        resultmemeberlisting = new MemberListingFeed();
        if (jsonObject == null)
            return resultmemeberlisting;
        try {
            if (jsonObject.has("url")) {
                resultmemeberlisting.setStatus("1");

                JSONArray jsonArray = jsonObject.getJSONArray("nm");
                List<MemberDetails> newMemberListNew = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        MemberDetails newMemeberObj = new MemberDetails();
                        JSONObject obj = jsonArray.getJSONObject(i);
                        newMemeberObj.setMember_id(obj.getString("k1"));
                        newMemeberObj.setUrl(jsonObject.getString("url"));
                        newMemeberObj.setMember_name(obj.getString("k2"));
                        newMemeberObj.setImage_url(jsonObject.getString("url") + obj.getString("k3"));
                        newMemeberObj.setDesignation(obj.getString("k4"));
                        newMemeberObj.setStarStatus(obj.getString("k7"));
                        if (obj.has("k6"))
                            newMemeberObj.setCompany_name(obj.getString("k6"));
                        else
                            newMemeberObj.setCompany_name("");
                        if (obj.has("k5"))
                            newMemeberObj.setJoined_date(obj.getString("k5"));
                        else
                            newMemeberObj.setJoined_date("");
                        if (obj.has("phone"))
                            newMemeberObj.setNum(obj.getString("phone"));

                        newMemberListNew.add(newMemeberObj);
                    } catch (Exception e) {
                        Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
                    }
                }
                resultmemeberlisting.setList_new(newMemberListNew);

            } else {
                resultmemeberlisting.setStatus("0");
                resultmemeberlisting.setMessage(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultmemeberlisting;

    }

    public static Object parseBirthdayAndAnniversariesListing(JSONObject jsonObject) {

        resultbirthdays = new BirthdaysAndAnniversariesFeed();
        if (jsonObject == null)
            return resultbirthdays;
        try {
            if (jsonObject.has("url")) {
                resultbirthdays.setStatus("1");
                JSONArray jsonArray = jsonObject.getJSONArray("mm");

                List<MemberDetails> list_b = new ArrayList<>();
                List<MemberDetails> list_a = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {

                        MemberDetails mobj = new MemberDetails();
                        JSONObject obj = jsonArray.getJSONObject(i);
                        mobj.setMember_id(obj.getString("k1"));
                        mobj.setMember_name(obj.getString("k2"));
                        mobj.setImage_url(obj.getString("k3"));
                        mobj.setUrl(jsonObject.getString("url"));
                        mobj.setDesignation(obj.getString("k4"));
                        mobj.setCompany_name(obj.getString("k5"));
                        mobj.setJoined_date("Today");
                        mobj.setCountry_code(Utils.checkData(obj,"k6"));

                        if (obj.has("k7"))
                            mobj.setNum(obj.getString("k7"));
                        if (obj.has("k9"))
                            mobj.setB_day(obj.getString("k9"));
                        if ("1".equalsIgnoreCase(obj.getString("k8")))
                            list_b.add(mobj);
                        else if ("0".equalsIgnoreCase(obj.getString("k8")))
                            list_a.add(mobj);

                    } catch (Exception e) {
                        Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
                    }
                }

                resultbirthdays.setList_birthday(list_b);
                resultbirthdays.setList_anniversaries(list_a);
            } else {
                resultbirthdays.setStatus("0");
                resultbirthdays.setMessage(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultbirthdays;

    }

    public static Object parseNetWorkMembersListing(JSONObject jsonObject) {

        resultnetworkmembers = new NetworkMembers();
        if (jsonObject == null)
            return resultnetworkmembers;
        try {
            if (!jsonObject.has("st")) {
                resultnetworkmembers.setStatus("1");

                JSONArray jsonArray_meme = jsonObject.getJSONArray("gm");


                String ids[] = new String[jsonArray_meme.length()];
                for (int i = 0; i < jsonArray_meme.length(); i++) {
                    ids[i] = jsonArray_meme.getString(i);
                }
                resultnetworkmembers.setArray(java.util.Arrays.toString(ids).replace('[', '(').replace(']', ')'));
            } else {
                resultnetworkmembers.setStatus("0");
                resultnetworkmembers.setMsg(Utils.checkData(jsonObject, msgConstant));
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, Constants.CLASSPARSER, e);
        }
        return resultnetworkmembers;

    }
}
