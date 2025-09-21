package com.example.billingmodule.service;

import com.example.billingmodule.dto.ClientDTO;
import com.example.billingmodule.dto.ClientDetailDTO;
import com.example.billingmodule.entity.Client;
import com.example.billingmodule.exception.ClientAlreadyExistsException;
import com.example.billingmodule.exception.ClientNotFoundException;
import com.example.billingmodule.mapper.ClientMapper;
import com.example.billingmodule.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<ClientDetailDTO> getAllClients(){
        return clientRepository.findAll().stream().map(ClientMapper::toDTO).toList();
    }

    public ClientDetailDTO getClientById(long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found !"));
        return ClientMapper.toDTO(client);
    }

    public void createClient(ClientDTO dto){
        if(clientRepository.existsByEmail(dto.getEmail())){
            throw new ClientAlreadyExistsException("Email Already exists ! ", ClientAlreadyExistsException.EXIST_TYPE.EXIST_BY_EMAIL);
        }else if(clientRepository.existsBySiret(dto.getSiret())){
            throw new ClientAlreadyExistsException("Siret Already exists !", ClientAlreadyExistsException.EXIST_TYPE.EXIST_BY_SIRET);
        }
        clientRepository.save(ClientMapper.toModel(dto));
    }

    public void updateClient(Long id,ClientDTO dto){
        Client client = clientRepository.findById(id).orElseThrow(()-> new ClientNotFoundException("Client not found !"));
        client.setName(dto.getName());
        client.setSiret(dto.getSiret());
        client.setEmail(dto.getEmail());

        clientRepository.save(client);
    }
    public void deleteClient(Long id){
        if(!clientRepository.existsById(id)){
            throw new ClientNotFoundException("Client not found !");
        }
        clientRepository.deleteById(id);
    }
}
