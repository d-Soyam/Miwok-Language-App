package com.example.android.miwok;

public class Word {

    private String mMiwokTranslation;
    private String mDefaultTranlation;
    private static final int NO_IMAGE_RESOURCE_PROVAIDED = -1;
    private  int mImageResourceId = NO_IMAGE_RESOURCE_PROVAIDED;
    private int mAudioResourceId;

    public Word(String mMiwokTranslation, String mDefaultTranlation, int mAudioResourceId) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranlation = mDefaultTranlation;
        this.mAudioResourceId = mAudioResourceId;
    }

    public Word(String mMiwokTranslation, String mDefaultTranlation, int mImageResourceId, int mAudioResourceId) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranlation = mDefaultTranlation;
        this.mImageResourceId = mImageResourceId;
        this.mAudioResourceId = mAudioResourceId;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getmDefaultTranlation() {
        return mDefaultTranlation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_RESOURCE_PROVAIDED;
    }


}

