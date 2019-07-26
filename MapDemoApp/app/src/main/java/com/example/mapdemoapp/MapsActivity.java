package com.example.mapdemoapp;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

public class MapsActivity extends BaseMapActivity {
    private ClusterManager<MyItem> mClusterManager;
    private Boolean isStateDraw = false;

    @Override
    protected void startMap() {
        LatLng sydney = new LatLng(51.503186, -0.126446);
        getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));

        mClusterManager = new ClusterManager<MyItem>(this, getMap());

        getMap().setOnCameraIdleListener(mClusterManager);
        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }

    }
    PolylineOptions polylineOptions=new PolylineOptions();
    @Override
    protected void onDragEvent(MotionEvent motionEvent) {
        if(!isStateDraw)
            return;
        Log.i("ON_DRAG", "X:" + String.valueOf(motionEvent.getX()));
            Log.i("ON_DRAG", "Y:" + String.valueOf(motionEvent.getY()));
        int action = motionEvent.getAction();

        if(action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_DOWN){
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int x_co = Integer.parseInt(String.valueOf(Math.round(x)));
            int y_co = Integer.parseInt(String.valueOf(Math.round(y)));
            Point x_y_points = new Point(x_co, y_co);
            LatLng latLng = getMap().getProjection().fromScreenLocation(x_y_points);
            //listLatLng.add(latLng);

            //if(listLatLng.size() > 1)
            {

                polylineOptions.add(latLng);
                //if(polylineOptions.getPoints().size() > 1)
                    getMap().addPolyline(polylineOptions);
                //listLatLng.clear();
            }
        } if(action == MotionEvent.ACTION_UP){
            polylineOptions = null;
            polylineOptions=new PolylineOptions();
        }
            // Handle motion event:
    }
    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        List<MyItem> items = new MyItemReader().read(inputStream);
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            for (MyItem item : items) {
                LatLng position = item.getPosition();
                double lat = position.latitude + offset;
                double lng = position.longitude + offset;
                MyItem offsetItem = new MyItem(lat, lng);
                mClusterManager.addItem(offsetItem);
            }
        }
    }

    public void onClickButtonEvent(View v) {
        int id = v.getId();
        if(id == R.id.btnDrawPolygon) {
            isStateDraw = !isStateDraw;
            if(isStateDraw){
                getMap().getUiSettings().setAllGesturesEnabled(false);
            } else {
                getMap().getUiSettings().setAllGesturesEnabled(true);
            }
        } else if(id == R.id.btnZoomIn) {
            getMap().animateCamera(CameraUpdateFactory.zoomIn());

        } else if(id == R.id.btnZoomOut) {
            getMap().animateCamera(CameraUpdateFactory.zoomOut());
        }
    }
}
