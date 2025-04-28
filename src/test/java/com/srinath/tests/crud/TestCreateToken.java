package com.srinath.tests.crud;

import com.srinath.base.BaseTest;
import com.srinath.endpoints.APIConstants;
import com.srinath.modules.PayLoadManager.*;
import com.srinath.pojos.BookingResponse;
import com.srinath.pojos.TokenResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {
    @Test(groups = "qa", priority = 1)
    @Owner("Srinath")
    @TmsLink("https://jira.int.srinath.com")
    @Description("TC#2-Create Token")
    public void testTokenPost() {
        requestSpecification.basePath(APIConstants.AUTH_Url);
        response = RestAssured.given(requestSpecification).when().body(payLoadManager.setAuthPayload()).post();
        validatableResponse = response.then().log().all().statusCode(200);
        String token = payLoadManager.getTokenFromJSON(response.asString());
        assertActions.verifyStringKeyNotNull(token);

    }
}
