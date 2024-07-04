package com.example.uiappfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Login extends AppCompatActivity {
    LinearLayout tabLogin, tabSignup;
    private View indicatorLogin, indicatorSignup;
    private LoginFragment loginFragment;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tabLogin = findViewById(R.id.tabLogin);
        tabSignup = findViewById(R.id.tabSignup);
        indicatorLogin = findViewById(R.id.indicatorLogin);
        indicatorSignup = findViewById(R.id.indicatorSignup);
        // Load LoginFragment by default
        loadFragment(new LoginFragment());
        showIndicator(true);
        tabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new LoginFragment());
                showIndicator(true);
            }
        });
        tabSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SignUpFragment());
                showIndicator(false);
            }
        });
        // Check Internet
        if (!NetworkUtil.isNetworkAvailable(this)) {
            Intent intent = new Intent(this, NoInternetActivity.class);
            startActivity(intent);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void showIndicator(boolean isLoginSelected) {
        if (isLoginSelected) {
            indicatorLogin.setVisibility(View.VISIBLE);
            indicatorSignup.setVisibility(View.GONE);
        } else {
            indicatorLogin.setVisibility(View.GONE);
            indicatorSignup.setVisibility(View.VISIBLE);
        }
    }

    private void setlogin() {
        db = new DatabaseHelper(this);
        loginFragment.editemail = findViewById(R.id.editemail);
        loginFragment.editpassword = findViewById(R.id.editpassword);
        loginFragment.btnlogin = findViewById(R.id.btnlogin);
        loginFragment.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginFragment.editemail.getText().toString();
                String password = loginFragment.editpassword.getText().toString();
                boolean ischeck = db.checkUse(email, password);
                if (ischeck) {

                }
            }
        });
    }
}