package com.itsoeh.mabrian.cacolearn;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jspecify.annotations.NonNull;

public class login extends Fragment {
    private NavController nav;
    private TextView crdRegistrar;
    private CardView crdEntrar;

    public login() {
        // Required empty public constructor
    }

    public static login newInstance() {
        return new login();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vincular(view);
        eventos(view);
    }

    private void eventos(@NonNull View view) {
        if (crdRegistrar != null) {
            crdRegistrar.setOnClickListener(v -> clicRegistrar(view));
        }
        if (crdEntrar != null) {
            crdEntrar.setOnClickListener(v -> clicEntrar(view));
        }
    }

    private void clicRegistrar(@NonNull View view) {
        reproducirSonido();
        if (nav != null) {
            nav.navigate(R.id.action_login_to_autoregistro);
        }
    }

    private void clicEntrar(@NonNull View view) {
        reproducirSonido();
        if (nav != null) {
            nav.navigate(R.id.action_login_to_menu);
        }
    }

    private void reproducirSonido() {
        try {
            MediaPlayer x = MediaPlayer.create(getContext(), R.raw.inicio);
            if (x != null) {
                x.start();
            }
        } catch (Exception ignored) {}
    }

    private void vincular(@NonNull View view) {
        crdRegistrar = view.findViewById(R.id.login_crdregistrar);
        crdEntrar = view.findViewById(R.id.login_crdentrar);
        try {
            nav = NavHostFragment.findNavController(this);
        } catch (Exception ignored) {}
    }
}
