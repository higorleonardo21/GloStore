package bo.hlva.glostore.ui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import bo.hlva.glostore.databinding.DetailsDialogBinding;
import bo.hlva.glostore.ui.dialogs.DetailsDialog;
import bo.hlva.glostore.ui.viewmodels.DetailsViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DetailsDialog extends BottomSheetDialogFragment {

  private DetailsDialogBinding binding;
  private DetailsViewModel mViewModel;
  
  private String idProduct;

  // return instance
  public static DetailsDialog getInstance(String idProduct, FragmentManager manager) {

    DetailsDialog dialog = new DetailsDialog(idProduct);
    dialog.show(manager, "details_dialog");

    return dialog;
  }
  
  public DetailsDialog(String idProduct){
      this.idProduct = idProduct;
      
      mViewModel = new ViewModelProvider(requireActivity()).get(DetailsViewModel.class);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveState) {
    
    binding = DetailsDialogBinding.inflate(inflater,parent,false);
    return binding.getRoot();
  }
  
  @Override
      public void onViewCreated(View arg0, Bundle arg1) {
          super.onViewCreated(arg0, arg1);
          
          //get item product
          mViewModel.getItem(idProduct).observe(this,(item) ->{
              
              binding.itemName.setText(item.getName());
              binding.itemName.setText(String.valueOf(item.getPrice()));
              
              //get image
             // Glide.with(getContext()).load(item.getUrlImage()).into(binding.);
              
              
          });
      }
      
}
