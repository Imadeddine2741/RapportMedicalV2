package org.creditMutuel.model.mapper;


import java.util.Collection;
import java.util.List;

import org.creditMutuel.model.dto.AssureDto;
import org.creditMutuel.model.dto.RapportDto;
import org.creditMutuel.model.entity.Assure;
import org.creditMutuel.model.entity.Rapport;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring") 	
public interface RapportMapperStruct {
	
	Rapport rapportDtoToRapport(RapportDto rapportDto);
	RapportDto rapportToRapportDto(Rapport rapport);
	List<RapportDto> rapportsToRapportsDtos(Collection<Rapport> rapports);
	
	Assure assureDtoToAssure(AssureDto assureDto);
	AssureDto AssureToAssureDto(Assure assure);
	List<AssureDto> assuresToAssuresDtos(Collection<Assure> assures);
	
	
}
 