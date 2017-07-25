package br.com.plyom.plyomgram.login.view;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.plyom.plyomgram.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";

    @BindView(R.id.email)
    TextInputEditText edtEmail;

    @BindView(R.id.password_create_account)
    TextInputEditText edtPassword;

    @BindView(R.id.confirmPassword)
    TextInputEditText edtConfirmPassword;

    @OnClick(R.id.join_us)
    void createUser() {
        createAccount();
    }

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        showToolbar(getResources().getString(R.string.toolbar_title_create_account), true);

        initFirebase();

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

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.i(TAG, "User logged " + firebaseUser.getEmail());
                } else  {
                    Log.w(TAG, "User not logged");
                }
            }
        };
    }

    private void createAccount() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPassword= edtConfirmPassword.getText().toString();

        if ( password != null && password.length() > 0 && confirmPassword != null && confirmPassword.length() > 0 && password.equals(confirmPassword) ) {

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CreateAccountActivity.this, "Account created succesfully", Toast.LENGTH_SHORT);
                            } else {
                                Toast.makeText(CreateAccountActivity.this, "An error occurred while trying to create account", Toast.LENGTH_SHORT);
                            }
                        }
                    });
        }
    }

}

