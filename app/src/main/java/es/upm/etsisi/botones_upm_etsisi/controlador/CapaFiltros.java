package es.upm.etsisi.botones_upm_etsisi.controlador;

import android.graphics.Bitmap;

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
    static public Bitmap cambiar_color(Bitmap bitmap_original, ORDEN orden){

        Filtros filtro = new Filtros(bitmap_original);

        switch (orden){
            case SEPIA:{
                bitmap_original = filtro.sepiaImagen(bitmap_original);
                break;}
            case GRIS:{
                bitmap_original = filtro.escalaGrisesImagen(bitmap_original);
                break;}
            case INVERT:{
                bitmap_original = filtro.invertirColoresImagen(bitmap_original);
                break;}
            case AZUL:{
                bitmap_original = filtro.azuladoImagen(bitmap_original);
                break;}
            case SOLAR:{
                bitmap_original = filtro.solarizarImagen(bitmap_original);
                break;}
            case PBAJO:{
                bitmap_original = filtro.pasoBajo(bitmap_original);
                break;}
            case MEDIANA:{
                bitmap_original = filtro.mediana(bitmap_original);
                break;}
            case GRADIENTE:{
                bitmap_original = filtro.gradiente(bitmap_original);
                break;}
            case PALTO:{
                bitmap_original = filtro.pasoAlto(bitmap_original);
                break;}
            case SOBEL:{
                bitmap_original = filtro.sobel(bitmap_original);
                break;}
            case PREWITT:{
                bitmap_original = filtro.prewitt(bitmap_original);
                break;}
            case ROBERTS:{
                bitmap_original = filtro.roberts(bitmap_original);
                break;}
            case FREICHEN:{
                bitmap_original = filtro.freiChen(bitmap_original);
                break;}
            case LAPLACE:{
                bitmap_original = filtro.laplaciana(bitmap_original);
                break;}
            case RLAPLACE:{
                bitmap_original = filtro.realceLaplaciana(bitmap_original);
                break;}
            case SROBERTS:{
                bitmap_original = filtro.suavizadoRoberts(bitmap_original);
                break;}

        }

        return bitmap_original;
    }


    /**
     * Método que modifica una imagen según un valor prefijado.
     *@param orden Elige la modificación que se va a efectuar
     *@param umbral Elige el umbral sobre el que tiene que actuar el filtro
     */
    static public Bitmap cambiar_color_variable(Bitmap bitmap_original, ORDEN orden, int umbral){

        Filtros filtro = new Filtros(bitmap_original);

        switch (orden){
            case BYN:{
                bitmap_original = filtro.blancoNegroImagen(bitmap_original,umbral);
                break;}
            case TRANS:{
                bitmap_original = filtro.transparenciaImagen(bitmap_original, umbral);
                break;}
            case MATIZ:{
                bitmap_original = filtro.matizImagen(bitmap_original, umbral);
                break;}
            case SATURA:{
                bitmap_original = filtro.saturacionImagen(bitmap_original, umbral);
                break;}
            case INTENSIDAD:{
                bitmap_original = filtro.intensidadImagen(bitmap_original, umbral);
                break;}
            case BRILLO:{
                bitmap_original = filtro.brilloImagen(bitmap_original, umbral);
                break;}
            case CONTRASTE:{
                bitmap_original = filtro.contrasteImagen(bitmap_original, umbral);
                break;}
            case POST:{
                bitmap_original = filtro.posterizarImagen(bitmap_original, umbral);
                break;}
        }

        return bitmap_original;

    }

}
