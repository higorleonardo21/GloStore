package bo.hlva.glostore.data.repositories;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainRepository {
    
    private FirebaseFirestore database;
    
    public MainRepository(){
        database = FirebaseFirestore.getInstance();
    }
    
    public MutableLiveData<Boolean> addFavorites(String idProduct){
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        
        //get id current user
        String idUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        
        database.collection("users").document(idUser).update();
        
        return data;
    }
    
    public MutableLiveData<Boolean> removeFavorites(String idProduct){
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        
        
        return data;
    }
}
