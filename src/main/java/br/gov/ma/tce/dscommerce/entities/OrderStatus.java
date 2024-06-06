package br.gov.ma.tce.dscommerce.entities;

public enum OrderStatus {
    //O enun, por padrao, já compara com o equals
    WAITING_PAYMENT,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELED;
}
