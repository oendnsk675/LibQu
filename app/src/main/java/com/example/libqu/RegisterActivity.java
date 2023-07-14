package com.example.libqu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.libqu.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnRegister.setOnClickListener(aVoid -> {
            String name = binding.name.toString().trim();
            String email = binding.email.toString().trim();
            String password = binding.password.toString().trim();

            if (TextUtils.isEmpty(email)) {
                binding.email.setError("Email is required");
                return;
            }else if (TextUtils.isEmpty(password)) {
                binding.password.setError("Password is required");
                return;
            }else if (TextUtils.isEmpty(name)) {
                binding.password.setError("Name is required");
                return;
            }
            // do action
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(task -> {
                        currentUser = firebaseAuth.getCurrentUser();
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Firebase", e.getMessage());
                        }
                    });
        });
    }
}