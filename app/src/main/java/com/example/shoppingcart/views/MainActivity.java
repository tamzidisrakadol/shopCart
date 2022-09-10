package com.example.shoppingcart.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.databinding.ActivityMainBinding;
import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.viewModels.ShopViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    ShopViewModel shopViewModel;
    private int cartQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);


        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                //to set text of cart badge
                int quantity = 0;
                for (CartItem cartItem : cartItems) {
                    quantity += cartItem.getQuantity();
                }
                cartQuantity = quantity;
                //to draw the cart badge
                invalidateMenu();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);

        //menuItem updating text and setOnClickListener
        MenuItem menuItem = menu.findItem(R.id.cartFragment);
        View actionView = menuItem.getActionView();
        TextView cartbadgeText = actionView.findViewById(R.id.cart_badge_text);
        cartbadgeText.setText(String.valueOf(cartQuantity));
        cartbadgeText.setVisibility(cartQuantity == 0 ? View.GONE : View.VISIBLE);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavigationUI.onNavDestinationSelected(item, navController);
        return super.onOptionsItemSelected(item);
    }
}