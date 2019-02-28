package edu.eci.cvds.calculator;

import static org.quicktheories.QuickTheory.qt;



import org.junit.Test;

import edu.eci.cvds.calculator.AirlineCalculator;
import edu.eci.cvds.model.BookingOutput;
import edu.eci.cvds.model.BookingResult;
import edu.eci.cvds.model.SeatCategory;

/**
 * Test class for {@linkplain AirlineCalculator} class
 */
public class AirlineCalculatorTest {

	/**
	 * The class under test.
	 */
	private AirlineCalculator calculator = new AirlineCalculator();

	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void calculateTest() {
		
		qt()
		.forAll(GeneradoBooking.asientos(),GeneradoBooking.seat())
		.check((asientos,seats)->{
			
			BookingOutput calculate = calculator.calculate(asientos,seats);
			if (asientos > 0 && asientos <=  100) {
				if (seats.equals(SeatCategory.ECONOMY_CLASS)) {
					if (asientos >= 50 ) {	
						return BookingResult.COMPRA_LIMITE_50 == calculate.getResult() && !calculate.getCost().isPresent(); 
					}else {
						float price = asientos * seats.getPrice();
						if (asientos> 5 && asientos <10) {
							price -= asientos * seats.getPrice()*0.02;  
						}else if (asientos> 5 && asientos < 15) {
							price -= asientos * seats.getPrice()*0.10;
						}else if (asientos> 5 && asientos > 15) {
							price -= asientos * seats.getPrice()*0.20;
						}
						return BookingResult.SUCCESS == calculate.getResult() && calculate.getCost().isPresent();
					}
					
				}else if  (seats.equals(SeatCategory.FIRST_CLASS)) {
					if (asientos >= 15 ) {	
						return BookingResult.COMPRA_LIMITE_15 == calculate.getResult() && !calculate.getCost().isPresent(); 
					}else {
						float price = asientos * seats.getPrice();
						if (asientos> 5 && asientos <10) {
							price -= asientos * seats.getPrice()*0.02;  
						}else if (asientos> 5 && asientos < 15) {
							price -= asientos * seats.getPrice()*0.10;
						}
						return BookingResult.SUCCESS == calculate.getResult() && calculate.getCost().isPresent() ;
					}
					
				}else {
					if (asientos >= 8 ) {	
						return BookingResult.COMPRA_LIMITE_8  == calculate.getResult() && !calculate.getCost().isPresent();
					}else {
						float price = asientos * seats.getPrice();
						if (asientos> 5 && asientos <8) {
							price -= asientos * seats.getPrice()*0.02;  
						}
						return BookingResult.SUCCESS == calculate.getResult() && calculate.getCost().isPresent();
						}
				}
			}else {
				return BookingResult.NOT_ENOUGH_SEATS == calculate.getResult() && !calculate.getCost().isPresent();
		}
			

		}); 

		
	}
}
