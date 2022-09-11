package bo.hlva.glostore.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import bo.hlva.glostore.databinding.ActivityAboutBuyBinding;

public class AboutBuyActivity extends AppCompatActivity {

  private ActivityAboutBuyBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // views binding
    binding = ActivityAboutBuyBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    setupViews();
  }

  private void setupViews() {
    // actionbar
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setSubtitle("Como Comprar");
  }
}
