package com.example.mapdemoapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * TODO: document your custom view class.
 */
public class MyMapView extends MapView implements com.google.android.gms.maps.MapView.OnDragListener {

    public MyMapView(Context context) {
        super(context);
    }

    public MyMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        //String dotTAG = (String) getTag();
        if (event.getLocalState() != this) {
            return false;
        }
        boolean myResult = true;
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.d("","ACTION_DRAG_STARTED");
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                Log.d("","ACTION_DRAG_LOCATION");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d("","ACTION_DRAG_ENTERED");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.d("","ACTION_DRAG_EXITED");
                break;
            case DragEvent.ACTION_DROP:
                Log.d("","ACTION_DROP");
                myResult = false;
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.d("","ACTION_DRAG_ENDED");
                break;
            default:
                myResult = false;
                break;
        }
        return false;
        //return myResult;

    }

    public boolean onTouchEvent(MotionEvent event){
        Log.d("","onTouchEvent");
        return true;
    }
}
