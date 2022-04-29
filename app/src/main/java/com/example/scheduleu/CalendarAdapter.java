package com.example.scheduleu;

import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>  {

    private List<Calendar> calendarList;
    private MainActivity mainActivity;

    public CalendarAdapter(List<Calendar> calendarList, MainActivity mainActivity) {
        this.calendarList = calendarList;
        this.mainActivity = mainActivity;
    }


    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.calander_item,parent,false);


        return new CalendarViewHolder(inflatedLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        Calendar cal = calendarList.get(position);
        holder.DateView.setText(cal.getDate());
        if (cal.isButton1() == false){
            holder.button1.setBackgroundColor(Color.RED);
        }
        if (cal.isButton2() == false){
            holder.button2.setBackgroundColor(Color.RED);
        }
        if (cal.isButton3() == false){
            holder.button3.setBackgroundColor(Color.RED);
        }
        if (cal.isButton4() == false){
            holder.button4.setBackgroundColor(Color.RED);
        }
        if (cal.isButton5() == false){
            holder.button5.setBackgroundColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
