package br.com.plyom.plyomgram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.plyom.plyomgram.view.ContainerActivity;
import br.com.plyom.plyomgram.view.CreateAccountActivity;

public class  LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goCreateAccount(View view) {
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);

    }

    public void goHome(View view) {
        Intent i = new Intent(this, ContainerActivity.class);
        startActivity(i);
    }

}
