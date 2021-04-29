package com.ss.utopia.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity(name = "seat")
public class Seats {

    @Id
    @Column(name = "flight_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "flight_id")
    @MapsId
    private Flight flight;

    private Integer first;
    private Integer business;
    private Integer economy;

   public Seats(Integer first, Integer business, Integer economy){
       this.first = first;
       this.business = business;
       this.economy = economy;
   }
   public Seats(Integer id){
       this.flight = new Flight(id);
   }
   public Seats(){

   }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public Integer getEconomy() {
        return economy;
    }

    public void setEconomy(Integer economy) {
        this.economy = economy;
    }


}
