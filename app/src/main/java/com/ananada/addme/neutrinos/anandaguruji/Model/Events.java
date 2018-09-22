package com.ananada.addme.neutrinos.anandaguruji.Model;


import java.io.Serializable;

/**
 * Created by mahiti on 12/6/15.
 */
public class Events implements Serializable {

    String event_id;
    String event_name;
    String event_date;
    String event_location;
    String image_id;
    String attend_status;
    String events;
    String sname;
    String description;
    String attendee_category;

    String checkin_status;
    String likes;
    String day_status;
    String like_status;
    String rated_status;
    String rating;
    String spekers;
    String url;
    String latitude;
    String longitude;
    String notificationMessage;
    String badgeUpdateCount;
    String singleUserRating;
    String dayChair;
    String userEventLike;
    int user_id;

    public String getDayChair() {
        return dayChair;
    }

    public void setDayChair(String dayChair) {
        this.dayChair = dayChair;
    }

    int eventCompare;

    public int getEventCompare() {
        return eventCompare;
    }

    public void setEventCompare(int eventCompare) {
        this.eventCompare = eventCompare;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpekers() {
        return spekers;
    }

    public void setSpekers(String spekers) {
        this.spekers = spekers;
    }

    public String getLike_status() {
        return like_status;
    }

    public void setLike_status(String like_status) {
        this.like_status = like_status;
    }

    public String getRated_status() {
        return rated_status;
    }

    public void setRated_status(String rated_status) {
        this.rated_status = rated_status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCheckin_status() {
        return checkin_status;
    }

    public void setCheckin_status(String checkin_status) {
        this.checkin_status = checkin_status;
    }

    public String getDay_status() {
        return day_status;
    }

    public void setDay_status(String day_status) {
        this.day_status = day_status;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getAttendee_category() {
        return attendee_category;
    }

    public void setAttendee_category(String attendee_category) {
        this.attendee_category = attendee_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getAttend_status() {
        return attend_status;
    }

    public void setAttend_status(String attend_status) {
        this.attend_status = attend_status;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getEvent_name() {

        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_id() {
        return event_id;

    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    /***
     * Event Details
     */

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getBadgeUpdateCount() {
        return badgeUpdateCount;
    }

    public void setBadgeUpdateCount(String badgeUpdateCount) {
        this.badgeUpdateCount = badgeUpdateCount;
    }

    public String getSingleUserRating() {
        return singleUserRating;
    }

    public void setSingleUserRating(String singleUserRating) {
        this.singleUserRating = singleUserRating;
    }

    public String getUserEventLike() {
        return userEventLike;
    }

    public void setUserEventLike(String userEventLike) {
        this.userEventLike = userEventLike;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
