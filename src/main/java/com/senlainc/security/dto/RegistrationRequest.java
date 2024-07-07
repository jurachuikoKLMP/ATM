package com.senlainc.security.dto;

import com.senlainc.entity.enums.CardType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private String name;
    private String surname;
    private CardType cardType;
}
