package bo.hlva.glostore.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import bo.hlva.glostore.data.repositories.MainRepository;

public class MainViewModel extends ViewModel {
    
    private MainRepository mRepository;
    
    public MainViewModel(){
        mRepository = new MainRepository();
    }
    
    public LiveData<Boolean> addFavorites(String idProduct){
        return mRepository.addFavorites(idProduct);
    }
    
    public LiveData<Boolean> removeFavorites(String idProduct){
        return mRepository.removeFavorites(idProduct);
    }
}
