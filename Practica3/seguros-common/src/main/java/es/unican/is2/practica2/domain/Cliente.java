package es.unican.is2.practica2.domain;

import java.util.LinkedList;
import java.util.List;

public class Cliente {

    private String dni;
    private String nombre;
    private boolean minusvalia;
    private List<Seguro> seguros = new LinkedList<>();

    
    public List<Seguro> getSeguros() { return seguros; }
    
    public void setSeguros(List<Seguro> seguros) { this.seguros = seguros; }

    
    public String getNombre() { return nombre; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }

    
    public String getDni() { return dni; }
    
    public void setDni(String dni) { this.dni = dni; }

    
    public boolean getMinusvalia() { return minusvalia; }
    
    public void setMinusvalia(boolean minusvalia) { this.minusvalia = minusvalia; }

    /**
     * Total a pagar por todos los seguros.
     * Si el cliente tiene minusvalía: -25% sobre el total.
     */
    public double totalSeguros() {
    	
        double total = 0.0;
        
        if (seguros != null) {
        	
            for (Seguro s : seguros) {
            	
                if (s != null) total += s.precio();
            }
        }
        
        if (minusvalia) total *= 0.75;
        
        return total;
    }
}