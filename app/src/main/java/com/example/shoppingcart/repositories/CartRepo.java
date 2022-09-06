package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {
    private MutableLiveData<List<CartItem>> mutableCartItem = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if (mutableCartItem.getValue()== null){
            initCart();
        }
        return mutableCartItem;
    }
    public void initCart(){
        mutableCartItem.setValue(new ArrayList<CartItem>());
    }

    public boolean addItemToCart(Product product){
        if (mutableCartItem.getValue()== null){
            initCart();
        }
        List<CartItem> cartItemsList = new ArrayList<>(mutableCartItem.getValue());
        CartItem cartItem = new CartItem(product,1);
        cartItemsList.add(cartItem);
        mutableCartItem.setValue(cartItemsList);
        return true;
    }

}
