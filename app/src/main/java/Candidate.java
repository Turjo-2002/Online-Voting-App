package com.example.onlinevotingsystem;

public class Candidate {

    private String id;
    private String name;
    private String party;
    private String area;

    // Constructor
    public Candidate(
            String id,
            String name,
            String party,
            String area
    ) {
        this.id = id;
        this.name = name;
        this.party = party;
        this.area = area;
    }


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getParty() {
        return party;
    }


    public String getArea() {
        return area;
    }
}