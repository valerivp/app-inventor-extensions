package com.valerivp.Pifagoras;
// package es.kio4.Pifagoras; // New version.

//  © Juan Antonio Villalpando 
// kio4.com
// Creacion de extensiones. Junio 2017.
// Esta extension calcula la hipotenusa mediante el Teorema de Pifagoras.

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.*;

@DesignerComponent(version = Pifagoras.VERSION,
    description = "Teorema de Pifagoras. " + "Juan Antonio Villalpando - KIO4.COM ",
    category = ComponentCategory.EXTENSION,
    nonVisible = true,
    iconName = "")
@SimpleObject(external = true)
public class Pifagoras extends AndroidNonvisibleComponent implements Component {

    public static final int VERSION = 1;
    public static final float DEFAULT_CATETO_A = 3f;
	public static final float DEFAULT_CATETO_B = 4f;
    private ComponentContainer container;
    private double cateto_a = 0;
	private double cateto_b = 0;
 
    public Pifagoras(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        Cateto_A(DEFAULT_CATETO_A);
		Cateto_B(DEFAULT_CATETO_B);
    }

    // Obtener el valor.
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Cateto_A() {
        return cateto_a;
    }
	
	 @SimpleProperty(category = PropertyCategory.BEHAVIOR)
	public double Cateto_B() {
        return cateto_b;
    }

    // Establecer el valor.
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT, defaultValue = Pifagoras.DEFAULT_CATETO_A + "")
    @SimpleProperty(description = "Asigna el valor del cateto A. " +  "El separador decimal es el punto.")
    public void Cateto_A(double nuevoCateto_A) {
        this.cateto_a = nuevoCateto_A;
    }
	
	@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT, defaultValue = Pifagoras.DEFAULT_CATETO_B + "")
    @SimpleProperty(description = "Asigna el valor del cateto B. " +  "El separador decimal es el punto.")
    public void Cateto_B(double nuevoCateto_B) {
        this.cateto_b = nuevoCateto_B;
    }

    // Funcion para calcular la hipotenusa.
    @SimpleFunction(description = "Introduce los dos catetos y obtendras la hipotenusa.")
    public double Pifagoras(double catetoA, double catetoB) {
        double hipotenusa, cuadrado;
		
        hipotenusa = Math.sqrt((catetoA*catetoA)+(catetoB*catetoB));
		cuadrado = hipotenusa * hipotenusa;  // Calcula el cuadrado de la hipotenusa.
            
       YaCalculada(cuadrado); 

        return hipotenusa;
    }

    // Bloque disponible despues de calcular la hipotenusa.
    @SimpleEvent(description = "Muestra la hipotenusa al cuadrado.")
    public void YaCalculada(double sucuadrado){
        EventDispatcher.dispatchEvent(this, "YaCalculada", sucuadrado);
    }    

 } 
