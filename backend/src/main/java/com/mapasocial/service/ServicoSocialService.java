package com.mapasocial.service;

import com.mapasocial.dto.ServicoSocialDTO;
import com.mapasocial.model.ServicoSocial;
import com.mapasocial.repository.AvaliacaoRepository;
import com.mapasocial.repository.ServicoSocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicoSocialService {
    private final ServicoSocialRepository servicoRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    public List<ServicoSocialDTO> listarAtivos() {
        return servicoRepository.findByAtivoTrue()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<ServicoSocialDTO> listarPorCategoria(UUID categoriaId) {
        return servicoRepository.findByCategoriaIdAndAtivoTrue(categoriaId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<ServicoSocialDTO> buscarPorTermo(String termo) {
        return servicoRepository.buscarPorTermo(termo)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public Optional<ServicoSocialDTO> buscarPorId(UUID id) {
        return servicoRepository.findById(id)
            .map(this::convertToDTO);
    }

    private ServicoSocialDTO convertToDTO(ServicoSocial servico) {
        Double media = avaliacaoRepository.calcularMediaNotas(servico.getId());

        return new ServicoSocialDTO(
            servico.getId(),
            servico.getCategoria() != null ? servico.getCategoria().getId() : null,
            servico.getCategoria() != null ? servico.getCategoria().getNome() : null,
            servico.getNome(),
            servico.getDescricao(),
            servico.getEndereco(),
            servico.getLatitude(),
            servico.getLongitude(),
            servico.getTelefone(),
            servico.getEmail(),
            servico.getHorarioFuncionamento(),
            servico.getSiteUrl(),
            servico.getRequisitos(),
            servico.getDocumentosNecessarios(),
            servico.getVerificado(),
            media
        );
    }
}
