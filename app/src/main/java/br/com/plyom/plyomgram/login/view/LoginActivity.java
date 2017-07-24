package br.com.plyom.plyomgram.login.view;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.plyom.plyomgram.R;
import br.com.plyom.plyomgram.login.presenter.LoginPresenter;
import br.com.plyom.plyomgram.login.presenter.LoginPresenterImpl;
import br.com.plyom.plyomgram.view.ContainerActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class  LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.username)
    TextInputEditText username;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.progressbarLogin)
    ProgressBar progressbarLogin;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setListenerGoWebSite();
        hideProgressBar();
        initPresenter();
        setListenerLogin();
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
                    presenter.signin(username.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    public void goCreateAccount(View view) {
        goCreateAccount();
    }

}
