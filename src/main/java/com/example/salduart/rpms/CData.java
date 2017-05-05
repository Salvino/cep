package com.example.salduart.rpms;


import java.io.Serializable;
//import javax.swing.ImageIcon;

public class CData implements Serializable {
    private static final long serialVersionUID = 46L;

    private String imei;
    private double latitude;
    private double longitude;
    private double velocidade;
    private String uuid;
    
    
CData(){
    
}

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }


    
    
    
    private int fc;


    public int getFc() {
        return fc;
    }

    public void setFc(int fc) {
        this.fc = fc;
    }


  @Override
  public String toString(){
	 String string = "";
	  return string;
  }





}
