package com.example.myapplication;

public class Upload {
    private String mName;
    private String mImageUrl;

    public Upload(){
        //thats for firebase
    }
    public Upload( String imageUrl){
        mImageUrl = imageUrl;
    }
    public String getImageUrl(){
        return mImageUrl;
    }
    public void setmImageUrl(String imageUrl){
        mImageUrl = imageUrl;
    }
}
