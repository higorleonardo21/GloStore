package bo.hlva.glostore.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import bo.hlva.glostore.databinding.ActivityCreateAccountBinding;
import bo.hlva.glostore.ui.activities.MainActivity;
import bo.hlva.glostore.ui.listeners.OnCreateAccountListener;
import bo.hlva.glostore.ui.viewmodels.CreateAccountViewModel;
import bo.hlva.glostore.utils.Constants;
import com.itsaky.androidide.logsender.LogSender;

public class CreateAccountActivity extends AppCompatActivity implements OnCreateAccountListener {

  private ActivityCreateAccountBinding binding;
  private CreateAccountViewModel mViewModel;

  private ProgressDialog mProgressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // log for AndroidIDE
    LogSender.startLogging(this);
    super.onCreate(savedInstanceState);

    binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // viewmodel
    mViewModel =
        new ViewModelProvider(this, new CreateAccountViewModel.Factory(this))
            .get(CreateAccountViewModel.class);

    setupViews();
  }

  private void setupViews() {
    // actionbar
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setSubtitle("Crear Cuenta");

    mProgressDialog = new ProgressDialog(this);
    mProgressDialog.setMessage("Creando Cuenta Espere, Por Favor..");
    mProgressDialog.setCancelable(false);

    binding.btnCreateAccount.setOnClickListener(
        (view) -> {
          if (checkInput()) {

            mProgressDialog.show();

            // get input text
            String name = binding.edtName.getText().toString();
            String lastName = binding.edtLastName.getText().toString();
            String email = binding.edtEmail.getText().toString();
            String password = binding.edtPassword.getText().toString();

            mViewModel
                .createAccount(name, lastName, email, password)
                .observe(
                    this,
                    new Observer<Boolean>() {

                      @Override
                      public void onChanged(Boolean state) {

                        if (state) {
                          mProgressDialog.dismiss();
                        }
                      }
                    });
          }
        });
  }

  private boolean checkInput() {

    // check name
    if (binding.edtName.getText().toString().isEmpty()) {
      binding.layoutName.setError("Campo Vacio");
      binding.layoutName.requestFocus();
      return false;
    } else {
      binding.layoutName.setError("");
      binding.layoutName.requestFocus();
    }

    // check last name
    if (binding.edtLastName.getText().toString().isEmpty()) {
      binding.layoutLastName.setError("Campo Vacio");
      binding.layoutLastName.requestFocus();
      return false;
    } else {
      binding.layoutLastName.setError("");
      binding.layoutLastName.requestFocus();
    }

    // check email
    if (binding.edtEmail.getText().toString().isEmpty()) {
      binding.layoutEmail.setError("Campo Vacio");
      binding.layoutEmail.requestFocus();
      return false;
    } else {
      binding.layoutEmail.setError("");
      binding.layoutEmail.requestFocus();
    }

    // check password
    if (binding.edtPassword.getText().toString().isEmpty()) {
      binding.layoutPassword.setError("Campo Vacio");
      binding.layoutPassword.requestFocus();
      return false;
    } else {
      binding.layoutPassword.setError("");
      binding.layoutPassword.requestFocus();
    }

    // check repeat password
    if (binding.edtRepeatPassword.getText().toString().isEmpty()) {
      binding.layoutRepeatPassword.setError("Campo Vacio");
      binding.layoutRepeatPassword.requestFocus();
      return false;
    } else {
      binding.layoutRepeatPassword.setError("");
      binding.layoutRepeatPassword.requestFocus();
    }

    // check input email
    String email = binding.edtEmail.getText().toString();

    if (!email.contains("@")) {
      binding.layoutEmail.setError("Correo No Valido");
      binding.layoutEmail.requestFocus();
      return false;
    } else {
      binding.layoutEmail.setError("");
      binding.layoutEmail.requestFocus();
    }

    if (!binding
        .edtPassword
        .getText()
        .toString()
        .equals(binding.edtRepeatPassword.getText().toString())) {

      binding.layoutPassword.setError("Contraseña No Coinciden, Verificar Por Favor");
      binding.layoutRepeatPassword.requestFocus();

      return false;
    }

    return true;
  }

  @Override
  public void onCreateAccountListener(boolean isOk, String message, int typeError) {

    if (isOk) {
      startActivity(new Intent(this, MainActivity.class));
      finishAffinity();
    } else {

      if (typeError == Constants.GET_AUTH_ERROR_COLLISION_EMAIL) {

        binding.layoutEmail.setError(message);
        binding.layoutEmail.requestFocus();

      } else {
        binding.layoutEmail.setError("");
        binding.layoutEmail.requestFocus();
      }
    }
  }
}
