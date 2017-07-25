package br.com.plyom.plyomgram.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.plyom.plyomgram.login.interactor.LoginInteractor;
import br.com.plyom.plyomgram.login.interactor.LoginInteractorImpl;
import br.com.plyom.plyomgram.login.view.LoginView;

/**
 * Created by javiles on 20/07/17.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void signin(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        loginView.disableInputs();
        loginView.showProgressBar();
        interactor.signin(username, password, activity, firebaseAuth);
    }

    @Override
    public void loginSuccess() {
        loginView.goHome();
        loginView.hideProgressBar();
    }

    @Override
    public void loginError(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.showError(error);
    }
}
