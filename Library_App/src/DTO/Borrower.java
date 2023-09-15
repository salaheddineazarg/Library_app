package DTO;

public class Borrower {
    private int id;
    private String name;
    private String n_phone;
    private String cih ;
    private String adresse;


    public void setId(int id) {
        this.id = id;
    }

    public int  getId(){

        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getN_phone() {
        return n_phone;
    }

    public void setN_phone(String n_phone) {
        this.n_phone = n_phone;
    }

    public String getCih() {
        return cih;
    }

    public void setCih(String cih) {
        this.cih = cih;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
