package com.example.connexeter.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;

import java.util.ArrayList;

public class OlsAdapter extends RecyclerView.Adapter<OlsAdapter.ViewHolder> {
    public static ArrayList<addOLS> allFormats;

    public OlsAdapter(ArrayList<addOLS> allFormats) {
        this.allFormats = allFormats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_dashboard,
                parent, false);

        return new ViewHolder(v);//grace nivera is so cool!!!! kodi is not!!!!
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public final View view;

        public final TextView format;

        public final TextView className;

        public final TextView classLevel;

        public final TextView roomNumber;

        public final TextView teacherName;


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            format = view.findViewById(R.id.format);
            className = view.findViewById(R.id.className);
            classLevel = view.findViewById(R.id.classLevel);
            roomNumber = view.findViewById(R.id.roomNumber);
            teacherName = view.findViewById(R.id.teacherName);
        }

    }


    @Override


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        addOLS format = allFormats.get(position);

        holder.format.setText(format.getFormat());
        holder.className.setText(format.getClassName());
        holder.classLevel.setText(format.getClassLevel());
        holder.roomNumber.setText(format.getRoomNumber());
    }


    @Override

    public int getItemCount() {
        if (allFormats != null) {
            return allFormats.size();
        } else {
            return 0;
        }
    }
}