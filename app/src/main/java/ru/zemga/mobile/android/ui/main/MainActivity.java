package ru.zemga.mobile.android.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import ru.zemga.mobile.android.R;
import ru.zemga.mobile.android.databinding.MainActivityBinding;
import ru.zemga.mobile.android.logic.LogicMain;
import ru.zemga.mobile.android.logic.RepositoryLandMessage;
import ru.zemga.mobile.android.logic.RepositoryMessage;
import ru.zemga.mobile.android.logic.RepositoryMessageHandler;

public class MainActivity extends AppCompatActivity implements RepositoryMessageHandler
{
    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_search_list, R.id.navigation_sell)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_host);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

        LogicMain.getInstance(getApplicationContext()).getRepository().applySubscription(this);

        hideNavBar();


    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    public void onMessageReceive(RepositoryMessage<?> message) {
        System.out.println("Получили сообщение! Оно имеет тип...");
        if (message instanceof RepositoryLandMessage)
        {
            System.out.println("RepositoryLandMessage!");

            RepositoryLandMessage msg = (RepositoryLandMessage) message;
            System.out.println("В списке " + msg.getData().size() + " элементов!");
        }
    }

    public void hideNavBar ()
    {
        binding.bottomNavigation.setVisibility(View.GONE);
    }

    public void showNavBar ()
    {
        binding.bottomNavigation.setVisibility(View.VISIBLE);
    }
}