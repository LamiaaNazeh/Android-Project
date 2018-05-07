package com.android.abdelmun3m.tripplaner; /**
 * Created by abdelmun3m on 23/03/18.
 */

import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public final class DBSingleTon {

        private static final String TAG = DBSingleTon.class.getSimpleName();
        private static final String TRIP_KEY = "trips";
        private static final String USER_INFO = "info";
        private static FirebaseDatabase DBinsatnce;
        private static DatabaseReference DBreference;
        private static ValueEventListener ValueListener;
        public FirebaseAuth myAuth;
        public FirebaseAuth.AuthStateListener myAuthListener;
        public  static  FirebaseUser firebaseUser;

        private  DBSingleTon(){
            //DBinsatnce = FirebaseDatabase.getInstance(); // get an instance from the database .
            //DBreference = DBinsatnce.getReference() ; // getting the reference to the root of the data base
            //myAuth = FirebaseAuth.getInstance();
        }

        public static DatabaseReference GET_ROOT_DATABASE_REFERENCE(){

            if(DBreference == null){
                DBinsatnce = FirebaseDatabase.getInstance(); // get an instance from the database .
                DBreference = DBinsatnce.getReference();
            }
            return DBreference;
        }

        public static DatabaseReference GET_USER_ROOT_REFERENCE(){
            if(firebaseUser == null){
                GET_LOGGED_USER(null);
            }
            return GET_ROOT_DATABASE_REFERENCE().child(firebaseUser.getUid());
        }

        public static DatabaseReference GET_TRIP_REFERENCE(){
            if(GET_USER_ROOT_REFERENCE() == null)
                return GET_ROOT_DATABASE_REFERENCE();
            return GET_USER_ROOT_REFERENCE().child(TRIP_KEY);
        }

        public static DatabaseReference GET_USER_INFO_REFERENCE(){

            return GET_USER_ROOT_REFERENCE().child(USER_INFO);
        }


        public static String ADD_TRIP(Object trip){
            String id = GET_TRIP_REFERENCE().push().getKey();
            GET_TRIP_REFERENCE().push().setValue(trip);
            return id;
        }

        public static void ADD_USER_INFO(Object info){
            GET_USER_INFO_REFERENCE().setValue(info);
        }

        public static FirebaseUser GET_LOGGED_USER(Context context){
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if(firebaseUser == null){
                Intent i = new Intent(context,LoginActivty.class);
                context.startActivity(i);
            }
            return firebaseUser;
        }

        //TODO MAke All Functions take context as parameters

}
