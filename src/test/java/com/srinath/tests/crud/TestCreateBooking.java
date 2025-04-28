package com.srinath.tests.crud;

import com.srinath.base.BaseTest;
import com.srinath.endpoints.APIConstants;
import com.srinath.modules.PayLoadManager;
import com.srinath.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {

    @Test(groups="qa", priority = 1)
    @Owner("Srinath")
    @TmsLink("https://jira.int.srinath.com")
    @Description("TC#1-Verify that the booking can be created")
    public void testCreateBooking(){
        requestSpecification.basePath(APIConstants.Create_booking);
        response = RestAssured.given(requestSpecification).when().body(payLoadManager.createPayloadBookingAsString()).post();
        validatableResponse = response.then().log().all().statusCode(200);

        BookingResponse bookingResponse = payLoadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"srinath");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
    }
}
