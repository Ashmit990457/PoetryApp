package com.example.poetryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.poetryapp.APIs.ApiInterface;
import com.example.poetryapp.APIs.RetrofitInstance;
import com.example.poetryapp.Response.addresponse;
import com.example.poetryapp.databinding.ActivityAddPoetryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Add_poetry extends AppCompatActivity {
    ActivityAddPoetryBinding binding;
    Button save;
    EditText poetry, poet;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding= ActivityAddPoetryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
binding.imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(Add_poetry.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
});

 binding.save.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {

         if (binding.Poetry.getText().toString().equals("")) {
             binding.Poetry.setError("Field must not be empty");
         } else if (binding.poetname.getText().toString().equals("")) {
             binding.poetname.setError("Field must not be empty");
         } else {
             Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
             apiInterface = retrofit.create(ApiInterface.class);
             apiInterface.addpoetry(binding.Poetry.getText().toString(), binding.poetname.getText().toString()).enqueue(new Callback<addresponse>() {
                 @Override
                 public void onResponse(Call<addresponse> call, Response<addresponse> response) {
                     try {
                         if (response.body().getStatus().equals("1")) {
                             Toast.makeText(Add_poetry.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
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
     }
 });


    }

}