package br.com.plyom.plyomgram.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import br.com.plyom.plyomgram.R;
import br.com.plyom.plyomgram.login.presenter.LoginPresenter;
import br.com.plyom.plyomgram.login.presenter.LoginPresenterImpl;
import br.com.plyom.plyomgram.view.ContainerActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class  LoginActivity extends AppCompatActivity implements LoginView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.username)
    TextInputEditText username;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.login_facebook)
    LoginButton loginFacebook;
    @BindView(R.id.progressbarLogin)
    ProgressBar progressbarLogin;

    private LoginPresenter presenter;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setListenerGoWebSite();
        hideProgressBar();
        initPresenter();
        setListenerLogin();
        initFirebase();
        initFacebook();

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressbarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressbarLogin.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, getString(R.string.login_error) + " " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goCreateAccount() {
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);

    }

    @Override
    public void goHome() {
        Intent i = new Intent(this, ContainerActivity.class);
        startActivity(i);
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setListenerGoWebSite() {
        ImageView imageView = (ImageView) findViewById(R.id.logo);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("http://platzi.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

    }

    private void initPresenter() {
        presenter = new LoginPresenterImpl(this);
    }

    private void setListenerLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    signIn(username.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    private void signIn(String username, String password) {
        presenter.signin(username, password, this, firebaseAuth);
    }

    public void goCreateAccount(View view) {
        goCreateAccount();
    }


    private void initFacebook() {

        callbackManager = CallbackManager.Factory.create();

        loginFacebook.setReadPermissions(Arrays.asList("email"));
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i(TAG, "Facebook Login Success Token: " + loginResult.getAccessToken().getApplicationId());
                signInFacebookFirebase(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.i(TAG, "Facebook Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "Facebook Login Error: " + error.toString());
                error.printStackTrace();
            }
        });

    }

    private void signInFacebookFirebase(AccessToken accessToken) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());

        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    SharedPreferences preferences
                            = getSharedPreferences("USER", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", user.getEmail());
                    editor.commit();
                    goHome();
                  Toast.makeText(LoginActivity.this, "Login Facebook Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Facebook Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.i(TAG, "Logged user: " + firebaseUser.getEmail());
                    goHome();
                } else  {
                    Log.w(TAG, "Not logged user");
                }
            }
        };
    }

}
;