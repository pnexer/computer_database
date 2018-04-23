package com.excilys.formation.cdb.dto;


public class ComputerDTO {
    private int id;
    private String name;
    private String dateIntroduced;
    private String dateDiscontinued;
    private int manufactorId;
    private String manufactorName;
    
    public ComputerDTO() { };

    private ComputerDTO(ComputerDTOBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.dateIntroduced = builder.dateIntroduced;
        this.dateDiscontinued = builder.dateDiscontinued;
        this.manufactorId = builder.manufactorId;
        this.manufactorName = builder.manufactorName;
    }
    
    @Override
    public String toString() {
        String res = "";
        res += "id= " + this.id;
        res += " | nom: " + this.name;
        res += " | dateIntro: " + this.dateIntroduced;
        res += " | dateDisco: " + this.dateDiscontinued;
        res += " | manufactor: " + this.manufactorName + "}";
        return res;
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

    public String getDateIntroduced() {
        return dateIntroduced;
    }

    public void setDateIntroduced(String dateIntroduced) {
        this.dateIntroduced = dateIntroduced;
    }

    public String getDateDiscontinued() {
        return dateDiscontinued;
    }

    public void setDateDiscontinued(String dateDiscontinued) {
        this.dateDiscontinued = dateDiscontinued;
    }

    public int getManufactorId() {
        return manufactorId;
    }

    public void setManufactorId(int manufactorId) {
        this.manufactorId = manufactorId;
    }

    public String getManufactorName() {
        return manufactorName;
    }

    public void setManufactorName(String manufactorName) {
        this.manufactorName = manufactorName;
    }


    public static class ComputerDTOBuilder {
        private int id;
        private final String name;
        private String dateIntroduced;
        private String dateDiscontinued;
        private int manufactorId;
        private String manufactorName;

        public ComputerDTOBuilder(String name) {
            this.name = name;
        }

        public ComputerDTOBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ComputerDTOBuilder dateIntroduced(String dateIntroduced) {
            this.dateIntroduced = dateIntroduced;
            return this;
        }

        public ComputerDTOBuilder dateDiscontinued(String dateDiscontinued) {
            this.dateDiscontinued = dateDiscontinued;
            return this;
        }

        public ComputerDTOBuilder manufactorId(int manufactorId) {
            this.manufactorId = manufactorId;
            return this;
        }

        public ComputerDTOBuilder manufactorName(String manufactorName) {
            this.manufactorName = manufactorName;
            return this;
        }

        public ComputerDTO build() {
            return new ComputerDTO(this);
        }
    }
}
