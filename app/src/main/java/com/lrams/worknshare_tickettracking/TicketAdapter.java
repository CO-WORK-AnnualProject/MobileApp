package com.lrams.worknshare_tickettracking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.MyViewHolder> {

    private List<Ticket> ticketsList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, year, genre;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    TicketsAdapter(List<Ticket> ticketsList) {
        this.ticketsList = ticketsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Ticket ticket = ticketsList.get(position);
        holder.title.setText(ticket.getObjet());
        holder.genre.setText(ticket.getDescription());
        holder.year.setText(ticket.getStatus());
    }

    @Override
    public int getItemCount() {
        return ticketsList.size();
    }
}