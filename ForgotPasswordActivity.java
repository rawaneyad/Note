package com.example.note_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText emailEt;
    Button forgotBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.emailforgetEt);
        forgotBtn =findViewById(R.id.forgotBtn);
        forgotBtn.setOnClickListener(v->{
            doforgot(emailEt.getText().toString() );
        });
    }
    private void doforgot(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPasswordActivity.this, "Email sent.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPasswordActivity.this, ConfirmessageActivity.class));
                            finish();
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "Check the email is entered", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void goBack(View view) {
        Intent intent = new Intent(ForgotPasswordActivity.this , SigninActivity.class);
        startActivity(intent);
    }
}