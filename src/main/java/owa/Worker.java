package owa;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import utils.AreaAptitud;
import utils.OwaUtileria;
import utils.FuncionValor;
import utils.PixelOwa;

/**
 *
 * @author victor
 */
public class Worker implements Runnable{
    private final FuncionValor [] capas;
    private final double alpha;
    private final WritableRaster rasterResultado;
    private final int fila;
    private final AreaAptitud areaApt;
    
    public Worker(FuncionValor [] capas,double alpha,int fila,WritableRaster rasterResultado,AreaAptitud areaApt){
        this.capas = capas;
        this.alpha = alpha;
        this.rasterResultado = rasterResultado;
        this.fila = fila;
        this.areaApt=areaApt;
    }
    @Override
    public void run(){
        BufferedImage resultadoInt = new BufferedImage(this.capas[0].getColumnas(), capas[0].getRenglones(), BufferedImage.TYPE_USHORT_GRAY);
        WritableRaster rasterResultadoInt = resultadoInt.getRaster();
        
        for(int i=0; i<this.capas[0].getColumnas();i++){
            double pixOwa=0;
            double[] listaValores = OwaUtileria.getValores(this.capas,i,this.fila);// Trae la lista de los pixeles de las capas en la ubicacion i,j
            double[] listaPesos = OwaUtileria.getPesos(this.capas);
            boolean existeNan = FuncionValor.contineNaN(listaValores);
            if (existeNan==true){
                  rasterResultado.setSample(i,this.fila, 0, 0);
                }else{
                  pixOwa = PixelOwa.valorPixel(listaValores, listaPesos, this.alpha);
                  rasterResultado.setSample(i,this.fila, 0, OwaUtileria.catAptitud(pixOwa));
                  this.areaApt.cuentaArea(OwaUtileria.catAptitud(pixOwa));

                }
        }
        }
    }

    


