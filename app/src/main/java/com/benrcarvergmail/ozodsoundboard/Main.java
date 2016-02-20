package com.benrcarvergmail.ozodsoundboard;

import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.benrcarvergmail.ozodsoundboard.R;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends AppCompatActivity {

    // MediaPlayer for playing the sounds
    protected MediaPlayer mPlayer;

    // SoundPool implementation to allow for overlapping audio
    protected SoundPool sPool;
    // ArrayList for storing streamIDs returned by sPool's play() method
    ArrayList<Integer> streamIDs = new ArrayList<Integer>();

    // Used to determine whether or not to call killSounds()
    protected int lastID;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the MediaPlayer object
        mPlayer = new MediaPlayer();

        // Instantiate the SoundPool object...
        // If we're on Android 5.0 or above, we must use SoundPool.Builder()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder sPoolLolli = new SoundPool.Builder(); // Temp SoundPool.Builder object for Android Lollipop and higher
            sPoolLolli.setMaxStreams(25); // Set the maximum number of audio streams to 25
            // Set the AudoAttributes using AudioAttributes.Builder()
            sPoolLolli.setAudioAttributes(new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA) // This is for media (akin to STREAM_MUSIC)
                    .build()); // Build the AudioAttributes object
            sPool = sPoolLolli.build(); // Use SoundPool.Builder to build the SoundPool object
        } else {
            // SoundPool(maxStreams, streamType, srcQuality)
            // maxStreams is the max number of audio streams
            // streamType is the audio stream type as described in AudioManager
            // '3' is the constant for STREAM_MUSIC, which is applicable here.
            // srcQuality is the sample-rate coverter quality. There is no effect currently. 0 is default.
            sPool = new SoundPool(25, 3, 0);
        }

        // Create an object for each button. We determine which button we're
        // creating an object for by grabbing the button's by their android:id
        // which was defined in content_main.xml
        Button button1 = (Button)this.findViewById(R.id.button1); // stop sounds button
        Button button2 = (Button)this.findViewById(R.id.button2); // h3h3 meme reference button
        Button button3 = (Button)this.findViewById(R.id.button3); // weird moaning sounds button
        Button button4 = (Button)this.findViewById(R.id.button4); // you fucking inbred
        Button button5 = (Button)this.findViewById(R.id.button5); // fuuuuuuck button
        Button button6 = (Button)this.findViewById(R.id.button6); // john cena
        Button button7 = (Button)this.findViewById(R.id.button7); // come on, dad
        Button button8 = (Button)this.findViewById(R.id.button8); // rick astley
        Button button9 = (Button)this.findViewById(R.id.button9); // meme spaghetti (pasta)
        Button button10 = (Button)this.findViewById(R.id.button10); // dude listen
        Button button11 = (Button)this.findViewById(R.id.button11); // nice memes
        Button button12 = (Button)this.findViewById(R.id.button12); // piece of shit
        Button button13 = (Button)this.findViewById(R.id.button13); // here come the memes
        Button button14 = (Button)this.findViewById(R.id.button14); // you piece of rat
        Button button15 = (Button)this.findViewById(R.id.button15); // put two up your ass
        Button button16 = (Button)this.findViewById(R.id.button16); // hello
        Button button17 = (Button)this.findViewById(R.id.button17); // yes
        Button button18 = (Button)this.findViewById(R.id.button18); // no
        Button button19 = (Button)this.findViewById(R.id.button19); // bye bye
        Button button20 = (Button)this.findViewById(R.id.button20); // what the fuck
        Button button21 = (Button)this.findViewById(R.id.button21); // you rat bastard

        // Create an object for the checkbox that allows or disallows audio to overlap
        final CheckBox overlapAudioCheckBox = (CheckBox) findViewById(R.id.overlapCheckBox);

        // Click-listener for the first (stop sounds)
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               killSounds();
            }
        });

        // Click-listener for the second (h3h3 meme reference) button
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.h3h3_reference);
                } else {
                    playSoundMediaPlayer(R.raw.h3h3_reference);
                }

            }
        });

        // Click-listener for the third (weird moaning sounds) button
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.weird_moans);
                } else {
                    playSoundMediaPlayer(R.raw.weird_moans);
                }
            }
        });

        // Click-listener for the forth (you fucking inbred) button
        button4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.you_fucking_inbred);
                } else {
                    playSoundMediaPlayer(R.raw.you_fucking_inbred);
                }
            }
        });

        // Click-listener for the fifth (fuuuuuuck) button
        button5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.fuck);
                } else {
                    playSoundMediaPlayer(R.raw.fuck);
                }
            }
        });

        // Click-listener for the sixth (john cena) button
        button6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.john_cena);
                } else {
                    playSoundMediaPlayer(R.raw.john_cena);
                }
                LinearLayout rootLayout = (LinearLayout)findViewById(R.id.root_layout);
                rootLayout.setBackgroundResource(R.drawable.background_john_cena);
            }
        });

        // Click-listener for the seventh (come on, dad) button
        button7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.come_on_dad);
                } else {
                    playSoundMediaPlayer(R.raw.come_on_dad);
                }
            }
        });

        // Click-listener for the eigth (rick astley) button
        button8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.never_gonna_give_you_up);
                } else {
                    playSoundMediaPlayer(R.raw.never_gonna_give_you_up);
                }
                LinearLayout rootLayout = (LinearLayout)findViewById(R.id.root_layout);
                rootLayout.setBackgroundResource(R.drawable.background_rick_astley);
            }
        });

        // Click-listener for the ninth (meme spaghetti/pasta) button
        button9.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.memes_spaghetti);
                } else {
                    playSoundMediaPlayer(R.raw.memes_spaghetti);
                }
            }
        });

        // Click-listener for the tenth (dude listen) button
        button10.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.dude_listen);
                } else {
                    playSoundMediaPlayer(R.raw.dude_listen);
                }
            }
        });

        // Click-listener for the eleventh (nice memes) button
        button11.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.nice_meme);
                } else {
                    playSoundMediaPlayer(R.raw.nice_meme);
                }
            }
        });

        // Click-listener for the twelfth (piece of shit) button
        button12.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.piece_of_shit);
                } else {
                    playSoundMediaPlayer(R.raw.piece_of_shit);
                }
            }
        });

        // Click-listener for the thirteenth (here come the memes) button
        button13.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.here_comes_my_memes);
                } else {
                    playSoundMediaPlayer(R.raw.here_comes_my_memes);
                }
            }
        });

        // Click-listener for the fourteenth (piece of rat) button
        button14.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.piece_of_rat);
                } else {
                    playSoundMediaPlayer(R.raw.piece_of_rat);
                }
            }
        });

        // Click-listener for the fifteenth (put two up your ass) button
        button15.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.put_two_up_your_ass);
                } else {
                    playSoundMediaPlayer(R.raw.put_two_up_your_ass);
                }
            }
        });

        // Click-listener for the sixteenth (hello) button
        button16.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.hello);
                } else {
                    playSoundMediaPlayer(R.raw.hello);
                }
            }
        });

        // Click-listener for the seventeenth (yes) button
        button17.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.yes);
                } else {
                    playSoundMediaPlayer(R.raw.yes);
                }
            }
        });

        // Click-listener for the eighteenth (no) button
        button18.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.no);
                } else {
                    playSoundMediaPlayer(R.raw.no);
                }
            }
        });

        // Click-listener for the nineteenth (bye mofo) button
        button19.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.bye_motherfucker);
                } else {
                    playSoundMediaPlayer(R.raw.bye_motherfucker);
                }
            }
        });

        // Click-listener for the twentieth (what the fuck) button
        button20.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.what_the_fuck);
                } else {
                    playSoundMediaPlayer(R.raw.what_the_fuck);
                }
            }
        });

        // Click-listener for the ninth (you rat bastard) button
        button21.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(overlapAudioCheckBox.isChecked()) {
                    playSoundWithSoundPool(R.raw.rat_bastard);
                } else {
                    playSoundMediaPlayer(R.raw.rat_bastard);
                }
            }
        });
    }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // All R.id.___ are just ints, so you can pass them as ints
    // Plays a sound with the MediaPlayer (no sound overlapping)
    private void playSoundMediaPlayer(int R_id) {
        // Change the background back to default if the user plays a sound that isn't John Cena
        if(R_id != R.raw.john_cena) {
            // Change the background back to default if the user plays a sound that isn't Rick Astley
            if(R_id != R.raw.never_gonna_give_you_up) {
                LinearLayout rootLayout = (LinearLayout) this.findViewById(R.id.root_layout);
                rootLayout.setBackgroundResource(R.drawable.background_default);
            }
        }

        // If the user pressed a different button than last time
        // stop all the sounds from playing and play the new sound.
        // If the user pressed the same button, the player already has the sound
        // loaded and is ready to go.
        if(lastID != R_id) {
            // Stop all sounds, release mPlayer, then recreate it with new sound.
            killSounds();
            mPlayer = MediaPlayer.create(this, R_id);
            lastID = R_id; // Update lastID

            // Ensure that the sound is loaded before attempting to play it
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    mPlayer.start();
                }
            });
        } else {
            mPlayer.stop(); // Stop the sounds
            try {
                mPlayer.prepare(); // Prepare the player to play again
            } catch (IOException e) {
                e.printStackTrace();
            }
            mPlayer.start(); // Play sounds
        }


    }

    // All R.id.___ are just ints, so you can pass them as ints
    // Plays a sound with the SoundPool object (yes sound overlapping)
    private void playSoundWithSoundPool(int R_id) {
        // Change the background back to default if the user plays a sound that isn't John Cena
        if(R_id != R.raw.john_cena) {
            // Change the background back to default if the user plays a sound that isn't Rick Astley
            if(R_id != R.raw.never_gonna_give_you_up) {
                LinearLayout rootLayout = (LinearLayout) this.findViewById(R.id.root_layout);
                rootLayout.setBackgroundResource(R.drawable.background_default);
            }
        }

        // load(context, resId, priority)
        // Application context, resource ID, priority of the sound.
        // Currently, priority has no effect but you are to use '1' for future compatibility.
        final int soundID = sPool.load(this, R_id, 1);
            // We have to ensure that the sound is properly loaded before we try to play it.
            // If we try to play a sound before it's loaded, we get a "sample not ready" error.
            sPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    // Play the sound and add the returned streamID to the streamIDs ArrayList
                    streamIDs.add(sPool.play(soundID, 1, 1, 1, 0, 1));
                }
            });
        // Method: play (int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        // soundID is a soundID returned by the load() function
        // leftVolume is the left volume from range 0.0 to 1.0
        // rightVolume is the right volume from range 0.0 to 1.0
        // priority is the stream's priority (with 0 being the lowest)
        // loop is loop mode (0 = no loop, -1 = loop forever)
        // rate is playback rate (1.0 = normal, range 0.5 - 2.0)
    }

    // Stops all sounds from playing (for mPlayer and sPool)
    private void killSounds() {

        // The user could cause killSounds() to be called during a window where
        // mPlayer is null. If this happens, a NullPointerException is thrown, so I only
        // check to see if mPlayer is playing if mPlayer isn't nulls
        if(mPlayer != null) {
            if(mPlayer.isPlaying()) {
                mPlayer.stop();
            }
        } else {
            Log.i(TAG, "mPlayer was null during killSounds()");
        }

        // The user could cause killSounds() to be called during a window where
        // sPool is null. If this happens, a NullPointerException is thrown, so I only
        // 'dot' sPool if it isn't null
        if(sPool != null) {
            sPool.autoPause(); // Pause all sound streams
            // Go through the list of stored streamIDs and stop each one (some may be done already).
            for(int i = 0; i < streamIDs.size(); i++) {
                sPool.stop(i);
            }
            // Clear the list since we stopped all the streams.
            streamIDs.clear();
        } else {
            Log.i(TAG, "sPool was null during killSounds()");
        }

        // Reset the background
        LinearLayout rootLayout = (LinearLayout)this.findViewById(R.id.root_layout);
        rootLayout.setBackgroundResource(R.drawable.background_default);
    }

    // Releases mPlayer's resources and sets its reference to null
    private void resetMediaPlayer() {
        Log.i(TAG, "mPlayer reset.");
        mPlayer.release();
        mPlayer = null;
    }

    // Releases sPool's resources and sets its reference to null
    private void resetSoundPool() {
        Log.i(TAG, "sPool reset.");
        sPool.release();
        sPool = null;
    }

    // This method is called when this activity loses focus (but is not destroyed)
    // This method is the counterpart to onStop()
    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "onPause() called");

        // Ensure I don't 'dot' a null reference!
        if(mPlayer != null) {
            resetMediaPlayer();
        }

        // Ensure I don't 'dot' a null reference!
        if(sPool != null) {
            resetSoundPool();
        }
    }

    // This method is called when this activity loses focus AND IS DESTROYED
    // This method is the counterpart to onPause()
    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "onStop() called");

        // Ensure I don't 'dot' a null reference
        if(mPlayer != null) {
            resetMediaPlayer();
        }

        // Ensure I don't 'dot' a null reference
        if(sPool != null) {
            resetSoundPool();
        }
    }

    // This method is called when the activity resumes focus when it was NOT destroyed
    @Override
    protected void onResume() {
        super.onResume();

        // If sPool is null, re-instantiate it
        if(sPool == null) {
            // Re-instantiate the SoundPool object...
            // If we're on Android 5.0 or above, we must use SoundPool.Builder()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                SoundPool.Builder sPoolLolli = new SoundPool.Builder(); // Temp SoundPool.Builder object for Android Lollipop and higher
                sPoolLolli.setMaxStreams(25); // Set the maximum number of audio streams to 25
                // Set the AudoAttributes using AudioAttributes.Builder()
                sPoolLolli.setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA) // This is for media (akin to STREAM_MUSIC)
                        .build()); // Build the AudioAttributes object
                sPool = sPoolLolli.build(); // Use SoundPool.Builder to build the SoundPool object
            } else {
                // SoundPool(maxStreams, streamType, srcQuality)
                // maxStreams is the max number of audio streams
                // streamType is the audio stream type as described in AudioManager
                // '3' is the constant for STREAM_MUSIC, which is applicable here.
                // srcQuality is the sample-rate coverter quality. There is no effect currently. 0 is default.
                sPool = new SoundPool(25, 3, 0);
            }

            Log.i(TAG, "sPool re-instantiated during onPause().");
        }

        // If mPlayer is null, re-instantiate it
        if(mPlayer == null) {
            mPlayer  = new MediaPlayer();
            Log.i(TAG, "mPlayer re-instantiated during onPause().");
        }

        Log.i(TAG, "onResume() called");
    }

    // This method is called when the activity resumes focus when it WAS DESTROYED
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() called");

        // If sPool is null, re-instantiate it
        if(sPool == null) {
            // Re-instantiate the SoundPool object...
            // If we're on Android 5.0 or above, we must use SoundPool.Builder()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                SoundPool.Builder sPoolLolli = new SoundPool.Builder(); // Temp SoundPool.Builder object for Android Lollipop and higher
                sPoolLolli.setMaxStreams(25); // Set the maximum number of audio streams to 25
                // Set the AudoAttributes using AudioAttributes.Builder()
                sPoolLolli.setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA) // This is for media (akin to STREAM_MUSIC)
                        .build()); // Build the AudioAttributes object
                sPool = sPoolLolli.build(); // Use SoundPool.Builder to build the SoundPool object
            } else {
                // SoundPool(maxStreams, streamType, srcQuality)
                // maxStreams is the max number of audio streams
                // streamType is the audio stream type as described in AudioManager
                // '3' is the constant for STREAM_MUSIC, which is applicable here.
                // srcQuality is the sample-rate coverter quality. There is no effect currently. 0 is default.
                sPool = new SoundPool(25, 3, 0);
            }

            Log.i(TAG, "sPool re-instantiated during onRestart().");
        }

        // If mPlayer is null, re-instantiate it
        if(mPlayer == null) {
            mPlayer  = new MediaPlayer();
            Log.i(TAG, "mPlayer re-instantiated during onRestart().");
        }
    }

}
