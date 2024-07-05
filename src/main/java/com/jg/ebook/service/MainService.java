package com.jg.ebook.service;

import com.jg.ebook.util.PdfUtil;
import org.springframework.stereotype.Service;

@Service
public class MainService {

	public String getPdf(){
		PdfUtil pdfUtil = new PdfUtil();
		return pdfUtil.getTextOfPdf();
	}
}
