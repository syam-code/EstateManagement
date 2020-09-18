package com.example.estatem;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.SystemClock;
import android.service.autofill.OnClickAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecordFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner customSpinner, customSpinner2, customSpinner3;
    ArrayList<CustomItem> customList, customList2, customList3;
    int width = 150; //set according to your use
    ImageView imgRecord, imgStop, imgRepeat, imgPlay;
    Button btnKirim;
    TextView txtKetRec;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    Chronometer timerRecord;

    final int REQUEST_PERMISSION_CODE = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_record, container, false);

        if (checkPermissionFromDevice())
            requestPermission();

        imgRecord = root.findViewById(R.id.img_record);
        imgStop = root.findViewById(R.id.img_stop);
        imgRepeat = root.findViewById(R.id.img_repeat);
        imgPlay = root.findViewById(R.id.img_play);
        btnKirim = root.findViewById(R.id.btn_kirim);
        txtKetRec = root.findViewById(R.id.txt_ket_rec);
        timerRecord = root.findViewById(R.id.timer_record);

        customSpinner = root.findViewById(R.id.customIconSpinner);
        customList = getCustomList();
        CustomAdapter adapter = new CustomAdapter(getActivity(), customList);
        if (customSpinner != null) {
            customSpinner.setAdapter(adapter);
            customSpinner.setOnItemSelectedListener(this);
        }

        customSpinner2 = root.findViewById(R.id.customIconSpinner2);
        customList2 = getCustomList2();
        CustomAdapter adapter2 = new CustomAdapter(getActivity(), customList2);
        if (customSpinner2 != null) {
            customSpinner2.setAdapter(adapter2);
            customSpinner2.setOnItemSelectedListener(this);
        }

        customSpinner3 = root.findViewById(R.id.customIconSpinner3);
        customList3 = getCustomList3();
        CustomAdapter adapter3 = new CustomAdapter(getActivity(), customList3);
        if (customSpinner3 != null) {
            customSpinner3.setAdapter(adapter3);
            customSpinner3.setOnItemSelectedListener(this);
        }

        btnKirim.setVisibility(View.GONE);

        imgRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermissionFromDevice()){
                    imgStop.setVisibility(View.VISIBLE);
                    imgRecord.setVisibility(View.GONE);
                    btnKirim.setVisibility(View.VISIBLE);
                    txtKetRec.setVisibility(View.GONE);

                    timerRecord.setBase(SystemClock.elapsedRealtime());
                    timerRecord.start();

                    pathSave = Environment.getExternalStorageDirectory()
                            .getAbsolutePath()+"/"
                            + UUID.randomUUID().toString()+"_audio_record.3gp";
                    setupMediaRecorder();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();

                    } catch (IOException e){
                        e.printStackTrace();
                    }

                    Toast.makeText(getActivity(), "Recording...", Toast.LENGTH_LONG).show();

                } else {
                    requestPermission();
                }

            }
        });

        imgStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgStop.setVisibility(View.GONE);
                imgRepeat.setVisibility(View.VISIBLE);
                imgPlay.setVisibility(View.VISIBLE);

                mediaRecorder.stop();
                timerRecord.stop();
            }
        });

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(pathSave);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(getActivity(), "Playing...", Toast.LENGTH_LONG).show();
            }
        });

        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgStop.setVisibility(View.GONE);
                imgRecord.setVisibility(View.VISIBLE);
                imgPlay.setVisibility(View.GONE);
                imgRepeat.setVisibility(View.GONE);
                btnKirim.setVisibility(View.GONE);
                txtKetRec.setVisibility(View.VISIBLE);

                timerRecord.setBase(SystemClock.elapsedRealtime());

                if (mediaPlayer != null){
                    mediaPlayer.stop();
                    setupMediaRecorder();
                }
            }
        });

        return root;
    }

    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:{
                {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(getActivity(),"Permission Granted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(),"Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO);
        return  write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;
    }

    private ArrayList<CustomItem> getCustomList() {
        customList = new ArrayList<>();
        customList.add(new CustomItem("Pilih Juz",0 ));
        customList.add(new CustomItem("Juz 1",0 ));
        customList.add(new CustomItem("Juz 2", 0));
        customList.add(new CustomItem("Juz 3", 0));
        customList.add(new CustomItem("Juz 4", 0));
        customList.add(new CustomItem("Juz 5", 0));
        return customList;
    }

    private ArrayList<CustomItem> getCustomList2() {
        customList2 = new ArrayList<>();
        customList2.add(new CustomItem("Pilih Surat",0 ));
        customList2.add(new CustomItem("Surat ..",0 ));
        customList2.add(new CustomItem("Surat ..", 0));
        customList2.add(new CustomItem("Surat ..", 0));
        customList2.add(new CustomItem("Surat ..", 0));
        customList2.add(new CustomItem("Surat ..", 0));
        return customList2;
    }

    private ArrayList<CustomItem> getCustomList3() {
        customList3 = new ArrayList<>();
        customList3.add(new CustomItem("Pilih Ayat",0 ));
        customList3.add(new CustomItem("Ayat ..",0 ));
        customList3.add(new CustomItem("Ayat ..", 0));
        customList3.add(new CustomItem("Ayat ..", 0));
        customList3.add(new CustomItem("Ayat ..", 0));
        customList3.add(new CustomItem("Ayat ..", 0));
        return customList3;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        try {
            LinearLayout linearLayout = view.findViewById(R.id.customSpinnerItemLayout);
            width = linearLayout.getWidth();
        } catch (Exception e) {
        }
        customSpinner.setDropDownWidth(width);
        CustomItem item = (CustomItem) adapterView.getSelectedItem();
//        Toast.makeText(getActivity(), item.getSpinnerItemName(), Toast.LENGTH_SHORT).show();

        customSpinner2.setDropDownWidth(width);
        CustomItem item2 = (CustomItem) adapterView.getSelectedItem();
//        Toast.makeText(getActivity(), item2.getSpinnerItemName(), Toast.LENGTH_SHORT).show();

        customSpinner3.setDropDownWidth(width);
        CustomItem item3 = (CustomItem) adapterView.getSelectedItem();
//        Toast.makeText(getActivity(), item3.getSpinnerItemName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}