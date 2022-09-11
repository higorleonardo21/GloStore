package bo.hlva.glostore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import bo.hlva.glostore.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding binding;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {

    binding = FragmentHomeBinding.inflate(inflater, root, false);
    return binding.getRoot();
  }
}
