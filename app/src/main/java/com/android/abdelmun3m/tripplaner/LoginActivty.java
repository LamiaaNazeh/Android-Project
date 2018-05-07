package com.android.abdelmun3m.tripplaner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class LoginActivty extends AppCompatActivity implements  View.OnClickListener{

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

        firebaseAuth= FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            //Profile Activity Here
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            finish();

        }


        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
        progressDialog= new ProgressDialog(this);
    }

    private void userLogin(){

        String email =editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            // email is empty
            Toast.makeText(this,"Please Enter Email!" ,Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please Enter Password!" , Toast.LENGTH_LONG).show();
            return;
        }

        //If Validation are OK We Will Show The ProgressDialog
        progressDialog.setMessage("Registering User....");
        progressDialog.show();
//        Toast.makeText(this, email+"::::"+password, Toast.LENGTH_LONG).show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isComplete()){
                            Toast.makeText(LoginActivty.this, "completed", Toast.LENGTH_LONG).show();
                        }
                        if(task.isSuccessful()){
                            //Start Profile Activity
                            Toast.makeText(LoginActivty.this, "Loged", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        }else{
                            Log.i("login",task.getException().toString());
                        }

                    }
                });

    }

    @Override
    public void onClick(View view) {

        if (view == buttonSignIn) {
            userLogin();
        }
        if(view == textViewSignUp){
            finish();
            startActivity(new Intent(this,registerActivity.class));
        }
    }

}
