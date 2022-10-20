package bo.hlva.glostore.ui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import bo.hlva.glostore.R;
import bo.hlva.glostore.databinding.ActivityMainBinding;
import bo.hlva.glostore.ui.fragments.FavoritesFragment;
import bo.hlva.glostore.ui.fragments.HomeFragment;
import bo.hlva.glostore.ui.listeners.OnFavoritesListener;
import bo.hlva.glostore.ui.viewmodels.MainViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.itsaky.androidide.logsender.LogSender;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, OnFavoritesListener {

  private ActivityMainBinding binding;
  private MainViewModel mViewModel;
  private ActionBarDrawerToggle toggle;

  private FirebaseAuth mAuth;

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    toggle.syncState();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // log for AndroidIDE
    LogSender.startLogging(this);
    super.onCreate(savedInstanceState);

    // views binding
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    
    //viewmodel
    mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    setupViews();
  }

  @Override
  protected void onStart() {
    super.onStart();

    if (mAuth.getCurrentUser() == null) {

      startActivity(new Intent(this, LoginActivity.class));
      finish();
    }
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    toggle.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_home, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.menu_item_buy:
        startActivity(new Intent(this, AboutBuyActivity.class));
        break;
      case R.id.menu_item_about:
        showDialogAbout();
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {

    if (binding.drawerLayout.isOpen()) {
      binding.drawerLayout.closeDrawers();
    } else {

      super.onBackPressed();
    }
  }

  // **************

  private void setupViews() {
      
    // actionbar
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    toggle =
        new ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.drawer_open,
            R.string.drawer_close);
    binding.drawerLayout.addDrawerListener(toggle);

    // check delfauft item
    binding.navigationView.setCheckedItem(R.id.menu_item_home);
    loadFragment(new HomeFragment(this));
    getSupportActionBar().setSubtitle("Home");

    binding.navigationView.setNavigationItemSelectedListener(this);

    // firebase database
    mAuth = FirebaseAuth.getInstance();
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.menu_item_home:
        loadFragment(new HomeFragment(this));
        getSupportActionBar().setSubtitle("Home");
        binding.navigationView.setCheckedItem(item);
        break;

      case R.id.menu_item_favorites:
        loadFragment(new FavoritesFragment());
        getSupportActionBar().setSubtitle("Favoritos");
        binding.navigationView.setCheckedItem(item);
        break;
    }

    // close drawer
    binding.drawerLayout.closeDrawers();

    return true;
  }

  // load fragment the navigation view
  private void loadFragment(Fragment fragment) {

    getSupportFragmentManager()
        .beginTransaction()
        .replace(binding.containerFragments.getId(), fragment, "fragment")
        .commit();
  }

  private void showDialogAbout() {
    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
    builder.setMessage(
        "Glo Store es una tienda online disponible en Ascencion de Guarayos,\n"
            + "Desarrollada por ****GloSoft****\n\n"
            + "Contacto Administracion de la tienda:\n"
            + "N° 72241373\n\n"
            + "Contacto del Desarrollador:\n"
            + "N° 67722285\n"
            + "Email hlvargasarrazola@gmail.com");
    builder.show();
  }

  @Override
  public void addFavorites(String idProduct) {
     // mViewModel.addFavorites(idProduct);
      Toast.makeText(this,"Añadido A Favoritos",Toast.LENGTH_SHORT).show();
  }

  @Override
  public void removeFavorites(String idProduct) {
      mViewModel.removeFavorites(idProduct);
      Toast.makeText(this,"Eliminado De Favoritos",Toast.LENGTH_SHORT).show();
  }
}
