package bo.hlva.glostore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import bo.hlva.glostore.databinding.FragmentFavoritesBinding;

public class FavoritesFragment extends Fragment {

  private FragmentFavoritesBinding binding;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {

    binding = FragmentFavoritesBinding.inflate(inflater, root, false);
    return binding.getRoot();
  }
}
