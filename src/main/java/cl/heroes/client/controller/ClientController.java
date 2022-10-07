package cl.heroes.client.controller;

import cl.heroes.client.dto.ClientRequestDTO;
import cl.heroes.client.dto.ResponseWrapper;
import cl.heroes.client.dto.UpdatedClientRequestDTO;
import cl.heroes.client.service.ClientService;
import cl.heroes.client.util.Utils;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientController Class
 *
 * @author chidalgogalvez@gmail.com
 */

@Slf4j
@RestController
@RequestMapping(path = "client")
@CrossOrigin(origins = {"*"})
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ResponseWrapper> addTransaction(
      @Valid @RequestBody ClientRequestDTO clientRequestDTO) {
    log.info("POST: /client/add");
    log.info("Data:{}", clientRequestDTO);
    ResponseWrapper response = Utils.formatResponse(
        HttpStatus.CREATED.value(),
        HttpStatus.CREATED.getReasonPhrase(),
        clientService.addClient(clientRequestDTO));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping(path = "/{rut}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ResponseWrapper> findClientByRut(
      @PathVariable(value = "rut") String rut) {

    log.info("GET: /client/rut={}", rut);

    ResponseWrapper response = Utils.formatResponse(
        HttpStatus.OK.value(),
        HttpStatus.OK.getReasonPhrase(),
        clientService.findClientByRut(rut));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping(path = "/{rut}")
  public ResponseEntity<ResponseWrapper> deleteClientByRut(
      @PathVariable(value = "rut") String rut) {

    log.info("DELETE: /client/rut={}", rut);

    ResponseWrapper response = Utils.formatResponse(
        HttpStatus.OK.value(),
        HttpStatus.OK.getReasonPhrase(),
        clientService.deleteClientByRut(rut));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping(path = "/{rut}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ResponseWrapper> updateClient(
      @PathVariable(name = "rut") String rut,
      @Valid @RequestBody UpdatedClientRequestDTO updatedClientRequestDTO) {

    log.info("PUT: /client/rut={}", rut);
    log.info("Data:{}", updatedClientRequestDTO);
    ResponseWrapper response = Utils.formatResponse(
        HttpStatus.OK.value(),
        HttpStatus.OK.getReasonPhrase(),
        clientService.updateClient(rut, updatedClientRequestDTO));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
