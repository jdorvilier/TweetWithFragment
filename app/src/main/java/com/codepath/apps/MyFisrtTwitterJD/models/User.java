package com.codepath.apps.MyFisrtTwitterJD.models;

/**
 * Created by Jonathan Dorvilier on 8/3/2017.
 */

public class User {
    private String name;
    private long uid;
    private  String screenName;
    private String profileImageUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }


    public String getScreenName() {

        return screenName;
    }


 /*   public static User fromJSON(JSONObject json){

        User u =  new User();

        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }
*/
}