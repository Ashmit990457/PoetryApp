package com.example.poetryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.poetryapp.APIs.ApiInterface;
import com.example.poetryapp.APIs.RetrofitInstance;
import com.example.poetryapp.Response.addresponse;
import com.example.poetryapp.databinding.ActivityUpdatePoetryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdatePoetry extends AppCompatActivity {
ActivityUpdatePoetryBinding binding;

ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityUpdatePoetryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

String id= getIntent().getStringExtra("id");
String poet= getIntent().getStringExtra("poet");
String poetry= getIntent().getStringExtra("poetry");
 binding.Poetry.setText(poetry);
 binding.poetname.setText(poet);
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UpdatePoetry.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        binding.save.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
         apiInterface=retrofit.create(ApiInterface.class);
         apiInterface.updatepoetry(binding.Poetry.getText().toString(),id).enqueue(new Callback<addresponse>() {
             @Override
             public void onResponse(Call<addresponse> call, Response<addresponse> response) {
                 try {
                     if (response.body().getStatus().equals("1")) {
                         Toast.makeText(UpdatePoetry.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                     }
                 } catch (Exception e) {
                     Log.d("Execption", e.getLocalizedMessage());
                 }
             }

             @Override
             public void onFailure(Call<addresponse> call, Throwable t) {
                 Log.d("failure", t.getLocalizedMessage());
             }
         });
     }
 });


    }
}