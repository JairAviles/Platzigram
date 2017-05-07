package br.com.plyom.plyomgram;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import br.com.plyom.plyomgram.view.ContainerActivity;
import br.com.plyom.plyomgram.view.CreateAccountActivity;

public class  LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gotoWebSite();
    }

    public void goCreateAccount(View view) {
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);

    }

    public void goHome(View view) {
        Intent i = new Intent(this, ContainerActivity.class);
        startActivity(i);
    }

    private void gotoWebSite() {
        ImageView imageView = (ImageView) findViewById(R.id.logo);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("http://platzigram.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

    }

}
