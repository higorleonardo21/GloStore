package bo.hlva.glostore.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import bo.hlva.glostore.databinding.ItemProductBinding;
import bo.hlva.glostore.ui.adapters.ProductsAdapter;
import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import java.util.ArrayList;
import bo.hlva.glostore.data.model.Product;

public class ProductsAdapter extends FirestoreRecyclerAdapter<Product, ProductsAdapter.ViewHolder> {
    
    public ProductsAdapter(FirestoreRecyclerOptions<Product> options){
        super(options);
    }

  @Override
  public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      ItemProductBinding binding = ItemProductBinding.inflate(inflater,parent,false);

    return new ViewHolder(binding);
  }

  @Override
  protected void onBindViewHolder(ProductsAdapter.ViewHolder holder, int position, Product model) {
      
      //get value
      holder.binding.itemName.setText(model.getName());
      holder.binding.itemPrice.setText(String.valueOf(model.getPrice()));
      holder.binding.itemCategorie.setText(model.getCategorie());
      
      //get url image
      Glide.with(holder.binding.getRoot().getContext()).load(model.getUrlImage()).into(holder.binding.itemImg);
      
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder {

    private ItemProductBinding binding;

    public ViewHolder(ItemProductBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}

