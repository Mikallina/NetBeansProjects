/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinema.controller;

import com.mycompany.cinema.model.Analise;
import com.mycompany.cinema.model.Filme;
import com.mycompany.cinema.repository.AnaliseRepository;
import com.mycompany.cinema.repository.FilmeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mi_bo
 */
@Controller
@RequestMapping("/analises")
public class AnaliseController {

    private final AnaliseRepository analiseRepository;
    private final FilmeRepository filmeRepository;

    public AnaliseController(AnaliseRepository analiseRepository, FilmeRepository filmeRepository) {
        this.analiseRepository = analiseRepository;
        this.filmeRepository = filmeRepository;
    }

    @GetMapping("/adicionar")
    public String adicionarAnalise(@RequestParam Long filmeId, Model model) {
        Filme filme = filmeRepository.findById(filmeId).orElseThrow();
        model.addAttribute("filme", filme);
        model.addAttribute("analise", new Analise());
        model.addAttribute("analises", analiseRepository.findAllByFilmeId(filmeId));
        return "analises/form";
    }

    @PostMapping("/salvar")
    public String salvarAnalise(@ModelAttribute Analise analise, @RequestParam Long filmeId) {
        Filme filme = filmeRepository.findById(filmeId).orElseThrow();
        analise.setFilme(filme);
        // Se a análise já tiver um ID, é uma edição, então salvamos a análise
        if (analise.getId() != null) {
            // Caso seja uma edição, você pode querer verificar se a análise existe antes de atualizar.
            analiseRepository.save(analise);
        } else {
            // Caso contrário, é uma criação de nova análise
            analiseRepository.save(analise);
        }

        return "redirect:/analises/adicionar?filmeId=" + filmeId;
    }

    @PutMapping("/editar/{id}")
    public String editarAnalise(@PathVariable Long id, Analise analiseAtualizada) {
        Analise analise = analiseRepository.findById(id).orElseThrow();
        analise.setComentario(analiseAtualizada.getComentario());
        analise.setNota(analiseAtualizada.getNota());
        analiseRepository.save(analise);

        return "redirect:/analises/adicionar?filmeId=";
    }

    @GetMapping("/editar/{id}")
    public String editarAnalise(@PathVariable Long id, Model model) {
        Analise analise = analiseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Análise não encontrada"));
        model.addAttribute("analise", analise); 
        model.addAttribute("filme", analise.getFilme());
        return "analises/form"; 
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarAnalise(@PathVariable Long id) {
        Analise analise = analiseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Análise não encontrada"));
        analiseRepository.delete(analise);
        return "redirect:/analises/adicionar?filmeId=" + analise.getFilme().getId();
    }

}
