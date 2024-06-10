package br.gov.ma.tce.dscommerce.dto;

import br.gov.ma.tce.dscommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    //Uma biblioteca para realizar a cópia dos dados da minha entidade para o DTO é o ModelMapper
    //Ela realiza a copia de atributos de mesmo nome de um objeto para outro.
    //https://www.baeldung.com/java-modelmapper
    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }
}
