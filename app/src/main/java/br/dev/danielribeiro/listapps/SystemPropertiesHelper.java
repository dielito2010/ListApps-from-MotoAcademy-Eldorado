package com.example.listapps2;

import static androidx.core.graphics.TypefaceCompatUtil.closeQuietly;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class SystemPropertiesHelper {
    public static final String GETPROP_EXECUTABLE_PATH = "/system/bin/getprop";
    private static final String SETPROP_EXECUTABLE_PATH = "/system/bin/setprop";

    public static String read(String propName) {
        Process process = null;
        BufferedReader bufferedReader = null;
        try {
            process = new ProcessBuilder().command(GETPROP_EXECUTABLE_PATH, propName).redirectErrorStream(true).start();
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bufferedReader.readLine();
            if (line == null) {
                line = ""; //prop not set
            }
            Log.i("SystemProperties", "Reading prop:" + propName + " value:" + line);
            return line;
        } catch (Exception e) {
            Log.e("SystemProperties", "Failed to read System Property " + propName, e);
            return "";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
    }

    public static void write(String Key, String value){ //throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {

        Log.i("SystemProperties", "Set prop");
        InputStreamReader in = null;
        BufferedReader reader = null;
        try {
            Process proc = Runtime.getRuntime().exec("/system/bin/setprop "+Key+" "+value);
            in = new InputStreamReader(proc.getInputStream());
            reader = new BufferedReader(in);

            String line = null;
            Log.d("Saurabh Shell" ,"<OUTPUT>");
            while ( (line = reader.readLine()) != null)
                Log.d("Shell" , line);
            Log.d("Saurabh Shell", "</OUTPUT>");
            int exitVal = proc.waitFor();
            Log.d("Saurabh Shell","Process exitValue: " + exitVal);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(in);
            closeQuietly(reader);
        }


      /*  Log.i("SystemProperties", "Set prop");

        Class clazz = null;
        clazz = Class.forName("android.os.SystemProperties");

        Method method = clazz.getDeclaredMethod("set", String.class, String.class);
        String prop = (String) method.invoke('2', "vold.has_adoptable");

        Log.e("so_test", "my prop is: <" + prop + ">");
*/
    }

}
