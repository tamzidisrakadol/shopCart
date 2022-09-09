package com.example.shoppingcart.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.repositories.CartRepo;
import com.example.shoppingcart.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {
    ShopRepo shopRepo = new ShopRepo();
    MutableLiveData<Product> mutableProduct =  new MutableLiveData<>();
    CartRepo cartRepo = new CartRepo();

    //this getProduct method will return mutableLiveData from Shop-repositories
    // and can use as live observer in ShopFragment
    public LiveData<List<Product>> getProducts(){
        return shopRepo.getProducts();
    }

    //onitemclick setProduct
    public void setProduct(Product product){
        mutableProduct.setValue(product);
    }

    //product detail
    public LiveData<Product> getProduct(){
        return mutableProduct;
    }


    //cart
    public LiveData<List<CartItem>> getCart(){
        return cartRepo.getCart();
    }
    public boolean addProductToCart(Product product){
        return cartRepo.addItemToCart(product);
    }

    //remove item
    public void removeCartItem(CartItem cartItem){
        cartRepo.removeCartItem(cartItem);
    }

    //spinner quantity
    public void changeQuantity(CartItem cartItem,int quantity){
        cartRepo.changeQuantity(cartItem,quantity);
    }

    //calculate total
    public LiveData<Integer> getTotalPrice(){
        return cartRepo.getTotalPrice();
    }

    //reset cart
    public void resetCart(){
        cartRepo.initCart();
    }


}
