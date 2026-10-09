package com.itsoeh.mabrian.cacolearn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.navigation.NavigationView;

public class miregion extends Fragment {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView btnOpenDrawer;

    // Region section cards
    private View cardOtomi;
    private View cardOrigen;
    private View cardCultura;
    private View cardTradiciones;
    private View cardFloraFauna;
    private View cardGaleria;

    public miregion() {
        // Required empty public constructor
    }

    public static miregion newInstance() {
        return new miregion();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_miregion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configurarMenuDrawer(view);
        vincularSecciones(view);
    }

    private void configurarMenuDrawer(View view) {
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        btnOpenDrawer = view.findViewById(R.id.btn_open_drawer);

        if (btnOpenDrawer != null && drawerLayout != null) {
            btnOpenDrawer.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        }

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();

                if (id == R.id.menu_inicio) {
                    if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
                    try {
                        NavHostFragment.findNavController(this).navigate(R.id.action_miregion_to_menu);
                    } catch (Exception e) {
                        mostrarMensaje("Navegando a Inicio");
                    }
                } else if (id == R.id.menu_region) {
                    if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.menu_curso) {
                    if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
                    mostrarMensaje("Navegando a Curso");
                } else if (id == R.id.menu_vocabulario) {
                    if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
                    mostrarMensaje("Navegando a Vocabulario");
                } else if (id == R.id.menu_juegos) {
                    if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
                    mostrarMensaje("Navegando a Juegos");
                }

                return true;
            });

            // Header profile actions
            View headerView = navigationView.getHeaderView(0);
            if (headerView != null) {
                TextView btnAdminProfile = headerView.findViewById(R.id.btn_drawer_admin_profile);
                TextView btnChangePassword = headerView.findViewById(R.id.btn_drawer_change_password);

                if (btnAdminProfile != null) {
                    btnAdminProfile.setOnClickListener(v -> {
                        if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
                        mostrarMensaje("Administrar mi perfil");
                    });
                }

                if (btnChangePassword != null) {
                    btnChangePassword.setOnClickListener(v -> {
                        if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
                        mostrarMensaje("Cambiar Contraseña");
                    });
                }
            }
        }
    }

    private void vincularSecciones(View view) {
        cardOtomi = view.findViewById(R.id.card_region_otomi);
        cardOrigen = view.findViewById(R.id.card_region_origen);
        cardCultura = view.findViewById(R.id.card_region_cultura);
        cardTradiciones = view.findViewById(R.id.card_region_tradiciones);
        cardFloraFauna = view.findViewById(R.id.card_region_florafauna);
        cardGaleria = view.findViewById(R.id.card_region_galeria);

        if (cardOtomi != null) {
            cardOtomi.setOnClickListener(v -> navegarA(R.id.action_miregion_to_otomi));
        }
        if (cardOrigen != null) {
            cardOrigen.setOnClickListener(v -> navegarA(R.id.action_miregion_to_origen));
        }
        if (cardCultura != null) {
            cardCultura.setOnClickListener(v -> navegarA(R.id.action_miregion_to_cultura));
        }
        if (cardTradiciones != null) {
            cardTradiciones.setOnClickListener(v -> navegarA(R.id.action_miregion_to_tradiciones));
        }
        if (cardFloraFauna != null) {
            cardFloraFauna.setOnClickListener(v -> navegarA(R.id.action_miregion_to_florafauna));
        }
        if (cardGaleria != null) {
            cardGaleria.setOnClickListener(v -> navegarA(R.id.action_miregion_to_galeria));
        }
    }

    private void navegarA(int actionId) {
        try {
            NavHostFragment.findNavController(this).navigate(actionId);
        } catch (Exception e) {
            mostrarMensaje("Abriendo sección...");
        }
    }

    private void mostrarMensaje(String mensaje) {
        if (getContext() != null) {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        }
    }
}
