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
	private Integer id;
    
    @NotNull
    @Size(min=2, max=60)
    @Pattern(regexp="^[\\wÀ-ÿ]+[\\wÀ-ÿ_\\-'\\+\\*. ]+$")
    private String name;
    
    @Pattern(regexp="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))")
    private LocalDate introduced;
    
    @Pattern(regexp="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))")
    private LocalDate discontinued;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "company_id")
    private Company company;



  public Computer() {
        
    }

    public Computer(String name) {
        this.name = name;
        company = new Company();
}
    
    public Computer(String name,LocalDate introduced,LocalDate discontinued,Company company) {
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.company = company;
}
    
    public Computer(Integer id,String name,LocalDate introduced,LocalDate discontinued,Company company) {
    	this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.company = company;
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

    public Optional<LocalDate> getIntroduced() {
        return Optional.ofNullable(introduced);
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public Optional<LocalDate> getDiscontinued() {
        return Optional.ofNullable(discontinued);
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public Optional<Company> getCompany() {
        return Optional.ofNullable(company);
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        String res = " ";
        res += "id= " + this.id;
        res += " | name= " + this.name;
        res += " | dateIntro= " + this.introduced;
        res += " | dateDisco= " + this.discontinued;
        res += " | company= " + this.company.getName();
        return res;
    }

}
