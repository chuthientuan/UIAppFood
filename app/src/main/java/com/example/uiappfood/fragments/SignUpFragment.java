package com.example.uiappfood.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uiappfood.database.DatabaseHelper;
import com.example.uiappfood.R;
import com.example.uiappfood.activities.Login;

public class SignUpFragment extends Fragment {
    public EditText editsignemail, editsignpassword, editsignconfirmpassword;
    public TextView haveaccount;
    public Button btnsignup;
    public Login login;
    DatabaseHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DatabaseHelper(getActivity());
        haveaccount = view.findViewById(R.id.haveaccount);
        editsignemail = view.findViewById(R.id.editsignemail);
        editsignpassword = view.findViewById(R.id.editsignpassword);
        editsignconfirmpassword = view.findViewById(R.id.editsignconfirmpassword);
        btnsignup = view.findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(v -> {
            String email = editsignemail.getText().toString();
            String password = editsignpassword.getText().toString();
            String confirmpassword = editsignconfirmpassword.getText().toString();
            if (email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (email.equals(db.getEmail())) {
                Toast.makeText(getActivity(), "Email already exist", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmpassword)) {
                Toast.makeText(getActivity(), "Password not match", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean isinsert = db.insertData(email, password);
            if (isinsert) {
                Toast.makeText(getActivity(), "Sign up success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Sign up failed", Toast.LENGTH_SHORT).show();
            }
        });
        haveaccount.setOnClickListener(v -> {
            login = (Login) getActivity();
            login.loadFragment(new LoginFragment());
            login.showIndicator(true);
        });
    }
}
