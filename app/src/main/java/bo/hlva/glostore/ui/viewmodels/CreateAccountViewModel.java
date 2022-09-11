package bo.hlva.glostore.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import bo.hlva.glostore.data.repositories.CreateAccountRepository;
import bo.hlva.glostore.ui.listeners.OnCreateAccountListener;
import bo.hlva.glostore.ui.viewmodels.CreateAccountViewModel;

public class CreateAccountViewModel extends ViewModel {

  private CreateAccountRepository mRepository;

  public CreateAccountViewModel(OnCreateAccountListener listener) {
    mRepository = new CreateAccountRepository(listener);
  }

  public LiveData<Boolean> createAccount(
      String name, String lastName, String email, String password) {
    return mRepository.createAccount(name, lastName, email, password);
  }

  public static class Factory implements ViewModelProvider.Factory {

    private OnCreateAccountListener listener;

    public Factory(OnCreateAccountListener listener) {
      this.listener = listener;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> arg0) {

      return (T) new CreateAccountViewModel(listener);
    }
  }
}
