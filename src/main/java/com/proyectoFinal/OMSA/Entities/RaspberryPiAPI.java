package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
public class  RaspberryPiAPI implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroSerial;
    private String ipAddress;
    private Integer puerto;
    private String macAddress;

    @OneToOne
    private Autobus autobus;

    private RaspberryPiAPI() {

    }

    public RaspberryPiAPI(String numeroSerial, String ipAddress, Integer puerto, String macAddress, Autobus autobus) {
        this.numeroSerial = numeroSerial;
        this.ipAddress = ipAddress;
        this.puerto = puerto;
        this.macAddress = macAddress;
        this.autobus = autobus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroSerial() {
        return numeroSerial;
    }

    public void setNumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }
}
