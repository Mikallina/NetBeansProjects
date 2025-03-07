/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinema.controller;

import com.mycompany.cinema.model.Filme;
import com.mycompany.cinema.repository.AnaliseRepository;
import com.mycompany.cinema.repository.FilmeRepository;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mi_bo
 */
@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeRepository filmeRepository;
    private final AnaliseRepository analiseRepository;

    public FilmeController(FilmeRepository filmeRepository, AnaliseRepository analiseRepository) {
        this.filmeRepository = filmeRepository;
        this.analiseRepository = analiseRepository;
    }

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeRepository.findAll());
        return "filmes/lista";
    }

    @GetMapping("/detalhes")
    public String detalhesFilme(Long filmeId, Model model) {
        Filme filme = filmeRepository.findById(filmeId).orElseThrow();
        model.addAttribute("filme", filme);
        model.addAttribute("analises", analiseRepository.findAllByFilmeId(filmeId));
        return "filmes/detalhes";
    }

    @GetMapping("/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "filmes/form";
    }

    @PostMapping("/salvar")
    public String salvarFilme(Filme filme) {
        filmeRepository.save(filme);
        return "redirect:/filmes";
    }

    // Método GET para editar um filme existente
    @GetMapping("/editar/{id}")
    public String editarFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeRepository.findById(id).orElseThrow();
        model.addAttribute("filme", filme);
        return "filmes/form";  // Usando o mesmo formulário para editar
    }

    @PutMapping("/editar/{id}")
    public String atualizarFilme(@PathVariable Long id, Filme filmeAtualizado) {
        Filme filme = filmeRepository.findById(id).orElseThrow();
        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setSinopse(filmeAtualizado.getSinopse());
        filme.setGenero(filmeAtualizado.getGenero());
        filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());

        filmeRepository.save(filme);
        return "redirect:/filmes";
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarFilme(@PathVariable Long id) {
        Optional<Filme> filmeOpt = filmeRepository.findById(id);

        if (filmeOpt.isPresent()) {
            Filme filme = filmeOpt.get();
            filmeRepository.delete(filme);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }
    }

}
