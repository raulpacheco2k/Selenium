import br.com.raulpacheco.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormTest {

    private static LoginPage loginPage;

    public FormTest() {
        loginPage = new LoginPage();
    }

    @BeforeEach
    public void beforeEach() {
        loginPage.beforeEach();
    }

    @AfterEach
    public void tearDown() {
        loginPage.afterEach();
    }

    @DisplayName("Form submission with mandatory data only")
    @Test
    void testFormSubmissionWithMandatoryDataOnly() {
        loginPage.fillForm("John", "Doe", LoginPage.FEMALE, "0123456789");
        loginPage.submitForm();

        assertTrue(loginPage.hasText("Student Registration Form"));
        assertEquals("John Doe", loginPage.getTableName());
        assertEquals("Female", loginPage.getTableGender());
        assertEquals("0123456789", loginPage.getTableNumber());
    }

    @DisplayName("Do not submit any information in the form")
    @Test
    void testDoNotSubmitAnyInformationInTheForm() {
        loginPage.submitForm();

        assertNull(loginPage.getModal());
    }
}
