package br.com.plyom.plyomgram.login.presenter;

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
    public void signin(String username, String password) {
        interactor.signin(username, password);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError() {

    }
}
