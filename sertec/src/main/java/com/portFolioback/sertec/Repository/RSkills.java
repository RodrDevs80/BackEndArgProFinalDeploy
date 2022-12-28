/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portFolioback.sertec.Repository;

import com.portFolioback.sertec.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CarlosAdmin
 */
public interface RSkills extends JpaRepository<Skills, Integer> {

    public Optional<Skills> findByNombreS(String nombreS);

    public boolean existsByNombreS(String nombreS);
}
