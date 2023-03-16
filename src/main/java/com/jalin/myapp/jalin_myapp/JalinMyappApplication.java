package com.jalin.myapp.jalin_myapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jalin.myapp.jalin_myapp.dto.MailRequest;
import com.jalin.myapp.jalin_myapp.dto.MailResponse;
import com.jalin.myapp.jalin_myapp.dto.ModelFormatBank;
import com.jalin.myapp.jalin_myapp.service.EmailService;

@SpringBootApplication
@RestController
public class JalinMyappApplication {

	@Autowired
	private EmailService service;

	@PostMapping("/sendingEmail")
	public MailResponse sendEmail(@RequestBody MailRequest request)throws IOException {
		List<ModelFormatBank> listOfStrings = new ArrayList<>();
   
		String line;
		BufferedReader bf = new BufferedReader(new FileReader("D:\\Data Alert.txt"));
		Map<String, String> model = new HashMap<>();

		while ((line = bf.readLine()) != null) {
			String[] values = line.split(";");

			if (values[0].contains("MDR")|| values[0].contains("BNI")) {
				listOfStrings.add(new ModelFormatBank(values[0], values[1], Integer.parseInt(values[2]), values[3], values[4]));
			}
		}

		String temp = "";
		for (int i = 0; i < listOfStrings.size(); i++) {
			ModelFormatBank modelFormatBank = listOfStrings.get(i);
			temp += "Envi " +  modelFormatBank.getMp() + " Port " + modelFormatBank.getCodeBank() + " terpantau " + modelFormatBank.getStatus() + "<br>";

		}
		model.put("text", temp);
	
		bf.close();
		return service.sendEmail(request, model);

	}
	
	public static void main(String[] args) {
		SpringApplication.run(JalinMyappApplication.class, args);
	}

}
