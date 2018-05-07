package com.android.abdelmun3m.tripplaner;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            if(FirebaseAuth.getInstance().getCurrentUser() == null){
                    signIn();
            }else{
                lancheProfile();
            }

    }



    private void signIn(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword("abdelmunemelbasiouny@gmail.com","Abdo@123")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            lancheProfile();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //      Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                            Toast.makeText(Login.this, "Faild "+task.getException(), Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }



    private void lancheProfile(){
        Intent i = new Intent(Login.this,ProfileActivity.class);
        Login.this.startActivity(i);
    }

}
