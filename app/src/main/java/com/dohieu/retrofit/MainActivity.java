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
    JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
        imgAvatar = findViewById(R.id.imgAvatar);
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://reqres.in/")
                .baseUrl("http://dummy.restapiexample.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //getUser();
        getEmployee();


    }

    private void getEmployee() {
                Call<List<Employee>> call = jsonPlaceHolderApi.getmployee();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
            if (!response.isSuccessful()){
                textViewResult.setText("Code: "+response.code());
                return;
            }

            List<Employee> posts = response.body();

            for (Employee  post : posts){
                String conten="";
                conten += "ID: "+post.getId()+"\n";
                conten += "User ID: "+post.getEmployeeAge()+"\n";
                conten += "Title: "+post.getEmployeeSalary()+"\n";
                conten += "Text: "+post.getEmployeeSalary()+"\n\n";
                textViewResult.append(conten);
                Glide
                        .with(MainActivity.this)
                        .load(post.getProfileImage())
                        .centerCrop()

                        .into(imgAvatar);
            }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

            }


        });
    }

    private void getUser() {
        Call<User> call = jsonPlaceHolderApi.getUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                User user = response.body();
                List<Datum> posts = user.getData();

                for (Datum post : posts) {
                    String conten = "";
                    conten += "ID: " + post.getId() + "\n";
                    conten += "User ID: " + post.getAvatar() + "\n";
                    conten += "Title: " + post.getEmail() + "\n";
                    conten += "Text: " + post.getFirstName() + "\n\n";
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
