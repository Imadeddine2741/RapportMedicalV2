package org.creditMutuel.service;

import java.util.List;

import org.creditMutuel.logger.LoggerService;
import org.creditMutuel.model.dto.RapportDto;
import org.creditMutuel.model.entity.Rapport;
import org.creditMutuel.model.mapper.RapportMapper;
import org.creditMutuel.model.mapper.RapportMapperStruct;
import org.creditMutuel.repository.RapportRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RapportServiceImpl implements RapportService{
	
	private static final Logger LOGGER = 
			(ch.qos.logback.classic.Logger) LoggerFactory.getLogger(RapportServiceImpl.class);

	@Autowired
	private  RapportRepository rapportRepository;

	@Autowired
	private  RapportMapper rapportMapper;
	
	@Autowired
	private RapportMapperStruct rapportMapperStruct;

	public List<RapportDto> getAll() {
		List<Rapport> rapports = rapportRepository.findAll();
		//return rapportMapper.entitiesToDtos(rapports);	
		return rapportMapperStruct.rapportsToRapportsDtos(rapports);
	}


	public RapportDto addRapport(RapportDto rapportDetails) {
		if( rapportDetails != null) {
			Rapport rapport= new Rapport();
			rapport.setDateCreation(rapportDetails.getDateCreation());
			rapport.setDomaine(rapportDetails.getDomaine());
			rapport.setPosition(rapportDetails.getPosition());
			rapport.setTitre(rapportDetails.getTitre());
			rapport.setNum(rapportDetails.getNum());
			rapport.setAssure(rapportDetails.getAssure());
			rapport.setCommentaires(rapportDetails.getCommentaires());
			Rapport rapportSave =  rapportRepository.save(rapport);
			//return rapportMapper.entityToDto(rapportSave);
			return  rapportMapperStruct.rapportToRapportDto(rapportSave);
		}else {
			return null;
		}
		
	}
	
	public RapportDto getByNumRapport(Integer num){
		
		// je  recherche le rapport en fonction du num_rapport
		
		Integer[] params =  {num};
    	LoggerService.ecritLogMessage(LOGGER, Level.INFO, "Rapport {} non présent en cache, on interroge la bdd", params);
    	
		Rapport myRapport =rapportRepository.findByNumRapport(num);
		if(myRapport !=null) {
			//return  rapportMapper.entityToDto(myRapport);
			return rapportMapperStruct.rapportToRapportDto(myRapport);
		}
		return null;
	}

	public List<RapportDto> getByNumAssure(int numAssure){
		
		// je  recherche le ou les rapports en fonction du num_assure
		
		Integer[] params =  {numAssure};
    	LoggerService.ecritLogMessage(LOGGER, Level.INFO, "Rapport {} non présent en cache, on interroge la bdd", params);
    	
		List<Rapport> myRapports = rapportRepository.findByNumAssure(numAssure);	
		if(myRapports !=null) {
			//return  rapportMapper.entitiesToDtos(myRapports);
			return rapportMapperStruct.rapportsToRapportsDtos(myRapports);
		}
		return null;
	}
	
	public List<RapportDto> getByNomAssure(String nomAssure){
		
		// je  recherche le ou les rapports en fonction du nom_assure
		
		String[] params =  {nomAssure};
    	LoggerService.ecritLogMessage(LOGGER, Level.INFO, "Rapport {} non présent en cache, on interroge la bdd", params);
    	
		List<Rapport> myRapports = rapportRepository.findByNomAssure(nomAssure);	
		if(myRapports !=null) {
			//return  rapportMapper.entitiesToDtos(myRapports);
			return rapportMapperStruct.rapportsToRapportsDtos(myRapports);
		}
		return null;
	}

	public void deleteRapport(Rapport rapport) {
		rapportRepository.delete(rapport);
	}

}
