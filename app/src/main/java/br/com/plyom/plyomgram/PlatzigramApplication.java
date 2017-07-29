package br.com.plyom.plyomgram;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Jair-MAC on 7/23/17.
 */

public class PlatzigramApplication extends Application {

    public final String TAG = PlatzigramApplication.class.getName();

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseStorage firebaseStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.i(TAG, "Logged user: " + firebaseUser.getEmail());
                } else  {
                    Log.w(TAG, "Not logged user");
                }
            }
        };
        firebaseStorage = FirebaseStorage.getInstance();
    }

    public StorageReference getStorageReferences() {
        return firebaseStorage.getReference();
    }

}
