package cl.heroes.client.service.impl;

import cl.heroes.client.dto.ClientRequestDTO;
import cl.heroes.client.dto.ClientResponseDTO;
import cl.heroes.client.dto.UpdatedClientRequestDTO;
import cl.heroes.client.entity.Client;
import cl.heroes.client.exception.ConflictException;
import cl.heroes.client.mapper.ClientMapper;
import cl.heroes.client.repository.ClientRepository;
import cl.heroes.client.service.ClientService;
import cl.heroes.client.util.Constants;
import cl.heroes.client.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientMapper clientMapper;

  @Override
  public ClientResponseDTO addClient(ClientRequestDTO clientRequestDTO) {

    Utils.verifyForConflict(
        clientRepository.findByRut(clientRequestDTO.getRut()).isPresent(),
        Constants.THE_RUT_ALREADY_IS_USED);

    Utils.verifyForConflict(
        clientRepository.findByEmail(clientRequestDTO.getEmail()).isPresent(),
        Constants.THE_EMAIL_ALREADY_IS_USED);

    Client clientEntity = clientRepository.save(
        clientMapper.clientDTOToEntity(clientRequestDTO));

    return clientMapper.clientEntityToDto(clientEntity);

  }

  @Override
  public ClientResponseDTO findClientByRut(String rut) {

    Client clientEntity = clientRepository.findByRut(rut)
        .orElseThrow(() -> new ConflictException(Constants.THE_RUT_NOT_EXIST));

    return clientMapper.clientEntityToDto(clientEntity);
  }

  @Override
  public String deleteClientByRut(String rut) {

    Utils.verifyForNotFound(
        clientRepository.findByRut(rut).isEmpty(),
        Constants.THE_RUT_NOT_EXIST);

    clientRepository.deleteByRut(rut);

    return Constants.DOCUMENT_DELETED;
  }

  @Override
  public ClientResponseDTO updateClient(String rut,
      UpdatedClientRequestDTO updatedClientRequestDTO) {

    Client clientEntity = clientRepository.findByRut(rut)
        .orElseThrow(() -> new ConflictException(Constants.THE_RUT_NOT_EXIST));

    return clientMapper.clientEntityToDto(
        clientRepository.save(clientMapper.clientUpdateDTOToEntity(
            updatedClientRequestDTO, clientEntity)));
  }
}
