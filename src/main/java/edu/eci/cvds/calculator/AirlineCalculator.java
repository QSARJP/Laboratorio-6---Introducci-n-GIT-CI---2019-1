package edu.eci.cvds.calculator;

import java.util.Optional;

import edu.eci.cvds.model.SeatCategory;
import edu.eci.cvds.model.BookingOutput;
import edu.eci.cvds.model.BookingResult;

/**
 * Utility class to validate an airline's booking
 */
public class AirlineCalculator implements BookingCalculator {

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public BookingOutput calculate(Integer seatsNumber, SeatCategory category) {
		
		if (seatsNumber > 0 && seatsNumber <= 100 ) {
			// TODO: Add required validations and calculate total price if applies
			if (SeatCategory.ECONOMY_CLASS.equals(category)) {
				if (seatsNumber >= 50  ) {
					 return new BookingOutput(BookingResult.COMPRA_LIMITE_50, Optional.empty());
				}else {
					float price = seatsNumber * category.getPrice();
					if (seatsNumber> 5 && seatsNumber <10) {
						price -= seatsNumber * category.getPrice()*0.02; 
					}else if (seatsNumber> 5 && seatsNumber<15) {
						price -= seatsNumber * category.getPrice()*0.10;
					}else if (seatsNumber> 5 && seatsNumber>15) {
						price -= seatsNumber * category.getPrice()*0.20;
					}
					return new BookingOutput(BookingResult.SUCCESS, Optional.of(price));
						
					
				}
			}else if (SeatCategory.FIRST_CLASS.equals(category)) {
				if (seatsNumber >= 15  ) {
					 return new BookingOutput(BookingResult.COMPRA_LIMITE_15, Optional.empty());
				}else {
					float price = seatsNumber * category.getPrice();
					if (seatsNumber> 5 && seatsNumber <10) {
						price -= seatsNumber * category.getPrice()*0.02; 
					}else if (seatsNumber> 5 && seatsNumber<15) {
						price -= seatsNumber * category.getPrice()*0.10;
					}
					return new BookingOutput(BookingResult.SUCCESS, Optional.of(price));
				}
			}
			else {
				if (seatsNumber >= 8  ) {
					 return new BookingOutput(BookingResult.COMPRA_LIMITE_8, Optional.empty());
				}else {
					float price = seatsNumber * category.getPrice();
					if (seatsNumber> 5 && seatsNumber <8) {
						price -= seatsNumber * category.getPrice()*0.02; 
					}
					return new BookingOutput(BookingResult.SUCCESS, Optional.of(price));
				}
			}
		}else {
			return new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());
		}

		
	}
}
