package br.com.plyom.plyomgram.login.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;

import br.com.plyom.plyomgram.login.presenter.LoginPresenter;

/**
 * Created by javiles on 20/07/17.
 */

public class LoginRepositoryImpl implements LoginRepository {

    LoginPresenter presenter;
    private final String TAG = LoginRepositoryImpl.class.getName();

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signin(final String username, String password, final Activity activity, FirebaseAuth firebaseAuth) {

        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            SharedPreferences preferences
                                    = activity.getSharedPreferences("USER", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("email", user.getEmail());
                            editor.commit();

                            FirebaseCrash.logcat(Log.INFO, TAG, "User logged " + user.getEmail());

                            presenter.loginSuccess();
                        } else {
                            FirebaseCrash.logcat(Log.ERROR, TAG, "An error occurred");
                        }
                    }
                });
    }
}
