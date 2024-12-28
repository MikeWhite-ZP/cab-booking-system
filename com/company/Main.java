package com.company;

import com.company.enums.Gender;
import com.company.exceptions.CreateException;
import com.company.models.*;
import com.company.services.CabBookingService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CabBookingService cabBookingService = CabBookingService.getInstance();
        Location l1 = new Location(0, 0);
        Location l2 = new Location(8, 8);

        Rider r1 = null;
        try {
            r1 = cabBookingService.registerRider("Srikant", 1, Gender.MALE, l1, l2);
            System.out.println(r1.getId() + " successfully registered.");
        } catch (CreateException e) {
            System.err.println("Error registering rider: " + e.getMessage());
        }

        Location l3 = new Location(2, 2);
        Vehicle v1 = new Vehicle("Swift", 1000, l3);
        Driver d1 = null;
        try {
            d1 = cabBookingService.registerDriver("Raju", 11, Gender.MALE, v1, true);
        } catch (CreateException e) {
            System.err.println("Error registering driver: " + e.getMessage());
        }

        Location l4 = new Location(4, 4);
        Vehicle v2 = new Vehicle("Indica", 2000, l4);
        Driver d2 = null;
        try {
            d2 = cabBookingService.registerDriver("Ramu", 12, Gender.MALE, v2, true);
        } catch (CreateException e) {
            System.err.println("Error registering driver: " + e.getMessage());
        }

        try {
            Driver d11 = cabBookingService.bookRide(1, l1, l2);
            if (d11 != null) {
                System.out.println("Ride successfully booked in " + d11.getVehicle().getModel() + " and your driver is " + d11.getName());
            } else {
                System.out.println("Failed to book ride.");
            }
        } catch (Exception e) {
            System.err.println("Error booking ride: " + e.getMessage());
        }

        if (d2 != null) {
            System.out.println("Availability is " + d2.isAvailable());
        }

        if (r1 != null) {
            List<Ride> rideList = r1.getRideList();
            for (Ride ride : rideList) {
                System.out.println(ride.getDriver().getName() + "," + ride.getRider().getName() + "," + ride.getFromLocation().getY() + "," + ride.getToLocation().getY());
            }
        }
    }
}
