package com.application.samsungfoodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.application.samsungfoodorder.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final DBHelper dbHelper = new DBHelper(this);
        if(getIntent().getIntExtra("type",0)==1){
            final int image = getIntent().getIntExtra("image",0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");
            binding.detailImage.setImageResource(image);
            binding.detailPrice.setText(String.format("%d",price));
            binding.detailFoodName.setText(name);
            binding.detailDescription.setText(description);
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = dbHelper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            description,
                            name,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if(isInserted)
                        Toast.makeText(DetailsActivity.this, "Data Successfully inserted"
                                , Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailsActivity.this, "Data not inserted"
                                , Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = dbHelper.getOrderById(id);
            int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.detailPrice.setText(String.format("%d",cursor.getInt(3)));
            binding.detailFoodName.setText(cursor.getString(7));
            binding.detailDescription.setText(cursor.getString(6));
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.quantity.setText(String.format("%d",cursor.getInt(5)));
            binding.insertButton.setText("Update Now");
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = dbHelper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.detailPrice.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.detailFoodName.getText().toString(),
                            Integer.parseInt(binding.quantity.getText().toString()),
                            id
                    );
                    if(isUpdated)
                        Toast.makeText(DetailsActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(binding.quantity.getText().toString());
                quantity = quantity+1;
                binding.quantity.setText(String.format("%d",quantity));
            }
        });
        binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(binding.quantity.getText().toString());
                quantity = quantity-1;
                binding.quantity.setText(String.format("%d",quantity));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(DetailsActivity.this,MainActivity.class));
                break;
            case R.id.orders:
                startActivity(new Intent(DetailsActivity.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}