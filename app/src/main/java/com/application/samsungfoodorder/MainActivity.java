package com.application.samsungfoodorder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.application.samsungfoodorder.Adapters.MainAdapter;
import com.application.samsungfoodorder.Models.MainModel;
import com.application.samsungfoodorder.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.burger,"Burger","220","Burger with Cheese"));
        list.add(new MainModel(R.drawable.bhel_puri,"Bhel Puri","220","Puffed rice, vegetables and a tangy tamarind sauce"));
        list.add(new MainModel(R.drawable.chole_bature,"Chole Bature","220","Spicy white chickpeas and,a fried bread"));
        list.add(new MainModel(R.drawable.dosa,"Dosa","220","A thin pancake"));
        list.add(new MainModel(R.drawable.fast_food_meal,"Fast Food Meal","220","Burger, Coke, Fries"));
        list.add(new MainModel(R.drawable.idli_sambar,"Idli Sambar","220","Rice cake"));
        list.add(new MainModel(R.drawable.pani_puri,"Pani Puri","220","Hollow, crispy-fried puffed ball"));
        list.add(new MainModel(R.drawable.pizza,"Pizza","220","Pizza with Cheese"));
        list.add(new MainModel(R.drawable.samosa,"Samosa","120","Fried or baked pastry with a savory filling"));
        list.add(new MainModel(R.drawable.wada,"Wada","220","Fritter made from Vigna mungo"));
        MainAdapter adapter = new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        Intent intent = new Intent(this, NotificationService.class);
        ContextCompat.startForegroundService(this,intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}