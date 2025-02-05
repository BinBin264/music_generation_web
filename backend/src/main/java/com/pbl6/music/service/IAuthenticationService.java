package com.pbl6.music.service;

import com.pbl6.music.dto.request.RegisterRequestDTO;

public interface IAuthenticationService {

    public void registerUser(RegisterRequestDTO registerRequestDTO) throws Exception;

}
