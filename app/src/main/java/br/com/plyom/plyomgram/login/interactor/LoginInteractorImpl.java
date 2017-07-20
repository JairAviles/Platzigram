package br.com.plyom.plyomgram.login.interactor;

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
    public void signin(String username, String password) {
        repository.signin(username, password);
    }
}
