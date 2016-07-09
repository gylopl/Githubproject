package makdroid.gitproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grzecho on 04.07.2016.
 */
public class User extends Item{

    @SerializedName("login")
    public String login;
    @SerializedName("avatar_url")
    public String avatarUrl;

}
