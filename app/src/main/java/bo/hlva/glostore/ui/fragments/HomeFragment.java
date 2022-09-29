package bo.hlva.glostore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import bo.hlva.glostore.data.model.Product;
import bo.hlva.glostore.databinding.FragmentHomeBinding;
import bo.hlva.glostore.ui.adapters.ProductsAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding binding;
  private ProductsAdapter mAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {

    binding = FragmentHomeBinding.inflate(inflater, root, false);
    
    setupViews();
    return binding.getRoot();
  }
  
  private void setupViews(){
      
      binding.recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
      binding.recyclerview.setHasFixedSize(true);
      
      Query query = FirebaseFirestore.getInstance().collection("products");
      FirestoreRecyclerOptions<Product> options = new FirestoreRecyclerOptions.Builder<Product>().setQuery(query,Product.class).build();
      
      mAdapter = new ProductsAdapter(options);
      mAdapter.startListening();
      
      binding.recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
      binding.recyclerview.setHasFixedSize(true);
      binding.recyclerview.setAdapter(mAdapter);
  }
  
  @Override
      public void onStop() {
          super.onStop();
          mAdapter.stopListening();
      }
      
}

