package ru.todo100.files.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/remove/file")
public class RemoveController {
    @ResponseBody
    @RequestMapping(value = "/{filename:.+}",produces = "text/html")
    public String index(@PathVariable String filename){
        Path path = Paths.get("C:/Server/" + filename);
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            x.printStackTrace();
        }
        return filename;
    }
}