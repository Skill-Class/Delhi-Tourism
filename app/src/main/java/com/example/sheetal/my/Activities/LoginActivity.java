package com.example.sheetal.my.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheetal.my.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private Button btn;
    private TextView textView;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private TextInputLayout emailtext, passwordtext;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        //   databaseReferenceroot = firebaseDatabase.getReference();

        btn = findViewById(R.id.signinbtn);
        textView = findViewById(R.id.textView10);
        emailtext = findViewById(R.id.textInputLayoutemail);
        passwordtext = findViewById(R.id.textinputlayoutpassword);


        progressDialog = new ProgressDialog(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Please wait..", Toast.LENGTH_SHORT).show();
                String email = emailtext.getEditText().getText().toString().trim();
                String password = passwordtext.getEditText().getText().toString().trim();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                progressDialog.setTitle("Sign In");
                                progressDialog.setMessage("Signing into your Account..");
                                progressDialog.show();

                                Intent mainIntent = new Intent(LoginActivity.this, MainHomeScreen.class);
                                startActivity(mainIntent);
                                finish();

                            } else {
                                String erroMessage = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error : " + erroMessage, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });

    }
}