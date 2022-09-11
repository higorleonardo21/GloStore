package bo.hlva.glostore.data.repositories;

import androidx.lifecycle.MutableLiveData;
import bo.hlva.glostore.data.repositories.CreateAccountRepository;
import bo.hlva.glostore.ui.listeners.OnCreateAccountListener;
import bo.hlva.glostore.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class CreateAccountRepository {

  private FirebaseFirestore firebaseFirestore;
  private FirebaseAuth mAuth;

  private OnCreateAccountListener listener;

  public CreateAccountRepository(OnCreateAccountListener listener) {

    this.listener = listener;

    // database
    firebaseFirestore = FirebaseFirestore.getInstance();
    mAuth = FirebaseAuth.getInstance();
  }

  public MutableLiveData<Boolean> createAccount(
      String name, String lastName, String email, String password) {

    MutableLiveData<Boolean> data = new MutableLiveData<>();

    mAuth
        .createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(
            new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(Task<AuthResult> task) {

                if (task.isSuccessful()) {

                  String id = mAuth.getCurrentUser().getUid();

                  Map<String, Object> map = new HashMap<>();
                  map.put("idUser", id);
                  map.put("name", name);
                  map.put("lastName", lastName);
                  map.put("email", email);
                  map.put("password", password);

                  firebaseFirestore
                      .collection("users")
                      .document(id)
                      .set(map)
                      .addOnSuccessListener(
                          new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                              data.setValue(true);
                              listener.onCreateAccountListener(true, "Bienvenido A Glo Store", 0);
                            }
                          });
                }
              }
            })
        .addOnFailureListener(
            new OnFailureListener() {
              @Override
              public void onFailure(Exception e) {

                if (e instanceof FirebaseAuthUserCollisionException) {

                  data.setValue(true);
                  listener.onCreateAccountListener(
                      false,
                      "Correo Esta En Uso, Prueba Con Otro",
                      Constants.GET_AUTH_ERROR_COLLISION_EMAIL);
                }
              }
            });

    return data;
  }
}
