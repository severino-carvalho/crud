package br.edu.ifrn.crud.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifrn.crud.domains.Arquivo;
import br.edu.ifrn.crud.repository.ArquivoRepository;

@Controller
public class DownloadArquivo {
    @Autowired
    private ArquivoRepository arquivoRepository;

    @GetMapping("/download/{idArquivo}")
    public ResponseEntity<?> downloadFile(@PathVariable Long idArquivo, @PathParam("salvar") String salvar) {

        Arquivo file = arquivoRepository.findById(idArquivo).get();

        String texto = (salvar == null || salvar.equals("true"))
                ? "attachment; filename=\"" + file.getNomeArquivo() + "\""
                : "inline; filename=\"" + file.getNomeArquivo() + "\"";

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getTipoArquivo()))
                .header(HttpHeaders.CONTENT_DISPOSITION, texto).body(new ByteArrayResource(file.getDados()));
    }
}
