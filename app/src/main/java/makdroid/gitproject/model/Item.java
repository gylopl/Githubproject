package makdroid.gitproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grzecho on 23.06.2016.
 */
public class Item implements Comparable<Item> {

    @SerializedName("id")
    public int id;

    @Override
    public int compareTo(Item another) {
        if (id > another.id) {
            return 1;
        } else if (id < another.id) {
            return -1;
        } else {
            return 0;
        }
    }
}