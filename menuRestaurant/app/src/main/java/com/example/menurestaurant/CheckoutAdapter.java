package com.example.menurestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {
    private List<CartItem> cartItems;
    private Context context;

    public CheckoutAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.checkout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.nameTextView.setText(cartItem.getMenuItem().getName());
        holder.quantityTextView.setText("Quantity: " + cartItem.getQuantity());
        holder.totalPriceTextView.setText("Total: $" + cartItem.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, quantityTextView, totalPriceTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.checkout_item_name);
            quantityTextView = itemView.findViewById(R.id.checkout_item_quantity);
            totalPriceTextView = itemView.findViewById(R.id.checkout_item_total_price);
        }
    }
}
