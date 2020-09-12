package com.example.estatem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecordFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner customSpinner, customSpinner2, customSpinner3;
    ArrayList<CustomItem> customList, customList2, customList3;
    int width = 150; //set according to your use

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_record, container, false);

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

        return root;
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