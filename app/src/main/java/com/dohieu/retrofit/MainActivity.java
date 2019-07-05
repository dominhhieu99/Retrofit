package com.dohieu.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private TextView textViewResult;
private ImageView imgAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        imgAvatar = findViewById(R.id.imgAvatar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<User> call = jsonPlaceHolderApi.getUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
            if (!response.isSuccessful()){
                textViewResult.setText("Code: "+response.code());
                return;
            }

            List<Datum> posts = response.body().getData();

            for (Datum  post : posts){
                String conten="";
                conten += "ID: "+post.getId()+"\n";
                conten += "User ID: "+post.getAvatar()+"\n";
                conten += "Title: "+post.getEmail()+"\n";
                conten += "Text: "+post.getFirstName()+"\n\n";
                textViewResult.append(conten);
                Glide
                        .with(MainActivity.this)
                        .load(post.getAvatar())
                        .centerCrop()

                        .into(imgAvatar);
            }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }
}
