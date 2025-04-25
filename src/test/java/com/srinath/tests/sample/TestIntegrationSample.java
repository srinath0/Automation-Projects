package com.srinath.tests.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationSample {
    //Create a Booking, Create A Token
    // Verify that booking
    // Update the booking
    // Delete the booking
    @Test(groups = "qa",priority = 1)
    @Owner("Srinath")
    @Description("TC-1:-Verify that the booing can be created")
    public void testCreateBooking(){
        Assert.assertTrue(true);
    }
    @Test(groups="qa",priority = 2)
    @Owner("Srinath")
    @Description("TC-2:-Verify that the booking can be Updated")
    public void testVerifyBooking(){
        Assert.assertTrue(true);
    }
    @Test(groups="qa",priority = -1)
    @Owner("Srinath")
    @Description("TC-3:-Verify the bookingID can be Updated")
    public void testUpdateBooking(){
        Assert.assertTrue(true);
    }
    @Test(groups="qa",priority = 3)
    @Owner("Srinath")
    @Description("TC-4:-BookingID can be deleted")
    public void testdeleteBooking(){
        Assert.assertTrue(true);
    }
}
