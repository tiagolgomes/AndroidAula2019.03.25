package com.example.opet.aulaandroid20190325;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {

    private EditText editLogin;
    private EditText editSenha;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            Toast.makeText(this, "Logado!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Usuário Não Logado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void signIn(View view) {
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(login, senha).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this, "Logado com Sucesso!", Toast.LENGTH_SHORT).show();
                callDash();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Erro ao Logar!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void signUp(View view) {
        callSignUp();
    }

    public void callDash(){
        Intent intent = new Intent(MainActivity.this, DashActivity.class);
        startActivity(intent);
    }

    public void callSignUp(){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
