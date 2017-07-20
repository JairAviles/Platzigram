package br.com.plyom.plyomgram.login.presenter;

/**
 * Created by javiles on 20/07/17.
 */

public interface LoginPresenter {
    void signin(String username, String password); // Interactor
    void loginSuccess();
    void loginError();
}
