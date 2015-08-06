package com.ssistudios.stormy.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.ssistudios.stormy.R;
import com.ssistudios.stormy.model.CurrentWeather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends Activity {

    double latitude = 37.8267;
    double longitude = -122.423;
    String API_KEY = "5ebdc2d8382733a947b84d1f9c0ec76c";
    String apiKey = "https://api.forecast.io/forecast/" + API_KEY + "/" + latitude + "," +longitude;
    Context context = this;
    CurrentWeather currentWeather;

    @Bind(R.id.time_label) TextView mTimeLabel;
    @Bind(R.id.temperature_value) TextView mTemperatureValue;
    @Bind(R.id.humidity_value) TextView mHumidityValue;
    @Bind(R.id.percip_value) TextView mPercepValue;
    @Bind(R.id.summary) TextView mSummary;
    @Bind(R.id.weather_icon) ImageView mWeatherIcon;
    @Bind(R.id.refresh_imageView) ImageView mRefreshImageView;
    @Bind(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRefreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForeCast();
            }
        });

        getForeCast();
    }

    private void getForeCast() {
        if(isNetworkAvailable()) {
            OkHttpClient httpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(apiKey)
                    .build();

            Call call = httpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e("Failure  ", e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefreshButton();
                        }
                    });
                }

                @Override
                public void onResponse(Response response) {

                    try {
                        if (response.isSuccessful()) {

                            currentWeather = getCurrentWeather(response.body().string());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showForecast(currentWeather);
                                    toggleRefreshButton();
                                }
                            });

                            //  ShowAlertDialogue();

                        }
                    } catch (IOException e) {

                    } catch (JSONException e) {

                    }


                }
            });
        }
        else
        {
            Toast.makeText(context, "Sorry, something went wrong, please try again later ", Toast.LENGTH_LONG).show();
        }
    }

    private void toggleRefreshButton() {
        if(mProgressBar.getVisibility() == View.VISIBLE){
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefreshImageView.setVisibility(View.VISIBLE);
        }

        else
        {
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        }
    }

    private void showForecast(CurrentWeather currentWeather) {
        mTimeLabel.setText("At "+ currentWeather.getFormattedTime() + " it will be");
        mTemperatureValue.setText(currentWeather.getTemprature() + "");
        mHumidityValue.setText(currentWeather.getHumidity() + "");
        mPercepValue.setText(currentWeather.getPercepProbability() + "");
        mSummary.setText(currentWeather.getSummary());

        Drawable drawable = getResources().getDrawable(currentWeather.getIconID());
        mWeatherIcon.setImageDrawable(drawable);

    }

    private CurrentWeather getCurrentWeather(String string) throws JSONException {

        CurrentWeather currentWeather = new CurrentWeather();
        JSONObject forecast = new JSONObject(string);
        String timeZone = forecast.getString("timezone");
        JSONObject currently = forecast.getJSONObject("currently");
        currentWeather.setTimezone(timeZone);
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setTemprature(currently.getDouble("temperature"));
        currentWeather.setApparentTemprature(currently.getInt("apparentTemperature"));
        currentWeather.setPercepValue(currently.getDouble("precipProbability"));
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setWindSpeed(currently.getDouble("windSpeed"));
        return currentWeather;
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        return  isAvailable;
    }


    private  void ShowAlertDialogue(){
        AlertDialogueFragment alertDialogueFragment = new AlertDialogueFragment();
        alertDialogueFragment.show(getFragmentManager(),"data_retrieved_dialogue");
    }


}
