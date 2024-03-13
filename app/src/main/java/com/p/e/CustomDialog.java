package com.p.e;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
        applyCustomDensity(context.getResources());
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        applyCustomDensity(context.getResources());
    }

    protected CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        applyCustomDensity(context.getResources());
    }

    private void applyCustomDensity(Resources resources) {
        if (resources != null) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE;
            }
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    @Override
    public void show() {
        // Before showing the dialog, apply the updated density settings
        applyCustomDensity(getContext().getResources());
        super.show();

        // Ensure the dialog's window uses the updated density settings
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
        }
    }
}