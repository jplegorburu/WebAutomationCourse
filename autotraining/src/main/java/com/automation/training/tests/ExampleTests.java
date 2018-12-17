package com.automation.training.tests;

import com.automation.training.Cruises;
import com.automation.training.Flight;
import com.automation.training.Hotel;
import com.automation.training.pages.*;
import com.automation.training.pages.cruises.CruisesReservationPage;
import com.automation.training.pages.cruises.CruisesResultPage;
import com.automation.training.pages.cruises.CruisesSearch;
import com.automation.training.pages.flights.FlightResultPage;
import com.automation.training.pages.flights.FlightSearch;
import com.automation.training.pages.hotels.HotelReservationPage;
import com.automation.training.pages.hotels.HotelResultPage;
import com.automation.training.pages.hotels.HotelSearch;
import com.automation.training.pages.packages.PackageSearch;

import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.*;

import static org.testng.Assert.*;

public class ExampleTests extends BaseTest{

	@Test(groups= {"example1"})
    public void testExampleOne() throws ParseException {
        //Search for flight
        TravelHome home = getTravelHome();
        FlightSearch flightSearchPage = home.searchFlights();
        flightSearchPage.setFromCity("LAS");
        flightSearchPage.setToCity("LAX");
        flightSearchPage.setFromDate("02/25/2019");
        flightSearchPage.setToDate("05/25/2019");
        flightSearchPage.setAdultsNum(1);
        
        FlightResultPage results = flightSearchPage.search();

        //Verify result page
        List<Flight> flights = results.getResultsList();
        for(Flight f:flights){
            assertNotNull(f.getSelectButton());
            assertNotNull(f.getBaggageDetail());
            assertNotNull(f.getDuration());
        }

        // Check orders criteria
        List<String> orderCriterias = Arrays.asList("Price", "Departure", "Arrival", "Duration");
        for (String criteria:orderCriterias) {
            assertTrue(results.getSortCriterias().stream().anyMatch(e -> e.getText().contains(criteria)));
        }
        results.setSortCriterias("duration:desc");

        // Ordered results
        List<Flight> flights_ordered = results.getResultsList();
        List<Flight> expected =new ArrayList<>(flights_ordered);
        expected.sort(Flight.FlighDurationComparator);
        assertEquals(flights_ordered, expected);
        flights_ordered.get(0).getSelectButton().click();
        if(!results.departureTripSelected()) {
        	flights_ordered.get(0).getSelectFareButton().click();
        }
        // Arrival flight
        String actual = results.switchTab();
        List<Flight> return_flights = results.getResultsList();
        return_flights.get(2).getSelectButton().click();
        if(actual.equals(results.switchTab())) {
        	return_flights.get(2).getSelectFareButton().click();
        }
        if(results.popupExists()){
            results.getNoThanksButton().click();
            assertFalse(results.popupExists());
        }

        // Verify Trip details
        TripDetailsPage trip_details = results.goToTripDetails();
        assertNotNull(trip_details.getTotalPrice());
        // Does not appears in Firefox
        // assertNotNull(trip_details.getPriceGuarantee());
        assertEquals(trip_details.getFlightInformation().size(),2);

        // 5 Verifications payment page
        PaymentPage paymentPage = trip_details.bookIt();
        assertNotNull(paymentPage.getCompleteBookingButton());
        assertNotNull(paymentPage.getEmailInfo());
        assertNotNull(paymentPage.getTravelerInfo());
        assertNotNull(paymentPage.getPaymentInfo());
        assertNotNull(paymentPage.getTripSummary());
    }
    
    @Test(groups= {"example2"})
    public void testExampleTwo() throws ParseException {
        //Search for flight
        TravelHome home = getTravelHome();
        PackageSearch packageSearchPage = home.searchPackages();
        packageSearchPage.setFromCity("LAS");
        packageSearchPage.setToCity("LAX");
        packageSearchPage.setFromDate("03/10/2019");
        packageSearchPage.setToDate("03/23/2019");
        packageSearchPage.setAdultsNum(1);
        packageSearchPage.setRoomsNum(1);
        HotelResultPage results = packageSearchPage.search();
        
        List<Hotel> hotels = results.getHotelResults();
        for(Hotel h:hotels) {
        	assertNotNull(h.getHotelName());
        	assertNotNull(h.getPrice());
        	assertNotNull(h.getStarRating());
        	assertNotNull(h.getPhone());
        	assertNotNull(h.getLocation());	
        }
        results.setSortCriteria("Price");
        
        // Ordered results
        List<Hotel> hotels_ordered = results.getHotelResults();
        List<Hotel> expected =new ArrayList<>(hotels_ordered);
        expected.sort(Hotel.HotelPriceComparator);
        assertEquals(hotels_ordered, expected);
        
        Hotel hotel_selected = hotels_ordered.get(0);
        for(Hotel h:hotels_ordered) {
        	if(Float.parseFloat(h.getStarRating()) >= 3) {
        		hotel_selected = h;
        		break;
        	}
        }
        String selected_name = hotel_selected.getHotelName().getText();
        String selected_phone = hotel_selected.getPhone();
        String selected_price = hotel_selected.getPrice();
        
        hotel_selected.selectHotel();
        HotelReservationPage hotelResultPage = results.goToReservationPage();
        assertEquals(hotelResultPage.getHotelName(), selected_name);
        // assertEquals(hotelResultPage.getPhone(), selected_phone);
        assertEquals(hotelResultPage.getHotelPrice(), selected_price);
        
        FlightResultPage flight_results = hotelResultPage.selectCheapestOption();
        flight_results.getResultsList().get(0).getSelectButton().click();
        String actual = results.switchTab();
        flight_results.getResultsList().get(2).getSelectButton().click();
        if(actual.equals(results.switchTab())) {
        	flight_results.getResultsList().get(2).getSelectFareButton().click();
        }
        // Verify Trip details
        TripDetailsPage trip_details = flight_results.goToTripDetails();
        trip_details.selectCar();
        assertNotNull(trip_details.getCarAdded());
        assertNotNull(trip_details.getPackagePrice());
        assertEquals(trip_details.getFlightInformation().size(),2);
        assertNotNull(trip_details.getHotel());
        String totalPrice = trip_details.getPackagePrice().getText();
        PaymentPage paymentPage = trip_details.bookPackage();
        assertEquals(paymentPage.getTripTotal().getText(), totalPrice);
        assertNotNull(paymentPage.getCompleteBookingButton());
        assertNotNull(paymentPage.getEmailInfo());
        assertNotNull(paymentPage.getRulesInfo());
        assertNotNull(paymentPage.getPaymentInfo());
        assertNotNull(paymentPage.getTripSummary());
           
    }

    @Test(groups= {"example3"})
    public void testExampleThree() {
    	TravelHome home = getTravelHome();
        HotelSearch hotelSearchPage = home.searchHotels();
        hotelSearchPage.setDestination("Montevideo, Uruguay");
        HotelResultPage results = hotelSearchPage.search();
        List<Hotel> hotels = results.getHotelResults();
        for(Hotel h:hotels.subList(0, 1)) {
        	assertEquals(h.getType(), "organic");
        }
        assertNotNull(results.getSignupLink());
        results.getSignupLink().click();
        assertNotNull(results.getSignupEmail());
        
    }
    
    @Test(groups= {"example4"})
    public void testExampleFour() throws ParseException {
        //Search for flight
        TravelHome home = getTravelHome();
        // Fill the form with invalid dates
        PackageSearch packageSearchPage = home.searchPackages();
        packageSearchPage.setFromCity("LAS");
        packageSearchPage.setToCity("LAX");
        packageSearchPage.setFromDate("01/10/2019");
        packageSearchPage.setToDate("01/20/2019");
        packageSearchPage.setAdultsNum(1);
        packageSearchPage.setRoomsNum(1);
        packageSearchPage.getPartialStay().click();
        packageSearchPage.setCheckinDate("01/05/2019");
        packageSearchPage.setCheckoutDate("01/22/2019");
        // Searchs for package
        packageSearchPage.clickSearchButton();
        String alertMessageExpected = "Your partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates.";
        assertTrue(packageSearchPage.getValidationAlert().getText().contains(alertMessageExpected));
    }
    
    @Test(groups= {"example5"})
    public void testExampleFive() throws ParseException {
        //Search for flight
        TravelHome home = getTravelHome();
        // Fill the form with invalid dates
        CruisesSearch cruisesSearchPage = home.searchCruises();
        cruisesSearchPage.setDestination("europe");
        cruisesSearchPage.setDate("2019-05-01");
        cruisesSearchPage.setAdultsNum(1);
        
        // Searchs for package
        CruisesResultPage results = cruisesSearchPage.search();
        results.closeModalPopup();
        assertEquals(results.getSelectedDestination().getText(), "Europe");
        assertEquals(results.getSelectedDate().getText(), "May 2019");
        results.set10to14daysFilter();
        List<Cruises> cruises = results.getCruisesResults();
        assertTrue(cruises.stream().anyMatch(c -> c.hasDiscount()));
        assertTrue(cruises.stream().anyMatch(c -> !c.hasDiscount()));
        cruises.sort(Cruises.CruiseDiscountComparator);
        Cruises selectedCruise = cruises.get(0);
        String cruiseTitle = selectedCruise.getTitle().getText();
        String cruisePort = selectedCruise.getDeparting().getText().split(",")[0];
        
        selectedCruise.getItinerary().click();
        selectedCruise.getSelectButton().click();
        CruisesReservationPage reservation = results.goToReservationPage();
        assertEquals(reservation.getTripTitle().toLowerCase(), cruiseTitle.toLowerCase());
        assertEquals(reservation.getDeparturePort(), cruisePort);
    	}	
    
    
}
