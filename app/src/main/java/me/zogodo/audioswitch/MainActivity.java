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
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
            audioManager.setWiredHeadsetOn(true);
            Toast.makeText(MainActivity.this, "My Mode", Toast.LENGTH_SHORT).show();
        } else {
            audioManager.setMode(AudioManager.MODE_NORMAL);
            audioManager.setWiredHeadsetOn(false);
            audioManager.setSpeakerphoneOn(true);
            Toast.makeText(MainActivity.this, "Reset OK", Toast.LENGTH_SHORT).show();
        }
    }

    void resetAudio() {
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(false);
        audioManager.setWiredHeadsetOn(false);
    }

    void myAudioMode() {
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setWiredHeadsetOn(true);
    }
}
