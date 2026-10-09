package com.itsoeh.mabrian.cacolearn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.navigation.NavigationView;

public class menu extends Fragment {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    // Home Cards
    private View homeCardContinue;
    private View btnHomeIr;

    public menu() {
        // Required empty public constructor
    }

    public static menu newInstance() {
        return new menu();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        configurarMenuDrawer(view);
        vincularVistasInicio(view);
        return view;
    }

    private void configurarMenuDrawer(View view) {
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        toolbar = view.findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setTitle("Hats'i, Mario");
        }

        // Configurar la Toolbar con la Actividad
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && toolbar != null && drawerLayout != null) {
            activity.setSupportActionBar(toolbar);

            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                    activity, drawerLayout, toolbar,
                    R.string.abrir_menu,
                    R.string.cerrar_menu
            );

            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }

        // Listener de las opciones del menú drawer XML (res/menu/menu_drawer.xml)
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();

                if (id == R.id.menu_inicio) {
                    if (toolbar != null) toolbar.setTitle("Inicio");
                } else if (id == R.id.menu_region) {
                    if (toolbar != null) toolbar.setTitle("Mi región");
                    if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
                    try {
                        NavHostFragment.findNavController(this).navigate(R.id.action_menu_to_miregion);
                    } catch (Exception e) {
                        mostrarMensaje("Navegando a Mi Región");
                    }
                    return true;
                } else if (id == R.id.menu_curso) {
                    if (toolbar != null) toolbar.setTitle("Curso");
                    mostrarMensaje("Navegando a Curso");
                } else if (id == R.id.menu_vocabulario) {
                    if (toolbar != null) toolbar.setTitle("Vocabulario");
                    mostrarMensaje("Navegando a Vocabulario");
                } else if (id == R.id.menu_juegos) {
                    if (toolbar != null) toolbar.setTitle("Juegos");
                    mostrarMensaje("Navegando a Juegos");
                }

                if (drawerLayout != null) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            });

            // Configurar botones del Header (res/layout/nav_header.xml)
            View headerView = navigationView.getHeaderView(0);
            if (headerView != null) {
                TextView btnAdminProfile = headerView.findViewById(R.id.btn_drawer_admin_profile);
                TextView btnChangePassword = headerView.findViewById(R.id.btn_drawer_change_password);

                if (btnAdminProfile != null) {
                    btnAdminProfile.setOnClickListener(v -> {
                        if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
                        try {
                            NavHostFragment.findNavController(this).navigate(R.id.action_menu_to_menuinfo);
                        } catch (Exception e) {
                            mostrarMensaje("Administrar mi perfil");
                        }
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

    private void vincularVistasInicio(View view) {
        homeCardContinue = view.findViewById(R.id.home_card_continue);
        btnHomeIr = view.findViewById(R.id.btn_home_ir);

        if (homeCardContinue != null) {
            homeCardContinue.setOnClickListener(v -> mostrarMensaje("Continuar Lección: Familia"));
        }
        if (btnHomeIr != null) {
            btnHomeIr.setOnClickListener(v -> mostrarMensaje("Continuar Lección: Familia"));
        }
    }

    private void mostrarMensaje(String mensaje) {
        if (getContext() != null) {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        }
    }
}
