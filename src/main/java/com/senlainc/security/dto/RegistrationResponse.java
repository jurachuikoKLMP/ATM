package com.senlainc.security.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {
    private int status;
    private String cardNumber;
    private int password;
    private int cvv;
    private Date duration;
    private double commission;

    @Override
    public String toString(){
        return String.format("""
status: %s
card number: %s
password: %s
cvv: %s *you shouldn't show it somebody*
duration: %s
commission: %s""", this.status, this.cardNumber, this.password, this.cvv, this.duration, this.commission);
    }
}
