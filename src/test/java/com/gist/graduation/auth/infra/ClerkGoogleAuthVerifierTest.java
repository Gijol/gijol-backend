package com.gist.graduation.auth.infra;

import com.gist.graduation.auth.dto.GoogleAuthBaseResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ClerkGoogleAuthVerifierTest {

    @Test
    void verifyTest() {
        //givnen
        final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn1gLqfLMyt9pjnQ3DPDQrknwWw0HUNk8YYj+pPCGYDyswqtbsGKLD2weqjzwe6ffELrCbSN+UMVysSqAEgViIbnw6FG7Iyvs9WZTWXDxPscU8NE+JSITQsMLd0wkW9ZEzDTcSSumXu1Y8AW48W+Gf4IIDOICTqIKLPZBqcEGjK9Y2oyxJZYwLBqLB2SCWIhaahYk7/bt9s/ueG6ugoOn9fhXTl4U/5lE8sukXO1hQo6F5AChYVSEDCxX0vOqy0xRF1B7gDLIPbBccb+/od3h0qlsCPgKktxyfO0bq7GWjlgImuOmzFcVPR2d9W00p7z7dMqLdA9r2L/MPtLVbRsmeQIDAQAB";
        final String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6Imluc18yVUNJaEZTblMwOWFlWnJ4WjVmYUFJRlZSNUYiLCJ0eXAiOiJKV1QifQ.eyJhenAiOiJodHRwOi8vbG9jYWxob3N0OjMwMDAiLCJlbWFpbCI6Imdpam9saGVscEBnbWFpbC5jb20iLCJleHAiOjE2OTI0NDcwODQsImlhdCI6MTY5MjQ0NjE4NCwiaWQiOiJ1c2VyXzJVQ0p1V2hvQ0w5UTVkZVZKWTYzVzFwV2U1ZyIsImltYWdlIjoiaHR0cHM6Ly9pbWcuY2xlcmsuY29tL2V5SjBlWEJsSWpvaWNISnZlSGtpTENKemNtTWlPaUpvZEhSd2N6b3ZMMmx0WVdkbGN5NWpiR1Z5YXk1a1pYWXZiMkYxZEdoZloyOXZaMnhsTDJsdFoxOHlWVU5LZFd0TGEwUnVURGxaZW5oRmFFODJPRnBsWkVsT1Rqa3VjRzVuSW4wIiwiaXNzIjoiaHR0cHM6Ly9hd2FrZS1jaGlja2VuLTk0LmNsZXJrLmFjY291bnRzLmRldiIsImp0aSI6IjVjMjQwYWYwNGQzZDZlMjM3YTg0IiwibmFtZSI6Ikdpam9sIG1lbWJlcnMiLCJuYmYiOjE2OTI0NDYxNzksInN1YiI6InVzZXJfMlVDSnVXaG9DTDlRNWRlVkpZNjNXMXBXZTVnIn0.b88fdhNZff1aqz0rYcKLLQW9SS5J4CmYSEx0j1MMK5yEo0SgUsZan1okO7JYTJl9RDwtTRYlQ_o1JL-62IO4i2H7nt_Ih3O4Pt600OQoRaut4azHlGmzR07uFtWgCQRG69Vm0pcYCTJ6HKVi37gmdhg3JLMqvOM2_XcLyWoq1BcXm4vax_ylrVnRwQ1mFSi5BLXLpChGghzgRexO4rWAS-1YPinIq30xoso4mUTZc47mU99fSPjUnqCNZX5b7WCgu7EEC2loONgDWSIGnY7QpK1-mBcw_8ccdUKnPhvVZBF6l1ir-isl_Fgf8C2w5qzJ6J96zPKuFZ8TzD4fSilS5w";
        final Long hundredYear = 31622400L * 100L;
        final ClerkGoogleAuthVerifier clerkGoogleAuthVerifier = new ClerkGoogleAuthVerifier(publicKey, hundredYear, new String[]{"http://localhost:3000"});

        //when
        final GoogleAuthBaseResponse response = clerkGoogleAuthVerifier.verify(token);

        //then
        final String EMAIL = "gijolhelp@gmail.com";
        Assertions.assertThat(response.getEmail()).isEqualTo(EMAIL);
    }
}