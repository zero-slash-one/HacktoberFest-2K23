package com.example.earthquake_finder;

public class Earthquake {
    private final double mMagnitude;
    private final String mLocation;
    private final long mTimeInMilliseconds;
    private String mUrl;

    public Earthquake(double Magnitude, String Location , long TimeInMilliseconds, String Url){
        mMagnitude = Magnitude;
        mLocation = Location;
        mTimeInMilliseconds = TimeInMilliseconds;
        mUrl = Url;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mLocation;
    }

    public long getTimeInMillisecond(){
        return mTimeInMilliseconds;
    }

    public String getUrl(){
        return mUrl;
    }

}
