package com.example.menurestaurant;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<CartItem> cartItems;

    // Constructor
    public MenuAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        MenuItem item = cartItem.getMenuItem();

        holder.nameTextView.setText(item.getName());
        holder.descriptionTextView.setText(item.getDescription());
        holder.priceTextView.setText("$" + item.getPrice());
        holder.imageView.setImageResource(item.getImageResId());
        holder.quantityEditText.setText(String.valueOf(cartItem.getQuantity()));

        // Update quantity when focus changes
        holder.quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int newQuantity = Integer.parseInt(editable.toString());
                    if (newQuantity >= 0) {
                        cartItem.setQuantity(newQuantity); // Update the CartItem quantity
                    } else {
                        // If negative quantity is entered, reset to 0 and show a message
                        cartItem.setQuantity(0);
                        Toast.makeText(holder.itemView.getContext(), "Quantity cannot be negative", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where the input is not a valid number
                    cartItem.setQuantity(0); // Reset quantity to 0 if invalid input
                    Toast.makeText(holder.itemView.getContext(), "Invalid input. Quantity set to 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView, priceTextView;
        ImageView imageView;
        EditText quantityEditText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.menu_item_name);
            descriptionTextView = itemView.findViewById(R.id.menu_item_description);
            priceTextView = itemView.findViewById(R.id.menu_item_price);
            imageView = itemView.findViewById(R.id.menu_item_image);
            quantityEditText = itemView.findViewById(R.id.menu_item_quantity);
        }
    }
}