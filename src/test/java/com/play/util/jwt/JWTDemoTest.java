package com.play.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JWTDemoTest {


    @Nested
    class JWTCreationTests {

        @Test
        void shouldThrowWeakKeyExceptionIfCreatingJWTwithSecretOfSmallSize() {

            JWTDemo demo = new JWTDemo();

            assertThrows(WeakKeyException.class,
                    () -> demo.createJWT("Demo App",
                            "dummySecret", "sunit@gmail.com",
                            1000));
        }

        @Test
        void shouldBeAbleToCreateJWT() {

            JWTDemo demo = new JWTDemo();

            String jwt = demo.createJWT("Demo App",
                    "9aaa4aba-6c85-4524-a72a-1021caf2e025",
                    "sunit@gmail.com",
                   1);

            assertNotNull(jwt);

            String[] jwtParts = jwt.split("\\.");

            assertEquals(3, jwtParts.length, "JWT Should contain 3 parts separated by dot");
        }

        @Test
        void shouldGenerateDifferentJWTIdEachTime() {
            final String SECRET_KEY = "9aaa4aba-6c85-4524-a72a-1021caf2e025";
            JWTDemo demo = new JWTDemo();

            String jwt1 = demo.createJWT("Demo App", SECRET_KEY, "sunit@gmail.com", 1);
            String jwt2 = demo.createJWT("Demo App", SECRET_KEY, "sunit@gmail.com", 1);

            String jti1 = demo.parseJWT(jwt1, SECRET_KEY).getId();
            String jti2 = demo.parseJWT(jwt2, SECRET_KEY).getId();
            assertNotEquals(jti1, jti2);
        }
    }

    @Nested
    class JWTParsingTest{

        final String VALID_JWT_WITH_LONG_EXPIRY = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ZWY2ZGU5MC1hMzljLTRhMjgtOTBmYy0xY2MyZmViZjFhMzciLCJzdWIiOiJzdW5pdEBnbWFpbC5jb20iLCJpc3MiOiJEZW1vIEFwcCIsImlhdCI6MTU5NzYwNDYwNywiZXhwIjoxMDIzNzUxODIwNywiZmlyc3ROYW1lIjoiU3VuaXQiLCJsYXN0TmFtZSI6IkNoYXR0ZXJqZWUiLCJpc0FkbWluIjp0cnVlfQ.VvvfRsZ75erZ6-k1-BeAI3IS6yXCM_3p5jSsxPCFkfI";
        final String VALID_SIGNING_KEY = "9aaa4aba-6c85-4524-a72a-1021caf2e025";



        @Test
        void shouldMatchClaimsSetInJWT() {
            JWTDemo demo = new JWTDemo();

            Claims claims = demo.parseJWT(VALID_JWT_WITH_LONG_EXPIRY, VALID_SIGNING_KEY);

            assertEquals("Demo App", claims.getIssuer());
            assertEquals("sunit@gmail.com", claims.getSubject());
            assertEquals("Sunit", claims.get("firstName", String.class));
            assertEquals("Chatterjee", claims.get("lastName", String.class));
            assertEquals(true, claims.get("isAdmin", Boolean.class));
        }
    }

    @Nested
    class JWTParsingErrorTests {

        final String VALID_JWT_WITH_LONG_EXPIRY = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ZWY2ZGU5MC1hMzljLTRhMjgtOTBmYy0xY2MyZmViZjFhMzciLCJzdWIiOiJzdW5pdEBnbWFpbC5jb20iLCJpc3MiOiJEZW1vIEFwcCIsImlhdCI6MTU5NzYwNDYwNywiZXhwIjoxMDIzNzUxODIwNywiZmlyc3ROYW1lIjoiU3VuaXQiLCJsYXN0TmFtZSI6IkNoYXR0ZXJqZWUiLCJpc0FkbWluIjp0cnVlfQ.VvvfRsZ75erZ6-k1-BeAI3IS6yXCM_3p5jSsxPCFkfI";
        final String EXPIRED_JWT = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2ZmU0ZTUwNC04NDg1LTQ0ZTgtOTM0ZC01ZTFhYzVhYjRlNDMiLCJzdWIiOiJzdW5pdEBnbWFpbC5jb20iLCJpc3MiOiJEZW1vIEFwcCIsImlhdCI6MTU5NzYwNDU1MSwiZXhwIjoxNTk3NjA0NTUyLCJmaXJzdE5hbWUiOiJTdW5pdCIsImxhc3ROYW1lIjoiQ2hhdHRlcmplZSIsImlzQWRtaW4iOnRydWV9.6aOy5pEpHd5VY3i3p6JfysZzZdp0VEH0QiSsK2QtPLU";
        final String VALID_SIGNING_KEY = "9aaa4aba-6c85-4524-a72a-1021caf2e025";

        @Test
        void shouldThrowExceptionIfJWTIsSignedUsingDifferentKey() {
            JWTDemo demo = new JWTDemo();

            assertThrows(SignatureException.class,
                    () -> demo.parseJWT(VALID_JWT_WITH_LONG_EXPIRY, "9aaa4aba-6c85-4524-a72a-1021caf2e02"));
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

