/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Formatter;
import java.util.FormatterClosedException;

/**
 *
 * @author victor
 */
public class AreaAptitud implements IAreaAptitud {
    private int muyBaja=0;
    private int baja=0;
    private int moderada=0;
    private int alta=0;
    private int muyAlta=0;
    private static Formatter salida;
    
    @Override
    public synchronized void cuentaArea(int cat){
        if(cat==1){this.muyBaja++;}
        if(cat==2){this.baja++;}
        if(cat==3){this.moderada++;}
        if(cat==4){this.alta++;}
        if(cat==5){this.muyAlta++;}
        
    }
 
    @Override
    public void escribeReporte(String pathReporte){
        File pathRepo = new File(pathReporte+BaseDatos.fechaFormat("_reporte.csv"));
            if (pathRepo.exists()) {
                System.out.println("El archivo del reporte ya existe. No se va a sobrescribir.");
                }
                else{
                    try {
                        salida = new Formatter(pathReporte+BaseDatos.fechaFormat("_reporte.csv")); // abre el archivo
                        salida.format("%s, %s\n", "Categoria de aptitud","Area (km2)");
                        salida.format("%s, %d\n", "Muy alta",this.muyAlta/100);
                        salida.format("%s, %d\n", "Alta",this.alta/100);
                        salida.format("%s, %d\n", "Moderada",this.moderada/100);
                        salida.format("%s, %d\n", "Baja",this.baja/100);
                        salida.format("%s, %d\n", "Muy baja",this.muyBaja/100);
                        }catch(IOException e){
                            e.printStackTrace();
                            }
                    salida.close();
                    }
    }
    
}
