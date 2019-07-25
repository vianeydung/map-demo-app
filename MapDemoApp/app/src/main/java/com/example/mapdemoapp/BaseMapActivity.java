/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mapdemoapp;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseMapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    protected List<LatLng> listLatLng;
    protected int getLayoutId() {
        return R.layout.activity_maps;
    }
    //protected LatLng prePosLatLng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        listLatLng=new ArrayList<LatLng>();
        /*FrameLayout fram_map = (FrameLayout) findViewById(R.id.map_layer);
        fram_map.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN:
                        // finger touches the screen
                        //listLatLng.add(new LatLng(latitude, longitude));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // finger moves on the screen
                        //listLatLng.add(new LatLng(latitude, longitude));
                        break;
                    case MotionEvent.ACTION_UP:
                        // finger leaves the screen
                        Draw_Map();
                        break;
                }
                return true;
        }
        });*/
        setUpMap();
    }

    public void Draw_Map() {
        /*PolylineOptions polylineOptions=new PolylineOptions();
        polylineOptions.addAll(listLatLng);
        mMap.addPolyline(polylineOptions);*/
        /*PolygonOptions rectOptions = new PolygonOptions();
        rectOptions.addAll(listLatLng);
        rectOptions.strokeColor(Color.BLUE);
        rectOptions.strokeWidth(7);
        rectOptions.fillColor(Color.CYAN);
        mMap.addPolygon(rectOptions);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        //mMap.getUiSettings().setZoomControlsEnabled(true);

        startMap();
    }

    private void setUpMap() {
        //((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        MySupportMapFragment customMapFragment = ((MySupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        customMapFragment.getMapAsync(this);

        customMapFragment.setOnDragListener(new MapWrapperLayout.OnDragListener() {
            @Override
        public void onDrag(MotionEvent motionEvent) {
            /*Log.i("ON_DRAG", "X:" + String.valueOf(motionEvent.getX()));
            Log.i("ON_DRAG", "Y:" + String.valueOf(motionEvent.getY()));

            float x = motionEvent.getX();
            float y = motionEvent.getY();

            int x_co = Integer.parseInt(String.valueOf(Math.round(x)));
            int y_co = Integer.parseInt(String.valueOf(Math.round(y)));


            Point x_y_points = new Point(x_co, y_co);
            LatLng latLng = mMap.getProjection().fromScreenLocation(x_y_points);
            listLatLng.add(latLng);
            if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                Draw_Map();
            }
            // Handle motion event:*/
            onDragEvent(motionEvent);
        }
        });

    }

    /**
     * Run the demo-specific code.
     */
    protected abstract void startMap();
    protected abstract void onDragEvent(MotionEvent motionEvent);

    protected GoogleMap getMap() {
        return mMap;
    }
}
