package com.example.onlinevotingsystem;

public class CandidateModel {
    String name, symbol;

    public CandidateModel(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() { return name; }
    public String getSymbol() { return symbol; }
}