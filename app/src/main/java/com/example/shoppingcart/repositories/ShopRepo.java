package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {
    private MutableLiveData<List<Product>> mutableProductList;


    //use this getProduct() in ViewModel to fetch all list of movies
    public LiveData<List<Product>> getProducts(){
        if (mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            loadData();
        }
        return mutableProductList;
    }


    //list of movies in loadData()
    private void loadData(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(),"Mobile phone","https://images.unsplash.com/photo-1601784551446-20c9e07cdbdb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=367&q=80",890,true));
        productList.add(new Product(UUID.randomUUID().toString(),"Mac","https://images.unsplash.com/photo-1541807084-5c52b6b3adef?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80",1890,true));
        productList.add(new Product(UUID.randomUUID().toString(),"Mug","https://images.unsplash.com/photo-1520031473529-2c06dea61853?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80",20,false));
        productList.add(new Product(UUID.randomUUID().toString(),"earbud","https://images.unsplash.com/photo-1632200004922-bc18602c79fc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80",80,false));
        productList.add(new Product(UUID.randomUUID().toString(),"headset","https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=388&q=80",500,true));
        productList.add(new Product(UUID.randomUUID().toString(),"watch","https://images.unsplash.com/photo-1524805444758-089113d48a6d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=388&q=80",250,true));
        productList.add(new Product(UUID.randomUUID().toString(),"sunglass","https://images.unsplash.com/photo-1572635196237-14b3f281503f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80",150,true));
        productList.add(new Product(UUID.randomUUID().toString(),"Led-Bulb","https://images.unsplash.com/photo-1612523563676-709f47fab6ea?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80",56,false));
        productList.add(new Product(UUID.randomUUID().toString(),"Photo Frame","https://images.unsplash.com/photo-1560828343-a0b3d8864d1b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=317&q=80",100,true));
        mutableProductList.setValue(productList);
    }
}
