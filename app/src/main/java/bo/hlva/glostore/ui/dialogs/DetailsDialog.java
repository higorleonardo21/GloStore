package bo.hlva.glostore.ui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import bo.hlva.glostore.databinding.DetailsDialogBinding;
import bo.hlva.glostore.ui.dialogs.DetailsDialog;
import bo.hlva.glostore.ui.listeners.OnFavoritesListener;
import bo.hlva.glostore.ui.viewmodels.DetailsViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DetailsDialog extends DialogFragment {

  private DetailsDialogBinding binding;
  private DetailsViewModel mViewModel;
  
  private OnFavoritesListener listener;
  
  private String idProduct;

  // return instance
  public static DetailsDialog getInstance(String idProduct, FragmentManager manager,OnFavoritesListener listener) {

    DetailsDialog dialog = new DetailsDialog(idProduct,listener);
    dialog.show(manager, "details_dialog");

    return dialog;
  }
  
  public DetailsDialog(String idProduct,OnFavoritesListener listener){
      this.idProduct = idProduct;
      this.listener = listener;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveState) {
    
    binding = DetailsDialogBinding.inflate(inflater,parent,false);
    return binding.getRoot();
  }
  
  @Override
      public void onViewCreated(View arg0, Bundle arg1) {
          super.onViewCreated(arg0, arg1);
          
          mViewModel = new ViewModelProvider(requireActivity()).get(DetailsViewModel.class);
          
          //show effect shimmer
          binding.container.setVisibility(View.GONE);
          binding.shimmer.setVisibility(View.VISIBLE);

          //get item product
          mViewModel.getItem(idProduct).observe(this,(item) ->{
              
              binding.container.setVisibility(View.VISIBLE);
              binding.shimmer.setVisibility(View.GONE);
              
              binding.itemName.setText(item.getName());
              if(item.getDescription() != null) binding.itemDescription.setText(item.getDescription());
              binding.itemPrice.setText(String.valueOf(item.getPrice()) + " Bs");
              
              Glide.with(getContext()).load(item.getUrlImage()).centerCrop().into(binding.itemImage);
              
          });
          
          binding.checkbox.setOnCheckedChangeListener((checkbox,state) ->{
              
              if(state){
                  listener.addFavorites(idProduct);
              }
              else{
                  listener.removeFavorites(idProduct);
              }
          });
      }
      
}
