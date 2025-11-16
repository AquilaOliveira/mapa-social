package com.mapa.social.demo.service;

import com.mapa.social.demo.model.Endereco;
import com.mapa.social.demo.model.ServicoSocial;
import com.mapa.social.demo.repository.ServicoSocialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoSocialService {

    private final ServicoSocialRepository servicoSocialRepository;
    private final GeocodingService geocodingService;

    public ServicoSocialService(ServicoSocialRepository servicoSocialRepository, GeocodingService geocodingService) {
        this.servicoSocialRepository = servicoSocialRepository;
        this.geocodingService = geocodingService;
    }

    @Transactional
    public ServicoSocial salvar(ServicoSocial servicoSocial) {
        if (servicoSocial.getNome() == null || servicoSocial.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do serviço social é obrigatório.");
        }

        Endereco end = servicoSocial.getEndereco();
        if (end != null && (end.getLatitude() == null || end.getLongitude() == null)) {
            String address = String.format("%s, %s, %s",
                    end.getRua() != null ? end.getRua() : "",
                    end.getCidade() != null ? end.getCidade() : "",
                    end.getUf() != null ? end.getUf() : "");
            double[] latlon = null;
            try {
                latlon = geocodingService.geocode(address);
            } catch (Exception ex) {
                latlon = null;
            }
            if (latlon != null) {
                end.setLatitude(latlon[0]);
                end.setLongitude(latlon[1]);
            }
        }

        return servicoSocialRepository.save(servicoSocial);
    }

    public List<ServicoSocial> buscarTodos() {
        return servicoSocialRepository.findAll();
    }

    public Optional<ServicoSocial> buscarPorId(Integer id) {
        return servicoSocialRepository.findById(id);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!servicoSocialRepository.existsById(id)) {
            throw new RuntimeException("Serviço Social com ID " + id + " não encontrado.");
        }
        servicoSocialRepository.deleteById(id);
    }
}
