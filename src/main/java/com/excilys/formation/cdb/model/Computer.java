package com.excilys.formation.cdb.model;

import java.time.LocalDate;
import java.util.Optional;

public class Computer {
  
	private int id;
    private String name;
    private LocalDate dateIntroduced;
    private LocalDate dateDiscontinued;
    private Company manufactor;

    private Computer(ComputerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.dateIntroduced = builder.dateIntroduced;
        this.dateDiscontinued = builder.dateDiscontinued;
        this.manufactor = builder.manufactor;
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

    public Optional<LocalDate> getDateIntroduced() {
        return Optional.ofNullable(dateIntroduced);
    }

    public void setDateIntroduced(LocalDate dateIntroduced) {
        this.dateIntroduced = dateIntroduced;
    }

    public Optional<LocalDate> getDateDiscontinued() {
        return Optional.ofNullable(dateDiscontinued);
    }

    public void setDateDiscontinued(LocalDate dateDiscontinued) {
        this.dateDiscontinued = dateDiscontinued;
    }

    public Optional<Company> getManufactor() {
        return Optional.ofNullable(manufactor);
    }

    public void setManufactor(Company manufactor) {
        this.manufactor = manufactor;
    }

    @Override
    public String toString() {
        String res = " ";
        res += "id= " + this.id;
        res += "| name= " + this.name;
        res += "| dateIntro= " + this.dateIntroduced;
        res += "| dateDisco= " + this.dateDiscontinued;
        res += "| manufactor= " + this.manufactor.getName();
        return res;
    }

    public static class ComputerBuilder {
        private int id;
        private final String name;
        private LocalDate dateIntroduced;
        private LocalDate dateDiscontinued;
        private Company manufactor;

        public ComputerBuilder(String name) {
            this.name = name;
        }

        public ComputerBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ComputerBuilder dateIntroduced(LocalDate dateIntroduced) {
            this.dateIntroduced = dateIntroduced;
            return this;
        }

        public ComputerBuilder dateDiscontinued(LocalDate dateDiscontinued) {
            this.dateDiscontinued = dateDiscontinued;
            return this;
        }

        public ComputerBuilder manufactor(Company manufactor) {
            this.manufactor = manufactor;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
