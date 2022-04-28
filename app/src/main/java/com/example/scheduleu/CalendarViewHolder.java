package com.example.scheduleu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    TextView DateView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);
        DateView = itemView.findViewById(R.id.DateView);
        button1 = itemView.findViewById(R.id.button1);
        button2 = itemView.findViewById(R.id.button2);
        button3 = itemView.findViewById(R.id.button3);
        button4 = itemView.findViewById(R.id.button4);
        button5 = itemView.findViewById(R.id.button5);
    }
}
