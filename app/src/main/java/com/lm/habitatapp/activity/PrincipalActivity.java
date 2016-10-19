package com.lm.habitatapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lm.habitatapp.R;
import com.lm.habitatapp.fragments.FerramentasFragment;
import com.lm.habitatapp.fragments.OrcamentoFragment;
import com.lm.habitatapp.fragments.PeixesFragment;
import com.lm.habitatapp.fragments.PlantasFragment;
import com.lm.habitatapp.fragments.ProdutosFragment;
import com.lm.habitatapp.fragments.SobreFragment;
import com.lm.habitatapp.helper.SQLiteHandler;
import com.lm.habitatapp.helper.SessionManager;

import java.util.HashMap;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView txtNome;
    private TextView txtEmail;
    private SQLiteHandler db;
    private SessionManager session;
    String nome, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUsuario();
        }

        HashMap<String, String> usuarios = db.getUsuarioDetalhes();

        nome = usuarios.get("nome");
        email = usuarios.get("email");

        initNavigationDrawer();
        initNavigationDrawerHeader();
        initDrawerListener(savedInstanceState);
    }

    private void initNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    private void initNavigationDrawerHeader() {
        View header = navigationView.getHeaderView(0);
        txtNome = (TextView) header.findViewById(R.id.username);
        txtEmail = (TextView) header.findViewById(R.id.email);

        setupUserInformations();
    }

    private void setupUserInformations() {
        txtNome.setText(nome);
        txtEmail.setText(email);
    }

    private void initDrawerListener(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            MenuItem item = navigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isNavigationDrawerOpen()) {
            closeNavigationDrawer();
        }
        else {
            super.onBackPressed();
        }
    }

    protected boolean isNavigationDrawerOpen() {
        return drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavigationDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();
        selectDrawerItem(item);

        return true;
    }

    private void logoutUsuario() {
        session.setLogin(false);
        db.deletarUsuarios();

        Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.peixesFragment:
                fragment = new PeixesFragment();
                break;

            case R.id.plantasFragment:
                fragment = new PlantasFragment();
                break;

            case R.id.produtosFragment:
                fragment = new ProdutosFragment();
                break;

            case R.id.orcamentoFragment:
                fragment = new OrcamentoFragment();
                break;

            case R.id.ferramentasFragment:
                fragment = new FerramentasFragment();
                break;

            case R.id.sobreFragment:
                fragment = new SobreFragment();
                break;

            case R.id.sairFragment:
                logoutUsuario();
                break;

            default:
                break;
        }

        if(fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();

            setTitle(menuItem.getTitle());
        }


    }
}
