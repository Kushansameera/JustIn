package com.example.kushansameera.justin.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kushansameera.justin.R;
import com.example.kushansameera.justin.models.Events;

import java.util.List;

/**
 * Created by Kushan Sameera on 6/11/2017.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    List<Events> events;
    Context context;
    LayoutInflater layoutInflater;


    public EventsAdapter(Context context, List<Events> event) {
        this.events = event;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.event_card, parent, false);
        return new EventsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        Events mEvents = events.get(position);
        holder.eventName.setText(mEvents.getEventName());
        holder.eventTime.setText(mEvents.getEventTime());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public Events getItem(int position){
        return events.get(position);
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder {

        TextView eventName;
        TextView eventTime;

        public EventsViewHolder(View itemView) {
            super(itemView);

            eventName = (TextView) itemView.findViewById(R.id.txtEventName);
            eventTime = (TextView) itemView.findViewById(R.id.txtEventTime);

        }

    }
}
