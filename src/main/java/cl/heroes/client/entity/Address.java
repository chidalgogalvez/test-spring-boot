package cl.heroes.client.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  private String country;

  private String region;

  private String commune;

  private String street;

  private Integer streetNumber;

  private String departmentNumber;

}
