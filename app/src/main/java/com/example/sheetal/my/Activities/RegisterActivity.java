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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button btn;
    TextView textView;
    private ProgressDialog progressDialog;
    // private DatabaseReference databaseReference;
    //private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private TextInputLayout email;
    private TextInputLayout pass;
    private TextInputLayout confmpass;

    private DatabaseReference databaseReference;
    private FirebaseDatabase mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn = findViewById(R.id.signupbtn);
        textView = findViewById(R.id.textView10);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        //  mAuth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        databaseReference = mdatabase.getReference();

        email = findViewById(R.id.textInputLayoutemail);
        pass = findViewById(R.id.textinputlayoutpassword);
        confmpass = findViewById(R.id.textInputLayout4);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this,"Please wait..",Toast.LENGTH_SHORT).show();
                createNewAccoutnt();
            }
        });
    }

    private void createNewAccoutnt() {
        final String emailID = email.getEditText().getText().toString().trim();
        String password = pass.getEditText().getText().toString().trim();
        String confirmpassword = confmpass.getEditText().getText().toString().trim();

        if (!TextUtils.isEmpty(emailID) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmpassword)) {

            if (password.equals(confirmpassword)) {
                mAuth.createUserWithEmailAndPassword(emailID, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            String userid = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = databaseReference.child("Users").child(userid);
                            currentUserDb.child("Email").setValue(emailID);
                            progressDialog.setTitle("Success");
                            progressDialog.setMessage("We are creating your account. Please wait..");
                            progressDialog.show();
                            senttomain();
                        } else {
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(RegisterActivity.this, "Error : " + errorMessage, Toast.LENGTH_SHORT).show();


                        }
                    }
                });

            } else {
                Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void senttomain() {
        Intent intent = new Intent(RegisterActivity.this, MainHomeScreen.class);
        startActivity(intent);
        finish();
    }
}
