package com.example.uiappfood;

import android.content.Intent;
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

public class LoginFragment extends Fragment {
    public Button btnlogin;
    public EditText editemail, editpassword;
    TextView forgot;
    DatabaseHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DatabaseHelper(getActivity());
        editemail = view.findViewById(R.id.editemail);
        editpassword = view.findViewById(R.id.editpassword);
        btnlogin = view.findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(v -> {
            String email = editemail.getText().toString();
            String password = editpassword.getText().toString();
            boolean ischeck = db.checkUse(email, password);
            if (ischeck) {
                Toast.makeText(getActivity(), "Login success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getActivity(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
        forgot = view.findViewById(R.id.forgot);
        forgot.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }
}
