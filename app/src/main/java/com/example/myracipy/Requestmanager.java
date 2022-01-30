package com.example.myracipy;

import android.content.Context;
import android.text.style.AlignmentSpan;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myracipy.Adapter.RandomRecipyAdapter;
import com.example.myracipy.Listner.RandonRecipyResponseLintner;
import com.example.myracipy.Modals.RandomRecipyapiresponse;
import com.example.myracipy.Modals.RandomdetailsResponce;
import com.example.myracipy.Modals.RecipyDetailsListner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Requestmanager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Requestmanager(Context context) {
        this.context=context;
    }
//List<String> tags
    public void getRandomRecipy(RandonRecipyResponseLintner lintner, List<String> tags){
        CallRandomRecipy callRandomRecipy = retrofit.create(CallRandomRecipy.class);
        Call<RandomRecipyapiresponse> call = callRandomRecipy.callRandomRecipy(context.getString(R.string.api_key),"10",tags);//tags
        call.enqueue(new Callback<RandomRecipyapiresponse>() {
            @Override
            public void onResponse(Call<RandomRecipyapiresponse> call, Response<RandomRecipyapiresponse> response) {
                if (!response.isSuccessful()){
                    lintner.didErropr(response.message());
                    return;
                }
                lintner.didFetch(response.body(), response.message());
            }

           @Override
           public void onFailure(Call<RandomRecipyapiresponse> call, Throwable t) {
                   lintner.didErropr(t.getMessage());
            }
        });
    }

//       public void getRecipyDetails(RecipyDetailsListner listner, int id){
//        CallRecipyDetails callRecipyDetails = retrofit.create(CallRecipyDetails.class);
//        Call<RandomdetailsResponce> call = callRecipyDetails.CallRecipy(id, context.getString())
//       }

    private interface CallRandomRecipy{
        @GET("recipes/random")
        Call<RandomRecipyapiresponse> callRandomRecipy(
                @Query("apiKey") String apikey,
                @Query("number") String number,
                @Query("tag") List<String> tags

                );

    }
    private interface  CallRecipyDetails{
        @GET("recipes/{id}/information")
        Call<RandomRecipyapiresponse> CallRecipy(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );

}


}
