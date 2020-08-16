package com.play.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JWTDemoTest {


    @Nested
    class JWTCreationTests {
        @Test
        void shouldThrowWeakKeyExceptionIfCreatingJWTwithSecretOfSmallSize() {

            JWTDemo demo = new JWTDemo();

            assertThrows(WeakKeyException.class,
                    () -> demo.createJWT("12345", "Demo App",
                            "dummySecret", "sunit@gmail.com",
                            1000));
        }

        @Test
        void shouldBeAbleToCreateJWT() {

            JWTDemo demo = new JWTDemo();

            String jwt = demo.createJWT("12345", "Demo App",
                    "9aaa4aba-6c85-4524-a72a-1021caf2e025",
                    "sunit@gmail.com",
                   1);

            assertNotNull(jwt);

            String[] jwtParts = jwt.split("\\.");

            assertEquals(3, jwtParts.length, "JWT Should contain 3 parts separated by dot");

        }
    }

    @Nested
    class JWTParsingTest{

        final String VALID_JWT_WITH_1000_YR_EXPIRY = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NSIsInN1YiI6InN1bml0QGdtYWlsLmNvbSIsImlzcyI6IkRlbW8gQXBwIiwiaWF0IjoxNTk3NTk0NTI0LCJleHAiOjEwMjM3NTA4MTI0LCJmaXJzdE5hbWUiOiJTdW5pdCIsImxhc3ROYW1lIjoiQ2hhdHRlcmplZSIsImlzQWRtaW4iOnRydWV9.fM0St7WEj4gkbD3iGfZgrfRBjZ0PJ0MRb8w6vl8nhL0";
        final String VALID_SIGNING_KEY = "9aaa4aba-6c85-4524-a72a-1021caf2e025";



        @Test
        void shouldMatchClaimsSetInJWT() {
            JWTDemo demo = new JWTDemo();

            Claims claims = demo.parseJWT(VALID_JWT_WITH_1000_YR_EXPIRY, VALID_SIGNING_KEY);

            assertEquals("12345", claims.getId());
            assertEquals("Demo App", claims.getIssuer());
            assertEquals("sunit@gmail.com", claims.getSubject());
            assertEquals("Sunit", claims.get("firstName", String.class));
            assertEquals("Chatterjee", claims.get("lastName", String.class));
            assertEquals(true, claims.get("isAdmin", Boolean.class));
        }
    }

    @Nested
    class JWTParsingErrorTests {

        final String VALID_JWT_WITH_1000_YR_EXPIRY = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NSIsInN1YiI6InN1bml0QGdtYWlsLmNvbSIsImlzcyI6IkRlbW8gQXBwIiwiaWF0IjoxNTk3NTkxMzg0LCJleHAiOjE1OTc1OTEzODQsImZpcnN0TmFtZSI6IlN1bml0IiwibGFzdE5hbWUiOiJDaGF0dGVyamVlIiwiaXNBZG1pbiI6dHJ1ZX0.Yz258ku-73Ie6D-zfXsqPZQo_A25jmPSwRdVvZQEE4g";
        final String EXPIRED_JWT = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NSIsInN1YiI6InN1bml0QGdtYWlsLmNvbSIsImlzcyI6IkRlbW8gQXBwIiwiaWF0IjoxNTk3NTk0NzExLCJleHAiOjE1OTc1OTQ3MTIsImZpcnN0TmFtZSI6IlN1bml0IiwibGFzdE5hbWUiOiJDaGF0dGVyamVlIiwiaXNBZG1pbiI6dHJ1ZX0.u9KpaIm4a4eis-IhMijdvfQYGrvDE_do9IMHtAFnSI8";
        final String VALID_SIGNING_KEY = "9aaa4aba-6c85-4524-a72a-1021caf2e025";

        @Test
        void shouldThrowExceptionIfJWTIsSignedUsingDifferentKey() {
            JWTDemo demo = new JWTDemo();

            assertThrows(SignatureException.class,
                    () -> demo.parseJWT( VALID_JWT_WITH_1000_YR_EXPIRY, "9aaa4aba-6c85-4524-a72a-1021caf2e02"));
        }

        @Test
        void shouldThrowExceptionWhenExpiredJWTTokenIsParsed() {
            JWTDemo demo = new JWTDemo();

            assertThrows(ExpiredJwtException.class,
                    () -> demo.parseJWT(EXPIRED_JWT, VALID_SIGNING_KEY));
        }

        @Test
        void shouldThrowExceptionWhenInvalidJWTTokenIsParsed() {
            JWTDemo demo = new JWTDemo();

            assertThrows(MalformedJwtException.class,
                    () -> demo.parseJWT("dummy.dummy.dummy", VALID_SIGNING_KEY));
        }

        @Test
        void shouldThrowExceptionWhenNullJWTTokenIsParsed() {
            JWTDemo demo = new JWTDemo();

            assertThrows(IllegalArgumentException.class,
                    () -> demo.parseJWT(null, VALID_SIGNING_KEY));
        }

        @Test
        void shouldThrowExceptionWhenAnEmptyJWTTokenIsParsed() {
            JWTDemo demo = new JWTDemo();

            assertThrows(IllegalArgumentException.class,
                    () -> demo.parseJWT( "", VALID_SIGNING_KEY));
        }
    }



}

