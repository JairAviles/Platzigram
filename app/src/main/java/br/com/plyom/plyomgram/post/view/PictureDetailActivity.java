package br.com.plyom.plyomgram.post.view;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import br.com.plyom.plyomgram.PlatzigramApplication;
import br.com.plyom.plyomgram.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureDetailActivity extends AppCompatActivity {

    private static final String PHOTO_NAME = "JPEG_20170728_22-10-01_866448116.jpg";
    private static final String TAG = PictureDetailActivity.class.getName();

    @BindView(R.id.imageHeader)
    ImageView imageHeader;

    private PlatzigramApplication app;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        ButterKnife.bind(this);
        showToolbar("", true);
        app = (PlatzigramApplication) getApplicationContext();
        storageReference = app.getStorageReferences();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }


        showData();

    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
    }

    private void showData() {
        storageReference.child("postImages/" + PHOTO_NAME)
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(PictureDetailActivity.this).load(uri.toString()).into(imageHeader);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PictureDetailActivity.this, "Oucrrio un error al traer la foto", Toast.LENGTH_SHORT);
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        });
    }

}
