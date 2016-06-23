package makdroid.gitproject.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzecho on 23.06.2016.
 */
public class GitHubResponse {

    public int totalCount;
    public boolean incompleteResults;
    public List<Item> items = new ArrayList<Item>();
}
