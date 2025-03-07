/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinema.repository;

import com.mycompany.cinema.model.Analise;
import com.mycompany.cinema.model.Filme;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mi_bo
 */
@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Long> {

    public List<Analise> findAllByFilmeId(Long filmeId);  

    public void deleteByFilme(Filme filme);  
    
}

