package com.itsoeh.mabrian.cacolearn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class tradiciones extends Fragment {

    private View cardFiestas;
    private View cardGastronomia;
    private View cardArte;

    public tradiciones() {
        // Required empty public constructor
    }

    public static tradiciones newInstance() {
        return new tradiciones();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tradiciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView btnBack = view.findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> NavHostFragment.findNavController(this).navigateUp());
        }

        vincularCategorias(view);
    }

    private void vincularCategorias(View view) {
        cardFiestas = view.findViewById(R.id.card_trad_fiestas);
        cardGastronomia = view.findViewById(R.id.card_trad_gastronomia);
        cardArte = view.findViewById(R.id.card_trad_arte);

        if (cardFiestas != null) {
            cardFiestas.setOnClickListener(v -> navegarA(R.id.action_tradiciones_to_trad_fiestas));
        }
        if (cardGastronomia != null) {
            cardGastronomia.setOnClickListener(v -> navegarA(R.id.action_tradiciones_to_trad_gastronomia));
        }
        if (cardArte != null) {
            cardArte.setOnClickListener(v -> navegarA(R.id.action_tradiciones_to_trad_arte));
        }
    }

    private void navegarA(int actionId) {
        try {
            NavHostFragment.findNavController(this).navigate(actionId);
        } catch (Exception e) {
            mostrarMensaje("Abriendo tradición...");
        }
    }

    private void mostrarMensaje(String mensaje) {
        if (getContext() != null) {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        }
    }
}
