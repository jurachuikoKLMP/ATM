package com.senlainc.service;

import com.senlainc.security.dto.AuthRequest;
import com.senlainc.security.dto.AuthResponse;
import com.senlainc.security.dto.RegistrationRequest;
import com.senlainc.security.dto.RegistrationResponse;

public interface AuthenticationService {
    AuthResponse authentication(AuthRequest request);
    RegistrationResponse registration(RegistrationRequest request);
}
