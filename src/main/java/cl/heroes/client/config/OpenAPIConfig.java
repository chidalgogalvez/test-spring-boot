package cl.heroes.client.config;

import cl.heroes.client.util.Constants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(new Info()
            .title(Constants.TITLE)
            .description(this.createDescription())
            .version(Constants.VERSION)
            .termsOfService("https://www.google.cl")
            .license(new License().name(Constants.LICENCE).url("https://www.google.cl"))
        )
        .addServersItem(new Server().url("http://localhost:8080"));
  }

  private String createDescription() {
    return "Version: " + Constants.VERSION + "\n\n" + "Responsible: " + Constants.RESPONSIBLE + "\n\n"
        + "Description: " + Constants.DESCRIPTION + "\n\n";
  }
}

