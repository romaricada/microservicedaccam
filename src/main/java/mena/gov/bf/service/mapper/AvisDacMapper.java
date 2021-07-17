/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mena.gov.bf.service.mapper;

import mena.gov.bf.service.dto.AvisDacDTO;
import org.mapstruct.Mapper;
import mena.gov.bf.domain.*;

/**
 *
 * @author nafolo
 */
@Mapper(componentModel = "spring", uses = {})
public interface AvisDacMapper extends EntityMapper<AvisDacDTO, AvisDac> {

    @Override
    AvisDacDTO toDto(AvisDac avisDac);

    @Override
    AvisDac toEntity(AvisDacDTO avisDacDTO);

   default AvisDac fromId(Long id) {
        if (id == null) {
            return null;
        }
        AvisDac avisDac = new AvisDac();
        avisDac.setId(id);
        return avisDac;
    }
}
