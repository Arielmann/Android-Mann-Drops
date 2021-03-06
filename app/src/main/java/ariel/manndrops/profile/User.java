package ariel.manndrops.profile;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by home on 4/14/2016.
 */
public class User implements Serializable {

    private int id;
    private String name;
    private String profileImageUrl = "";
    private String profileImagePath;
    private String gcmToken = "el9hAVOFKos:APA91bFEeFnKCt9Rm-Bv7384Zo0SWCUCG21iG8BGuOztmpaexo9DYSMf9Ln2KTE7EPAx_fiW7bUPce8xS-_vzOHYsHYkyhLH5s7ehtOn2BBH3UFT6bixs6cyA-1_0qdlauEMr7vxz87p";
    private Bitmap userImageBitmap;


    public User(int id, String name, String profileImageUrl, String token) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.profileImagePath = "none (Defined in User class)";
        this.gcmToken = token;
        //this.userImageBitmap = ImageUtils.defaultProfileImage;
        //this.profileImagePath = "file path was not define (init is in User class)";
    }


    //*******************Getters****************************//
    public String getGcmToken() {
        return gcmToken;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public Bitmap getUserImageBitmap() {
        return userImageBitmap;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    //***************************Setters****************************//
    public void setId(int id) {
        this.id = id;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public void setUserImageBitmap(Bitmap stylistImageBitmap) {

        this.userImageBitmap = stylistImageBitmap;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    @Override
    public String toString() {
        return name + " from " + " Profile image file:" + profileImagePath
                + " Token " + gcmToken + " Description: ";
    }

}
