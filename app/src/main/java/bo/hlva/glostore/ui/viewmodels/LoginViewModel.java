package bo.hlva.glostore.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import bo.hlva.glostore.data.repositories.LoginRepository;
import bo.hlva.glostore.ui.listeners.OnSignInListener;
import bo.hlva.glostore.ui.viewmodels.LoginViewModel;

public class LoginViewModel extends ViewModel {

  private LoginRepository repository;

  public LoginViewModel(OnSignInListener listener) {
    repository = new LoginRepository(listener);
  }

  public LiveData<Boolean> signIn(String email, String password) {

    return repository.signIn(email, password);
  }

  public static class Factory implements ViewModelProvider.Factory {

    private OnSignInListener listener;

    public Factory(OnSignInListener listener) {
      this.listener = listener;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

      return (T) new LoginViewModel(listener);
    }
  }
}
