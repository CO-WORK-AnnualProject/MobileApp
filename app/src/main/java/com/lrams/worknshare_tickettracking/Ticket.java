package com.lrams.worknshare_tickettracking;

class Ticket {
    private String objet, description, status;

    Ticket(String objet, String description, String status) {
        this.objet = objet;
        this.description = description;
        this.status = status;
    }

    String getObjet() {
        return objet;
    }

    String getStatus() {
        return status;
    }

    String getDescription() {
        return description;
    }

}