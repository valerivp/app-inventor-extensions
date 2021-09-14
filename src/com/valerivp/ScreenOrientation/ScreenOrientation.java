package com.valerivp.ScreenOrientation;
// package es.kio4.Pifagoras; // New version.

//  © Juan Antonio Villalpando 
// kio4.com
// Creacion de extensiones. Junio 2017.
// Esta extension calcula la hipotenusa mediante el Teorema de Pifagoras.

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.*;

import android.app.Activity;
import android.content.pm.ActivityInfo;
//import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.Surface;

@DesignerComponent(version = ScreenOrientation.VERSION,
    description = "",
    category = ComponentCategory.EXTENSION,
    nonVisible = true,
    iconName = "")
@SimpleObject(external = true)
public class ScreenOrientation extends AndroidNonvisibleComponent implements Component {

    public static final int VERSION = 3;
    private ComponentContainer container;
    private final Activity activity;

    public ScreenOrientation(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        activity = container.$context();

    }
    private static class Orientation {
        private static final int LANDSCAPE = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        private static final int PORTRAIT = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        private static final int REVERSE_LANDSCAPE = 8; // ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
        private static final int REVERSE_PORTRAIT = 9; // ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
        private static final int UNSPECIFIED = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int LANDSCAPE() {
        return Orientation.LANDSCAPE;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int PORTRAIT() {
        return Orientation.PORTRAIT;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int REVERSE_LANDSCAPE() {
        return Orientation.REVERSE_LANDSCAPE;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int REVERSE_PORTRAIT() {
        return Orientation.REVERSE_PORTRAIT;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int UNSPECIFIED() {
        return Orientation.UNSPECIFIED;
    }


    @SimpleProperty(category = PropertyCategory.BEHAVIOR,
            description = "get screen orientation")
//    @SimpleFunction(description = "get screen orientation")
    public int Orientation() {
        int result = Orientation.LANDSCAPE;
        final Display display = activity.getWindowManager().getDefaultDisplay();
        final int rotation = display.getRotation();

        final int width, height;
        if (Build.VERSION.SDK_INT >= 13) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
        } else {
            width = display.getWidth();
            height = display.getHeight();
        }
        switch (rotation) {
            case Surface.ROTATION_90:
                if (width > height) {
                    result = Orientation.LANDSCAPE;
                }
                else {
                    result = Orientation.REVERSE_PORTRAIT;
                }
                break;
            case Surface.ROTATION_180:
                if (height > width) {
                    result = Orientation.REVERSE_PORTRAIT;
                }
                else {
                    result = Orientation.REVERSE_LANDSCAPE;
                }
                break;
            case Surface.ROTATION_270:
                if (width > height) {
                    result = Orientation.REVERSE_LANDSCAPE;
                }
                else {
                    result = Orientation.PORTRAIT;
                }
                break;
            default:
                if (height > width) {
                    result = Orientation.PORTRAIT;
                }
                else {
                    result = Orientation.LANDSCAPE;
                }
        }

        return result;

    }


    @SimpleFunction(description = "Lock screen orientation")
    public void LockOrientation(int orientation) {
         activity.setRequestedOrientation(orientation);
    }

    @SimpleFunction(description = "Unlock screen orientation")
    public void UnockOrientation() {
        activity.setRequestedOrientation(Orientation.UNSPECIFIED);
    }

 } 
