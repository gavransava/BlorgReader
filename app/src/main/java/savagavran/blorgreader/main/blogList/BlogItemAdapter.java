package savagavran.blorgreader.main.blogList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import savagavran.blorgreader.ItemListener;
import savagavran.blorgreader.R;
import savagavran.blorgreader.RecyclerAdapterView;
import savagavran.blorgreader.shared.RecyclerAdapterModel;
import savagavran.blorgreader.utils.BlogItem;

public class BlogItemAdapter extends RecyclerView.Adapter<BlogItemAdapter.BlogItemHolder>
        implements RecyclerAdapterModel<BlogItem>, RecyclerAdapterView {

    private List<BlogItem> mItems;
    private ItemListener mItemListener;

    public BlogItemAdapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public BlogItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new BlogItemHolder(layoutInflater.inflate(R.layout.blog_item, parent, false),
                mItemListener);
    }

    @Override
    public void onBindViewHolder(BlogItemHolder holder, int position) {
        BlogItem blogItem = mItems.get(position);
        holder.title.setText(blogItem.getTitle());
        Picasso.with(holder.image.getContext()).load(blogItem.getImageUrl()).into(holder.image);
        holder.description.setText(Html.fromHtml(blogItem.getDescription()).toString().trim());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void setItems(List<BlogItem> itemList) {
        mItems = itemList;
    }

    @Override
    public boolean removeItem(int position) {
        return false;
    }

    @Override
    public boolean removeItem(BlogItem item) {
        return false;
    }

    @Override
    public void addItem(BlogItem item, int position) {

    }

    @Override
    public BlogItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public List<BlogItem> getItems() {
        return mItems;
    }

    @Override
    public void setItemListener(ItemListener itemListener) {
        mItemListener = itemListener;
    }

    public class BlogItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView image;
        TextView description;


        private ItemListener mItemListener;

        BlogItemHolder(View itemView, ItemListener listener) {
            super(itemView);
            mItemListener = listener;
            title = (TextView) itemView.findViewById(R.id.blog_title);
            image = (ImageView) itemView.findViewById(R.id.blog_image);
            description = (TextView) itemView.findViewById(R.id.blog_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mItemListener.onItemClick(position);
        }
    }
}
