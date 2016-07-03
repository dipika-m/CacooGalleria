package com.demo.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.demo.CacooException;
import com.demo.CacooService;

/**
 * @author Dipika
 */
public class CacooServiceImpl implements CacooService {

	private String cacooDiagramListURL;
	private String cacooDiagramURLPattern;
	private OAuthRestTemplate cacooRestTemplate;

	public List<String> getCacooDiagramIds() throws CacooException {
		try {
			// InputStream diagramsXML = new
			// ByteArrayInputStream(getCacooRestTemplate().getForObject(URI.create(getCacooDiagramListURL()),
			// byte[].class));
			byte[] bytes = getCacooRestTemplate().getForObject(URI.create("https://cacoo.com/api/v1/diagrams.xml"),
					byte[].class);
			InputStream diagramsXML = new ByteArrayInputStream(bytes);
			final List<String> diagramIds = new ArrayList<String>();
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(false);
			parserFactory.setXIncludeAware(false);
			parserFactory.setNamespaceAware(false);

			SAXParser parser = parserFactory.newSAXParser();
			parser.parse(diagramsXML, new DefaultHandler() {
				boolean inDiagramId = false;

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					if ("diagramId".equals(qName)) {
						inDiagramId = true;
					}
				}

				@Override
				public void characters (char ch[], int start, int length)
				        throws SAXException
				    {
					if (inDiagramId) {
					diagramIds.add(new String(ch, start, length));
				}
				    }
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {

					if ("diagramId".equals(qName)) {
						inDiagramId = false;
					}
				}
			});
			return diagramIds;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} catch (SAXException e) {
			throw new IllegalStateException(e);
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException(e);
		}
	}

	public InputStream loadCacooDiagram(String id) throws CacooException {
		System.out.println(String.format(getCacooDiagramURLPattern(), id));
		return new ByteArrayInputStream(getCacooRestTemplate()
				.getForObject(URI.create(String.format(getCacooDiagramURLPattern(), id)), byte[].class));
	}

	public String getCacooDiagramURLPattern() {

		return cacooDiagramURLPattern;
	}

	public void setCacooDiagramURLPattern(String cacooDiagramURLPattern) {
		this.cacooDiagramURLPattern = cacooDiagramURLPattern;
	}

	public String getCacooDiagramListURL() {
		return cacooDiagramListURL;
	}

	public void setCacooDiagramListURL(String cacDiagramListURL) {
		this.cacooDiagramListURL = cacooDiagramListURL;
	}

	public OAuthRestTemplate getCacooRestTemplate() {
		return cacooRestTemplate;
	}

	public void setCacooRestTemplate(OAuthRestTemplate cacooRestTemplate) {
		this.cacooRestTemplate = cacooRestTemplate;
	}
}
