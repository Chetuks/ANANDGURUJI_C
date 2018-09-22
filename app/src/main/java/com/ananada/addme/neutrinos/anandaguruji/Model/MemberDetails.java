package com.ananada.addme.neutrinos.anandaguruji.Model;

import java.io.Serializable;

/**
 * Created by mahiti on 9/6/15.
 */
public class MemberDetails implements Serializable {


    String comment_id;
    String member_name;
    String member_id;
    String joined_date;
    String image_url;
    String designation;
    String country_code;
    String reply_count;
    String url;
    String board_position;
    String company_name;
    String comment;
    String like;
    String like_status;
    String member_about;
    String b_day;
    String num;
    String starStatus;
    String message_status;
    int excomType;


    public MemberDetails(String n, String e) {
        this.member_name = n;
    }
    public MemberDetails()
    {

    }

    public String getStarStatus() {
        return starStatus;
    }

    public void setStarStatus(String starStatus) {
        this.starStatus = starStatus;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReply_count() {
        return reply_count;
    }

    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getBoard_position() {
        return board_position;
    }

    public void setBoard_position(String board_position) {
        this.board_position = board_position;
    }

    public String getIndicator(){
        return Character.toString(member_name.charAt(0));
    }



    public String getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(String joined_date) {
        this.joined_date = joined_date;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getLike_status() {
        return like_status;
    }

    public void setLike_status(String like_status) {
        this.like_status = like_status;
    }

    public String getMember_about() {
        return member_about;
    }

    public void setMember_about(String member_about) {
        this.member_about = member_about;
    }

    public String getB_day() {
        return b_day;
    }

    public void setB_day(String b_day) {
        this.b_day = b_day;
    }

    public String toString()
    {
        return member_name;
    }


    public String getMessage_status() {
        return message_status;
    }

    public void setMessage_status(String message_status) {
        this.message_status = message_status;
    }

    public int getExcomType() {
        return excomType;
    }

    public void setExcomType(int excomType) {
        this.excomType = excomType;
    }
}
