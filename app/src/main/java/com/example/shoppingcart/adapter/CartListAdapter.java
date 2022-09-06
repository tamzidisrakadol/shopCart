package com.example.shoppingcart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.databinding.CartRowBinding;
import com.example.shoppingcart.models.CartItem;

public class CartListAdapter extends ListAdapter<CartItem,CartListAdapter.Viewholder> {


    public CartListAdapter() {
        super(CartItem.itemCallback);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartRowBinding cartRowBinding = CartRowBinding.inflate(layoutInflater,parent,false);
        return new Viewholder(cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.cartRowBinding.setCartItem(getItem(position));
        holder.cartRowBinding.executePendingBindings();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
    CartRowBinding cartRowBinding;
        public Viewholder(@NonNull CartRowBinding cartRowBinding) {
            super(cartRowBinding.getRoot());
            this.cartRowBinding=cartRowBinding;
        }
    }
}
