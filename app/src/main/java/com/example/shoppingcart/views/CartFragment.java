package com.example.shoppingcart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingcart.R;
import com.example.shoppingcart.adapter.CartListAdapter;
import com.example.shoppingcart.databinding.CartRowBinding;
import com.example.shoppingcart.databinding.FragmentCartBinding;
import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.viewModels.ShopViewModel;

import java.util.List;


public class CartFragment extends Fragment implements CartListAdapter.CartInterface{
ShopViewModel shopViewModel;
FragmentCartBinding fragmentCartBinding;
NavController navController;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater,container,false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    //nav controller
        navController=Navigation.findNavController(view);

        //add list to cart and decoration
        CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));

        shopViewModel= new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
                //btn on cart fragment will only on cart size is more than 0
                fragmentCartBinding.cartOrderBtn.setEnabled(cartItems.size()>0);
            }
        });
        fragmentCartBinding.cartOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_cartFragment_to_orderFragment);
            }
        });

        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                fragmentCartBinding.cartTotalBill.setText("Total: $"+integer.toString());
            }
        });


    }

    @Override
    public void deleteItem(CartItem cartItem) {
        shopViewModel.removeCartItem(cartItem);
    }

    @Override
    public void updateQuantity(CartItem cartItem, int quantity) {
        shopViewModel.changeQuantity(cartItem,quantity);
    }
}