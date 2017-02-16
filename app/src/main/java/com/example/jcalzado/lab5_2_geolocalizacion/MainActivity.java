package com.example.jcalzado.lab5_2_geolocalizacion;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView outLat, outLong, outAlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outLat = (TextView) findViewById(R.id.outLat);
        outLong = (TextView) findViewById(R.id.outLong);
        outAlt = (TextView) findViewById(R.id.outAlt);

        LocationManager gestorLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gestorLoc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        outLat.setText(String.valueOf(location.getLatitude()));
        outLong.setText(String.valueOf(location.getLongitude()));
        outAlt.setText(String.valueOf(location.getAltitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        String mensaje = "";
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                mensaje = "Estado del GPS: Fuera de servicio.";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                mensaje = "Estado del GPS: Temporalmente no disponible.";
                break;
            case LocationProvider.AVAILABLE:
                mensaje = "Estado del GPS: Disponible.";
                break;
        }
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getApplicationContext(), "GPS activado por el usuario.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getApplicationContext(), "GPS desactivado por el usuario.", Toast.LENGTH_SHORT).show();
    }
}
