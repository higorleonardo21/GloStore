package bo.hlva.glostore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import bo.hlva.glostore.data.model.Product;
import bo.hlva.glostore.databinding.FragmentHomeBinding;
import bo.hlva.glostore.ui.adapters.ProductsAdapter;
import bo.hlva.glostore.ui.dialogs.DetailsDialog;
import bo.hlva.glostore.ui.listeners.OnItemClickListener;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HomeFragment extends Fragment implements OnItemClickListener{

  private FragmentHomeBinding binding;
  private ProductsAdapter mAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {

    binding = FragmentHomeBinding.inflate(inflater, root, false);

    setupViews();
    return binding.getRoot();
  }

  private void setupViews() {
      
      
  }

  @Override
  public void onStart() {
          super.onStart();
          
              Query query = FirebaseFirestore.getInstance().collection("products");
            FirestoreRecyclerOptions<Product> options =
            new FirestoreRecyclerOptions.Builder<Product>().setQuery(query, Product.class).build();

            mAdapter = new ProductsAdapter(options, this);

            binding.recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
            binding.recyclerview.setHasFixedSize(true);
            binding.recyclerview.setAdapter(mAdapter);
          
            mAdapter.startListening();
  }

  @Override
  public void onStop() {
    super.onStop();
    mAdapter.stopListening();
  }

    @Override
    public void onItemClick(String idProduct) {
        //show details dialog
        DetailsDialog.getInstance(idProduct,getFragmentManager());
    }
    
}

