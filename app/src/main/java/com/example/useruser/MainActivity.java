package com.example.useruser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view_result);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()//https://randomapi.com/api/dd8o7o76?key=NP6E-BXNO-84B3-47IE
                .baseUrl("https://randomapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RandomAPI randomAPI = retrofit.create(RandomAPI.class);

        Call<ApiResponse> call = randomAPI.getResults();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (!response.isSuccessful()) {
                    textView.setText("HTTP Code: " + response.code());
                    return;
                }
                List<Result> results = response.body().getResults();

                for (Result result : results) {

                    String content = "";
                    content += "Name: " + result.getName();
                    content += "\nStrength :" + result.getStrength();
                    content += "\nIntelligence: " + result.getIntelligence();
                    content += "\nHealth: " + result.getHealth() + "\n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                textView.setText("Hello" + t.getMessage());
            }
        });
    }
}