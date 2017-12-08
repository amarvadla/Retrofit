package com.example.avadla.retrofit_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List list = new ArrayList();
    String name,realname,team,firstappearance,createdby,publisher,imageurl,bio;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        getHeroes();
    }

    private void getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList = response.body();

                for (int i = 0; i < heroList.size(); i++) {
                    name = heroList.get(i).getName();
                    realname = heroList.get(i).getRealname();
                    team = heroList.get(i).getTeam();
                    firstappearance = heroList.get(i).getFirstappearance();
                    createdby = heroList.get(i).getCreatedby();
                    publisher = heroList.get(i).getPublisher();
                    imageurl = heroList.get(i).getImageurl();
                    id = i;
                    bio = heroList.get(i).getBio();
                    Info info = new Info(name,realname,team,firstappearance,createdby,publisher,imageurl,bio,id);
                    info.setName(name); info.setRealname(realname); info.setTeam(team); info.setFirstappearance(firstappearance);
                    info.setCreatedby(createdby); info.setPublisher(publisher); info.setImageurl(imageurl); info.setBio(bio);
                    info.setId(id);
                    list.add(info);
                }
                Log.e("tag",list.toString());
                HeroAdapter heroAdapter = new HeroAdapter(MainActivity.this,list);
                recyclerView.setAdapter(heroAdapter);
                heroAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
