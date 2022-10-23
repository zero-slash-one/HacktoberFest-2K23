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

public class PhrasesActivity extends AppCompatActivity {
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

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Good evening!","¡Buenas noches! ",R.raw.phrases_good_evening));
        words.add(new Word("Good bye","Adiós",R.raw.phrases_good_bye));
        words.add(new Word("Thank you (very much)","(Muchas) Gracias",R.raw.phrases_thank_you));
        words.add(new Word("You're welcome","De nada",R.raw.phrases_yours_welcome));
        words.add(new Word("I'm sorry","Lo siento",R.raw.phrases_iam_sorry));
        words.add(new Word("How are you?","¿Cómo está usted? ",R.raw.phrases_how_are_you));
        words.add(new Word("What is your name?","¿Cómo se llama usted? ",R.raw.phrases_what_is_your_name));
        words.add(new Word("My name is...","Me llamo...  / Mi nombre es...",R.raw.phrases_my_name_is));
        words.add(new Word("Nice to meet you","Mucho gusto. / Encantado",R.raw.phrases_nice_to_meet_you));
        words.add(new Word("I love you","Te amo", R.raw.phrases_i_love_you));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

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

                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceID());
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