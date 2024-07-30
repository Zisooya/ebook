package com.jg.ebook.service;

import com.jg.ebook.dto.response.ImageResponse;
import com.jg.ebook.util.CommonUtil;
import com.jg.ebook.util.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {

	private final FileUtil fileUtil;

	private final CommonUtil commonUtil;

	@Value("${ebook.value.img.dir}")
	private String IMG_DIR;

	@Value("${spring.config.activate.on-profile}")
	private String PROFILE;

	public String[] getPdf(String fileName){
		return fileUtil.getTextOfPdf(fileName);
	}

	public ImageResponse getEbookImageInfo(HttpServletRequest req){
		//해당 폴더에 이미지 갯수를 구하는 로직
		String imagePath = "";
		if("local".equals(PROFILE)){
			imagePath = "src/main/resources/static";
		}
		else{
			imagePath = "";
		}

		imagePath = imagePath.concat(IMG_DIR);

		File dir = new File(imagePath);
		String[] extensions = new String[]{"jpg", "jpeg", "png", "gif"};
		int imageCount = 0;

		if(dir.exists() && dir.isDirectory()){
			imageCount = (int) Arrays.stream(dir.listFiles())
					.filter(file -> {
						String fileName = file.getName().toLowerCase();
						return Arrays.stream(extensions)
								.anyMatch(fileName::endsWith);
					})
					.count();
		}

		//화면에서 보여줄 이미지 경로
		imagePath = IMG_DIR;
		ImageResponse imageResponse = new ImageResponse();
		imageResponse.setImageCount(imageCount);
		imageResponse.setImagePath(imagePath);

		return imageResponse;
	}
}
