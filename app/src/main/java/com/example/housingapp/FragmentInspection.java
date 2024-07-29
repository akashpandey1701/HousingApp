package com.example.housingapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class FragmentInspection extends Fragment {
    private static final int IMAGE_ID1 = 1;
    private static final int IMAGE_ID2 = 2;
    private static final int IMAGE_ID3 = 3;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final int LOCATION_REQUEST_CODE = 101;

    private Button photobutton1;
    private Button photobutton2;
    private Button photobutton3;
    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private double latitude;
    private double longitude;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private boolean[] imagesTaken = {false, false, false};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inspection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photobutton1 = view.findViewById(R.id.photobutton1);
        photobutton2 = view.findViewById(R.id.photobutton2);
        photobutton3 = view.findViewById(R.id.photobutton3);
        imageView = view.findViewById(R.id.imageView);
        imageView2 = view.findViewById(R.id.imageView2);
        imageView3 = view.findViewById(R.id.imageView3);
        latitudeTextView = view.findViewById(R.id.latitudeTextView);
        longitudeTextView = view.findViewById(R.id.longitudeTextView);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        photobutton1.setOnClickListener(v -> takePicture(IMAGE_ID1));
        photobutton2.setOnClickListener(v -> takePicture(IMAGE_ID2));
        photobutton3.setOnClickListener(v -> takePicture(IMAGE_ID3));

        Button submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> submitData());
    }

    private void takePicture(int imageId) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            fetchLocationAndTakePicture(imageId);
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        }
    }

    private void fetchLocationAndTakePicture(int imageId) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                fusedLocationProviderClient.getLastLocation()
                        .addOnSuccessListener(location -> {
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                latitudeTextView.setText("Latitude: " + latitude);
                                longitudeTextView.setText("Longitude: " + longitude);
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, imageId);
                            } else {
                                Log.e("FragmentInspection", "Location is null");
                                Toast.makeText(getContext(), "Unable to fetch location", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(e -> {
                            Log.e("FragmentInspection", "Failed to fetch location: " + e.getMessage());
                            Toast.makeText(getContext(), "Failed to fetch location: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                // Prompt the user to enable location services
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocationAndTakePicture(IMAGE_ID1);  // Optionally specify imageId here if needed
            } else {
                Toast.makeText(getContext(), "Location permission required", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                photobutton1.performClick();
            } else {
                Toast.makeText(getContext(), "Camera permission is required to take photos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && data != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if (photo != null) {
                switch (requestCode) {
                    case IMAGE_ID1:
                        imageView.setImageBitmap(photo);
                        imagesTaken[0] = true;
                        break;
                    case IMAGE_ID2:
                        imageView2.setImageBitmap(photo);
                        imagesTaken[1] = true;
                        break;
                    case IMAGE_ID3:
                        imageView3.setImageBitmap(photo);
                        imagesTaken[2] = true;
                        break;
                }
            }
        }
    }

    private void submitData() {
        if (imagesTaken[0] || imagesTaken[1] || imagesTaken[2]) {
            new Thread(() -> {
                AppDatabase database = AppDatabase.getDatabase(requireContext());
                if (imagesTaken[0]) {
                    saveImageToDatabase(imageView.getDrawable(), IMAGE_ID1);
                }
                if (imagesTaken[1]) {
                    saveImageToDatabase(imageView2.getDrawable(), IMAGE_ID2);
                }
                if (imagesTaken[2]) {
                    saveImageToDatabase(imageView3.getDrawable(), IMAGE_ID3);
                }
            }).start();
            Toast.makeText(getContext(), "Data submitted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "No images taken", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImageToDatabase(Drawable drawable, int imageId) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            byte[] imageData = ImageUtils.bitmapToByteArray(bitmap);
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImageData(imageData);
            imageEntity.setLatitude(latitude);
            imageEntity.setLongitude(longitude);
            Log.d("FragmentInspection", "Saving image with Latitude: " + latitude + " Longitude: " + longitude);

            AppDatabase database = AppDatabase.getDatabase(requireContext());
            database.imageDao().insertImage(imageEntity);
        } else {
            Log.e("FragmentInspection", "Drawable is not a BitmapDrawable");
        }
    }
}
