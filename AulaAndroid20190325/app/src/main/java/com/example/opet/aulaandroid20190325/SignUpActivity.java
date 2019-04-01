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

public class SignUpActivity extends Activity {

    private EditText editLogin;
    private EditText editSenha;
    private EditText editSenhaConf;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);
        editSenhaConf = findViewById(R.id.editSenhaConf);

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

    public void signUp(View view) {
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();
        String senhaConf = editSenhaConf.getText().toString();

        if (senha == senhaConf){
            if (senha.length() <= 8){
                firebaseAuth.createUserWithEmailAndPassword(login,senha)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                callMain();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
            else{
                Toast.makeText(this, "A senha não pode ser menor que 8 digitos!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "A senha não confere com a confirmação!", Toast.LENGTH_SHORT).show();
        }
    }

    public void callMain(){
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
