package br.gov.ma.tce.dscommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException { //não exige o trycatch

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
