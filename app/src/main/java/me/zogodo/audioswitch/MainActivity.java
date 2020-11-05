package me.zogodo.audioswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.button2) {
            resetAudio();
            myAudioMode();
            Toast.makeText(MainActivity.this, "btn2", Toast.LENGTH_SHORT).show();
        } else {
            resetAudio();
            Toast.makeText(MainActivity.this, "btn1", Toast.LENGTH_SHORT).show();
        }
    }

    void resetAudio() {
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.stopBluetoothSco();
        audioManager.setBluetoothScoOn(false);
        audioManager.setSpeakerphoneOn(false);
        audioManager.setWiredHeadsetOn(false);
    }

    void myAudioMode() {
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setWiredHeadsetOn(true);
    }
}
