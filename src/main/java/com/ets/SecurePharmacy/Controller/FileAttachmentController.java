package com.ets.SecurePharmacy.Controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileAttachmentController {

	@Autowired
	RestTemplate restTemplate;
	@RequestMapping(value = "/upload", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "multipart/form-data");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file ", file);

		HttpEntity<?> entity = new HttpEntity<MultiValueMap<String, Object>>(body, headers);

		try {
			restTemplate.exchange("http://localhost:9990/file/upload", HttpMethod.POST, entity, String.class);
		} finally {

		}

		/*
		 * ResponseEntity<Boolean> response =
		 * restTemplate.postForEntity("http://localhost:9990/file/upload", entity,
		 * Boolean.class); response.getBody();
		 * 
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		 * HttpEntity<MultipartFile> entity = new
		 * HttpEntity<MultipartFile>(file,headers); MultiValueMap<String, Object> body =
		 * new LinkedMultiValueMap<>(); body.add("file", file);
		 * body.add("circularAttachment", file.getBytes());
		 * 
		 * HttpEntity<MultiValueMap<String, Object>> requestEntity = new
		 * HttpEntity<>(body, headers);
		 * 
		 * String serverUrl = "http://localhost:9990/file/upload";
		 * 
		 * RestTemplate restTemplate = new RestTemplate(); ResponseEntity<String>
		 * response = restTemplate .postForEntity(serverUrl, requestEntity,
		 * String.class);
		 * 
		 */

		return null;

	}

	@PostMapping("/test")
	@PreAuthorize("hasRole('USER') " + "|| hasRole('ADMIN') ")
	public String testWelcome() {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      
	      return restTemplate.exchange("http://localhost:9990/file/welcome", HttpMethod.GET, entity, String.class).getBody();
		
	}
} 
