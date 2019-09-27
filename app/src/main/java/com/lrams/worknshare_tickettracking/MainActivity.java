package com.lrams.worknshare_tickettracking;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Ticket> ticketList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TicketsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TicketsAdapter(ticketList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // set the adapter
        recyclerView.setAdapter(mAdapter);

        prepareTicketData();
    }

    private void prepareTicketData() {
        Ticket ticket = new Ticket("Ecran bleu", "L'écran est tout d'un coup devenu bleu...", "Nouveau");
        ticketList.add(ticket);

        ticket = new Ticket("Inside Out", "Animation, Kids & Family", "2015");
        ticketList.add(ticket);

        ticket = new Ticket("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        ticketList.add(ticket);

        ticket = new Ticket("Shaun the Sheep", "Animation", "2015");
        ticketList.add(ticket);

        ticket = new Ticket("The Martian", "Science Fiction & Fantasy", "2015");
        ticketList.add(ticket);

        ticket = new Ticket("Mission: Impossible Rogue Nation", "Action", "2015");
        ticketList.add(ticket);

        ticket = new Ticket("Up", "Animation", "2009");
        ticketList.add(ticket);

        ticket = new Ticket("Star Trek", "Science Fiction", "2009");
        ticketList.add(ticket);

        ticket = new Ticket("The LEGO Ticket", "Animation", "2014");
        ticketList.add(ticket);

        ticket = new Ticket("Iron Man", "Action & Adventure", "2008");
        ticketList.add(ticket);

        ticket = new Ticket("Aliens", "Science Fiction", "1986");
        ticketList.add(ticket);

        ticket = new Ticket("Chicken Run", "Animation", "2000");
        ticketList.add(ticket);

        ticket = new Ticket("Back to the Future", "Science Fiction", "1985");
        ticketList.add(ticket);

        ticket = new Ticket("Raiders of the Lost Ark", "Action & Adventure", "1981");
        ticketList.add(ticket);

        ticket = new Ticket("Goldfinger", "Action & Adventure", "1965");
        ticketList.add(ticket);

        ticket = new Ticket("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        ticketList.add(ticket);

        mAdapter.notifyDataSetChanged();
    }
}