package com.example.ecomver_web.controller;

import com.example.ecomver_web.model.entity.Product;
import com.example.ecomver_web.model.response.FileResponse;
import com.example.ecomver_web.service.FileService;
import com.example.ecomver_web.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/files")
@SecurityRequirement(name = "bearerAuth")
public class FileUploadController {
    private final FileService fileService;
    private final ProductService productService;

    public FileUploadController(FileService fileService, ProductService productService) {
        this.fileService = fileService;
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, name = "/upload")
    @Operation(summary = "Upload File")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) throws IOException {
        String fileName = fileService.saveFile(file);
        FileResponse fileResponse = new FileResponse(fileName, file.getContentType(), file.getSize());
        return new ResponseEntity<>(fileResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Get File")
    public ResponseEntity<?> getFile(@RequestParam String fileName) throws IOException {
        Resource resource = fileService.getFileByFileName(fileName);
        MediaType mediaType;
        if (fileName.endsWith(".pdf")) {
            mediaType = MediaType.APPLICATION_PDF;
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
            mediaType = MediaType.IMAGE_PNG;
        } else {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .contentType(mediaType).body(resource);
    }
}
