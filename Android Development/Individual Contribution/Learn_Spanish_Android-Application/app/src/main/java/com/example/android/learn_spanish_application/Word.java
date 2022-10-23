package com.example.android.learn_spanish_application;

public class Word {
    private final String mEnglishTranslation;
    private final String mSpanishTranslation;
    private final int mAudioResourceID;
    private int mImageResourceID = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String EnglishTranslation,String SpanishTranslation,int AudioR){
        mEnglishTranslation = EnglishTranslation;
        mSpanishTranslation = SpanishTranslation;
        mAudioResourceID = AudioR;
    }

    public Word(String EnglishTranslation,String SpanishTranslation,int Resource,int AudiR){
        mEnglishTranslation = EnglishTranslation;
        mSpanishTranslation = SpanishTranslation;
        mImageResourceID = Resource;
        mAudioResourceID = AudiR;
    }

    public String getEnglishTranslation(){
        return mEnglishTranslation;
    }

    public String getSpanishTranslation(){
        return mSpanishTranslation;
    }

    public int getAudioResourceID(){
        return mAudioResourceID;
    }

    public int getImageResourceID(){ return mImageResourceID; }

    public boolean hasImage(){
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }
}

