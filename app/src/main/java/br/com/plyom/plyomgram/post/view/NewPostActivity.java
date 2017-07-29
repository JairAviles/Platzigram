package br.com.plyom.plyomgram.post.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import br.com.plyom.plyomgram.PlatzigramApplication;
import br.com.plyom.plyomgram.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewPostActivity extends AppCompatActivity {

    public final String TAG = NewPostActivity.class.getName();

    @BindView(R.id.imgPhoto)
    ImageView imgPhoto;

    @OnClick(R.id.btnCreatePost)
    void upload() {
        uploadPhoto();
    }

    private String photoPath;
    private PlatzigramApplication app;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        ButterKnife.bind(this);
        app = (PlatzigramApplication) getApplicationContext();
        storageReference = app.getStorageReferences();

        if (getIntent().getExtras() != null) {
            photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            showPhoto();
        }

    }

    private void showPhoto() {
        Picasso.with(this).load(photoPath).into(imgPhoto);
    }

    private void uploadPhoto() {
        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();

        Bitmap bitmap = imgPhoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] photoByte = baos.toByteArray();
        String photoName = photoPath.substring(photoPath.lastIndexOf("/")+1, photoPath.length());

        StorageReference photoReference = storageReference.child("postImages").child(photoName);

        UploadTask uploadTask = photoReference.putBytes(photoByte);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, e.toString());
                e.printStackTrace();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri uriPhoto = taskSnapshot.getDownloadUrl();
                String photoUrl = uriPhoto.toString();
                Log.i(TAG, "URL photo > " + photoUrl);
                finish();
            }
        });

    }
}
