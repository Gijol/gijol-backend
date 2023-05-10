package com.gist.graduation.auth.infra;

import com.gist.graduation.auth.dto.GoogleIdTokenVerificationResponse;
import com.gist.graduation.auth.dto.GoogleVerificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
class GoogleAuthTokenVerifierTest {

    @Autowired
    private GoogleAuthTokenVerifier googleAuthTokenVerifier;

    @Test
    void verifyGoogleAccessTokenInfo() {
        String accessToken = "ya29.a0AWY7CknHSPVCfGLXjEYBcvvs0I3pJ16zgf-QiPP7b_RFlWlKdzShzeOrQIdywll5eyGtIe9jLQZg9__LNwGIbO54Wzfd8ufWGvSFHprO_3nkpZp5GMBGjrXTNX5FMBdimSpkG2YC3UFGCxVxzLpvJgAL1VbvaCgYKAaUSARISFQG1tDrpn-50p8p29tHhIAw2Gg6txQ0163";
        GoogleVerificationResponse response = googleAuthTokenVerifier.verifyGoogleOAuth2AccessToken(accessToken);
        String exp = response.getExp();
        Instant instant = Instant.ofEpochMilli(Long.parseLong(exp) * 1000);
        System.out.println(instant);
        System.out.println(response.getEmail_verified());
    }

    @Test
    void verifyGoogleIdToken() {
        String idToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6Ijc3NzBiMDg1YmY2NDliNzI2YjM1NzQ3NjQwMzBlMWJkZTlhMTBhZTYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI4ODMxOTM5NjkzMDUtZmQ1MG81b21jN2Nic3FjdG91djVwZWU4ZnB1bWwxaTEuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI4ODMxOTM5NjkzMDUtZmQ1MG81b21jN2Nic3FjdG91djVwZWU4ZnB1bWwxaTEuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTgwMTI5MzUxMDk1NDY0NTYzNDQiLCJlbWFpbCI6ImVoY3dzMzMzQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoieDVzWXpGZUM4S1luZmNuQml6aVhCdyIsIm5hbWUiOiLshJzrj5ntmLgiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUdObXl4YTFyRjBpNDVIclJHVkZZVmJ5WGs1cmRGamdydFRLcjFHaDNiTWRYUT1zOTYtYyIsImdpdmVuX25hbWUiOiLrj5ntmLgiLCJmYW1pbHlfbmFtZSI6IuyEnCIsImxvY2FsZSI6ImtvIiwiaWF0IjoxNjgzNzIzMDkyLCJleHAiOjE2ODM3MjY2OTJ9.FKTdkDYL51tm6-3vYIFCoSmtKLszebZLXw7pJjSIjNg0aak_7z2oCXnf7Zmzh1lWkvlIm7BoUApZZ8JpMoxu_z_sCPSPcNfpVvJWfJZ-wLFIXbZ-fFr9TQQIVFeUfAG71oNnwv5GzGMKOrUeTCTmOcnoEvI5Nj0ONXgVmexbo0dw4MSrKgr5RiHNlBGEhfKqSGPYGD2IGni4y3XpQ3XT20NT-DaEqRbqoAQUqaFE7qJKbTNU-GzbC4H9q3Pr_2Xrt1mGBHxi050obVcOANDZTGYdZzkEkmqtfXBxQatxSYS-z8GcWK8rowwrZHuH-E6g8nzsoA64T3GYC0gglf-iNg";
        GoogleIdTokenVerificationResponse response = googleAuthTokenVerifier.verifyGoogleOAuth2IdToken(idToken);
        String exp = response.getExp();
        Instant instant = Instant.ofEpochMilli(Long.parseLong(exp) * 1000);
        LocalDateTime expiration = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Seoul"));
        System.out.println(expiration);
        System.out.println(response.getEmail_verified());
    }
}