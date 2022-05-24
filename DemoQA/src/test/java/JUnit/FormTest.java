package JUnit;

import Selenium.PageObjects.FormPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormTest {

    private static FormPage formPage;

    public FormTest() {
        formPage = new FormPage();
    }

    @BeforeEach
    public void beforeEach() {
        formPage.beforeEach();
    }

    @AfterEach
    public void tearDown() {
        formPage.afterEach();
    }

    @DisplayName("Form submission with mandatory data only")
    @Test
    void testFormSubmissionWithMandatoryDataOnly() {
        formPage.fillForm("John", "Doe", FormPage.FEMALE, "0123456789")
                .submitForm();

        assertTrue(formPage.hasText("Student Registration Form"));
        assertEquals("John Doe", formPage.getTableName());
        assertEquals("Female", formPage.getTableGender());
        assertEquals("0123456789", formPage.getTableNumber());
    }

    @DisplayName("Do not submit any information in the form")
    @Test
    void testDoNotSubmitAnyInformationInTheForm() {
        formPage.submitForm();

        assertNull(formPage.getModal());
    }
}
