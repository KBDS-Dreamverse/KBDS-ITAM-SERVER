package com.kbds.itamserveradmin.global.file;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.lowagie.text.Chapter;
//import com.lowagie.text.Document;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.PdfWriter;
//
////pdf 파일을 보여주는 view로 만들기위해 extends
//public class CustomPdfView extends AbstractPdfView {
//
//    @Override
//    protected void buildPdfDocument(Map<String, Object> model,
//                                    Document document, PdfWriter writer,
//                                    HttpServletRequest request,
//                                    HttpServletResponse response) throws Exception {
//
//
//        Chapter chapter = new Chapter(new Paragraph("Spring Message"),1);
//        chapter.add(new Paragraph((String)model.get("message"))); //
//
//        document.add(chapter);
//
//    }
//
//}