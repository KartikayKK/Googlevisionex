package com.kkapp.googlevisiondemo;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;


public class basic extends Fragment {
    ImageView ig;
    Button bt;
    TextView tt;

    public basic() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_basic, container, false);
        ig=v.findViewById(R.id.imageView);
        bt=v.findViewById(R.id.button);

        tt=v.findViewById(R.id.textView);
        final Bitmap bitmap= BitmapFactory.decodeResource(
                getContext().getResources(),R.drawable.detector
        );
        ig.setImageBitmap(bitmap);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextRecognizer textRecognizer =new TextRecognizer.Builder(getContext()).build();
                if(!textRecognizer.isOperational()){
//                    Toast.makeText(MainActivity.this,"OOps",Toast.LENGTH_LONG);
                }else{
                    Frame frame=new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items=textRecognizer.detect(frame);
                    StringBuilder stringBuilder=new StringBuilder();
                    for(int i=0;i<items.size();++i){
                        TextBlock item=items.valueAt(i);
                        stringBuilder.append(item.getValue());
                        stringBuilder.append("\n");
                    }
                    tt.setText(stringBuilder.toString());
                }
            }
        });
        return v;
    }

}
