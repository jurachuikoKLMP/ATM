package com.senlainc.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private int status;
    private String message;

    @Override
    public String toString(){
        return String.format("""
status: %s
message: %s
                """, this.status, this.message);
    }
}
