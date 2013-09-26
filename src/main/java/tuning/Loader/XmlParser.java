package tuning.Loader;

import java.io.File;

import javax.xml.bind.Unmarshaller;



public class XmlParser {
	private String file;
	private String detailsFile;
	private Unmarshaller unmarshaller;


	public void set10kFile() {
		file = "sampledata/orders_rand_10000.xml";
		detailsFile = "sampledata/orderdetails_rand_10000.xml";
		buildXmlDocument();

	}

	public void set20kFile() {
		file = "sampledata/orders_rand_20000.xml";
		detailsFile = "sampledata/orderdetails_rand_20000.xml";
		buildXmlDocument();

	}

	public void buildXmlDocument() {
	
		}
	}



