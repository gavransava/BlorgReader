package savagavran.blorgreader.utils;

import com.google.gson.annotations.SerializedName;

public class BlogItem {

    @SerializedName("id")
    private Long mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("image_url")
    private String mImageUrl;

    @SerializedName("description")
    private String mDescription;


    public Long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getDescription() {
        return mDescription;
    }
}
