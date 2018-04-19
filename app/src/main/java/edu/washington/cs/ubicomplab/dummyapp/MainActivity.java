package edu.washington.cs.ubicomplab.dummyapp;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;
    ImageView image;
    int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        image = findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClassName("edu.washington.cs.ubicomplab.rdt_reader", "edu.washington.cs.ubicomplab.rdt_reader.ImageQualityActivity");
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();

                byte[] byteArray = extras.getByteArray("RDTCaptureByteArray");
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                image.setImageBitmap(bmp);
            }
        }
    }
}
