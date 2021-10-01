package com.example.safetyformsnewversion.rigsafetyform.fragmnets;

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


public class RigSafetyInspectionFragment extends Fragment
{

    Button button_next_from_first,
           button_take_photo;

    AppCompatAutoCompleteTextView autocomptext_inspect_date,
                                  autocomptext_inspect_time,
                                  autocomptext_lisence_expire,
                                  autocomptext_manuf_date,
                                  autocomptext_plateNumber,
                                  autocomptext_device_id,
                                  autocomptext_drillman_name;

    TextInputEditText edittxt_rig_model,
                      edittxt_rig_type,
                      edittxt_company_name,
                      edittxt_work_hour;

    DatePickerDialog.OnDateSetListener date,
                                       date2,
                                       date3;

    int scaledWidth,scaledHeight;
    int mHour, mMinute;
    RecyclerView recyclerView_image;
    Camera camera;
    RecyclerViewImagesAdapter recyclerViewimagesAdapter;
    ArrayList<byte[]> image_list;
    Calendar calendar;
    ArrayAdapter<String> adapter;


    String device_id=null,
           plate_number=null,
           drilman_name=null,
           inpect_date=null,
           inspect_time=null,
           lisence_expire_date=null,
           maunfacture_date=null;



    //greendao
    Long row_id;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rig_safety_inspection, container, false);

        init(view);
        initDatePickerDialogs();
        fillAllSpinners();

       /* MainActivity.daoSession.getRigPhotosDao().deleteAll();
        MainActivity.daoSession.getRSFIDao().deleteAll();*/

        recyclerView_image.setLayoutManager(new LinearLayoutManager(getActivity(),
                                                LinearLayoutManager.HORIZONTAL, false));



        inpect_date = autocomptext_inspect_date.getText().toString();
        inspect_time = autocomptext_inspect_time.getText().toString();
        lisence_expire_date = autocomptext_lisence_expire.getText().toString();
        maunfacture_date = autocomptext_manuf_date.getText().toString();


        autocomptext_plateNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            { plate_number=autocomptext_plateNumber.getAdapter().getItem(position).toString(); }});

        autocomptext_drillman_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            { drilman_name=autocomptext_drillman_name.getAdapter().getItem(position).toString(); }});


        autocomptext_device_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            { device_id=autocomptext_device_id.getAdapter().getItem(position).toString(); }});





        button_next_from_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(autocomptext_inspect_date.getText().toString().length()==0)
                {autocomptext_inspect_date.setError("zehmet olmasa daxil edin");return;}

                if(autocomptext_inspect_time.getText().toString().length()==0)
                {autocomptext_inspect_time.setError("zehmet olmasa daxil edin");return;}

                if(autocomptext_manuf_date.getText().toString().length()==0)
                {autocomptext_manuf_date.setError("zehmet olmasa daxil edin");return;}

                if(autocomptext_lisence_expire.getText().toString().length()==0)
                {autocomptext_lisence_expire.setError("zehmet olmasa daxil edin");return;}

                checkValid();
                if(checkValid()) {
                    addToSRFİTable(); }
                else { Toast.makeText(getActivity(), "Bütün paramertləri daxil edin!", Toast.LENGTH_SHORT).show(); }
            }
        });



        button_take_photo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                take_photo();
            }
        });




        autocomptext_inspect_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();}});

        autocomptext_manuf_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date2, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show(); }});

        autocomptext_lisence_expire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date3, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show(); }});


        autocomptext_inspect_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c =Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog =  new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        autocomptext_inspect_time.setText(hourOfDay+" : "+minute);
                        autocomptext_inspect_time.setTextColor(Color.GREEN);
                    }},mHour,mMinute,false);timePickerDialog.show(); }});
        return  view;
    }



    private void fillAllSpinners()
    {
        // todo: plate numbers
        List<String> mList = Arrays.asList(getResources().getStringArray(R.array.plate_number));
        adapter =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line,mList);
        autocomptext_plateNumber.setAdapter(adapter);
        autocomptext_plateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { autocomptext_plateNumber.showDropDown(); }});

        // todo: drilman names
        List<String> mList2 = Arrays.asList(getResources().getStringArray(R.array.drillmans));
        adapter =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line,mList2);
        autocomptext_drillman_name.setAdapter(adapter);
        autocomptext_drillman_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { autocomptext_drillman_name.showDropDown(); }});

        // todo: device Id's
        List<String> mList3 = Arrays.asList(getResources().getStringArray(R.array.id_numbers));
        adapter =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line,mList3);
        autocomptext_device_id.setAdapter(adapter);
        autocomptext_device_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { autocomptext_device_id.showDropDown(); }});


    }

    private void addToSRFİTable()
    {
        Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();

    }

    private boolean checkValid()
    {


        return true;
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


        date2 = new DatePickerDialog.OnDateSetListener()
        {

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
        autocomptext_inspect_date.setText(sdf.format(calendar.getTime()));
        autocomptext_inspect_date.setTextColor(Color.GREEN);
    }


    private void updateLabel2()
    {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        autocomptext_manuf_date.setText(sdf.format(calendar.getTime()));
        autocomptext_manuf_date.setTextColor(Color.GREEN);
    }


    private void updateLabel3()
    {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        autocomptext_lisence_expire.setText(sdf.format(calendar.getTime()));
        autocomptext_lisence_expire.setTextColor(Color.GREEN);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void take_photo()
    {
        camera = new Camera.Builder()
                .setDirectory("pics")
                .setName("rig_" + System.currentTimeMillis())
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

                    recyclerViewimagesAdapter = new RecyclerViewImagesAdapter(image_list,getActivity());
                    recyclerView_image.setAdapter(recyclerViewimagesAdapter);
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


    private void init(View view)
    {
        button_take_photo = view.findViewById(R.id.button_take_photo);
        button_next_from_first = view.findViewById(R.id.button_next_from_first);

        autocomptext_plateNumber = view.findViewById(R.id.autocomptext_plateNumber);
        autocomptext_device_id = view.findViewById(R.id.autocomptext_device_id);
        autocomptext_drillman_name = view.findViewById(R.id.autocomptext_drillman_name);
        autocomptext_inspect_date = view.findViewById(R.id.autocomptext_inspect_date);
        autocomptext_inspect_time = view.findViewById(R.id.autocomptext_inspect_time);
        autocomptext_manuf_date = view.findViewById(R.id.autocomptext_manuf_date);
        autocomptext_lisence_expire = view.findViewById(R.id.autocomptext_lisence_expire);

        edittxt_rig_model= view.findViewById(R.id.edittxt_rig_model);
        edittxt_rig_type= view.findViewById(R.id.edittxt_rig_type);
        edittxt_company_name= view.findViewById(R.id.edittxt_company_name);
        edittxt_work_hour= view.findViewById(R.id.edittxt_work_hour);

        image_list = new ArrayList<>();
        calendar = Calendar.getInstance();
        recyclerView_image = view.findViewById(R.id.recycler_images_rig);
    }


}
