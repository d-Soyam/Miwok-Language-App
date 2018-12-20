package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class PhrasesFragment extends Fragment {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    public PhrasesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("minto wuksus", "Where are you going?", R.raw.phrase_where_are_you_going));
        words.add(new Word("tinnә oyaase'nә", "What is your name?", R.raw.phrase_what_is_your_name));
        words.add(new Word("oyaaset...", "My name is...", R.raw.phrase_my_name_is));
        words.add(new Word("michәksәs?", "How are you feeling?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("kuchi achit", "I’m feeling good.", R.raw.phrase_im_feeling_good));
        words.add(new Word("әәnәs'aa?", "Are you coming?", R.raw.phrase_are_you_coming));
        words.add(new Word("hәә’ әәnәm", "Yes, I’m coming.", R.raw.phrase_yes_im_coming));
        words.add(new Word("әәnәm","I’m coming.", R.raw.phrase_im_coming));
        words.add(new Word("yoowutis", "Let’s go.", R.raw.phrase_lets_go));
        words.add(new Word("әnni'nem", "Come here.", R.raw.phrase_come_here));


        WordAdapter itemAdapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listView = (ListView)rootView.findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
//                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResourceId());
//
//                mediaPlayer.start();
//                mediaPlayer.setOnCompletionListener(mCompletionListener);
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());

                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);

                }
            }
        });
        return rootView;

    }

    private void releaseMediaPlayer(){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
