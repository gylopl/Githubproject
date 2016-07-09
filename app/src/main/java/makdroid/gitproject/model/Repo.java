package makdroid.gitproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grzecho on 04.07.2016.
 */
public class Repo extends Item {

    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;


}
