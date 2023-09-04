package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder;
	
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID(); 	//uuid
		String imageFileName  = uuid + "_"+  imageUploadDto.getFile().getOriginalFilename(); 	//1.jpg 
		
		System.out.println("이미지 파일이름 :: " + imageFileName );
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		//통신, I/O(하드디스크 기록하거나 읽을때) -> 예외가 발생할 수 있다. -> 예외처리해줘야한다. 
		try {			//path 				실제이미지파일 +바이트화 
			Files.write(imageFilePath,  imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		System.out.println("imageEntity :: " +imageEntity);
		
		
	}
	
}
