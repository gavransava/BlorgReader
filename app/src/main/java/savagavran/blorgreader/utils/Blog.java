package savagavran.blorgreader.utils;

import com.google.gson.annotations.SerializedName;

public class Blog {
    @SerializedName("content")
    private String mContent;

    public String getContent() {
        return mContent;
    }
}
