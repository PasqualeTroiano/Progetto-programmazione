package com.example;
/**
 * The DownlodFile class takes care of downloading the file 
 * from a link containing a data-set
 * The file will be downloaded and called "dati.csv"
 * Then the file is ready to be serialized
 * 
 * @author Pasquale Pio Troiano
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

//class of download from url the the necessary data-set of the program

public class DownloadFile {
	private static final String csvfile = "dati.csv";
	public static void downloadFileFromUrl(String url, String filePathName, int readTimeoutInSeconds)
			throws MalformedURLException, IOException {

		      // create connection
		URLConnection urlConn = new URL(url).openConnection();
		if (readTimeoutInSeconds > 0)
			urlConn.setReadTimeout(readTimeoutInSeconds * 1000);  // setting a timeout

		  // create the stream
		BufferedInputStream in = new BufferedInputStream(urlConn.getInputStream());
		FileOutputStream fos = new FileOutputStream(filePathName);

		   // read the file
		BufferedOutputStream bout = new BufferedOutputStream(fos, 1024); //create a buffer output stream
		byte[] data = new byte[1024];
		int x = 0;
		while ((x = in.read(data, 0, 1024)) >= 0) {
			bout.write(data, 0, x);
		}
		bout.close(); //close output stream
		in.close(); //close input stream
	}
	/**
	 * Use the connection for download file csv
	 * @param args
	 */
	
	public static void main(String args[]) {
        //url of data-set assigned 
		String url = "https://www.dati.gov.it/api/3/action/package_show?id=a784b3d3-dc43-47f6-b1e7-d6fdb185e0d4";
		if (args.length == 1)
			url = args[0];
		try {
            //establishes a connection
			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();  //open connection

			String data = "";  //creates and initialized String "data"
			String line = "";//creates and initialized String "line"
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {  //while line is empty read line
					data += line;
					System.out.println(line);//print "line"
				}
			} finally {
				in.close();   //close input stream
			}
			/*        the download link search starts!
			         this is the path taken on the website
			        result->resources->desired format->url of csv       */
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
			JSONObject objI = (JSONObject) (obj.get("result"));  // 
			JSONArray objA = (JSONArray) (objI.get("resources"));

			for (Object o : objA) {
				if (o instanceof JSONObject) {
					JSONObject o1 = (JSONObject) o;
					String format = (String) o1.get("format");
					String urlD = (String) o1.get("url");
					System.out.println(format + " | " + urlD);
					if (format.equals("csv")) {
						downloadFileFromUrl(urlD, csvfile , 1000);
					}
				}
			}
			System.out.println("OK");  //Print 'OK' if finds everything and all done
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("File scaricato" + csvfile);

	}
}
