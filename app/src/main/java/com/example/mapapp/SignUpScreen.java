package com.example.mapapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpScreen extends AppCompatActivity {

    private EditText nameEditText;
    private EditText rollNumberEditText;
    private EditText emailSignUpEditText;
    private EditText passwordSignUpEditText;
    private Button doSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        nameEditText = findViewById(R.id.nameEditText);
        rollNumberEditText = findViewById(R.id.rollNumberEditText);
        emailSignUpEditText = findViewById(R.id.emailSignUpEditText);
        passwordSignUpEditText = findViewById(R.id.passwordSignUpEditText);
        doSignUpButton = findViewById(R.id.doSignUpButton);

        doSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameEditText.getText().toString();
                String rollNumber = rollNumberEditText.getText().toString();
                String email = emailSignUpEditText.getText().toString();
                String password = passwordSignUpEditText.getText().toString();

                if (name.isEmpty() || rollNumber.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpScreen.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8 || password.length() > 10) {
                    Toast.makeText(SignUpScreen.this, "Password must be between 8 to 10 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                postData(name, email, rollNumber, password);
            }
        });
    }

    private void postData(final String name, String email, String roll, String pass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://map-backend-1.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        DataModel modal = new DataModel(name, email, roll, pass);

        Call<DataModel> call = retrofitAPI.createPost(modal);

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                showDialog("Success", "User registered successfully!");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // After 3 seconds, this code will run
                        Intent intent = new Intent(SignUpScreen.this, Profile.class);
                        intent.putExtra("username", name);  // pass the user name
                        startActivity(intent);
                        finish(); // Close the SignUpScreen
                    }
                }, 3000);  // 3000 milliseconds = 3 seconds
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                showDialog("Failure", "Failed to register user: " + t.getMessage());
            }
        });
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpScreen.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
