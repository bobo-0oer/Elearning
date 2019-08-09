package com.example.elearning.Home_Fragment.Activity.User_Activity;

public class UserActivity {


    public UserActivity(int headId,String userName,String content,String pictureId,int LikeId){

        this.headId=headId;
        this.userName=userName;
        this.content=content;
        this.pictureId=pictureId;
        this.likeId=LikeId;
    }

    private int headId=0;
    private String userName=null;
    private String content=null;
    private String pictureId=null;
    private int likeId=0;

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

}
