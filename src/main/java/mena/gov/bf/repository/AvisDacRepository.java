/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mena.gov.bf.repository;

import mena.gov.bf.domain.AvisDac;
import mena.gov.bf.service.dto.AvisDacDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author nafolo
 */
public interface AvisDacRepository extends JpaRepository<AvisDac, Long>{
    List<AvisDac> findByExerciceId (Long exerciceId);
    
}
