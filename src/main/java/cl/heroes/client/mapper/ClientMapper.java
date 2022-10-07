package cl.heroes.client.mapper;

import cl.heroes.client.dto.ClientRequestDTO;
import cl.heroes.client.dto.ClientResponseDTO;
import cl.heroes.client.dto.UpdatedClientRequestDTO;
import cl.heroes.client.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

  ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

  Client clientDTOToEntity(ClientRequestDTO clientRequestDTO);

  ClientResponseDTO clientEntityToDto(Client clientEntity);

  Client clientUpdateDTOToEntity(UpdatedClientRequestDTO updatedClientRequestDTO,
      @MappingTarget Client clientEntity);
}
