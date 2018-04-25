package com.excilys.formation.cdb.dto;


public class CompanyDTO {
    private int id;
    private String name;

    @Override
    public String toString() {
        String res = "";
        res += " id= " + this.id;
        res += "| nom= " + this.name;
        return res;
    }
    
    public CompanyDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
