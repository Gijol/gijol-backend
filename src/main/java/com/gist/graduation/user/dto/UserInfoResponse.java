package com.gist.graduation.user.dto;


import com.gist.graduation.user.domain.User;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class UserInfoResponse {
    private final String name;
    private final String email;
    private final String studentId;
    private final String majorType;

    public static UserInfoResponse of(User user){
        return new UserInfoResponse(user.getName(), user.getEmail(), user.getStudentId(), user.getMajorType().name());
    }
}
