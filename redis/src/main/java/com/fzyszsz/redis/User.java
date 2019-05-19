package com.fzyszsz.redis;

import lombok.*;

import java.io.Serializable;

/**
 * @author fuzy
 * create time 18-11-24-下午11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User implements Serializable {

    @NonNull
    private Long id;
    @NonNull
    private String username;
    private String password;
}
