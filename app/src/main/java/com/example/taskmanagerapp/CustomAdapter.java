package com.example.taskmanagerapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> task_id, task_title, task_description, task_date;

    CustomAdapter(Activity activity, Context context, ArrayList<String> task_id, ArrayList<String> task_title, ArrayList<String> task_description,
                  ArrayList<String> task_date){
        this.activity = activity;
        this.context = context;
        this.task_id = task_id;
        this.task_title = task_title;
        this.task_description = task_description;
        this.task_date = task_date;
    }

    //USING THE MY_ROW TEMPLATE
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.task_id_txt.setText(String.valueOf(task_id.get(position)));
        holder.task_title_txt.setText(String.valueOf(task_title.get(position)));
        holder.task_description_txt.setText(String.valueOf(task_description.get(position)));
        holder.task_date_txt.setText(String.valueOf(task_date.get(position)));

        // Format the due date from String to Date format
        String formattedDate = formatDate(task_date.get(position));
        holder.task_date_txt.setText(formattedDate);

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(task_id.get(position)));
                intent.putExtra("title", String.valueOf(task_title.get(position)));
                intent.putExtra("description", String.valueOf(task_description.get(position)));
                intent.putExtra("date", String.valueOf(task_date.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    // TO GET THE NUMBERS OF TASK LISTED
    @Override
    public int getItemCount() {
        return task_id.size();
    }

    // TO CORRECTLY FORMAT
    private String formatDate(String dateStr) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        SimpleDateFormat sdfOutput = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());

        try {
            Date date = sdfInput.parse(dateStr);
            return sdfOutput.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Unknown date";
        }
    }

    //ENSURE PROPER AND EFFICIENT RECYCLERVIEW (EG. CROLLING)
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView task_id_txt, task_title_txt, task_description_txt, task_date_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_id_txt = itemView.findViewById(R.id.task_id_txt);
            task_title_txt = itemView.findViewById(R.id.task_title_txt);
            task_description_txt = itemView.findViewById(R.id.task_description_txt);
            task_date_txt = itemView.findViewById(R.id.task_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

    }

}