package com.example.android.miwok;

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceId = NO_IMAGE;

    private static final int NO_IMAGE = -1;

    public Word(String miwokTranslation, String defaultTranslation) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
    }

    public Word(String miwokTranslation, String defaultTranslation, int imageResourceId) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageResourceId = imageResourceId;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImageResourceId() { return  mImageResourceId; }

    public boolean hasImage() {
        if (mImageResourceId == NO_IMAGE) {
            return false;
        }
        return true;
    }
}
