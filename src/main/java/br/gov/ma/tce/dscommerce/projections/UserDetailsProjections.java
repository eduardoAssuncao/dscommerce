package br.gov.ma.tce.dscommerce.projections;

public interface UserDetailsProjections {

    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
