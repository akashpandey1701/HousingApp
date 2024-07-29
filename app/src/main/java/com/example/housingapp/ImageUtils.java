package com.example.housingapp;


import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

public class ImageUtils {
    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }
}
