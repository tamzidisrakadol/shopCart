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

//instead of RecyclerView adapter extend ListAdapter for DiffUtilItemCallBack
public class ShopListAdapter extends ListAdapter<Product, ShopListAdapter.ShopViewholder> {
    ShopInterface shopInterface;


    //remove all parameter from ShopListAdapter Constructor & pass shopInterface for itemClick
    public ShopListAdapter(ShopInterface shopInterface) {
        //set DiffUtil.ItemCallback() from Product Class in SuperClass Method
        super(Product.itemCallback);
        this.shopInterface=shopInterface;

    }

    @NonNull
    @Override //inflate the row layout file
    public ShopViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ShopRowBinding shopRowBinding = ShopRowBinding.inflate(layoutInflater, parent, false);
        shopRowBinding.setShopInterface(shopInterface);
        return new ShopViewholder(shopRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewholder holder, int position) {
        Product product = getItem(position);
        holder.shopRowBinding.setProduct(product);
    }

    public class ShopViewholder extends RecyclerView.ViewHolder {
        ShopRowBinding shopRowBinding;

        public ShopViewholder(ShopRowBinding shopRowBinding) {
            super(shopRowBinding.getRoot());
            this.shopRowBinding = shopRowBinding;
        }
    }


    //set up onClick handling method
    public interface ShopInterface {
        void addItem(Product product);

        void onItemClick(Product product);
    }
}
