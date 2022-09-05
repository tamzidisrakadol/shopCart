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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingcart.R;
import com.example.shoppingcart.adapter.ShopListAdapter;
import com.example.shoppingcart.databinding.FragmentShopBinding;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.viewModels.ShopViewModel;

import java.util.List;


public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface{
    FragmentShopBinding fragmentShopBinding;
    ShopListAdapter shopListAdapter;
    ShopViewModel shopViewModel;
    NavController navController;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter =  new ShopListAdapter(this);
        fragmentShopBinding.recyclerView.setAdapter(shopListAdapter);

        //set vertical and horizontal divider
        fragmentShopBinding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        fragmentShopBinding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));

        //initialize shopViewModel class and set Observer
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                shopListAdapter.submitList(products);
            }
        });
        navController = Navigation.findNavController(view);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentShopBinding = FragmentShopBinding.inflate(inflater,container,false);
        return fragmentShopBinding.getRoot();
    }
    @Override
    public void addItem(Product product) {

    }

    @Override
    public void onItemClick(Product product) {
        Log.d("tag","onItemClick : "+product.getName());
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_shopFragment_to_productDetailsFragment);
    }
}