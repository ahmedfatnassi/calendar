package com.ERP.authentification.services;


import com.ERP.authentification.Models.DBFile;
import com.ERP.authentification.repositories.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        /*try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
              //  throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
           // throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }*/
        DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
        return dbFileRepository.save(dbFile);
    }

    public DBFile getFile(Long fileId) {
        return dbFileRepository.findById(fileId).get();
        //  .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
