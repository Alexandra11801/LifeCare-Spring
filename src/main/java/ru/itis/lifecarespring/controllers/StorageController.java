package ru.itis.lifecarespring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.lifecarespring.services.FilesService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/files")
public class StorageController {

	@Autowired
	private FilesService filesService;

	@GetMapping("/{file-name}")
	public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response){
		filesService.writeToResponse(fileName, response);
	}

}
