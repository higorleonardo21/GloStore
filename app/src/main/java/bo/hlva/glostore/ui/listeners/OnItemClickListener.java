package bo.hlva.glostore.ui.listeners;

public interface OnItemClickListener {
    
    void onItemClick(String idProduct);
    
    void onItemClickFavorites(boolean state,String idProduct);
}
