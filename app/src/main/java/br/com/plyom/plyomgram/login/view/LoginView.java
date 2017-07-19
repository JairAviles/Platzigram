package br.com.plyom.plyomgram.login.view;


/**
 * Created by javiles on 18/07/17.
 */

public interface LoginView {

    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();
    void showError(String error);
    void goCreateAccount();
    void goHome();

}
