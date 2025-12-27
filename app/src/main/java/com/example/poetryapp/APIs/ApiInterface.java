package com.example.poetryapp.APIs;

import com.example.poetryapp.Response.Apiresponse;
import com.example.poetryapp.Response.addresponse;
import com.example.poetryapp.Response.deleteresponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("getpoetry.php")
    Call<Apiresponse>getpoetry();

     @FormUrlEncoded
    @POST("deletepoetry.php")
    Call<deleteresponse>deletepoetry(@Field("id") String id);


   @FormUrlEncoded
   @POST("addpoetry.php")
    Call<addresponse>addpoetry(@Field("poetry") String poetry, @Field("poet_name") String poet_name);
 @FormUrlEncoded
   @POST("updatepoetry.php")
    Call<addresponse>updatepoetry(@Field("poetry_data") String poetry_data, @Field("id") String id);

}
