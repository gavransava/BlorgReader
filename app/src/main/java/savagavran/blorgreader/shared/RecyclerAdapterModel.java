package savagavran.blorgreader.shared;

import java.util.List;

public interface RecyclerAdapterModel<T> {

    void setItems(List<T> itemList);

    boolean removeItem(int position);

    boolean removeItem(T item);

    void addItem(T item, int position);

    T getItem(int position);

    List<T> getItems();
}
