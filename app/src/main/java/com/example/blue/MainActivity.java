package com.car.bluetooth.bluetoothcar;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //MENÜPUNKTE
        if (id == R.id.action_settings) {
            Intent settingsintent = new Intent(MainActivity.this,
                    settingsactivity.class);
            startActivity(settingsintent);
            return false;
        }
        if (id == R.id.action_connect) {
            Intent connectintent = new Intent(MainActivity.this,
                    connectactivity.class);
            startActivity(connectintent);
            return false;
        }

        return super.onOptionsItemSelected(item);
    }


//SeekBars

    private SeekBar seekBarGas;
    private TextView textViewGas;

    private SeekBar seekBarSteering;
    private TextView textViewSteering;

    private CheckBox backwards_checkBox;
    boolean rückwärts_var = false;

    boolean safeMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        seekBarGas = (SeekBar) findViewById(R.id.seekBarGas);
        textViewGas = (TextView) findViewById(R.id.textViewGas);
        seekBarGas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewGas.setText(progress + "  /  " + seekBarGas.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarGas.setProgress(0);
            }
        });

        seekBarSteering = (SeekBar) findViewById(R.id.seekBarSteering);
        textViewSteering = (TextView) findViewById(R.id.textViewSteering);
        seekBarSteering.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSteering.setText(progress + "  /  " + seekBarSteering.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarSteering.setProgress(3);
            }
        });


        //GET DATA Settings

        Intent safeMode_Intent = getIntent();
        safeMode = safeMode_Intent.getBooleanExtra("safeMode", false);


        //Rückwärts

        CheckBox backwards_checkBox=(CheckBox)findViewById(R.id.backwards_checkBox);

        backwards_checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (((CheckBox)v).isChecked()) {
                    Toast.makeText(getApplicationContext(), "Rückwärts", Toast.LENGTH_SHORT).show();
                    rückwärts_var = true;

                    if (safeMode == true) {
                        seekBarGas.setMax(2);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Vorwärts", Toast.LENGTH_SHORT).show();
                    rückwärts_var = false;

                    if (safeMode == true){
                        seekBarGas.setMax(5);
                    }
                }
            }
        });


        //Bluetooth



        if (btAdapter == null){
            Toast.makeText(getApplicationContext(), "Bluetooth wird auf diesem Gerät nicht unterstützt", Toast.LENGTH_LONG).show();
        }






    }





}