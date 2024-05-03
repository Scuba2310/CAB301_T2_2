package jesh.project.jeshproject.tests;

import org.junit.jupiter.api.*;

public class AuthenticateTests {

    @BeforeEach
    void setup() {
    }
    // Sign Up
    @Test
    void goodSignUp() {

    }
    @Test
    void addToDBOnSignUp() {

    }

    @Test
    void FirstnameFieldIsEmptyShouldThrowException() {

    }
    @Test
    void SurnameFieldIsEmptyShouldThrowException() {

    }
    @Test
    void BirthdayDateIsInvalidShouldThrowException() {

    }
    @Test
    void EmailIsNotValidShouldThrowException() {

    }
    @Test
    void UsernameFieldIsEmptyOnSignUpShouldThrowException() {

    }
    @Test
    void PasswordFieldIsEmptyOnSignUpShouldThrowException() {

    }

    // Log In
    @Test
    void goodLogIn() {

    }
    @Test
    void InvalidUsernameOnLogIn() {

    }
    @Test
    void InvalidPasswordOnLoginShouldThrowException() {

    }




    /*
    // Tests

    successful sign up:
    - no problems with supplied fields
    - user added to db
    - success message/transfer to next stage

    successful log in:
    - supplied data matches db
    - success message/transfer to next stage

    sign up details entered wrong:
    - first name
    - last name
    - birthday
    - email
    - username
    - password
    - error messages

    unsuccessful login:
    - wrong username
    - invalid username
    - wrong password
    - invalid password
    - error messages

    */

}
