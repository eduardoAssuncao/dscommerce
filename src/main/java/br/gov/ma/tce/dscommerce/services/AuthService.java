package br.gov.ma.tce.dscommerce.services;

import br.gov.ma.tce.dscommerce.entities.User;
import br.gov.ma.tce.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void validateSelfOrAdmin(Long userId){

        User me = userService.authenticated();
        if(!me.hasRole("ROLE_ADMIN") && me.getId().equals(userId)){
            throw new ForbiddenException("Access denied");
        }
    }
}
