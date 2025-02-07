package com.kaique.spring.integrationtestes.controllers.withjson;

import com.kaique.spring.configs.TestConfigs;
import com.kaique.spring.integrationtestes.testcontainers.AbstractIntegrationTests;

import com.kaique.spring.integrationtestes.vo.PersonVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerJsonTest extends AbstractIntegrationTests {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonVO personVO;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        // Ignores Exceptions when Java objects do not recognize HATEOAS links while converting VO to Java objects.
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        personVO = new PersonVO();
    }

    @Test
    @Order(1)
    void createTest() throws Exception {
        mockPerson();

        specification = new RequestSpecBuilder()
                // Similar in Postman Headers
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "https://www.erudio.com.br")
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(personVO)
                .when()
                    .post()
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .asString();

        PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
        personVO = createdPerson;

        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);

        assertEquals("Mocked", createdPerson.getFirstName());
        assertEquals("Person VO", createdPerson.getLastName());
        assertEquals("Male", createdPerson.getGender());
        assertEquals("Address", createdPerson.getAddress());
    }

    private void mockPerson() {
        personVO.setFirstName("Mocked");
        personVO.setLastName("Person VO");
        personVO.setAddress("Address");
        personVO.setGender("Male");
    }
}