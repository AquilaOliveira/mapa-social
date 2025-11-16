package com.mapa.social.demo.controller;

import com.mapa.social.demo.model.ServicoSocial;
import com.mapa.social.demo.service.ServicoSocialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import com.mapa.social.demo.dto.ServicoMapaDTO;

@RestController
@RequestMapping("/servicos")
public class ServicoSocialController {

    private final ServicoSocialService servicoSocialService;

    public ServicoSocialController(ServicoSocialService servicoSocialService) {
        this.servicoSocialService = servicoSocialService;
    }

    @PostMapping
    public ResponseEntity<ServicoSocial> criarServico(@RequestBody ServicoSocial servicoSocial) {
        ServicoSocial novoServico = servicoSocialService.salvar(servicoSocial);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServicoSocial>> buscarTodos() {
        List<ServicoSocial> servicos = servicoSocialService.buscarTodos();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoSocial> buscarPorId(@PathVariable Integer id) {
        return servicoSocialService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoSocial> atualizarServico(@PathVariable Integer id, @RequestBody ServicoSocial detalhes) {
        return servicoSocialService.buscarPorId(id)
                .map(servicoExistente -> {
                    servicoExistente.setNome(detalhes.getNome());
                    servicoExistente.setTipo(detalhes.getTipo());
                    servicoExistente.setTelefone(detalhes.getTelefone());
                    if (detalhes.getEndereco() != null) {
                        servicoExistente.setEndereco(detalhes.getEndereco());
                    }
                    if (detalhes.getCategoria() != null) {
                        servicoExistente.setCategoria(detalhes.getCategoria());
                    }
                    ServicoSocial atualizado = servicoSocialService.salvar(servicoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Integer id) {
        if (servicoSocialService.buscarPorId(id).isPresent()) {
            servicoSocialService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mapa")
    public ResponseEntity<List<ServicoMapaDTO>> listarParaMapa() {
        List<ServicoSocial> servicos = servicoSocialService.buscarTodos();
        List<ServicoMapaDTO> dto = servicos.stream().map(s -> {
            ServicoMapaDTO m = new ServicoMapaDTO();
            m.setId(s.getId());
            m.setNome(s.getNome());
            m.setTipo(s.getTipo());
            if (s.getEndereco() != null) {
                m.setLatitude(s.getEndereco().getLatitude());
                m.setLongitude(s.getEndereco().getLongitude());
                m.setEnderecoResumo(s.getEndereco().getRua() + ", " + s.getEndereco().getNumero());
            }
            return m;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }
}
