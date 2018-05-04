package com.ttn.Controller;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Books {

    @ManyToOne
    User user;
}
