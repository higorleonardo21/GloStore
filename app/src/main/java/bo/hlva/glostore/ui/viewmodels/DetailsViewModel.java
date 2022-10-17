package bo.hlva.glostore.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import bo.hlva.glostore.data.model.Product;
import bo.hlva.glostore.data.repositories.DetailsRepository;

public class DetailsViewModel extends ViewModel {
    
    private DetailsRepository mRepository;
    
    public DetailsViewModel(){
        mRepository = new DetailsRepository();
    }
    
    public LiveData<Product> getItem(String idProduct){
        return mRepository.getItem(idProduct);
    }
}
