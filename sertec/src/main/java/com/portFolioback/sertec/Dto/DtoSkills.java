/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portFolioback.sertec.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author CarlosAdmin
 */
public class DtoSkills {
    @NotBlank
    private String nombreS;
    @NotBlank
    private String porcentaje;

    public DtoSkills() {
    }

    public DtoSkills(String nombreS, String porcentaje) {
        this.nombreS = nombreS;
        this.porcentaje = porcentaje;
    }

    public String getNombreS() {
        return nombreS;
    }

    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
