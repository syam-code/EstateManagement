package com.example.estatem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetorHafalanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetorHafalanFragment extends Fragment {

    private Button btnGabung;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SetorHafalanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetorHafalanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetorHafalanFragment newInstance(String param1, String param2) {
        SetorHafalanFragment fragment = new SetorHafalanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_setor_hafalan, container, false);

        btnGabung = root.findViewById(R.id.btn_gabung);

        btnGabung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setIcon(R.drawable.houses_rafiki_1_);
                alert.setTitle("Konfirmasi");
                alert.setMessage("Anda yakin untuk bergabung dengan program Hafidz Quran ?");
                alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Anda Berhasil Gabung!", Toast.LENGTH_SHORT).show();
                        PopupGabungFragment popupGabungFragment = new PopupGabungFragment();
                        popupGabungFragment.show(getFragmentManager(),popupGabungFragment.getClass().getSimpleName());

                        FragmentManager fragmentManager;
                        fragmentManager = getFragmentManager();

                        RecordFragment recordFragment = new RecordFragment();
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, recordFragment).commit();

                    }
                });
                alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();

            }
        });

        return root;
    }
}