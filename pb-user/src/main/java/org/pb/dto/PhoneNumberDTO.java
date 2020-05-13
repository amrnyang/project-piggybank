package org.pb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhoneNumberDTO {

    private Integer id;
    private Integer countryCode;
    private Long phone;
}
