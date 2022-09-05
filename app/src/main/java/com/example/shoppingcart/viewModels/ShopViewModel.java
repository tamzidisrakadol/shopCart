package com.example.shoppingcart.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {
    ShopRepo shopRepo = new ShopRepo();

    //this getProduct method will return mutableLiveData from Shop-repositories
    // and can use as live observer in ShopFragment
    public LiveData<List<Product>> getProducts(){
        return shopRepo.getProducts();
    }

}
