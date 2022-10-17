package bo.hlva.glostore;

import android.app.Application;
import com.google.android.material.color.DynamicColors;

public class Applications extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    
    //apply theme material 3 android
   // DynamicColors.applyToActivitiesIfAvailable(this);
  }
}
