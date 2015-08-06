package com.ssistudios.stormy.model;

import com.ssistudios.stormy.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by fc on 7/26/2015.
 */
public class CurrentWeather {

    private String mTimezone = null;

    private long mTime;
    private String mSummary;
    private String mIcon;
    private int mIconId = R.drawable.clear_day;
    private double mTemprature;
    private int mApparentTemprature;
    private double mHumidity;
    private double mWindSpeed;
    private double mPercepValue;

    public double getPercepValue() {
        return mPercepValue ;
    }

    public void setPercepValue(double percepValue) {
        mPercepValue = percepValue;
    }


    public int getPercepProbability(){
        return  (int) Math.round(getPercepValue() * 100);
    }


    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getTemprature() {
        return (int)Math.round(mTemprature);
    }

    public void setTemprature(double temprature) {
        mTemprature = temprature;
    }

    public int getApparentTemprature() {
        return mApparentTemprature;
    }

    public void setApparentTemprature(int apparentTemprature) {
        mApparentTemprature = apparentTemprature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        mWindSpeed = windSpeed;
    }

    public String getFormattedTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimezone()));

        Date dateTime = new Date(getTime()*1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }


    public String getFormattedDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimezone()));

        Date date = new Date(getTime()*1000);
        String dateString = formatter.format(date);
        return dateString;
    }


    public int getIconID() {
        if (mIcon.equals("clear-day")) {
            mIconId = R.drawable.clear_day;
        }
        else if (mIcon.equals("clear-night")) {
            mIconId = R.drawable.clear_night;
        }
        else if (mIcon.equals("rain")) {
            mIconId = R.drawable.rain;
        }
        else if (mIcon.equals("snow")) {
            mIconId = R.drawable.snow;
        }
        else if (mIcon.equals("sleet")) {
            mIconId = R.drawable.sleet;
        }
        else if (mIcon.equals("wind")) {
            mIconId = R.drawable.wind;
        }
        else if (mIcon.equals("fog")) {
            mIconId = R.drawable.fog;
        }
        else if (mIcon.equals("cloudy")) {
            mIconId = R.drawable.cloudy;
        }
        else if (mIcon.equals("partly-cloudy-day")) {
            mIconId = R.drawable.partly_cloudy;
        }
        else if (mIcon.equals("partly-cloudy-night")) {
            mIconId = R.drawable.cloudy_night;
        }

        return  mIconId;
    }

}
