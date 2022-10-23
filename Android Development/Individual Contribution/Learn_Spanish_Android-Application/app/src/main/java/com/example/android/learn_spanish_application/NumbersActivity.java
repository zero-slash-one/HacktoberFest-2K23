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

public class NumbersActivity extends AppCompatActivity {

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
        words.add(new Word("One", "uno", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("Two", "dos", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("Three", "tres", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("Four", "cuatro", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("Five", "cinco", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("Six", "seis", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("Seven", "siete", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("Eight", "ocho", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("Nine", "nueve'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("Ten", "diez", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

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
                    //we have audio focus now to play

                    //release the mediaplayer if it currently exits because we are about to play a diffrent
                    // sound, Relase previous resourses and start new mediaplayer object
                    releaseMediaPlayer();

                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceID());
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

    private void releaseMediaPlayer() {
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