package com.srinath.tests.Integration;

import com.srinath.base.BaseTest;
import com.srinath.endpoints.APIConstants;
import com.srinath.pojos.Booking;
import com.srinath.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;
import static org.assertj.core.api.Assertions.assertThat;

public class TestIntegrationFlowE2E_01 extends BaseTest {

    @Test(groups = "qa", priority = 1)
    @Owner("Srinath")
    @TmsLink("https://www.bugz.atlassian.com")
    @Description("TC#1 - Verify that booking can be created")
    public void testCreateBooking(ITestContext iTestContext) {
        requestSpecification.basePath(APIConstants.Create_booking);
        response = RestAssured.given(requestSpecification)
                .when()
                .body(payLoadManager.createPayloadBookingAsString())
                .post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payLoadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "srinath");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Srinath")
    @Description("TC#2 - Verify booking by ID")
    public void testVerifyBookingId(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGET = APIConstants.Create_booking + "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse = response.then().log().all().statusCode(200);

        Booking booking = payLoadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("srinath");
    }

    @Test(groups = "qa", priority = 3)
    @Owner("Srinath")
    @Description("TC#3 - Verify updated booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token", token);

        String basePathPUTPATCH = APIConstants.Create_booking + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured.given(requestSpecification)
                .cookie("token", token)
                .when()
                .body(payLoadManager.fullUpdatePayloadAsString())
                .put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payLoadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("keshetti");
        assertThat(booking.getLastname()).isEqualTo("srinath");
    }
    @Test(groups="qa",priority = 4)
    @Owner("Srinath")
    @Description("TC#4 - Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){
        String token = (String)iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathDELETE = APIConstants.Create_booking + "/" + bookingid;
        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification).when().delete().then().log().all().statusCode(201);
    }
}
