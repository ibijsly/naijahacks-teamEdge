package com.securedEdgePay.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EndUser extends User{
    private String nin;
    private String vin;

    @Transient
    private String base64Image;
    private byte[] photo;

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
