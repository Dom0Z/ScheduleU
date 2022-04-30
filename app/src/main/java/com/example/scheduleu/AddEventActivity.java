package com.example.scheduleu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title;
    private EditText body;
    private EditText date;
    private EditText startTime;
    private EditText endTime;
    private EditText maxRSVP;
    private Button confirmButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        title = findViewById(R.id.editTextEventTitle);
        body = findViewById(R.id.editTextEventBody);
        date = findViewById(R.id.editTextEventDate);
        startTime = findViewById(R.id.editEventStartTime);
        endTime = findViewById(R.id.editEventEndTime);
        maxRSVP = findViewById(R.id.editTextMaxRSVP);
        confirmButton = findViewById(R.id.buttonEventConfirm);
        backButton = findViewById(R.id.buttonEventBack);

        confirmButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == confirmButton) {
            Intent intent = new Intent();
            intent.putExtra("Title", title.getText().toString());
            intent.putExtra("Body", body.getText().toString());
            intent.putExtra("Date", date.getText().toString());
            intent.putExtra("StartTime", startTime.getText().toString());
            intent.putExtra("EndTime", endTime.getText().toString());
            intent.putExtra("MaxRSVP", maxRSVP.getText().toString());

            setResult(RESULT_OK, intent);
            finish();
        }
        else if (view == backButton) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
