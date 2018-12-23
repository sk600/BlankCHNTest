package com.blankchn.test.dataprovider.controller;

import com.blankchn.test.dataprovider.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author BlankCHN
 * @date 2018-12-14 22:11
 */
@RestController
@RequestMapping("/uploadFile")
public class FileUploadController {
    @Autowired
    private FileRepository fileRepository;

    @PostMapping(value = "/server", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUploadServer(MultipartFile file) throws IOException {
        fileRepository.save(file.getInputStream(),file.getName(), file.getContentType());
        System.out.println("lala");
        return file.getOriginalFilename();
    }

    @PostMapping("/download/{id}")
    public void download(@PathVariable("id") String id, HttpServletResponse response) throws IOException {

        GridFsResource resource = fileRepository.getFile(id);
        InputStream inputStream = resource.getInputStream();
        byte[] data = new byte[1024];

        OutputStream stream = response.getOutputStream();
        while (inputStream.read(data) != -1) {
            stream.write(data);
            stream.flush();
        }
        stream.flush();
        stream.close();
        inputStream.close();
    }

}
