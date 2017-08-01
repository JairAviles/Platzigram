package br.com.plyom.plyomgram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.plyom.plyomgram.login.presenter.LoginPresenter;
import br.com.plyom.plyomgram.login.repository.LoginRepository;
import br.com.plyom.plyomgram.login.repository.LoginRepositoryImpl;

/**
 * Created by javiles on 20/07/17.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        repository = new LoginRepositoryImpl(presenter);
    }

    @Override
    public void signin(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        repository.signin(username, password, activity, firebaseAuth);
    }
}
