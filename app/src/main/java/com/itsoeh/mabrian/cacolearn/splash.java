package com.itsoeh.mabrian.cacolearn;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class splash extends Fragment {

    public splash() {
        // Required empty public constructor
    }

    public static splash newInstance() {
        return new splash();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Referencias a las vistas
        View cardSplash = view.findViewById(R.id.card_splash);
        View cardLogo = view.findViewById(R.id.card_logo);
        View txtTitle = view.findViewById(R.id.txt_title);
        View txtSubtitle = view.findViewById(R.id.txt_subtitle);
        View progressBar = view.findViewById(R.id.progress_bar);
        View txtVersion = view.findViewById(R.id.txt_version);

        // Animación de entrada fluida para la tarjeta principal
        if (cardSplash != null) {
            cardSplash.setAlpha(0f);
            cardSplash.setScaleX(0.7f);
            cardSplash.setScaleY(0.7f);
            cardSplash.setTranslationY(60f);

            cardSplash.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .translationY(0f)
                    .setDuration(700)
                    .setInterpolator(new OvershootInterpolator(1.1f))
                    .start();
        }

        // Animación con rebote sutil para el contenedor del logo
        if (cardLogo != null) {
            cardLogo.setAlpha(0f);
            cardLogo.setScaleX(0.5f);
            cardLogo.setScaleY(0.5f);

            cardLogo.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setStartDelay(200)
                    .setDuration(600)
                    .setInterpolator(new OvershootInterpolator(1.3f))
                    .start();
        }

        // Animación de aparición suave para los textos de título y subtítulo
        if (txtTitle != null && txtSubtitle != null) {
            txtTitle.setAlpha(0f);
            txtTitle.setTranslationY(30f);
            txtSubtitle.setAlpha(0f);
            txtSubtitle.setTranslationY(30f);

            txtTitle.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(350)
                    .setDuration(500)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();

            txtSubtitle.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setStartDelay(450)
                    .setDuration(500)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();
        }

        // Aparecer suavemente el indicador de carga
        if (progressBar != null) {
            progressBar.setAlpha(0f);
            progressBar.animate()
                    .alpha(1f)
                    .setStartDelay(600)
                    .setDuration(400)
                    .start();
        }

        if (txtVersion != null) {
            txtVersion.setAlpha(0f);
            txtVersion.animate()
                    .alpha(0.8f)
                    .setStartDelay(700)
                    .setDuration(500)
                    .start();
        }

        // Retraso de 3 segundos antes de pasar a la pantalla de Login
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAdded() && getView() != null) {
                    Navigation.findNavController(getView()).navigate(R.id.action_splash_to_login);
                }
            }
        }, 3000);
    }
}
