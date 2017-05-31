package com.cz.iot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.contrib.driver.button.Button;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * The GPIO pin to activate to listen for button presses.
     */
    private final String BUTTON_GPIO_PIN_5 = "BCM5";
    private final String BUTTON_GPIO_PIN_6 = "BCM6";
    private final String BUTTON_GPIO_PIN_12 = "BCM12";
    private final String BUTTON_GPIO_PIN_13 = "BCM13";
    private final String BUTTON_GPIO_PIN_16 = "BCM16";
    private final String BUTTON_GPIO_PIN_19 = "BCM19";
    private final String BUTTON_GPIO_PIN_20 = "BCM20";
    private final String BUTTON_GPIO_PIN_21 = "BCM21";
    private final String BUTTON_GPIO_PIN_26 = "BCM26";

    private Button mButtonPin5;
    private Button mButtonPin6;
    private Button mButtonPin12;
    private Button mButtonPin13;
    private Button mButtonPin16;
    private Button mButtonPin19;
    private Button mButtonPin20;
    private Button mButtonPin21;
    private Button mButtonPin26;

    private FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGPIOButton();
        mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference log = mDatabase.getReference("logs").push();
        log.child("test").setValue("test");
    }

    private void setGPIOButton(){
        try {
            mButtonPin5 = new Button(BUTTON_GPIO_PIN_5, Button.LogicState.PRESSED_WHEN_LOW);
            mButtonPin5.setOnButtonEventListener(mButtonCallbackPin5);

            mButtonPin6 = new Button(BUTTON_GPIO_PIN_6, Button.LogicState.PRESSED_WHEN_LOW);
            mButtonPin6.setOnButtonEventListener(mButtonCallbackPin6);

            mButtonPin12 = new Button(BUTTON_GPIO_PIN_12, Button.LogicState.PRESSED_WHEN_LOW);
            mButtonPin12.setOnButtonEventListener(mButtonCallbackPin12);

            mButtonPin13 = new Button(BUTTON_GPIO_PIN_13, Button.LogicState.PRESSED_WHEN_LOW);
            mButtonPin13.setOnButtonEventListener(mButtonCallbackPin13);

            mButtonPin16 = new Button(BUTTON_GPIO_PIN_16, Button.LogicState.PRESSED_WHEN_LOW);
            mButtonPin16.setOnButtonEventListener(mButtonCallbackPin16);

            mButtonPin19 = new Button(BUTTON_GPIO_PIN_19, Button.LogicState.PRESSED_WHEN_LOW);
            mButtonPin19.setOnButtonEventListener(mButtonCallbackPin19);

            mButtonPin20 = new Button(BUTTON_GPIO_PIN_20, Button.LogicState.PRESSED_WHEN_LOW);
            mButtonPin20.setOnButtonEventListener(mButtonCallbackPin20);

            mButtonPin21 = new Button(BUTTON_GPIO_PIN_21, Button.LogicState.PRESSED_WHEN_LOW);
            mButtonPin21.setOnButtonEventListener(mButtonCallbackPin21);

            mButtonPin26 = new Button(BUTTON_GPIO_PIN_26, Button.LogicState.PRESSED_WHEN_LOW);
            mButtonPin26.setOnButtonEventListener(mButtonCallbackPin26);

        }catch (IOException ex){
            Log.e(TAG, "Error on find button");
        }
    }

    /**
     * Callback for button events.
     */
    private Button.OnButtonEventListener mButtonCallbackPin5 = new Button.OnButtonEventListener() {
        @Override

        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                final DatabaseReference log = mDatabase.getReference("GPIO Button 1").push();
                // upload data to firebase
                log.child("timestamp").setValue(ServerValue.TIMESTAMP);
                log.child("pin value").setValue("BCM5");
                Log.d(TAG, "button pressed");
            }
        }
    };

    private Button.OnButtonEventListener mButtonCallbackPin6 = new Button.OnButtonEventListener() {
        @Override

        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                final DatabaseReference log = mDatabase.getReference("GPIO Button 2").push();
                // upload data to firebase
                log.child("timestamp").setValue(ServerValue.TIMESTAMP);
                log.child("pin value").setValue("BCM6");
                Log.d(TAG, "button pressed");
            }
        }
    };

    private Button.OnButtonEventListener mButtonCallbackPin12 = new Button.OnButtonEventListener() {
        @Override

        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                final DatabaseReference log = mDatabase.getReference("GPIO Button 3").push();
                // upload data to firebase
                log.child("timestamp").setValue(ServerValue.TIMESTAMP);
                log.child("pin value").setValue("BCM12");
                Log.d(TAG, "button pressed");
            }
        }
    };

    private Button.OnButtonEventListener mButtonCallbackPin13 = new Button.OnButtonEventListener() {
        @Override

        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                final DatabaseReference log = mDatabase.getReference("GPIO Button 4").push();
                // upload data to firebase
                log.child("timestamp").setValue(ServerValue.TIMESTAMP);
                log.child("pin value").setValue("BCM13");
                Log.d(TAG, "button pressed");
            }
        }
    };

    private Button.OnButtonEventListener mButtonCallbackPin16 = new Button.OnButtonEventListener() {
        @Override

        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                final DatabaseReference log = mDatabase.getReference("GPIO Button 5").push();
                // upload data to firebase
                log.child("timestamp").setValue(ServerValue.TIMESTAMP);
                log.child("pin value").setValue("BCM16");
                Log.d(TAG, "button pressed");
            }
        }
    };

    private Button.OnButtonEventListener mButtonCallbackPin19 = new Button.OnButtonEventListener() {
        @Override

        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                final DatabaseReference log = mDatabase.getReference("GPIO Button 6").push();
                // upload data to firebase
                log.child("timestamp").setValue(ServerValue.TIMESTAMP);
                log.child("pin value").setValue("BCM19");
                Log.d(TAG, "button pressed");
            }
        }
    };

    private Button.OnButtonEventListener mButtonCallbackPin20 = new Button.OnButtonEventListener() {
        @Override

        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                final DatabaseReference log = mDatabase.getReference("GPIO Button 7").push();
                // upload data to firebase
                log.child("timestamp").setValue(ServerValue.TIMESTAMP);
                log.child("pin value").setValue("BCM20");
                Log.d(TAG, "button pressed");
            }
        }
    };

    private Button.OnButtonEventListener mButtonCallbackPin21 = new Button.OnButtonEventListener() {
        @Override

        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                final DatabaseReference log = mDatabase.getReference("GPIO Button 8").push();
                // upload data to firebase
                log.child("timestamp").setValue(ServerValue.TIMESTAMP);
                log.child("pin value").setValue("BCM21");
                Log.d(TAG, "button pressed");
            }
        }
    };

    private Button.OnButtonEventListener mButtonCallbackPin26 = new Button.OnButtonEventListener() {
        @Override

        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                final DatabaseReference log = mDatabase.getReference("GPIO Button 9").push();
                // upload data to firebase
                log.child("timestamp").setValue(ServerValue.TIMESTAMP);
                log.child("pin value").setValue("BCM26");
                Log.d(TAG, "button pressed");
            }
        }
    };


}
