package com.ananada.addme.neutrinos.anandaguruji;

import com.ananada.addme.neutrinos.anandaguruji.Model.Options;
import com.ananada.addme.neutrinos.anandaguruji.Model.Questions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Whats_new implements Serializable {
    private static final long serialVersionUID = 0L;
    String id;
    String title;
    String img_url;
    String event_desc;
    String event_date;
    String event_likes_count;
    String event_comment_count;
    String like_status;
    String type;
    String poll_status;
    String question_id;
    String url;
    private List<Questions> questions = new ArrayList<>();
    private String surveyTitle;
    private int surveyId;
    private int surveyStatus;
    private String surveyResponseMessage;
    String s_status;
    String whatNewCompare;
    String updateBadgeCount;
    ArrayList<Options> list_options;


    public String getWhatNewCompare() {
        return whatNewCompare;
    }

    public void setWhatNewCompare(String whatNewCompare) {
        this.whatNewCompare = whatNewCompare;
    }

    public int getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(int surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public String getSurveyResponseMessage() {
        return surveyResponseMessage;
    }

    public void setSurveyResponseMessage(String surveyResponseMessage) {
        this.surveyResponseMessage = surveyResponseMessage;
    }



    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }


    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getS_status() {
        return s_status;
    }

    public void setS_status(String s_status) {
        this.s_status = s_status;
    }

    public ArrayList<Options> getList_options() {
        return list_options;
    }

    public void setList_options(ArrayList<Options> list_options) {
        this.list_options = list_options;
    }

    public String getPoll_status() {
        return poll_status;
    }

    public void setPoll_status(String poll_status) {
        this.poll_status = poll_status;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getLike_status() {
        return like_status;
    }

    public void setLike_status(String like_status) {
        this.like_status = like_status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_comment_count() {
        return event_comment_count;
    }

    public void setEvent_comment_count(String event_comment_count) {
        this.event_comment_count = event_comment_count;
    }

    public String getEvent_likes_count() {
        return event_likes_count;
    }

    public void setEvent_likes_count(String event_likes_count) {
        this.event_likes_count = event_likes_count;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUpdateBadgeCount() {
        return updateBadgeCount;
    }

    public void setUpdateBadgeCount(String updateBadgeCount) {
        this.updateBadgeCount = updateBadgeCount;
    }
}