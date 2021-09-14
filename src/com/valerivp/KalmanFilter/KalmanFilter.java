package com.valerivp.KalmanFilter;
// package es.kio4.KalmanFilter; // New version.

//  © Juan Antonio Villalpando 
// kio4.com
// Creacion de extensiones. Junio 2017.
// Esta extension calcula la hipotenusa mediante el Teorema de KalmanFilter.

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

@DesignerComponent(version = KalmanFilter.VERSION,
    category = ComponentCategory.EXTENSION,
    nonVisible = true)
@SimpleObject(external = true)
public class KalmanFilter extends AndroidNonvisibleComponent implements Component {

    public static final int VERSION = 3;
    private ComponentContainer container;
 
    public KalmanFilter(ComponentContainer container) {
        super(container.$form());
        this.container = container;
		reset();
    }

	private boolean f1537e;// = false;
    private double f1533a;// = 0.125d;
    private double f1534b;// = 0.8d;

    private double f1535c;

    private double f1536d;

 
    @SimpleFunction(description = "Filter reset")
    public void reset() {
		f1537e = false;
		f1533a = 0.125d;
		f1534b = 0.8d;
    }
	
    @SimpleFunction(description = "Filter value")
    public double filter(double value) {
        double d3;
        double d4;
        if (!this.f1537e) {
            this.f1537e = true;
            d4 = value;
            d3 = 1.0d;
        } else {
            d4 = this.f1535c;
            d3 = this.f1536d + this.f1533a;
        }
        double d5 = d3 / (this.f1534b + d3);
        double d6 = d4 + ((value - d4) * d5);
        this.f1535c = d6;
        this.f1536d = (1.0d - d5) * d3;
        return d6;
    }



 } 
