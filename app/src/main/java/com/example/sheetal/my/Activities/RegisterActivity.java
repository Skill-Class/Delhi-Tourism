package com.example.sheetal.my.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheetal.my.Model.Users;
import com.example.sheetal.my.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
    private StorageReference mFirebaseStorage;
    private TextView testLogin;
    private ImageView profilePic;
    private Uri resultUri = null;
    private final static int GALLERY_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn = findViewById(R.id.signupbtn);
        textView = findViewById(R.id.textView10);
        profilePic= findViewById(R.id.profilepic_imageView);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

    //    mdatabase = FirebaseDatabase.getInstance();
  //      databaseReference = mdatabase.getReference().child("MUsers");


//        mFirebaseStorage = FirebaseStorage.getInstance().getReference().child("MBlog_Profile_Pics");

        testLogin = findViewById(R.id.testlogin);
        testLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainHomeScreen.class);
                startActivity(intent);
            }
        });


        // getting image from gallery start
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);

            }
        });
        // getting image from gallery ends

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

    // accout with image start here.
    /*
    private void createNewAccoutnt() {
        final String name = username.getEditText().getText().toString().trim();
       // final String lname = lastName.getText().toString().trim();
        String em = email.getEditText().getText().toString().trim();
        String pwd = pass.getEditText().getText().toString().trim();

        if (!TextUtils.isEmpty(name)
                && !TextUtils.isEmpty(em) && !TextUtils.isEmpty(pwd)) {

            progressDialog.setMessage("Creating Account...");
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(em, pwd)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (authResult != null) {

                                StorageReference imagePath = mFirebaseStorage.child("MBlog_Profile_Pics")
                                        .child(resultUri.getLastPathSegment());

                                imagePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                        String userid = mAuth.getCurrentUser().getUid();

                                        DatabaseReference currenUserDb = databaseReference.child(userid);
                                        currenUserDb.child("firstname").setValue(name);
                                       // currenUserDb.child("lastname").setValue(lname);
                                        currenUserDb.child("image").setValue(resultUri.toString());


                                        progressDialog.dismiss();

                                        //send users to postList
                                        Intent intent = new Intent(RegisterActivity.this, MainHomeScreen.class );
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                });
                            }

                        }
                    });


        }
    }

*/ // account with iamge ends here.


    // working code starts here.
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

//                            StorageReference imagePath = mFirebaseStorage.child("MBlog_Profile_Pics")
  //                                  .child(resultUri.getLastPathSegment());


                            String userid = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = databaseReference.child("Users").push();
                            String pushId = currentUserDb.getKey();

                            Users users = new Users("userName","userEmailId","userProfilePic");

                            Map<String, String> DataToSave = new HashMap<>();
                            DataToSave.put("userName", userName);
                            DataToSave.put("userEmailId", emailID);
                            DataToSave.put("userProfilePic", password);
                          //  DataToSave.put()
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
 // working code ends here.

    private void senttomain() {

        Intent intent = new Intent(RegisterActivity.this, MainHomeScreen.class);
    //    Bundle bundle = new Bundle();
      //  bundle.putString("UserName",currentusername);
       // intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
