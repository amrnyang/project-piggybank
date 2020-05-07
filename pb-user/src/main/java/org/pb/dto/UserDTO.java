package org.pb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
}
