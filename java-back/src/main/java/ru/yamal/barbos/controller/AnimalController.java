package ru.yamal.barbos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.yamal.barbos.dto.AnimalDto;
import ru.yamal.barbos.service.AnimalService;
import ru.yamal.barbos.service.PhotoStorageService;

import java.util.List;

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
@RequiredArgsConstructor
@RequestMapping("/animals")
@RestController
public class AnimalController {

    private final AnimalService animalService;
    private final PhotoStorageService photoStorageService;

    @GetMapping
    public List<AnimalDto> getAll() {
        return animalService.getAll();
    }

    @PostMapping
    public AnimalDto createAnimal(AnimalDto animalDto) {
        return animalService.createAnimal(animalDto);
    }

    @GetMapping("/{id}")
    public AnimalDto getById(@PathVariable("id") Long id) {
        return animalService.getAnimal(id);
    }

    @PutMapping("/{id}")
    public AnimalDto getById(@PathVariable("id") Long id, @RequestBody AnimalDto animalDto) {
        animalDto.setId(id);
        return animalService.update(animalDto);
    }

    @PostMapping("/{id}/photo")
    public AnimalDto addPhoto(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        String fileName = photoStorageService.storeFile(file);
        return animalService.addPhoto(id, fileName);
    }

    @DeleteMapping("/{id}/photo/{photo:.+}}")
    public AnimalDto removePhoto(@PathVariable("id") Long id, @PathVariable("photo") String photo) {
        photoStorageService.delete(photo);
        return animalService.addPhoto(id, photo);
    }
}
