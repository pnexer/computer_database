package com.excilys.formation.cdb.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "computer")
public class Computer {
  
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    @NotNull
    @Size(min=2, max=60)
    @Pattern(regexp="^[\\wÀ-ÿ]+[\\wÀ-ÿ_\\-'\\+\\*. ]+$")
    private String name;
    
    @Pattern(regexp="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))")
    private LocalDate dateIntroduced;
    
    @Pattern(regexp="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))")
    private LocalDate dateDiscontinued;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "company_id")
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
        res += " | name= " + this.name;
        res += " | dateIntro= " + this.dateIntroduced;
        res += " | dateDisco= " + this.dateDiscontinued;
        res += " | manufactor= " + this.manufactor.getName();
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
