package com.example.javaxml.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javaxml.R;
import com.example.javaxml.databinding.ActivitySignUpBinding;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    TextView txt1, txt2, login;
    EditText username, email, password, phone;
    Button signUp;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        var binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        var rootView = binding.getRoot();

        EdgeToEdge.enable(this);
        setContentView(rootView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt1 = binding.textView;
        txt2 = binding.textView2;
        login = binding.account;
        signUp = binding.register;
        password = binding.password;
        phone = binding.phone;
        username = binding.username;
        email = binding.email;

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        login.setOnClickListener(
                view -> {
                    startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                    finish();
                }
        );

        signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String mail = email.getText().toString().trim();
                        String pass = password.getText().toString().trim();
                        String user = username.getText().toString().trim();
                        String ph = phone.getText().toString().trim();

                        if (TextUtils.isEmpty(mail)) {
                            email.setError("Please fill all the fields");
                            return;
                        }
                        if (TextUtils.isEmpty(ph)) {
                            phone.setError("Please fill all the fields");
                            return;
                        }
                        if (TextUtils.isEmpty(user)) {
                            username.setError("Please fill all the fields");
                            return;
                        }
                        if (pass.length() < 8) {
                            password.setError("Please enter atlest 8 characters");
                        }

                        fAuth.createUserWithEmailAndPassword(mail, pass)
                                .addOnCompleteListener(
                                        task -> {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(
                                                                SignUpActivity.this,
                                                                "User Created",
                                                                Toast.LENGTH_SHORT)
                                                        .show();

                                                userId = fAuth.getCurrentUser().getUid();

                                                DocumentReference df = fStore.collection("users").document(userId);

                                                Map<String, Object> userInfo = new HashMap<>();
                                                userInfo.put("username", user);
                                                userInfo.put("email", mail);
                                                userInfo.put("phone", ph);

                                                df.set(userInfo).addOnSuccessListener(
                                                        new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                Log.d("user", "User profile is created for" + userId);
                                                            }
                                                        });



                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(
                                                                SignUpActivity.this,
                                                                "Error : " + task.getException().getMessage(),
                                                                Toast.LENGTH_SHORT)
                                                        .show();
                                            }
                                        }
                                );
                    }
                }
        );



    }
}