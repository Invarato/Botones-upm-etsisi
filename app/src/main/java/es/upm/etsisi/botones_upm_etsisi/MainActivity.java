package es.upm.etsisi.botones_upm_etsisi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.upm.etsisi.botones_upm_etsisi.vista.interfaz;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        finish();
        Intent nuevoIntent = new Intent(MainActivity.this, interfaz.class);

        startActivity (nuevoIntent);
    }

}
