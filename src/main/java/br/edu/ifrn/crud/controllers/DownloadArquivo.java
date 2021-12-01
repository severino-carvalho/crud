package br.edu.ifrn.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifrn.crud.repository.ArquivoRepository;

@Controller
public class DownloadArquivo {
    @Autowired
    private ArquivoRepository arquivoRepository;

    @GetMapping("/download/{idArquivo}")
    public String download() {
        return "";
    }
}
