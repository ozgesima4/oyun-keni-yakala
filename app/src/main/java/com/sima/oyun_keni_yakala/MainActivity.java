package com.sima.oyun_keni_yakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.RunnableFuture;

public class MainActivity extends AppCompatActivity {
  TextView timetext;
  TextView scoretext;
  int scor;
  ImageView imageview,imageview2,imageview3,imageview4,imageview5,imageview6,imageview7,imageview8,imageview9;
  ImageView [] imageArray;
  Handler handler;
  Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        scor=0;

        timetext=findViewById(R.id.timetextwiew);
        scoretext=findViewById(R.id.scoretext);
        imageview=findViewById(R.id.imageView);
        imageview2=findViewById(R.id.imageView2);
        imageview3=findViewById(R.id.imageView3);
        imageview4=findViewById(R.id.imageView4);
        imageview5=findViewById(R.id.imageView5);
        imageview6=findViewById(R.id.imageView6);
        imageview7=findViewById(R.id.imageView7);
        imageview8=findViewById(R.id.imageView8);
        imageview9=findViewById(R.id.imageView9);
        imageArray= new ImageView[]{imageview,imageview2,imageview3,imageview4,imageview5,imageview6,imageview7,imageview7,imageview8,imageview9};
        hidepicture();

        

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                timetext.setText("TİME:"+(l/1000));
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                 scoretext.setVisibility(View.INVISIBLE);


                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("GAME OVER!");
                alert.setMessage("oyunu kaybettiniz tekrardan oynamak ister misiniz?");
                alert.setPositiveButton("evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent();
                        finish();

                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"GAME OVER",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }); alert.show();


            }
        }.start();

    }
     public void tikla(View view){
        scor++;
        scoretext.setText("SCORE: "+scor);
     }

     public void hidepicture(){
       handler=new Handler();
       runnable=new Runnable() {
           @Override
           public void run() {
               for (ImageView image:imageArray) {
                   image.setVisibility(View.INVISIBLE);
               }
               Random rndm=new Random();
               int i=rndm.nextInt(9);
               imageArray[i].setVisibility(View.VISIBLE);

               handler.postDelayed(this,300);
           }
       }; handler.post(runnable);



     }
}