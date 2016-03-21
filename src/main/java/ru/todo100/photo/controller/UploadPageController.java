package ru.todo100.photo.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/upload")
public class UploadPageController {
    @ResponseBody
    @RequestMapping("")
    public void index(HttpServletResponse response,@RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        //response.getWriter().println(image.getContentType());
        String contentType = image.getContentType();
        String extension = null;

        switch(contentType) {
            case "image/png": extension = "png"; break;
            case "image/jpeg": extension = "jpg"; break;
            default: throw new UnsupportedOperationException();
        }
        final String filename = RandomStringUtils.randomAlphanumeric(6) + "." + extension;
        File file = new File("C:/Server/" +filename);
        FileUtils.writeByteArrayToFile(file, image.getBytes());
        response.getWriter().println(filename);
    }

    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    @ResponseBody public FileSystemResource getFile(@PathVariable("file_name") String fileName) {
        File file = new File("C:/Server/" +fileName);
        return new FileSystemResource(file);
    }

}
