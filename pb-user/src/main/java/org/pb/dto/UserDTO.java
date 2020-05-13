package org.pb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.pb.model.PhoneNumber;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @Email
    private String email;
    private List<PhoneNumberDTO> phoneNumber;
}
