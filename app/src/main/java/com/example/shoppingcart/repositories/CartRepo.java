package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {
    private MutableLiveData<List<CartItem>> mutableCartItem = new MutableLiveData<>();
    private MutableLiveData<Integer> mutableTotalProduct =  new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if (mutableCartItem.getValue()== null){
            initCart();
        }
        return mutableCartItem;
    }

    //get item from Productlist and added to new list CartList
    public void initCart(){
        mutableCartItem.setValue(new ArrayList<CartItem>());
        calculateCart();
    }

    public boolean addItemToCart(Product product){
        if (mutableCartItem.getValue()== null){
            initCart();
        }
        List<CartItem> cartItemsList = new ArrayList<>(mutableCartItem.getValue());

        //to check same product is added or not , if added just rise the quantity
        for(CartItem cartItem: cartItemsList){
            if (cartItem.getProduct().getId().equals(product.getId())){
                if (cartItem.getQuantity()==5){
                    return false;
                }
                int index = cartItemsList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItemsList.set(index,cartItem);
                mutableCartItem.setValue(cartItemsList);
                return true;

            }
        }

        CartItem cartItem = new CartItem(product,1);
        cartItemsList.add(cartItem);
        mutableCartItem.setValue(cartItemsList);
        calculateCart();
        return true;

    }

    //remove cart item
    public void removeCartItem(CartItem cartItem){
        if (mutableCartItem.getValue()==null){
            return;
        }

        //first get the list of Product from cart
        List<CartItem> cartItemList = new ArrayList<>(mutableCartItem.getValue());
        //2nd remove the selected product from the cartlist
        cartItemList.remove(cartItem);
        //3rd set the new list to mutableLivedata
        mutableCartItem.setValue(cartItemList);
    calculateCart();
    }

    //spinner in cart
    public void changeQuantity(CartItem cartItem,int quantity){
        if (mutableCartItem.getValue()==null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCartItem.getValue());
        CartItem updateCartItem = new CartItem(cartItem.getProduct(),quantity);
        cartItemList.set(cartItemList.indexOf(cartItem),updateCartItem );
        mutableCartItem.setValue(cartItemList);
        calculateCart();
    }
    //getcalculatetotal
    public LiveData<Integer> getTotalPrice(){
        if (mutableCartItem==null){
            mutableTotalProduct.setValue(0);
        }
        return mutableTotalProduct;
    }

    //setCalculateTotal
    private void calculateCart(){
        if (mutableCartItem==null){
            return;
        }
        int total = 0;
        List<CartItem> cartItemsList = mutableCartItem.getValue();
        for (CartItem cartItem:cartItemsList){
            total += cartItem.getProduct().getPrice()*cartItem.getQuantity();
        }
        mutableTotalProduct.setValue(total);
    }


}
