package dev.mdb.mdbpokedex.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String forceThreeDigits(int n) {
        String num = Integer.toString(n);
        while (num.length() < 3) {
            num = "0" + num;
        }
        return num;
    }
}