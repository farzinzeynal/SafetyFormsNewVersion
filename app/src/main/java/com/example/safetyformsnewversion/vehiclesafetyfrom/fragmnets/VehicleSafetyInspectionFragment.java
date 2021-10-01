package com.example.safetyformsnewversion.vehiclesafetyfrom.fragmnets;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.rigsafetyform.adapters.RecyclerViewImagesAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.mindorks.paracamera.Camera;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class VehicleSafetyInspectionFragment extends Fragment
{

    Button button_next_from_first,
            button_take_photo_vehicle;

    AppCompatAutoCompleteTextView autocomptext_inspect_date_vehicle,
            autocomptext_inspect_time_vehicle,
            autocomptext_lisence_expire_vehicle,
            autocomptext_manuf_date_vehicle;


    int mHour, mMinute;
    RecyclerView recyclerView_image;
    Camera camera;
    RecyclerViewImagesAdapter recyclerViewAdapter;
    List<byte[]> image_list;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener date2;
    DatePickerDialog.OnDateSetListener date3;

    int scaledWidth;
    int scaledHeight;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_safety_inspection, container, false);

        button_take_photo_vehicle = view.findViewById(R.id.button_take_photo_vehicle);
        button_next_from_first = view.findViewById(R.id.button_next_from_first_vehicle);



        recyclerView_image = view.findViewById(R.id.recycler_images_vehicle);

        autocomptext_inspect_date_vehicle = view.findViewById(R.id.autocomptext_inspect_date_vehicle);
        autocomptext_inspect_time_vehicle = view.findViewById(R.id.autocomptext_inspect_time_vehicle);
        autocomptext_lisence_expire_vehicle = view.findViewById(R.id.autocomptext_lisence_expire_vehicle);
        autocomptext_manuf_date_vehicle = view.findViewById(R.id.autocomptext_manuf_date_vehicle);

        image_list = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_image.setLayoutManager(layoutManager);
        initDatePickerDialogs();

        calendar = Calendar.getInstance();


        button_next_from_first.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v)
            {

            }
        });

        button_take_photo_vehicle.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                take_photo();
            }
        });

        autocomptext_inspect_date_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        autocomptext_manuf_date_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date2, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        autocomptext_lisence_expire_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date3, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        autocomptext_inspect_time_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c =Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog =  new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        autocomptext_inspect_time_vehicle.setText(hourOfDay+" : "+minute);
                        autocomptext_inspect_time_vehicle.setTextColor(Color.GREEN);
                    }
                },mHour,mMinute,false);
                timePickerDialog.show();
            }
        });

        return  view;
    }



    private void initDatePickerDialogs()
    {

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }

        };


        date3 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel3();
            }

        };


    }


    private void updateLabel()
    {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        autocomptext_inspect_date_vehicle.setText(sdf.format(calendar.getTime()));
        autocomptext_inspect_date_vehicle.setTextColor(Color.GREEN);
    }


    private void updateLabel2()
    {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        autocomptext_manuf_date_vehicle.setText(sdf.format(calendar.getTime()));
        autocomptext_manuf_date_vehicle.setTextColor(Color.GREEN);
    }


    private void updateLabel3()
    {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        autocomptext_lisence_expire_vehicle.setText(sdf.format(calendar.getTime()));
        autocomptext_lisence_expire_vehicle.setTextColor(Color.GREEN);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void take_photo()
    {
        camera = new Camera.Builder()
                .setDirectory("pics")
                .setName("vehicle_" + System.currentTimeMillis())
                .setImageFormat(Camera.IMAGE_JPEG)
                .setCompression(75)
                .setImageHeight(1000)
                .build(this);
        try {
            camera.takePicture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (requestCode == Camera.REQUEST_TAKE_PHOTO) {
            Bitmap bitmap = camera.getCameraBitmap();
            if (bitmap != null) {
                Bitmap reszized = resize_image(bitmap);
                byte[] image = to_byte_array(reszized);
                if (image_list.size()<11)
                {
                    image_list.add(image);

                    recyclerViewAdapter = new RecyclerViewImagesAdapter(image_list,getActivity());
                    recyclerView_image.setAdapter(recyclerViewAdapter);
                }
                else
                {
                    Toast.makeText(getActivity(), "Limiti doldurmusunuz", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this.getActivity(), "Picture not taken!", Toast.LENGTH_SHORT).show();
            }
        }

    }



    private byte[] to_byte_array(Bitmap reszized)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        reszized.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        reszized.recycle();

        return byteArray;

    }



    private Bitmap resize_image(Bitmap bitmap)
    {
        scaledWidth = (bitmap.getWidth()*15)/100;
        scaledHeight = (bitmap.getHeight()*15)/100;

        Bitmap resized = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);

        return resized;
    }
}
