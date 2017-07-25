package br.com.plyom.plyomgram.login.repository;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by javiles on 20/07/17.
 */

public interface LoginRepository {
    void signin(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
}
