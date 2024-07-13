package com.jg.ebook.service;

import com.jg.ebook.util.CommonUtil;
import com.jg.ebook.util.PdfUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MainService {

	private final PdfUtil pdfUtil;

	private final CommonUtil commonUtil;

	public String[] getPdf(String fileName){
		//return pdfUtil.getTextOfPdf(fileName);
		return pdfUtil.getFileStr(fileName);
	}

	public int getImageCount(){
		//해당 폴더에 이미지 갯수를 구하는 로직
		String imagePath = "src/main/resources/static/img/ebook_desk";
		File dir = new File(imagePath);
		String[] extensions = new String[]{"jpg", "jpeg", "png", "gif"};
		int imageCount = 0;

		if (dir.exists() && dir.isDirectory()) {
			imageCount = (int) Arrays.stream(dir.listFiles())
					.filter(file -> {
						String fileName = file.getName().toLowerCase();
						return Arrays.stream(extensions)
								.anyMatch(fileName::endsWith);
					})
					.count();
		}

		return imageCount;
	}
}
