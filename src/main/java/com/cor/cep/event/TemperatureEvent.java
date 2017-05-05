package com.cor.cep.event;

import java.util.Date;

/**
 * Immutable Temperature Event class. The process control system creates these events. The
 * TemperatureEventHandler picks these up and processes them.
 */
public class TemperatureEvent {

    /** Temperature in Celcius. */
    private int temperature;
    
    /** Temperature in Celcius. */
    private int fc;
    
    /** Temperature in Celcius. */
    private int fr;
    
    /** Temperature in Celcius. */
    private int inf;
    
    /** Temperature in Celcius. */
    private boolean correr;
    
    /** Temperature in Celcius. */
    private boolean andar;
        
    /** Temperature in Celcius. */
    private boolean sentar;
    
    /** Temperature in Celcius. */
    private String dia;
    
    /** Time temerature reading was taken. */
    private Date timeOfReading;
    
    /**
     * Single value constructor.
     * @param value Temperature in Celsius.
     */
    /**
     * Temeratur constructor.
     * @param temperature Temperature in Celsius
     * @param timeOfReading Time of Reading
     */
    public TemperatureEvent(int temperature, Date timeOfReading, int fc, int fr, int inf, boolean correr, boolean andar, boolean sentar, String dia ) {
        this.temperature = temperature;
        this.timeOfReading = timeOfReading;
        this.fc = fc;
        this.fr = fr;
        this.inf = inf;
        this.correr = correr;
        this.andar = andar;
        this.sentar = sentar;
        this.dia = dia;
    }

    /**
     * Get the Temperature.
     * @return Temperature in Celsius
     */
    public int getTemperature() {
        return temperature;
    }
    
    
    public int getFc() {
        return fc;
    }
     
    public int getFr() {
        return fr;
    }
    
    public int getInf() {
        return inf;
    }
     
    public int get() {
        return inf;
    }
    
      public boolean isAndar() {
	  return andar;
     }


	public boolean isCorrer() {
		return correr;
	}

	

	public boolean isSentar() {
		return sentar;
	}

   public String getDia() {
		return dia;
	}
       
    /**
     * Get time Temperature reading was taken.
     * @return Time of Reading
     */
    public Date getTimeOfReading() {
        return timeOfReading;
    }

    @Override
    public String toString() {
        return "ActivityEvent={Fc="+ fc+", Fr="+ fr+", Intensidade="+ inf+", Andar="+ andar+", Correr="+ correr+", Sentar="+ sentar+", Dia='"+ dia+"'}";
    }

}
