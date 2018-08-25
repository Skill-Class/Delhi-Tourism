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

import com.example.sheetal.my.Model.Users;
import com.example.sheetal.my.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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
    private TextInputLayout username;
    private TextInputLayout confmpass;
private String currentusername= null;
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
        username = findViewById(R.id.textInputLayoutusername);

        currentusername=username.getEditText().getText().toString();

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
        final String password = pass.getEditText().getText().toString().trim();
        final String confirmpassword = confmpass.getEditText().getText().toString().trim();
        final String userName = username.getEditText().getText().toString();


        if (!TextUtils.isEmpty(emailID) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmpassword)) {

            if (password.equals(confirmpassword)) {
                mAuth.createUserWithEmailAndPassword(emailID, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            String userid = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = databaseReference.child("Users").push();
                            String pushId = currentUserDb.getKey();

                            Users users = new Users("userName","userEmailId","userProfilePic");

                            Map<String, String> DataToSave = new HashMap<>();
                            DataToSave.put("userName", userName);
                            DataToSave.put("userEmailId", emailID);
                            DataToSave.put("userProfilePic", password);
                           // DataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));

                            currentUserDb.setValue(DataToSave);

                            //currentUserDb.child("Email").setValue(emailID);
                            //currentUserDb.child("Username").setValue(username);

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
    //    Bundle bundle = new Bundle();
      //  bundle.putString("UserName",currentusername);
       // intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
