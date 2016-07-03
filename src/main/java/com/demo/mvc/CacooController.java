package com.demo.mvc;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.UnavailableException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.CacooService;

/**
 * @author Dipika
 */
@Controller
public class CacooController {

	private CacooService cacooService;

	@RequestMapping("/cacoo/diagrams")
	public String diagrams(Model model) throws Exception {
		model.addAttribute("cacooDiagramIds", cacooService.getCacooDiagramIds());
		return "cacoo";
	}

	@RequestMapping("/cacoo/diagram/{id}")
	public ResponseEntity<BufferedImage> photo(@PathVariable String id) throws Exception {
		InputStream diagram = cacooService.loadCacooDiagram(id);
		if (diagram == null) {
			throw new UnavailableException("The requested diagram does not exist");
		}
		BufferedImage body;
		MediaType contentType = MediaType.IMAGE_PNG;
		Iterator<ImageReader> imageReaders = ImageIO.getImageReadersByMIMEType(contentType.toString());
		if (imageReaders.hasNext()) {
			ImageReader imageReader = imageReaders.next();
			ImageReadParam irp = imageReader.getDefaultReadParam();
			imageReader.setInput(new MemoryCacheImageInputStream(diagram), true);
			body = imageReader.read(0, irp);
		} else {
			throw new HttpMessageNotReadableException("Could not find javax.imageio.ImageReader for Content-Type ["
					+ contentType + "]");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<BufferedImage>(body, headers, HttpStatus.OK);
	}

	public void setCacooService(CacooService cacooService) {
		this.cacooService = cacooService;
	}
}
