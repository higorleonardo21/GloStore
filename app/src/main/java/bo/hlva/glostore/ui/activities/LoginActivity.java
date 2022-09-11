package bo.hlva.glostore.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import bo.hlva.glostore.databinding.ActivityLoginBinding;
import bo.hlva.glostore.ui.activities.CreateAccountActivity;
import bo.hlva.glostore.ui.activities.LoginActivity;
import bo.hlva.glostore.ui.activities.MainActivity;
import bo.hlva.glostore.ui.listeners.OnSignInListener;
import bo.hlva.glostore.ui.viewmodels.LoginViewModel;
import android.view.View;
import bo.hlva.glostore.utils.Constants;
import com.itsaky.androidide.logsender.LogSender;

public class LoginActivity extends AppCompatActivity implements OnSignInListener {

  private ActivityLoginBinding binding;
  private LoginViewModel mViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // log for AndroidIDE
    LogSender.startLogging(this);
    super.onCreate(savedInstanceState);

    // views binding
    binding = ActivityLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // viewmodel
    mViewModel =
        new ViewModelProvider(this, new LoginViewModel.Factory(this)).get(LoginViewModel.class);

    setupViews();
  }

  private void setupViews() {
    // actionbar
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setSubtitle("Inciar Sesion");

    binding.btnLogin.setOnClickListener(
        (view) -> {

          // get input text
          String email = binding.edtEmail.getText().toString();
          String password = binding.edtPassword.getText().toString();

          if (isOk(email, password)) {

            binding.progressBar.setVisibility(View.VISIBLE);
            binding.btnLogin.setVisibility(View.GONE);

            mViewModel
                .signIn(email, password)
                .observe(
                    this,
                    new Observer<Boolean>() {

                      @Override
                      public void onChanged(Boolean state) {

                        if (state) {
                          binding.progressBar.setVisibility(View.GONE);
                          binding.btnLogin.setVisibility(View.VISIBLE);
                        }
                      }
                    });
          }
        });
        
        binding.tvCreateAccount.setOnClickListener((view) ->{
            
            startActivity(new Intent(this,CreateAccountActivity.class));
            
        });
        
  }

  @Override
  public void onSignIn(boolean isOk, String message, int typeError) {

    if (isOk) {

      startActivity(new Intent(LoginActivity.this, MainActivity.class));
      finish();
      Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    } else {

      switch (typeError) {
        case Constants.GET_AUTH_ERROR_USER:
          binding.layoutEmail.setError(message);
          binding.layoutEmail.requestFocus();
          break;

        case Constants.GET_AUTH_ERROR_PASSWORD:
          binding.layoutPassword.setError(message);
          binding.layoutPassword.requestFocus();
      }
    }
  }

  private boolean isOk(String email, String password) {

    if (TextUtils.isEmpty(email)) {
      binding.layoutEmail.setError("Campo Vacio");
      binding.layoutEmail.requestFocus();
      return false;
    } else {
      binding.layoutEmail.setError("");
      binding.layoutEmail.requestFocus();
    }
    if (TextUtils.isEmpty(password)) {
      binding.layoutPassword.setError("Campo Vacio");
      binding.layoutPassword.requestFocus();
      return false;
    } else {
      binding.layoutPassword.setError("");
      binding.layoutPassword.requestFocus();
    }

    return true;
  }
}
