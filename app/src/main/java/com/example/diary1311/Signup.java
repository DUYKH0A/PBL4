package com.example.diary1311;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtEmail, edtPassword,confirmPassword;
    private Button btnSignup,btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnSignup = findViewById(R.id.btn_login1);
        btnLogin = findViewById(R.id.btn_login2);
        confirmPassword = findViewById(R.id.confirm_password);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtEmail.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();
                String CFpass = confirmPassword.getText().toString().trim();

                if (user.isEmpty()){
                    edtEmail.setError("Email cannot be empty");
                }
                if (pass.isEmpty()){
                    edtPassword.setError("Password cannot be empty");
                }
                if (CFpass.isEmpty()){
                    edtPassword.setError("Password cannot be empty");
                }else
                    if(!pass.equals(CFpass))
                        {
                            confirmPassword.setError("Password Would Not be matched");
                        }
                        else
                            {
                                mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Signup.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Signup.this, LoginActivity2.class));
                                        } else {
                                            Toast.makeText(Signup.this, "Sign Up Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this, LoginActivity2.class));
            }
        });
    }

//    private void initListener(){
//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
//    private void onClickSignUp(){
//        String strEmail = edtEmail.getText().toString().trim();
//        String strPassword = edtPassword.getText().toString().trim();
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        mAuth.createUserWithEmailAndPassword(strEmail, strPassword)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Intent intent = new Intent(Signup.this,MainActivity.class);
//                            startActivity(intent);
//                            finishAffinity();
//                        }else{
//                            Toast.makeText(Signup.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//    private boolean validatePassword() {
//        String passwordInput = edtPassword.getText().toString().trim();
//        String confitmpasswordInput = confirmPassword.getText().toString().trim();
//        if (passwordInput.isEmpty()) {
//            MainActivityPasswordError.setText("Field can't be empty");
//            return false;
//        }  if (passwordInput.length()<5) {
//            MainActivityPasswordError.setText("Password must be at least 5 characters");
//            return false;
//        }
//        if (!passwordInput.equals(confitmpasswordInput)) {
//            MainActivityConfirmPassError.setText("Password Would Not be matched");
//            return false;
//        }else {
//            MainActivityConfirmPassError.setText("Password Matched");
//            return true;
//        }
//    }
}
