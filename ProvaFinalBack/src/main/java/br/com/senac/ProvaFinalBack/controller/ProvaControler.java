package br.com.senac.ProvaFinalBack.controller;

import br.com.senac.ProvaFinalBack.dtos.ProvaFiltroDto;
import br.com.senac.ProvaFinalBack.dtos.ProvaRequestDto;
import br.com.senac.ProvaFinalBack.entidades.Prova;
import br.com.senac.ProvaFinalBack.service.ProvaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prova")
public class ProvaControler {
    private ProvaService provaService;

    public ProvaControler(ProvaService provaService) {
        this.provaService = provaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Prova>> listar(ProvaFiltroDto filtro) {
        return ResponseEntity
                .ok(provaService.listar(filtro));
    }

    @PostMapping("/criar")
    public ResponseEntity<Prova> criar(
            @RequestBody ProvaRequestDto prova) {
        try {
            return ResponseEntity
                    .ok(provaService.criar(prova));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Prova> atualizar(
            @RequestBody ProvaRequestDto cliente,
            @PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok(provaService.atualizar(id, cliente));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        try {
            provaService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

}
