package com.example.diary1311;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DangKyTaiKhoan_Activity extends AppCompatActivity {

    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private EditText edtHoTen;
    private EditText edtCMND,edtBSX;
    private Button btnDangKy;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_tai_khoan);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        edtHoTen = findViewById(R.id.edt_HoTen);
        edtCMND = findViewById(R.id.edt_CCCD);
        edtBSX = findViewById(R.id.BienSoXe);
        btnDangKy = findViewById(R.id.btn_DangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String HoTen = edtHoTen.getText().toString();
                String CMND = edtCMND.getText().toString();
                String BSX = edtBSX.getText().toString();
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("Hoten", HoTen);
                user.put("CCCD", CMND);
                user.put("Biensoxe", BSX);

                FirebaseDatabase.getInstance().getReference().child("bienso").push().setValue(BSX)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DangKyTaiKhoan_Activity.this, "DANG KY THANH CONG", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DangKyTaiKhoan_Activity.this, LoginActivity2.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(DangKyTaiKhoan_Activity.this, "DANG KY THAT BAI " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}