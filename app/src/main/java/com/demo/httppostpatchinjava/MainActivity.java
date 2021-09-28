package com.demo.httppostpatchinjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModel();
        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createNewUser();
                updateUser();
            }
        });

        loadUserData();
    }

    private void loadUserData() {
        viewModel.getLoadUserObserver().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if(userResponse != null) {
                    ((EditText)findViewById(R.id.etName)).setText(userResponse.getData().getName());
                    ((EditText)findViewById(R.id.etEmail)).setText(userResponse.getData().getEmail());
                }
            }
        });
        viewModel.loadUserData("1383");
    }

    private void updateUser() {
        String name  = ((EditText)findViewById(R.id.etName)).getText().toString();
        String email  = ((EditText)findViewById(R.id.etEmail)).getText().toString();
        User user = new User("1383", name, email, "Active", "Male");
        viewModel.updateUserData(user, "1383");
    }

    private void createNewUser() {
        String name  = ((EditText)findViewById(R.id.etName)).getText().toString();
        String email  = ((EditText)findViewById(R.id.etEmail)).getText().toString();
        User user = new User("", name, email, "Active", "Male");
        viewModel.createNewUser(user);
    }

    private void initViewModel() {
         viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getCreateUserObserver().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if(userResponse == null) {
                    Toast.makeText(MainActivity.this, "failed to create new user", Toast.LENGTH_LONG).show();
                } else {
                    //https://gorest.co.in/public-api/users/1383
                    //{"code":201,"meta":null,"data":{"id":1383,"name":"TestPOST123","email":"TestPOST123@Yahoo.com","gender":"male","status":"active"}}
                    Toast.makeText(MainActivity.this, "Successfully created new user", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}