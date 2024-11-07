package com.example.menurestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private TextView totalPriceTextView;
    private List<CartItem> cartItems;
    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        totalPriceTextView = findViewById(R.id.total_price_text_view);
        payButton = findViewById(R.id.pay_button);

        // Get Intent and extras
        Intent intent = getIntent();
        cartItems = (List<CartItem>) intent.getSerializableExtra("cart_items");

        if (cartItems == null) {
            // If cartItems is null, log an error
            Log.e("CheckoutActivity", "cartItems is null!");
            return;
        }

        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }

        // Set the total price on the TextView
        totalPriceTextView.setText("Total: $" + totalPrice);

        payButton.setOnClickListener(v -> {
            Toast.makeText(CheckoutActivity.this, "Payment went Through", Toast.LENGTH_SHORT).show();

            //after payment is successfull, go back to MainActivity
            Intent mainIntent = new Intent(CheckoutActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish(); // Close this activity to prevent back navigation to the checkout
        });
    }
}
