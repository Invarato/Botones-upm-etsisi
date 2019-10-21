package es.upm.etsisi.botones_upm_etsisi.vista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import es.upm.etsisi.botones_upm_etsisi.R;
import es.upm.etsisi.botones_upm_etsisi.controlador.Capa;

import java.util.LinkedList;

/** Clase interfaz de PFG_Intbotones
 * @author Ramón Invarato Menéndez
 * @author Roberto Pérez
 */
public class interfaz extends Activity {
	
	private Button button_aumentar, button_reducir, button_escalar;
	private ImageButton imagebutton_arriba, imagebutton_abajo, imagebutton_derecha, imagebutton_izquierda, imagebutton_guardar, imagebutton_cargar;
	private TextView textView_tamanio;
	private ImageView imageView_NoImagen;
	
	private FrameLayout marco;   
	
	private int tamanio = 0;
	private final int const_var_tamanio = 5, max_tamanio = 100, min_tamanio = -100;
	private final int const_var_mover = 20;
	private final boolean imgMantenerseEnPantalla = true;
	
	
	
	private Context contexto;
	
	private static final int IMAGEN_SELECCIONADA = 1;
	
	private Capa capa;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_interfaz);
        
        contexto = this;

        button_escalar = findViewById(R.id.button_escalar);
        button_escalar.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				new emergente_coordenadas(contexto);
			}
		});
        
        
        button_aumentar = findViewById(R.id.button_mas);
        button_aumentar.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				cambiar_tamanio(true);
			}
		});
        
        button_reducir = findViewById(R.id.button_menos);
        
        button_reducir.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				cambiar_tamanio(false);
			}
		});
        
        
        imagebutton_arriba = findViewById(R.id.imageButton_arriba);
        imagebutton_arriba.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				capa.desplazar_tanto_como(0, -const_var_mover);
			}
		});
        
        imagebutton_abajo = findViewById(R.id.imageButton_abajo);
        imagebutton_abajo.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				capa.desplazar_tanto_como(0, const_var_mover);
			}
		});
        
        imagebutton_derecha = findViewById(R.id.imageButton_derecha);
        imagebutton_derecha.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				capa.desplazar_tanto_como(const_var_mover, 0);
			}
		});
        
        imagebutton_izquierda = findViewById(R.id.imageButton_izquierda);
        imagebutton_izquierda.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				capa.desplazar_tanto_como(-const_var_mover, 0);
			}
		});
        
        
        imagebutton_guardar = findViewById(R.id.imageButton_guardar);
        imagebutton_guardar.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				capa.guardar_imagen("TFG");
			}
		});
        
        imagebutton_cargar = findViewById(R.id.imageButton_cargar);
        imagebutton_cargar.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				seleccionar_imagen ();
			}
		});

        textView_tamanio = findViewById(R.id.textView_tamanio);

        imageView_NoImagen = findViewById(R.id.imageView_NoImagen);
        
    	marco = findViewById(R.id.frameLayout_ContenedorImagenes);
    }
    
    
    
    /** Cambia el tamaño y cambia el texto del porcentaje
     * @param aumentar Si TRUE entonces aumenta, FALSE disminuye
     */
    private void cambiar_tamanio(boolean aumentar){
    	if (aumentar){
    		tamanio+=const_var_tamanio;
        	if (tamanio>max_tamanio)
        		tamanio=max_tamanio;
        	else
        		capa.cambiar_tamanio_imagen(const_var_tamanio);
    	}else{
    		tamanio -=const_var_tamanio;
    		if (tamanio<min_tamanio)
        		tamanio=min_tamanio;
    		else
    			capa.cambiar_tamanio_imagen(-const_var_tamanio);
    	}
    	
    	textView_tamanio.setText(tamanio+"%");
    }
    
    
    

    


    
    
    
    //Seleccionar archivo y cargarlo.........................................
	public void seleccionar_imagen (){
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);	//Permite al usuarios seleccionar un tipo de datos y devolverlo
		intent.setType("image/*"); //Tipos de archivos
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		try {	//Prueba que exista un selector de ficheros
			this.startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), IMAGEN_SELECCIONADA);
		} catch (android.content.ActivityNotFoundException ex) {	//En caso de que no exista ningún selector de ficheros
			Toast.makeText(this, "No existe ningún administrador de imágenes. Instale uno", Toast.LENGTH_SHORT).show();
		}
	}
	
	//Cuando se seleccione la imagen entra aquí
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK) {
	        if (requestCode == IMAGEN_SELECCIONADA) {
	        	marco.removeAllViews();
	        	capa = new Capa(this);
	        	marco.addView(capa);
	        	
	        	imageView_NoImagen.setVisibility(ImageView.GONE);
	        	
	        	Uri uriSeleccionado = data.getData();
	        	capa.cargar_imagen(uriSeleccionado);

	        	capa.ajustar_imagen_a(marco.getWidth(), marco.getHeight());
	        }
	    }
	}
	
    
    
	/** Clase interna que genera la ventana emergente de escalar
	 *
	 */
	private class emergente_coordenadas {
		private Context contexto;
		
		private EditText edittext_x_der, edittext_y_der, edittext_x_izq, edittext_y_izq, edittext_escalar;

		LinkedList<Capa.SIMBOLO> lista_elementos;
		Capa.SIMBOLO simboloElegido;
		
		public emergente_coordenadas (Context contexto){
			this.contexto = contexto;
			mensaje ();
		}

	    private void mensaje (){
	    	DialogInterface.OnClickListener dialogoListener = new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int which) {
		        	try {
						capa.modificar_pixeles_de_region (simboloElegido,
								Integer.parseInt(edittext_escalar.getText()+""),
								Integer.parseInt(edittext_x_der.getText()+""),
								Integer.parseInt(edittext_y_der.getText()+""),
								Integer.parseInt(edittext_x_izq.getText()+""),
								Integer.parseInt(edittext_y_izq.getText()+""));
					} catch (Exception e) {
						e.printStackTrace();
					};
	    		}
	    	};

	    	//Frame hinchado.............................
	    	LayoutInflater inflater = getLayoutInflater();
	    	FrameLayout frameHinchado = new FrameLayout(contexto);
	    	frameHinchado.addView(inflater.inflate(R.layout.emergente_escalar, frameHinchado, false));

	    	View view = frameHinchado.getChildAt(0);
	    	//Frame hinchado.............................
	    	
	    	//Campos......................................
	    	edittext_x_der  = view.findViewById(R.id.EditText_x1);
	    	edittext_y_der  = view.findViewById(R.id.EditText_y1);
	    	edittext_x_izq  = view.findViewById(R.id.EditText_x2);
	    	edittext_x_izq.setText(""+capa.get_tamImg_anchura());
	    	edittext_y_izq  = view.findViewById(R.id.EditText_y2);
	    	edittext_y_izq.setText(""+capa.get_tamImg_altura());
	    	edittext_escalar  = view.findViewById(R.id.EditText_escalar);
	    	//Campos......................................
	    	
	    	//Spinner.....................................	    	
	    	LinkedList<String> lista_elementos_nombre = new LinkedList<>();
	    	lista_elementos = new LinkedList<>();
	    	for (Capa.SIMBOLO c:Capa.SIMBOLO.values()){
	    		lista_elementos_nombre.add(c.toString());
	    		lista_elementos.add(c);
	    	}

			Spinner spinner_operacion = view.findViewById(R.id.spinner_operacion);

	    	ArrayAdapter<String> adaptadorSpinner_simbolos = new ArrayAdapter<String>(contexto, android.R.layout.simple_spinner_item, lista_elementos_nombre);
	    	adaptadorSpinner_simbolos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    	spinner_operacion.setAdapter(adaptadorSpinner_simbolos);
	    	spinner_operacion.setSelection(0);
	    	
	    	spinner_operacion.setOnItemSelectedListener(new OnItemSelectedListener(){
				public void onItemSelected(AdapterView<?> arg0, View arg1,int id, long arg3) {
					simboloElegido = lista_elementos.get(id);
				}

				public void onNothingSelected(AdapterView<?> arg0) {
				}
			});			
	    	//Spinner.....................................

	    	//Diálogo....................................
	    	new AlertDialog.Builder(contexto)
	    	.setPositiveButton("Procesar", dialogoListener)
	    	.setNegativeButton("Cancelar", null)
	    	.setView(frameHinchado)
	    	.show();
	    	//Diálogo....................................
	    }
		
	}
	
	

}

