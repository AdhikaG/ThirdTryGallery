package com.example.thirdtrygallery;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    ImageView ImageViewer;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view);

        ImageViewer=(ImageView) findViewById(R.id.image_view);
        String word = getIntent().getExtras().getString("image");
        ImageViewer.setImageURI(Uri.parse(word));
    }
}