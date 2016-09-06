package com.lm.habitatapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends Activity {

    private EditText edtusuario, edtSenha;
    private Button btnEntrar, btnNovoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnNovoCadastro = (Button)findViewById(R.id.btnNovoCadastro);
        novoCadastro();
    }

    public void novoCadastro(){
        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastroActivity.class);
                startActivity(intent);
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
