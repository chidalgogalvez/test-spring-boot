package cl.heroes.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO implements Serializable {

  private static final long serialVersionUID = -9216393170181415620L;

  @NotNull(message = "The country is required.")
  private String country;
  @NotNull(message = "The region is required.")
  private String region;
  @NotNull(message = "The commune is required.")
  private String commune;
  @NotNull(message = "The street is required.")
  private String street;
  @NotNull(message = "The streetNumber is required.")
  private Integer streetNumber;

  private String departmentNumber;

}
