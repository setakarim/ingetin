package dev.karim.ingetin.Others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dev.karim.ingetin.Model.ProfilModel;
import dev.karim.ingetin.R;
import dev.karim.ingetin.RealmHelper;

/**
 * Created by Karim on 11/17/2017.
 */

public class ProfilFragment extends Fragment {

    TextView txt_nama, txt_email, txt_instansi, txt_edit;

    private RealmHelper realmHelper;

    private ArrayList<ProfilModel> profilModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);

        txt_nama = (TextView) rootView.findViewById(R.id.txt_nama);
        txt_email = (TextView) rootView.findViewById(R.id.txt_email);
        txt_instansi = (TextView) rootView.findViewById(R.id.txt_instansi);
        txt_edit = (TextView) rootView.findViewById(R.id.txt_edit);

        try {
            realmHelper = new RealmHelper(ProfilFragment.super.getContext());

            profilModels = realmHelper.findAllProfil();
            txt_nama.setText(profilModels.get(0).getNama());
            txt_email.setText(profilModels.get(0).getEmail());
            txt_instansi.setText(profilModels.get(0).getInstansi());
        } catch (Exception e) {
            e.printStackTrace();
        }

        txt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfilActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
