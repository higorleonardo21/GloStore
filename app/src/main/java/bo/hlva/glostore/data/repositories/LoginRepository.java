package bo.hlva.glostore.data.repositories;

import androidx.lifecycle.MutableLiveData;
import bo.hlva.glostore.ui.listeners.OnSignInListener;
import bo.hlva.glostore.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginRepository {

  private FirebaseAuth auth;
  private OnSignInListener listener;

  public LoginRepository(OnSignInListener listener) {

    this.listener = listener;
    auth = FirebaseAuth.getInstance();
  }

  public MutableLiveData<Boolean> signIn(String email, String password) {
    MutableLiveData<Boolean> data = new MutableLiveData<>();

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(
            new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(Task<AuthResult> task) {

                if (task.isSuccessful()) {

                  data.setValue(true);
                  listener.onSignIn(true, "Bienvenido A Glo Store",0);
                }
              }
            })
        .addOnFailureListener(
            new OnFailureListener() {

              @Override
              public void onFailure(Exception e) {

                if (e instanceof FirebaseAuthInvalidUserException) {
                  data.setValue(true);
                  listener.onSignIn(false, "Usuario No Valido",Constants.GET_AUTH_ERROR_USER);
                } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                  data.setValue(true);
                  listener.onSignIn(false, "Contraseña No Valida",Constants.GET_AUTH_ERROR_PASSWORD);
                }
              }
            });

    return data;
  }
}
