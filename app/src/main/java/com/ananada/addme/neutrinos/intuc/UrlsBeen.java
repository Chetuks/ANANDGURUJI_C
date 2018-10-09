package com.ananada.addme.neutrinos.intuc;

/**
 * Created by guru on 10/11/17.
 */

public class UrlsBeen {
    String filetype;
    String url;
    int viewCount;
    String imagetype;
    int uploadid;
    int commentcount;
    int likecount;
    boolean likestatus;

    public UrlsBeen(String filetype, String url, int viewCount, String imagetype, int uploadid, int likecount, int commentcount, boolean likestatus)
    {
        this.filetype = filetype;
        this.url = url;
        this.viewCount=viewCount;
        this.imagetype=imagetype;
        this.uploadid=uploadid;
        this.likecount=likecount;
        this.commentcount=commentcount;
        this.likestatus=likestatus;

    }
    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public boolean isLikestatus() {
        return likestatus;
    }

    public void setLikestatus(boolean likestatus) {
        this.likestatus = likestatus;
    }
    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getImagetype() {
        return imagetype;
    }

    public void setImagetype(String imagetype) {
        this.imagetype = imagetype;
    }

    public int getUploadid() {
        return uploadid;
    }

    public void setUploadid(int uploadid) {
        this.uploadid = uploadid;
    }
}