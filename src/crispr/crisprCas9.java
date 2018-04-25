package crispr;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class crisprCas9 {

	class temp {
		String theSeq; // = object.getString("GBSeq_sequence").toString();
		String theDefinition; // =
								// object.getString("GBSeq_definition").toString();
		String theAccession; // =
		// object.getString("GBSeq_accession-version").toString();
		String theSource; // object.getString("GBSeq_source").toString();
		String theOrganism; // = object.getString("GBSeq_organism").toString();
		String seqLocus; // = object.getString("GBSeq_locus").toString();
		String GBSeq_organism;

		@Override
		public String toString() {
			return GBSeq_organism;
		}
	}

	public static void main(String[] args) throws Exception {
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		SAXHandler handler = new SAXHandler();
		parser.parse(ClassLoader.getSystemResourceAsStream("/Users/snajar/Desktop/sequence.gbx.xml"), handler);

		// Printing the list of employees obtained from XML
		for (temp emp : handler.empList) {
			System.out.println(emp);
		}
	}

	class SAXHandler extends DefaultHandler {

		List<temp> empList = new ArrayList<>();
		temp emp = null;
		String content = null;

		@Override
		// Triggered when the start of tag is found.
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {

			switch (qName) {
			// Create a new Employee object when the start tag is found
			case "employee":
				emp = new temp();
				// emp.id = attributes.getValue("id");
				break;
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			switch (qName) {
			case "GBSeq_organism":
				emp.GBSeq_organism = content;
				break;

			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			content = String.copyValueOf(ch, start, length).trim();
		}

	}

}
