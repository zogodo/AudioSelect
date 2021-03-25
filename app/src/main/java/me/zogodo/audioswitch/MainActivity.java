package me.zogodo.audioswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    public enum AudioRouteMode {
        WIRED_HEADPHONE,
        SPEAKER,
        USB_AUDIO,
        BLUETOOTH_A2DP,
        NO_ROUTING
    }

    public static final int DEVICE_STATE_UNAVAILABLE = 0;
    public static final int DEVICE_STATE_AVAILABLE = 1;

    public static final int DEVICE_OUT_EARPIECE = 0x1;
    public static final int DEVICE_OUT_SPEAKER = 0x2;
    public static final int DEVICE_OUT_WIRED_HEADSET = 0x4;
    public static final int DEVICE_OUT_WIRED_HEADPHONE = 0x8;
    public static final int DEVICE_OUT_BLUETOOTH_SCO = 0x10;
    public static final int DEVICE_OUT_BLUETOOTH_SCO_HEADSET = 0x20;
    public static final int DEVICE_OUT_BLUETOOTH_SCO_CARKIT = 0x40;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP = 0x80;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES = 0x100;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER = 0x200;
    public static final int DEVICE_OUT_AUX_DIGITAL = 0x400;
    public static final int DEVICE_OUT_ANLG_DOCK_HEADSET = 0x800;
    public static final int DEVICE_OUT_DGTL_DOCK_HEADSET = 0x1000;
    public static final int DEVICE_OUT_USB_ACCESSORY = 0x2000;
    public static final int DEVICE_OUT_USB_DEVICE = 0x4000;
    public static final int DEVICE_OUT_REMOTE_SUBMIX = 0x8000;

    public static final int FORCE_NONE = 0;
    public static final int FORCE_SPEAKER = 1;
    public static final int FORCE_HEADPHONES = 2;
    public static final int FORCE_BT_SCO = 3;
    public static final int FORCE_BT_A2DP = 4;
    public static final int FORCE_WIRED_ACCESSORY = 5;
    public static final int FORCE_BT_CAR_DOCK = 6;
    public static final int FORCE_BT_DESK_DOCK = 7;
    public static final int FORCE_ANALOG_DOCK = 8;
    public static final int FORCE_DIGITAL_DOCK = 9;
    public static final int FORCE_NO_BT_A2DP = 10;
    public static final int FORCE_DEFAULT = FORCE_NONE;

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        //setStreamVolume(AudioDeviceInfo.TYPE_WIRED_HEADPHONES);

        Class<?> audioSystem = null;
        try {
            audioSystem = Class.forName("android.media.AudioSystem");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Method method = audioSystem.getMethod("setForceUse", Integer.TYPE, Integer.TYPE);
            int result = (Integer) method.invoke(null, DEVICE_OUT_SPEAKER, DEVICE_STATE_AVAILABLE);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onClick(View v) {
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if (v.getId() == R.id.button1) {
            enableSpeaker();
            Toast.makeText(MainActivity.this, "Force Speaker", Toast.LENGTH_SHORT).show();
        } else {
            myAudioMode();
            Toast.makeText(MainActivity.this, "My Mode", Toast.LENGTH_SHORT).show();
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
        resetAudio();
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setWiredHeadsetOn(true);
    }

    private void enableSpeaker() {
        resetAudio();
        audioManager.setSpeakerphoneOn(true);
        audioManager.setMicrophoneMute(false);
    }
}
