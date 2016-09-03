package ru.todo100.files.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String index(@RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        String contentType = image.getContentType();
        final String extension;
        switch(contentType) {
            case "image/png": extension = "png"; break;
            case "image/jpeg": extension = "jpg"; break;
            default: throw new UnsupportedOperationException();
        }
        final String filename = RandomStringUtils.randomAlphanumeric(6) + "." + extension;
        File file = new File("C:/Server/" + filename);
        FileUtils.writeByteArrayToFile(file, image.getBytes());
        return filename;
    }

    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("file_name") String fileName) {
        File file = new File("C:/Server/" +fileName);
        return new FileSystemResource(file);
    }

}
