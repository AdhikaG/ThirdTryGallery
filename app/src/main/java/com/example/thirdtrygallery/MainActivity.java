package com.example.thirdtrygallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<File> ImageList;
    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = (GridView)findViewById(R.id.image_grid);

        ImageList = imageReader(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/Basic"));

        grid.setAdapter(new gridAdapter());

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,ImageActivity.class);
                intent.putExtra("image",ImageList.get(i).toString());

                startActivity(intent);

            }
        });


    }

    public class gridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return ImageList.size();
        }

        @Override
        public Object getItem(int i) {
            return ImageList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View Converter = null;

            if(Converter == null)
            {
                Converter = getLayoutInflater().inflate(R.layout.row,viewGroup,false);
                ImageView myImage = (ImageView) Converter.findViewById(R.id.my_Image);
                myImage.setImageURI(Uri.parse(ImageList.get(i).toString()));
            }

            return Converter;
        }
    }


    private ArrayList<File> imageReader(File externalStorageDirectory) {

        ArrayList<File> arr = new ArrayList<>();

        File[] files = externalStorageDirectory.listFiles();

        for(int x =0;x<files.length;x++)
        {
            if(files[x].isDirectory())
            {
                arr.addAll(imageReader(files[x]));
            }
            else
            {
                if(files[x].getName().endsWith(".jpg"))
                {
                    arr.add(files[x]);
                }
            }
        }

        return arr;
    }
}