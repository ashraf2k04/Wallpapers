package com.example.javaxml.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javaxml.R;
import com.example.javaxml.databinding.ActivityLogInBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity {

    TextView txt1, txt2, register;
    EditText email, password;
    Button login ;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        var binding = ActivityLogInBinding.inflate(getLayoutInflater());
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
        login = binding.login;
        register = binding.register;
        password = binding.password;
        email = binding.email;

        fAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(
                view -> {
                    startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                    finish();
                }
        );

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String mail = email.getText().toString().trim();
                        String pass = password.getText().toString().trim();

                        if (TextUtils.isEmpty(mail)) {
                            email.setError("Please fill all the fields");
                            return;
                        }
                        if (pass.length() < 8 || TextUtils.isEmpty(pass)) {
                            password.setError("Please enter at least 8 characters");
                        }

                        fAuth.signInWithEmailAndPassword(mail, pass)
                                .addOnCompleteListener(
                                        new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(
                                                                    LogInActivity.this,
                                                                    "Login Successful",
                                                                    Toast.LENGTH_SHORT)
                                                            .show();
                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                    finish();
                                                } else {
                                                    Toast.makeText(
                                                                    LogInActivity.this,
                                                                     "Error : " + Objects.requireNonNull(task.getException()).getMessage(),
                                                                    Toast.LENGTH_SHORT)
                                                            .show();
                                                }
                                            }
                                        }
                                );
                    }
                }
        );

    }
}