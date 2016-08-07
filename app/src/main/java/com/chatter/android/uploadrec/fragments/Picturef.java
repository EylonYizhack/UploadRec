package com.chatter.android.uploadrec.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chatter.android.uploadrec.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Picturef extends Fragment {

    private String picturePath = "";
    private ImageView myImage;
    Bitmap myBitmap;
    Button fireCamBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_picturef, container, false);
        // recFreeEditText=(EditText) rootView.findViewById(R.id.recFreeEditText);
        myImage = (ImageView) rootView.findViewById(R.id.camImage);
        Log.e("camera", "onCreate:  " + picturePath);
        fireCamBtn = (Button) rootView.findViewById(R.id.fireCamID);
        fireCamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgTakeAPicture();
            }
        });
        return rootView;
    }

    private void fireCam() {
        Log.e("Camera", "fireCam: start");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        picturePath = storageDir.getAbsolutePath() + "/" + imageFileName;
        File file = new File(picturePath);
        Uri outputFileUri = Uri.fromFile(file);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        Log.e("Camera", "fireCam: intent");
        startActivityForResult(cameraIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            File imgFile = new File(picturePath);
            if (imgFile.exists()) {
                myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                myImage.setImageBitmap(myBitmap);
            }
        }
    }


    private void dlgTakeAPicture()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("העלאת תמונה");
        final TextView txt = new TextView(getActivity());
        //specify the type of input expected
        txt.setText("העלה תמונה של המתכון או של מתכון דומה");
        //add EditText view to the Dialog
        builder.setView(txt);
        // positive feed back
        builder.setPositiveButton("העלה מהמכשיר", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
             //upload from device
            }
        });
        //negative feed back
        builder.setNegativeButton("צלם תמונה", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fireCam();
            }
        });
        builder.show();
    }
}
