package bo.hlva.glostore.data.repositories;

import androidx.lifecycle.MutableLiveData;
import bo.hlva.glostore.data.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailsRepository {

  private FirebaseFirestore database;

  public DetailsRepository() {
    database = FirebaseFirestore.getInstance();
  }
  
  //get item product 
  public MutableLiveData<Product> getItem(String idProduct) {
    MutableLiveData<Product> data = new MutableLiveData<>();

    database
        .collection("products")
        .document(idProduct)
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<DocumentSnapshot>() {
              @Override
              public void onComplete(Task<DocumentSnapshot> task) {
                  
                  if(task.isSuccessful()){
                      
                      Product item = task.getResult().toObject(Product.class);
                      data.setValue(item);
                  }
                  
              }
            });

    return data;
  }
}
