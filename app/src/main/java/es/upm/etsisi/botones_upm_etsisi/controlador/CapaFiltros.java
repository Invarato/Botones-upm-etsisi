package es.upm.etsisi.botones_upm_etsisi.controlador;

import android.content.Context;
import android.graphics.Bitmap;

import es.upm.etsisi.imagefilter.Filtros;


public class CapaFiltros {

    public enum ORDEN {
        SEPIA("Sepia"),
        BYN("Blanco y Negro"),
        GRIS("Gris"),
        INVERT("Invertir"),
        AZUL("Azulado"),
        TRANS("Transparencia"),
        PBAJO("Difusion"),
        MATIZ("Matiz"),
        SATURA("Saturacion"),
        INTENSIDAD("Intensidad"),
        BRILLO("Brillo"),
        SOLAR("Solarizar"),
        POST("Posterizar"),
        CONTRASTE("Contraste"),
        MEDIANA("Mediana"),
        GRADIENTE("Gradiente"),
        SOBEL("Sobel"),
        PREWITT("Prewitt"),
        ROBERTS("Roberts"),
        FREICHEN("Frei-Chen"),
        PALTO("Realce"),
        LAPLACE("Laplaciana"),
        RLAPLACE("Realce Laplaciana"),
        SROBERTS("Suavizado Roberts");

        String orden;
        ORDEN (String orden){
            this.orden = orden;
        }

        public String toString (){
            return orden;
        }
    }


    /**
     * Método que modifica una imagen completamente. Ya sea solo el color, o algún filtrado.
     *@param orden Elige la modificación que se va a efectuar
     */
    static public Bitmap cambiar_color(Context context, Bitmap bitmap_original, ORDEN orden){

        Filtros f = new Filtros(context, bitmap_original);

        switch (orden){
            case SEPIA:{
                f.sepia();
                break;}
            case GRIS:{
                f.escalaDeGrisesHDR();
                break;}
            case INVERT:{
                f.invertir();
                break;}
            case AZUL:{
                 f.azulado();
                break;}
            case SOLAR:{
                 f.solarizar();
                break;}
//TODO            case PBAJO:{
//TODO                 f.pasoBajo();
//TODO                break;}
            case MEDIANA:{
                 f.mediana();
                break;}
//TODO             case GRADIENTE:{
//TODO                  f.gradiente();
//TODO                 break;}
//TODO             case PALTO:{
//TODO                  f.pasoAlto();
//TODO                 break;}
//TODO             case SOBEL:{
//TODO                  f.sobel();
//TODO                 break;}
//TODO             case PREWITT:{
//TODO                  f.prewitt();
//TODO                 break;}
//TODO             case ROBERTS:{
//TODO                  f.roberts();
//TODO                 break;}
//TODO             case FREICHEN:{
//TODO                  f.freiChen();
//TODO                 break;}
//TODO             case LAPLACE:{
//TODO                  f.laplaciana();
//TODO                 break;}
//TODO             case RLAPLACE:{
//TODO                  f.realceLaplaciana();
//TODO                 break;}
//TODO             case SROBERTS:{
//TODO                  f.suavizadoRoberts();
//TODO                 break;}

        }

        return f.getBitmapProcessed();
    }


    /**
     * Método que modifica una imagen según un valor prefijado.
     *@param orden Elige la modificación que se va a efectuar
     *@param umbral Elige el umbral sobre el que tiene que actuar el filtro
     */
    static public Bitmap cambiar_color_variable(Context context, Bitmap bitmap_original, ORDEN orden, int umbral){
        Filtros f = new Filtros(context, bitmap_original);

        switch (orden){
            case BYN:{
                f.blancoNegro(umbral);
                break;}
//TODO            case TRANS:{
//TODO                f.transparenciaImagen( umbral);
//TODO                break;}
            case MATIZ:{
                f.matiz(umbral);
                break;}
            case SATURA:{
                f.saturacion(umbral);
                break;}
            case INTENSIDAD:{
                f.intensidad(umbral);
                break;}
            case BRILLO:{
                f.brillo(umbral);
                break;}
            case CONTRASTE:{
                f.contraste(umbral);
                break;}
            case POST:{
                f.posterizar(umbral);
                break;}
        }

        return f.getBitmapProcessed();
    }

}
