package com.example.scheduleu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddAlertActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText title;
    private EditText body;
    private EditText location;
    private Button confirmButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);

        title = findViewById(R.id.editTextAlertTitle);
        body = findViewById(R.id.editTextAlertBody);
        location = findViewById(R.id.editTextAlertLocation);
        confirmButton = findViewById(R.id.buttonAlertConfirm);
        backButton = findViewById(R.id.buttonAlertBack);

        confirmButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == confirmButton) {
            Intent intent = new Intent();
            intent.putExtra("Title", title.getText().toString());
            intent.putExtra("Body", body.getText().toString());
            intent.putExtra("Location", location.getText().toString());

            setResult(RESULT_OK, intent);
            finish();
        }
        else if (view == backButton) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
