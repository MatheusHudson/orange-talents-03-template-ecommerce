package br.com.zup.treinomercadolivre.Produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploudFake {

	public Set<String> send(List<MultipartFile> fotos) {

		return fotos.stream().map(foto -> "http://cloud.io/" + foto.getOriginalFilename()).collect(Collectors.toSet());

	}

}
