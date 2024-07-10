package com.jg.ebook.service;

import com.jg.ebook.util.PdfUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

	private final PdfUtil pdfUtil;

    public String[] getPdf(String fileName){
		//return pdfUtil.getTextOfPdf(fileName);
		return pdfUtil.getFileStr(fileName);
	}
}
