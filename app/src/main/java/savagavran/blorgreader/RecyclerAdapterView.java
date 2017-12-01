package savagavran.blorgreader;

public interface RecyclerAdapterView {

    void notifyDataSetChanged();

    void notifyItemChanged(int position);

//    void notifyItemInserted(int position);
//
//    void notifyItemRemoved(int position);
//
//    void notifyItemRangeChanged(int start, int end);
//
//    void notifyItemRangeInserted(int start, int end);
//
//    void notifyItemRangeRemoved(int start, int end);

    void setItemListener(ItemListener itemListener);
}
