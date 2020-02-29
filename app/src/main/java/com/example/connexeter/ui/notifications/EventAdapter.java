package com.example.connexeter.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context mCtx;
    private List<Event> eventList;

    public EventAdapter(Context mCtx, List<Event> eventList) {
        this.mCtx = mCtx;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_events, null);
        EventViewHolder holder = new EventViewHolder(view);
        return holder;
    }

    //sets values for events via saved data
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.textViewTitle.setText(event.getTitle());
        holder.textViewDescription.setText(event.getDescription());
        if (!event.getEndTime().equals("")) {
            holder.textViewTime.setText(event.getStartTime()+" to "+event.getEndTime());
        } else {
            holder.textViewTime.setText(event.getStartTime());
        }
        holder.textViewDate.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle, textViewDescription, textViewTime, textViewDate;

        //saves data of each events' information
        public EventViewHolder(View eventView) {
            super(eventView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewDate = itemView.findViewById(R.id.textViewDate);


        }
    }

}
