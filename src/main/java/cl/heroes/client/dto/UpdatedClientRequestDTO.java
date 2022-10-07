package cl.heroes.client.dto;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
public class UpdatedClientRequestDTO implements Serializable {

  private static final long serialVersionUID = -5092376512541437781L;

  @NotEmpty(message = "The [name] field cannot be empty.")
  private String name;

  @NotEmpty(message = "The [lastName] field cannot be empty.")
  private String lastName;

  @NotEmpty(message = "The [phone] field cannot be empty.")
  private String phone;

  @NotEmpty(message = "The [email] field cannot be empty.")
  @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
      flags = Pattern.Flag.CASE_INSENSITIVE)
  private String email;

  @Valid
  private AddressDTO address;


}
