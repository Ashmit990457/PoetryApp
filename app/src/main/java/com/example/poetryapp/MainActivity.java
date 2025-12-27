package com.example.poetryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poetryapp.APIs.ApiInterface;
import com.example.poetryapp.APIs.RetrofitInstance;
import com.example.poetryapp.Response.Apiresponse;
import com.example.poetryapp.adapters.poetryrecyleradapter;
import com.example.poetryapp.models.poetryrecylermodel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inialize();
        setSupportActionBar(toolbar);
        getdata();

    }

    private void setadapter(List<poetryrecylermodel>list) {
        poetryrecyleradapter poetryrecyleradapter=new poetryrecyleradapter(list,MainActivity.this);
        recyclerView.setAdapter(poetryrecyleradapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void inialize() {
        recyclerView=findViewById(R.id.poetryrecyler);
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        apiInterface=retrofit.create(ApiInterface.class);
        toolbar=findViewById(R.id.toolbar);

    }
    private void getdata(){
        apiInterface.getpoetry().enqueue(new Callback<Apiresponse>() {
            @Override
            public void onResponse(Call<Apiresponse> call, Response<Apiresponse> response) {

                try {
                    if(response!=null){
                        if(response.body().getStatus().equals("1")){
                           setadapter(response.body().getData());

                        }
                        else{
                            Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch (Exception e){
                    Log.d("exp",e.getLocalizedMessage());
                }

            }

            @Override
            public void onFailure(Call<Apiresponse> call, Throwable t) {
                Log.d("failure",t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.add_property){
            Intent intent =new Intent(MainActivity.this,Add_poetry.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}