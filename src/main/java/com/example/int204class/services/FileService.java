package com.example.int204class.services;

import com.example.int204class.properties.FileStorageProperties;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileService {
    private final Path fileStorageLocation;

    @Autowired
    public FileService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Cloud not create the directory where the uploaded files will be stored", ex);
        }
    }

    //    public String storeA(MultipartFile file) {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        try {
//            if (fileName.contains("..")) {
//                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//            return fileName;
//        } catch (IOException ex) {
//            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }
    public String store(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = StringUtils.cleanPath("abc" + "." + extension);
        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry!!");
            }

            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                fileName = StringUtils.cleanPath(System.currentTimeMillis() + "." + extension);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);
            return fileName;

        } catch (IOException ex) {
            throw new RuntimeException("Cloud not store file " + fileName + ". Please try again!!", ex);
        }
    }

    public List<String> saveFile(List<MultipartFile> multipartFile) {
        List<String> fileName = new ArrayList<>();
        multipartFile.forEach(imgae -> {
            String name = this.store(imgae);
            fileName.add(name);
        });
        return fileName;
    }

    public Resource loadFileAsResource(String fileName) throws FileNotFoundException {
//        fileName = null;
//        Integer.parseInt(fileName);
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File Not Found!");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Name is null");
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File operation error!");
        }
    }

    public Resource loadAllFile() {
        try {
            Path filePath = this.fileStorageLocation.normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found");
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File operation error!");
        }
    }

    public void deleteResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            } else {
                throw new RuntimeException("File not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("File operation error");
        }
    }
}
