package bo.hlva.glostore.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import bo.hlva.glostore.data.model.Product;
import bo.hlva.glostore.databinding.ItemProductBinding;
import bo.hlva.glostore.ui.listeners.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class ProductsAdapter extends FirestoreRecyclerAdapter<Product, ProductsAdapter.ViewHolder> {
    
    private OnItemClickListener listener;
    
    public ProductsAdapter(FirestoreRecyclerOptions<Product> options,OnItemClickListener listener){
        super(options);
        this.listener = listener;
  }

  @Override
  public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      ItemProductBinding binding = ItemProductBinding.inflate(inflater,parent,false);

    return new ViewHolder(binding);
  }
  

  @Override
  protected void onBindViewHolder(ProductsAdapter.ViewHolder holder, int position, Product model) {
      
      DocumentSnapshot document = getSnapshots().getSnapshot(position);
      final String idProduct = document.getId();
      
      //get value
      holder.binding.itemName.setText(model.getName());
      holder.binding.itemPrice.setText("Precio: " + String.valueOf(model.getPrice()) + " Bs");
      holder.binding.itemCategorie.setText("Categoria: " + model.getCategorie());
      
      //get url image
      Glide.with(holder.binding.getRoot().getContext()).load(model.getUrlImage()).centerCrop().into(holder.binding.itemImg);
      
      //click item
      holder.binding.getRoot().setOnClickListener(view -> {
          listener.onItemClick(idProduct);
      });
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private ItemProductBinding binding;

    public ViewHolder(ItemProductBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}

