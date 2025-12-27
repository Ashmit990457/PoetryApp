package com.example.poetryapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poetryapp.APIs.ApiInterface;
import com.example.poetryapp.APIs.RetrofitInstance;
import com.example.poetryapp.R;
import com.example.poetryapp.Response.deleteresponse;
import com.example.poetryapp.UpdatePoetry;
import com.example.poetryapp.models.poetryrecylermodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class poetryrecyleradapter extends RecyclerView.Adapter<poetryrecyleradapter.viewholder>{

    List<poetryrecylermodel>list;
    ApiInterface apiInterface;
    Context context;

    public poetryrecyleradapter(List<poetryrecylermodel> list, Context context) {
        this.list = list;
        this.context = context;
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        apiInterface=retrofit.create(ApiInterface.class);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.poetryrecyler,parent,false);
        return new viewholder(view);
    }

    public poetryrecyleradapter(List<poetryrecylermodel> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        poetryrecylermodel poetryrecylermodel=list.get(position);
        holder.poetname.setText(poetryrecylermodel.getPoetName());
        holder.poetrydata.setText(poetryrecylermodel.getPoetryData());
        holder.date_time.setText(poetryrecylermodel.getDateTime());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               deletepoetry(poetryrecylermodel.getId());
            }
        });


        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UpdatePoetry.class);
                intent.putExtra("id",poetryrecylermodel.getId());
                intent.putExtra("poet",poetryrecylermodel.getPoetName());
                intent.putExtra("poetry",poetryrecylermodel.getPoetryData());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
       TextView poetrydata,poetname,date_time;
       Button update,delete;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            poetname=itemView.findViewById(R.id.poetname);
            poetrydata=itemView.findViewById(R.id.poetrydata);
            date_time=itemView.findViewById(R.id.dateandtime);
            update=itemView.findViewById(R.id.updatebutton);
            delete=itemView.findViewById(R.id.deletebutton);
        }
    }
    private void deletepoetry(String id){
        apiInterface.deletepoetry(id).enqueue(new Callback<deleteresponse>() {
            @Override
            public void onResponse(Call<deleteresponse> call, Response<deleteresponse> response) {
                try {
                    if(response!=null){
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e){
                    Log.d("exp",e.getLocalizedMessage());

                }
            }

            @Override
            public void onFailure(Call<deleteresponse> call, Throwable t) {
                Log.d("failure",t.getLocalizedMessage());
            }
        });
    }


}
