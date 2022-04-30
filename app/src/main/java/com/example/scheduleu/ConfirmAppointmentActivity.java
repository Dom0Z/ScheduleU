package com.example.scheduleu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmAppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ConfirmAppointmentActiv";

    private Button confirmButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_appointment);

        confirmButton = findViewById(R.id.buttonConfirmAppointment);
        backButton = findViewById(R.id.buttonAppointmentBack);

        confirmButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == confirmButton) {
            setResult(RESULT_OK);
            finish();
        }
        else if (view == backButton) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
