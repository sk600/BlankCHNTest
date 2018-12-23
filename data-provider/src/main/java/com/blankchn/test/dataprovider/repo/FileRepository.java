package com.blankchn.test.dataprovider.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author BlankCHN
 * @date 2018-12-20 16:49
 */
@Repository("fileRepository")
public class FileRepository {

    @Autowired
    GridFsOperations operations;

    public GridFsResource getFile(String filename) throws IOException {
        GridFsResource resource = operations.getResource(filename);
        return resource;
    }

    public void save(InputStream inputStream, String fileName, String content_type) {
        operations.store(inputStream,fileName,content_type);
    }

}
