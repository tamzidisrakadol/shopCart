package com.example.shoppingcart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.databinding.ShopRowBinding;
import com.example.shoppingcart.models.Product;

public class ShopListAdapter extends ListAdapter<Product,ShopListAdapter.ShopViewholder> {


    public ShopListAdapter() {
        super(Product.itemCallback);
    }

    @NonNull
    @Override
    public ShopViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ShopRowBinding shopRowBinding = ShopRowBinding.inflate(layoutInflater,parent,false);
        return new ShopViewholder(shopRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewholder holder, int position) {
    Product product = getItem(position);
    holder.shopRowBinding.setProduct(product);
    }

    public class ShopViewholder extends RecyclerView.ViewHolder{
        ShopRowBinding shopRowBinding;
        public ShopViewholder(ShopRowBinding shopRowBinding) {
            super(shopRowBinding.getRoot());
        this.shopRowBinding= shopRowBinding;
        }
    }

    public interface ShopInterface{
        void addItem(Product product);
        void onItemClick(Product product);
    }
}
