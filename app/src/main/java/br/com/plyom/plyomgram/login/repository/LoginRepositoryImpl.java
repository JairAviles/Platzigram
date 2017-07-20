package br.com.plyom.plyomgram.login.repository;

import br.com.plyom.plyomgram.login.presenter.LoginPresenter;

/**
 * Created by javiles on 20/07/17.
 */

public class LoginRepositoryImpl implements LoginRepository {

    LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signin(String username, String password) {
        boolean success = true;
        if (success) {
            presenter.loginSuccess();
        } else {
            presenter.loginError("Error al hacer login");
        }
    }
}
