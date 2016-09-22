package com.lm.habitatapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lm.habitatapp.R;
import com.lm.habitatapp.helper.SQLiteHandler;
import com.lm.habitatapp.helper.SessionManager;

import java.util.HashMap;

public class PrincipalActivity extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtEmail;
    private Button btnSair;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtNome = (TextView) findViewById(R.id.txtNome);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        btnSair = (Button) findViewById(R.id.btnLogout);

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUsuario();
        }

        HashMap<String, String> usuarios = db.getUsuarioDetalhes();

        String nome = usuarios.get("nome");
        String email = usuarios.get("email");

        txtNome.setText(nome);
        txtEmail.setText(email);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUsuario();
            }
        });
    }

    private void logoutUsuario() {
        session.setLogin(false);
        db.deletarUsuarios();

        Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
