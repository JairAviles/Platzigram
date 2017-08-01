package br.com.plyom.plyomgram.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by javiles on 20/07/17.
 */

public interface LoginPresenter {
    void signin(String username, String password, Activity activity, FirebaseAuth firebaseAuth); // Interactor
    void loginSuccess();
    void loginError(String error);
}
