package br.com.plyom.plyomgram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by javiles on 20/07/17.
 */

public interface LoginInteractor {
    void signin(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
}
