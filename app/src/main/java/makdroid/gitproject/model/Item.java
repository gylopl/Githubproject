package makdroid.gitproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grzecho on 23.06.2016.
 */
public class Item {

    @SerializedName("id")
    public int id;
    @SerializedName("login")
    public String login;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("type")
    public String type;
    @SerializedName("score")
    public float score;

    @SerializedName("name")
    public String name;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("description")
    public String description;

}