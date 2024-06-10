package br.gov.ma.tce.dscommerce.dto;

import br.gov.ma.tce.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDTO {
    //As validações não estão funcionando -> EU SÓ TINHA QUE REINICIAR O PROJETO
    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido") //é a junção do @NotNull e  @NotEmpty
    private String name;
    @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;
    @Positive(message = "O preço deve ser positivo")
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
