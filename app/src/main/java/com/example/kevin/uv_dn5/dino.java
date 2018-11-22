package com.example.kevin.uv_dn5;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import android.os.Vibrator;
public class dino extends AppCompatActivity {

    public int chosen;
    MediaPlayer mp,mp_s,mp_win;
    ImageView imageButton;
    GridView gv,grid1;
    Context mContext;
    Handler handler;
    int delay=5000;
    int s = 0;
    public Vibrator v ;
    Button nazaj;
    Integer[] images = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j,
            R.drawable.k,
            R.drawable.l,
            R.drawable.m,
            R.drawable.n,
            R.drawable.o,
            R.drawable.p,
            R.drawable.q,
            R.drawable.r,
            R.drawable.s,
            R.drawable.t,
            R.drawable.u,
            R.drawable.v,
            R.drawable.w,
            R.drawable.x,
            R.drawable.y,
            R.drawable.z
    };
    Integer[] dog = {
            R.drawable.b,
            R.drawable.o,
            R.drawable.k,
            R.drawable.s,
    };

    Integer[] dog_b = {

            R.drawable.s,
            R.drawable.k,
            R.drawable.o,
            R.drawable.b,

    };





    List<ImageView> solved = new ArrayList<ImageView>();
    List<ImageView> available = new ArrayList<ImageView>();

    private Integer[] img;

    public List<Integer> used = new ArrayList();




    @Override
    protected void onResume()
    {
        super.onResume();
        hideAndroidApplicationBar();
    }

    private void hideAndroidApplicationBar()
    {
        final View view = getWindow().getDecorView();
        // Hide Android Soft keys
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide navigation bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_dino);

        for(int i = 0;i< dog_b.length;i++){
            used.add(i);
        }
        mContext = this;

        //Collections.shuffle(used);
        mp_win = MediaPlayer.create(this, R.raw.win);
        mp = MediaPlayer.create(this, R.raw.error);
        mp_s = MediaPlayer.create(this, R.raw.succes);
        v  = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        nazaj = (Button) findViewById(R.id.button);

        imageButton = (ImageView) findViewById(R.id.imageView);
        gv = (GridView) findViewById(R.id.grid);
        grid1 = (GridView) findViewById(R.id.grid1);

        //findViewById(R.id.imageView).setOnTouchListener(new MyTouchListener());

        //intent switch to random new image
        img = dog_b;
        gv.setAdapter(new ImageAdapter(this,1));
        grid1.setAdapter(new ImageAdapter2(this,1));


        /*int childCount = gv.getChildCount();
        for (int i= 0; i < childCount; i++){
            LinearLayout container = (LinearLayout) gv.getChildAt(i);
            container.setOnTouchListener(new MyTouchListener());
        }

        childCount = grid1.getChildCount();
        for (int i= 0; i < childCount; i++){
            LinearLayout container = (LinearLayout) grid1.getChildAt(i);
            container.setOnDragListener(new MyDragListener());
        }*/

        final List<Integer> images1 = new ArrayList<Integer>(Arrays.asList(images));

        // Create a new ArrayAdapter
/*
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //do something
                ImageView tmp = solved.get(0);
                for(int i=0;i<available.size();i++){
                    if(tmp.getId() == available.get(i).getId()){
                        tmp = available.get(i);
                        break;
                    }
                }

                AnimateBell(tmp);


            }
        }, delay);
        */

        //start_handler();
        nazaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void vibrate(){
        v.vibrate(300);
    }

    public void start_handler(){

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                // do something...
                ImageView tmp = solved.get(s);
                Toast.makeText(getApplicationContext(), tmp.getId()+" "+s+" "+solved.size(),
                        Toast.LENGTH_LONG).show();
                for(int i=0;i<available.size();i++){
                    if(tmp.getId() == available.get(i).getId()){
                        tmp = available.get(i);
                        break;
                    }
                }

                AnimateBell(tmp);
            }
        }, 1000);

    }



    public void AnimateBell(ImageView image) {
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        image.startAnimation(shake); // starts animation
    }


    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                chosen = view.getId();

                Intent intent = new Intent();
                intent.putExtra("num",view.getId());
                ClipData data = ClipData.newIntent("intent",intent);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                return true;
            } else {
                //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                return false;
            }

        }
    }



    class MyDragListener implements View.OnDragListener {


        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    ImageView view = (ImageView) event.getLocalState();
                    ClipData data = event.getClipData();
                    ClipData.Item in = data.getItemAt(0);
                    Intent intent = in.getIntent();
                    int i = intent.getIntExtra("intent",0);



                    //v.setBackgroundColor(0xFF00FFFF);

                    int id= 0;
                    if(view.getId() == 0){
                       id=3;
                    }else if ((int) view.getId() == 1){
                        id = 2;
                    }
                    else if ((int) view.getId() == 2){
                        id = 1;
                    }
                    else if ((int) view.getId() == 3){
                        id = 0;
                    }
                    else if ((int) view.getId() == 4){
                        id = 5;
                    }
                    else if ((int) view.getId() == 5){
                        id = 4;
                    }

                    if(v.getId() == id){

                        v.setBackgroundResource(dog[id]);
                        ViewGroup owner = (ViewGroup) view.getParent();
                        view.setVisibility(View.INVISIBLE);

                        ListIterator listIterator = solved.listIterator();

                        s++;



                        // start_handler();
                        if(s == img.length){
                            mp_win.start();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    // yourMethod();
                                    final Intent inte = new Intent(getApplicationContext(),wolf.class);
                                    startActivity(inte);
                                    finish();
                                }
                            }, 2000);

                        }else{
                            mp_s.start();
                        }
                    }else{
                        ImageView tmp;

                        if((int)v.getId() == 0){
                            tmp =(ImageView) gv.getChildAt(3);
                        }else if((int)v.getId() == 1){
                            tmp = (ImageView)gv.getChildAt(2);
                        }else if((int)v.getId() == 2) {
                            tmp = (ImageView) gv.getChildAt(1);
                        }
                            else if((int)v.getId() == 3){
                                tmp = (ImageView)gv.getChildAt(0);
                        }  else if((int)v.getId() == 4){
                            tmp = (ImageView)gv.getChildAt(5);
                        }  else {
                            tmp = (ImageView)gv.getChildAt(4);
                        }
                        vibrate();


                        AnimateBell(tmp);
                        mp.start();
                    }

                    //owner.removeView(view);
                    //LinearLayout container = (LinearLayout) v;
                    //container.addView(view);
                    //view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                default:
                    break;
            }
            return true;
        }
    }



    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private int x;

        public ImageAdapter(Context c,int x) {
            mContext = c;
            this.x = x;

        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(20, 20, 20, 20);
                imageView.setOnTouchListener(new MyTouchListener());
            } else {
                imageView = (ImageView) convertView;
            }
            //imageView.setBackgroundColor(0xFF00FF00);
            imageView.getLayoutParams().height = 150;
            imageView.getLayoutParams().width = 150;

            imageView.setImageResource(mThumbIds[used.get(position)]);
            imageView.setId(used.get(position));
            available.add(imageView);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = img;

    }



    public class ImageAdapter2 extends BaseAdapter {
        private Context mContext;
        private int x;

        public ImageAdapter2(Context c,int x) {
            mContext = c;
            this.x = x;

        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public void setView(int position,ImageView view){

            view.setImageResource(mThumbIds[position]);
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(15, 15, 15, 15);
                imageView.setOnDragListener(new MyDragListener());
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setBackgroundColor(0xFFFF0000);
            imageView.getLayoutParams().height = 150;
            imageView.getLayoutParams().width = 150;
            imageView.setId(position);
            solved.add(imageView);
            //imageView.setImageResource(mThumbIds[position]);


            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = img;

    }


}
