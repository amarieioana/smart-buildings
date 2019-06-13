package degree.SmartBuildings.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class FileService {


    String path = "src/main/resources/static";

    File file = new File(path);
    String absolutePath = file.getAbsolutePath();
    private final String FILE_DIRECTORY = absolutePath + '\\';

    /**
     * @param filename filename
     * @param response Http response.
     * @return file from system.
     */

    public Resource getFileSystem(String filename, HttpServletResponse response) {
        return getResource(filename, response);
    }


    private Resource getResource(String filename, HttpServletResponse response) {
        response.setContentType("text/csv; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setHeader("filename", filename);

        Resource resource = null;
        resource = new FileSystemResource(FILE_DIRECTORY + filename);

        return resource;
    }

}
