package br.com.plyom.plyomgram.login.interactor;

import br.com.plyom.plyomgram.login.presenter.LoginPresenter;

/**
 * Created by javiles on 20/07/17.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter presenter;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signin(String username, String password) {

    }
}
