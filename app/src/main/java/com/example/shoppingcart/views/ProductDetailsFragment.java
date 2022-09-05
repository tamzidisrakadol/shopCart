package com.example.shoppingcart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingcart.R;
import com.example.shoppingcart.databinding.FragmentProductDetailsBinding;
import com.example.shoppingcart.viewModels.ShopViewModel;


public class ProductDetailsFragment extends Fragment {
    FragmentProductDetailsBinding fragmentProductDetailsBinding;
    ShopViewModel shopViewModel;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProductDetailsBinding = FragmentProductDetailsBinding.inflate(inflater,container,false);
        return fragmentProductDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopViewModel= new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentProductDetailsBinding.setShopViewModel(shopViewModel);
    }
}