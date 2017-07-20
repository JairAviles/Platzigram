package br.com.plyom.plyomgram.post.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.plyom.plyomgram.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPostActivity extends AppCompatActivity {

    @BindView(R.id.fabCamera)
    ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            String photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            Picasso.with(this).load(photoPath).into(imgPhoto);
        }


    }
}
