package com.example.ecomver_web.service.serviceimpl;

import com.example.ecomver_web.service.FileService;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private final Storage storage;

    public FileServiceImpl(Storage storage) {
        this.storage = storage;
    }
    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        if (fileName.contains(".jpg") || fileName.contains(".png") || fileName.contains(".jpeg") || fileName.contains("gif") || fileName.contains("bmp")) {
            String uniqueFileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
            BlobId blobId = BlobId.of("javaproject-f64e4.appspot.com", uniqueFileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .setAcl(Collections.singletonList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))) // Make the object publicly readable
                    .build();
            Blob blob = storage.create(blobInfo, file.getInputStream());

            return blob.getMediaLink();
        } else {
            return "Upload Failed";
        }
    }
}
