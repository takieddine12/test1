package com.p.e;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    private static final int[] ORDERED_DENSITY_DP = {
            DisplayMetrics.DENSITY_LOW,
            DisplayMetrics.DENSITY_MEDIUM,
            DisplayMetrics.DENSITY_TV,
            DisplayMetrics.DENSITY_HIGH,
            DisplayMetrics.DENSITY_260,
            DisplayMetrics.DENSITY_280,
            DisplayMetrics.DENSITY_XHIGH,
            DisplayMetrics.DENSITY_300,
            DisplayMetrics.DENSITY_600,
            DisplayMetrics.DENSITY_520,
            DisplayMetrics.DENSITY_450,
            DisplayMetrics.DENSITY_340,
            DisplayMetrics.DENSITY_360,
            DisplayMetrics.DENSITY_400,
            DisplayMetrics.DENSITY_420,
            DisplayMetrics.DENSITY_440,
            DisplayMetrics.DENSITY_XXHIGH,
            DisplayMetrics.DENSITY_560,
            DisplayMetrics.DENSITY_XXXHIGH,
    };

    @Override
    protected void attachBaseContext(final Context baseContext) {
        Context newContext = baseContext;

        // Screen zoom is supported from API 24+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Resources resources = baseContext.getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();

            if (displayMetrics.densityDpi != DisplayMetrics.DENSITY_DEVICE_STABLE) {
                if (isDisplaySizeForced(baseContext)) {
                    float defaultDensity = (DisplayMetrics.DENSITY_DEVICE_STABLE / (float) DisplayMetrics.DENSITY_DEFAULT);
                    float defaultScreenWidthDp = displayMetrics.widthPixels / defaultDensity;
                    configuration.densityDpi = findDensityDpCanFitScreen((int) defaultScreenWidthDp);
                } else {
                    // Use device default density
                    configuration.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE;
                }
                configuration.fontScale = 1.0f;
                newContext = baseContext.createConfigurationContext(configuration);
            }

        }
        super.attachBaseContext(newContext);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private boolean isDisplaySizeForced(Context context) {
        // Check if display size is forced
        String displaySizeForced = Settings.Global.getString(context.getContentResolver(), "display_size_forced");
        return displaySizeForced != null && !displaySizeForced.isEmpty();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static int findDensityDpCanFitScreen(final int densityDp) {
        int[] orderedDensityDp = ORDERED_DENSITY_DP;
        int index = 0;
        while (densityDp >= orderedDensityDp[index]) {
            index++;
        }
        return orderedDensityDp[index];
    }
}