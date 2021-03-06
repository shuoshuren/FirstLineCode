package com.example.xiao.coolweather;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.xiao.coolweather.gson.Forecast;
import com.example.xiao.coolweather.gson.Weather;
import com.example.xiao.coolweather.service.AutoUpdateService;
import com.example.xiao.coolweather.util.HttpUtils;
import com.example.xiao.coolweather.util.LogUtils;
import com.example.xiao.coolweather.util.PreferencesUtil;
import com.example.xiao.coolweather.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ImageView bingPicImage;

    private DrawerLayout drawerLayout;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ScrollView weatherLayout;

    private TextView titleCity;
    private TextView titleUpdateTime;
    private Button titleNavBtn;

    private TextView degreeText;
    private TextView weatherInfoText;

    private LinearLayout forecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;

    private String weatherId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);

        initView();

        initEvent();

        getDataFromPrefs();


    }


    /**
     * 初始化控件
     */
    private void initView() {
        bingPicImage = (ImageView) findViewById(R.id.bing_pic_img);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        weatherLayout.setVisibility(View.GONE);

        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        titleNavBtn = (Button) findViewById(R.id.title_nav);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView) findViewById(R.id.aqi_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        comfortText = (TextView) findViewById(R.id.comfort_text);
        carWashText = (TextView) findViewById(R.id.car_wash_text);
        sportText = (TextView) findViewById(R.id.sport_text);
    }

    /**
     * 从SharedPreference中获取 缓存的天气数据和图片地址，如果没有缓存，则从服务器上获取
     */
    private void getDataFromPrefs() {


        String weatherString = PreferencesUtil.getWeatherInfo(this, null);
        if (weatherString != null) {
            //有缓存直接解析数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
            weatherId = weather.basic.weatherId;
            showWeatherInfo(weather);
        } else {
            weatherId = getIntent().getStringExtra("weather_id");
            requestWeather(weatherId);
        }

        /**
         * 获取图片地址
         */
        String bingPic = PreferencesUtil.getBingPicInfo(this, null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(bingPicImage);
        } else {
            loadBingPic();
        }
    }


    /**
     * 设置事件
     */
    private void initEvent() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.i("xc", "下拉刷新数据");
                requestWeather(weatherId);
            }
        });

        titleNavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    /**
     * 加载图片
     */
    private void loadBingPic() {

        String address = "http://guolin.tech/api/bing_pic";
        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                PreferencesUtil.setBingPicInfo(WeatherActivity.this, bingPic);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImage);
                    }
                });

            }
        });
    }


    /**
     * 根据天气id请求城市天气信息
     *
     * @param weatherId
     */
    private void requestWeather(String weatherId) {
        String address = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=d8d79a1a98064292bf626ba84caed267";
        HttpUtils.sendOkHttpRequest(address, new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            PreferencesUtil.setWeatherInfo(WeatherActivity.this, responseText);
                            showWeatherInfo(weather);

                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气失败", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }


        });

        loadBingPic();
    }

    /**
     * 处理并展示数据
     *
     * @param weather
     */
    private void showWeatherInfo(Weather weather) {
        if (weather != null && "ok".equals(weather.status)) {

            String cityName = weather.basic.cityName;
            String updateTime = weather.basic.update.updateTime.split(" ")[1];
            String degree = weather.now.temperature + "℃";
            String weatherInfo = weather.now.more.info;

            titleCity.setText(cityName);
            titleUpdateTime.setText(updateTime);
            degreeText.setText(degree);
            weatherInfoText.setText(weatherInfo);

            forecastLayout.removeAllViews();
            for (Forecast forecast : weather.forecastList) {
                View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
                TextView dateText = (TextView) view.findViewById(R.id.date_text);
                TextView infoText = (TextView) view.findViewById(R.id.info_text);
                TextView maxText = (TextView) view.findViewById(R.id.max_text);
                TextView minText = (TextView) view.findViewById(R.id.min_text);
                dateText.setText(forecast.date);
                infoText.setText(forecast.more.info);
                maxText.setText(forecast.temperature.max);
                minText.setText(forecast.temperature.min);
                forecastLayout.addView(view);
            }

            if (weather.aqi != null) {
                aqiText.setText(weather.aqi.city.aqi);
                pm25Text.setText(weather.aqi.city.pm25);
            }

            String comfort = "舒适度：" + weather.suggestion.comfort.info;
            String carWash = "洗车指数：" + weather.suggestion.carWash.info;
            String sport = "运动建议：" + weather.suggestion.sport.info;
            comfortText.setText(comfort);
            carWashText.setText(carWash);
            sportText.setText(sport);

            weatherLayout.setVisibility(View.VISIBLE);

            weatherId = weather.basic.weatherId;

            Intent intent = new Intent(WeatherActivity.this, AutoUpdateService.class);
            startService(intent);

        }

    }


    /**
     * 根据ChoseAreaFragment传入的值改变天气数据
     *
     * @param weatherId
     */
    public void changeAreaWeather(String weatherId) {
        drawerLayout.closeDrawer(GravityCompat.START);
        swipeRefreshLayout.setRefreshing(true);
        requestWeather(weatherId);
    }
}
