package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastrar extends AppCompatActivity {

    EditText cadastro_login;
    EditText cadastro_senha;
    EditText cadastro_confirmarsenha;
    Button botao_cadastrar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);

        // Remover a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();
        cadastro_login = findViewById(R.id.cadastro_login);
        cadastro_senha = findViewById(R.id.cadastro_senha);
        cadastro_confirmarsenha = findViewById(R.id.cadastro_confirmarsenha);
        botao_cadastrar = findViewById(R.id.botao_cadastrar);

        botao_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = cadastro_login.getText().toString().trim();
                String password = cadastro_senha.getText().toString().trim();
                String confirmPassword = cadastro_confirmarsenha.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Cadastrar.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Cadastrar.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(Cadastrar.this, "Enter confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(Cadastrar.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Cadastrar.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Cadastrar.this, "Account created", Toast.LENGTH_SHORT).show();
                                    // Redirecionar para a tela de login
                                    startActivity(new Intent(Cadastrar.this, MainActivity.class));
                                    finish(); // Encerrar a atividade de cadastro
                                } else {
                                    Toast.makeText(Cadastrar.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
