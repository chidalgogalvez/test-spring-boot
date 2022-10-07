package cl.heroes.client.service;

import cl.heroes.client.dto.ClientRequestDTO;
import cl.heroes.client.dto.ClientResponseDTO;
import cl.heroes.client.dto.UpdatedClientRequestDTO;

public interface ClientService {

  ClientResponseDTO addClient(ClientRequestDTO clientRequestDTO);

  ClientResponseDTO findClientByRut(String rut);

  String deleteClientByRut(String rut);

  ClientResponseDTO updateClient(String rut, UpdatedClientRequestDTO updatedClientRequestDTO);
}
