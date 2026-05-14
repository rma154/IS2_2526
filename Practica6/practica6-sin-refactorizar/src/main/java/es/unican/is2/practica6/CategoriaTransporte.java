package es.unican.is2.practica6;

/**
 * Enumeracion usada como codigo de tipo del transporte.
 *
 * METRICAS INICIALES:
 * Criterio WMC: WMC es la suma de la complejidad ciclomatica de los metodos
 * con cuerpo. Los metodos implicitos del enum no se cuentan.
 * Criterio CCog: se cuentan estructuras de control, anidamiento, else/else if,
 * secuencias de operadores logicos y recursividad. Aqui no hay codigo propio.
 *
 * Calculo WMC: no hay constructores ni metodos explicitos con cuerpo. WMC = 0; WMCn = 0.00.
 * Calculo CCog: no hay estructuras de control. CCog = 0; CCogn = 0.00.
 * CBO = 0: no usa otras clases de la aplicacion ni excepciones.
 * DIT = 1; NOC = 0.
 */
// CBO = 0.
// DIT = 1; NOC = 0.
public enum CategoriaTransporte {

    Mercancias, MercanciasPeligrosas, Personas
}
