package com.example.int204class.controllers;

import com.example.int204class.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws FileNotFoundException {
        Resource file = fileService.loadFileAsResource(filename);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
    }

    @PostMapping("")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        return "You successfully uploaded " + fileService.store(file) + "!";
    }

    @PostMapping("/muti")
    public List<String> fileUploads(@RequestParam("files") List<MultipartFile> file) {
       return fileService.saveFile(file);
    }

    @DeleteMapping("/{filename:.+}")
    public ResponseEntity fileUpload(@PathVariable String filename) {
        fileService.deleteResource(filename);
        Map<String , String> test = new HashMap<>();
        test.put("SayHi", "Hello");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(test);
    }
}