package com.example.menurestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.pick_up);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Ordered", Toast.LENGTH_SHORT).show();
                Intent myIntent2 = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(myIntent2);
            }
        });
    }

    // Method that is called when the "Order Pickup" button is clicked
    public void onOrderPickupClicked(View view) {
        // Start MenuActivity when the button is clicked
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
