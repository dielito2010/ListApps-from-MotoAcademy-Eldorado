package br.dev.danielribeiro.listapps;

import android.annotation.SuppressLint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SystemPropertiesHelper {

    private static final String PROP_NAME = "app.selected_name";

    public static void setAppName(String appName) {
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method setMethod = systemPropertiesClass.getDeclaredMethod("set", String.class, String.class);
            setMethod.invoke(null, PROP_NAME, appName);
        } catch
        (@SuppressLint({"NewApi", "LocalSuppress"})
        ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

