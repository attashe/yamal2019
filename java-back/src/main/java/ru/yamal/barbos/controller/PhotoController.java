package ru.yamal.barbos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.yamal.barbos.dto.PhotoDto;
import ru.yamal.barbos.service.PhotoStorageService;

@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/photos")
public class PhotoController {

    private final PhotoStorageService photoStorageService;

    @GetMapping("/{photo:.+}")
    public ResponseEntity<Resource> getById(@PathVariable("photo") String photo) {
        Resource resource = photoStorageService.loadFileAsResource(photo);
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<PhotoDto> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = photoStorageService.storeFile(file);
        return ResponseEntity.ok(new PhotoDto(fileName));
    }

    @DeleteMapping("/{photo:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable("photo") String photo) {
        photoStorageService.delete(photo);
        return ResponseEntity.ok(photo);
    }
}
