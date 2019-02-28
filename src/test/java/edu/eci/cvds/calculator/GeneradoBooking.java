package edu.eci.cvds.calculator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;
import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;



import edu.eci.cvds.model.SeatCategory;


public class GeneradoBooking {
	

	public static Gen<SeatCategory> seat(){
		return Generate.enumValues(SeatCategory.class);
	}
	
	public static Gen<Integer> asientos(){
		qt();
		return integers().between(0, 101);
	}
	
}
