package com.example.android.learn_spanish_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener OnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        mediaPlayer.pause();
                        //if our audiofocus will be b ack then user will hear from the begginning of the audio file
                        mediaPlayer.seekTo(0);

                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        releaseMediaPlayer();
                    }
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Red","rojo / roja",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("Green","verde",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("Brown","marrón",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("Gray","gris",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("Black","negro / negra",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("White","blanco / blanca",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("Pink","rosado / rosada",R.drawable.color_dusty_yellow,R.raw.color_pink));
        words.add(new Word("Yellow","amarillo / amarilla",R.drawable.color_mustard_yellow,R.raw.color_yellow));

        WordAdapter adapter = new WordAdapter(this, words,R.color.category_colors);

        ListView listView = findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);
                int result = audioManager.requestAudioFocus(OnAudioFocusChangeListener,
                        //Use music stream
                        AudioManager.STREAM_MUSIC,
                        //request focus for short period of time
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                );

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //release the mediaplayer if it currently exits because we are about to play a diffrent
                    // sound, Relase previoous resourses and start new mediaplayer object
                    releaseMediaPlayer();

                    mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceID());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });

        listView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            audioManager.abandonAudioFocus(OnAudioFocusChangeListener);
        }
    }
}