package com.example.lab3_bt2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GraphicsView(this));
    }
    public class Graphics extends Activity {
        @Override
        public void onCreate(Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
            setContentView(new GraphicsView(this));
        }
    }

    public class GraphicsView extends  View{
        int x = 0;
        int y = 0;
        int d = 100;
        int r = 50;
        public GraphicsView(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
           if(x!=0 &&y!=0){
               int right = x+d;
               int bottom = y+r;
               Rect r=new Rect(x,y,right,bottom);
               Paint paint=new Paint();
               paint.setStyle(Paint.Style.FILL);
               paint.setColor(Color.RED);
               canvas.drawRect(r, paint);
           }
            invalidate();
        }
        @Override
        public boolean onTouchEvent(MotionEvent event){
            x=(int) event.getX();
            y=(int) event.getY();
            return super.onTouchEvent(event);
        }
    }
}