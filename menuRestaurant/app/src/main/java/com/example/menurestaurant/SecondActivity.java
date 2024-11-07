package com.example.menurestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create CartItem list by pairing MenuItems with quantities (initialize quantities as 0)
        cartItems = new ArrayList<>();
        cartItems.add(new CartItem(new MenuItem("Pizza", "Delicious cheese pizza", 12.99, R.drawable.pizza), 0));
       cartItems.add(new CartItem(new MenuItem("Burger", "Juicy beef burger", 8.99, R.drawable.burger), 0));
       cartItems.add(new CartItem(new MenuItem("Pasta", "Creamy Fettuccine", 10.99, R.drawable.pasta), 0));
       cartItems.add(new CartItem(new MenuItem("Pizza", "Delicious pepperoni pizza", 12.99, R.drawable.pizza), 0));
       cartItems.add(new CartItem(new MenuItem("Burger", "Juicy vegan burger", 8.99, R.drawable.burger), 0));
       cartItems.add(new CartItem(new MenuItem("Pasta", "Mushrooms with tomato", 10.99, R.drawable.pasta), 0));

        // Set the adapter for RecyclerView
        menuAdapter = new MenuAdapter(cartItems);
        recyclerView.setAdapter(menuAdapter);

        // Checkout button logic
        Button checkoutButton = findViewById(R.id.proceed_to_checkoutt);
        checkoutButton.setOnClickListener(v -> {
            // Calculate total price
            double totalPrice = calculateTotalPrice();

            // Only proceed if there's at least one item in the cart
            if (totalPrice > 0) {
                Intent intent = new Intent(SecondActivity.this, CheckoutActivity.class);
                intent.putExtra("cart_items", (Serializable) cartItems);
                intent.putExtra("total_price", totalPrice);
                startActivity(intent);
            } else {
                Toast.makeText(SecondActivity.this, "Please select quantities for the items.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getTotalPrice();
        }
        return total;
    }
}