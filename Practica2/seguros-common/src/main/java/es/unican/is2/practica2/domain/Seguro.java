package es.unican.is2.practica2.domain;

import java.time.LocalDate;

public class Seguro {

    private long id;
    private String matricula;
    private int potencia;
    private Cobertura cobertura;
    private LocalDate fechaInicio;
    private String conductorAdicional;

    // NUEVO: DNI del cliente propietario (para persistencia/validación)
    private String dniCliente;

    
    public long getId() { return id; }
    
    public void setId(long id) { this.id = id; }

    
    public String getMatricula() { return matricula; }
    
    public void setMatricula(String matricula) { this.matricula = matricula; }

    
    public int getPotencia() { return potencia; }
    
    public void setPotencia(int potencia) { this.potencia = potencia; }

    
    public Cobertura getCobertura() { return cobertura; }
    
    public void setCobertura(Cobertura cobertura) { this.cobertura = cobertura; }

    
    public LocalDate getFechaInicio() { return fechaInicio; }
    
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    
    public String getConductorAdicional() { return conductorAdicional; }
    
    public void setConductorAdicional(String conductorAdicional) { this.conductorAdicional = conductorAdicional; }

    
    public String getDniCliente() { return dniCliente; }
    
    public void setDniCliente(String dniCliente) { this.dniCliente = dniCliente; }

    /**
     * Precio:
     * - Base: TODO_RIESGO 1000, TERCEROS_LUNAS 600, TERCEROS 400
     * - Potencia: 90..110 +5%, >110 +20%
     * - Oferta: primer año -20%
     * - Si se consulta antes del inicio => 0
     */
    public double precio() {
    	
        LocalDate hoy = LocalDate.now();
        
        if (fechaInicio == null || hoy.isBefore(fechaInicio)) return 0.0;

        double base;
        
        switch (cobertura) {
        
            case TODO_RIESGO:      base = 1000.0; break;
            
            case TERCEROS_LUNAS:   base = 600.0;  break;
            
            case TERCEROS:
            	
            default:              base = 400.0;  break;
        }

        double precio = base;

        if (potencia >= 90 && potencia <= 110) {
        	
            precio = precio * 1.05;
            
        } else if (potencia > 110) {
        	
            precio = precio * 1.20;
        }

        // oferta durante el primer año desde la fecha de inicio
        if (hoy.isBefore(fechaInicio.plusYears(1))) {
        	
            precio = precio * 0.80;
        }

        return precio;
    }
}