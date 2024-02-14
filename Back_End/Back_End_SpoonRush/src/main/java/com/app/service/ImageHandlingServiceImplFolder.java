package com.app.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ApiException;

@Component
public class ImageHandlingServiceImplFolder implements ImageHandlingService {
	@Value("${folder.location}")
	private String folderPath;

	@PostConstruct
	public void init()
	{
		File folder = new File(folderPath);
		if(!folder.exists())
		{
			folder.mkdir();
		}
	}
	
	@Override
	public String uploadImage(MultipartFile image) {
		String path = folderPath.concat(image.getOriginalFilename());
		try {
			FileUtils.writeByteArrayToFile(new File(path), image.getBytes());
			return path;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public byte[] downloadImage(String imageName) {
		String absolutePath = folderPath.concat(imageName);
		try {
			return FileUtils.readFileToByteArray(new File(absolutePath));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Image does not exist.");
		}
	}

}
