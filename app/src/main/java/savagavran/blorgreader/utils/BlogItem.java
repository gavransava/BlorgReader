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
}
