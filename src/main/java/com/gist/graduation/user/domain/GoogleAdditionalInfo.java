package com.gist.graduation.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoogleAdditionalInfo {

    @Column(name = "google_picture_url")
    private String pictureUrl;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "locale")
    private String locale;

    public GoogleAdditionalInfo(String pictureUrl, String givenName, String familyName, String locale) {
        this.pictureUrl = pictureUrl;
        this.givenName = givenName;
        this.familyName = familyName;
        this.locale = locale;
    }
}
