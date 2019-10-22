package ru.yamal.barbos.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yamal.barbos.domain.model.AnimalEntity;
import ru.yamal.barbos.domain.repository.AnimalRepository;
import ru.yamal.barbos.dto.AnimalDto;
import ru.yamal.barbos.exception.CustomException;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final ModelMapper modelMapper;

    public List<AnimalDto> getAll() {
        return animalRepository.findAll().stream().map(animalEntity -> modelMapper.map(animalEntity, AnimalDto.class)).collect(Collectors.toList());
    }

    public AnimalDto createAnimal(AnimalDto animalDto) {
        AnimalEntity animalEntity = modelMapper.map(animalDto, AnimalEntity.class);
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }

    public AnimalDto getAnimal(Long id) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        return modelMapper.map(animalEntity, AnimalDto.class);
    }

    public AnimalDto update(AnimalDto animalDto) {
        AnimalEntity animalEntity = animalRepository.findById(animalDto.getId()).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        modelMapper.map(animalDto, animalEntity);
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }

    public List<AnimalDto> history(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        AuditQuery q = auditReader.createQuery().forRevisionsOfEntity(AnimalEntity.class, true, true);
        q.add(AuditEntity.id().eq(id));
        List<AnimalEntity> audit = q.getResultList();

//        AnimalEntity original = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Original record not found"));
//        audit.forEach(serviceRequestEntity -> serviceRequestEntity.setCreator(original.getCreator()));

        return audit.stream().map(animalEntity -> modelMapper.map(animalEntity, AnimalDto.class)).collect(Collectors.toList());
    }

    public AnimalDto addPhoto(Long id, String photo) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        animalEntity.getPhotoIds().add(photo);
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }

    public AnimalDto removePhoto(Long id, String photo) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        animalEntity.getPhotoIds().remove(photo);
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }
}
