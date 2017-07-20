package br.com.plyom.plyomgram.post.view;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.plyom.plyomgram.R;
import br.com.plyom.plyomgram.adapter.PictureAdapterRecyclerView;
import br.com.plyom.plyomgram.model.Picture;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;

    @BindView(R.id.fabCamera)
    FloatingActionButton fabCamera;

    @BindView(R.id.pictureRecycler)
    RecyclerView picturesRecycler;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        showToolbar(getResources().getString(R.string.tab_home), false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);
        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        return view;
    }

    private ArrayList<Picture> buildPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg", "Tiago Peixoto", "4 días", "3 Me gusta"));
        pictures.add(new Picture("http://www.enjoyart.com/library/landscapes/tuscanlandscapes/large/Tuscan-Bridge--by-Art-Fronckowiak-.jpg", "Jair Avilés", "3 días", "10 Me gusta"));
        pictures.add(new Picture("http://www.educationquizzes.com/library/KS3-Geography/river-1-1.jpg", "Aline Castro", "2 días", "9 Me gusta"));
        pictures.add(new Picture("https://cache-graphicslib.viator.com/graphicslib/thumbs360x240/16674/SITours/excurs-o-particular-ao-p-o-de-a-car-e-cristo-redentor-in-rio-de-janeiro-278478.jpg", "Luiz Figueiro", "9 días", "11 Me gusta"));
        pictures.add(new Picture("https://cache-graphicslib.viator.com/graphicslib/thumbs360x240/36292/SITours/private-st-petersburg-tour-with-boat-ride-in-saint-petersburg-344290.jpg", "Juan Perez", "4 días", "2 Me gusta"));
        pictures.add(new Picture("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Mumbai_03-2016_31_Gateway_of_India.jpg/220px-Mumbai_03-2016_31_Gateway_of_India.jpg", "Fernando Pileggi", "6 días", "5 Me gusta"));

        return pictures;
    }


    private void showToolbar(String title, boolean upButton) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void takePicture() {
        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentTakePicture.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intentTakePicture, REQUEST_CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK) {
            Log.d("HomeFragment", "Camera accessed");
        }
    }
}
