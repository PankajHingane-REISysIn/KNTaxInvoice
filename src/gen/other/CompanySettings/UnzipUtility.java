/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.CompanySettings;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author admin
 */
public class UnzipUtility {

    List<String> company_SQLFile_List = new ArrayList<String>();
    
    /**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified
     * by destDirectory (will be created if does not exists)
     *
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    public List<String> unzip(String zipFilePath, String destDirectory) throws IOException {
	company_SQLFile_List.clear();
	File destDir = new File(destDirectory);
	if (!destDir.exists()) {
	    destDir.mkdir();
	}
	ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
	ZipEntry entry = zipIn.getNextEntry();

	//System.out.println("zipIn.getNextEntry() ------------- " + zipIn.getNextEntry());

	// iterates over entries in the zip file
	while (entry != null) {
//            String filePath = destDirectory + File.separator + entry.getName();

	    String filePath = File.separator + entry.getName();
	    System.out.println("filePath ------------- " + filePath);
//	    System.out.println("entry.getName() ------------- " + entry.getName());
//	    System.out.println("File.separator ------------- " + File.separator);

	    StringTokenizer token = new StringTokenizer(filePath, "/");
	    String comapny_File_Name = "";
	    while (token.hasMoreTokens()) {
		comapny_File_Name = token.nextToken();
	    }
//	    System.out.println("VASlues for the fdfsdfdsfd      " + comapny_File_Name);
	    company_SQLFile_List.add(comapny_File_Name);

	    if (!entry.isDirectory()) {
		// if the entry is a file, extracts it
		extractFile(zipIn, filePath);
	    } else {
		// if the entry is a directory, make the directory
		File dir = new File(filePath);
		dir.mkdir();
	    }
	    zipIn.closeEntry();
	    entry = zipIn.getNextEntry();
	}
	zipIn.close();
	return company_SQLFile_List;
    }

    /**
     * Extracts a zip entry (file entry)
     *
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
	byte[] bytesIn = new byte[BUFFER_SIZE];
	int read = 0;
	while ((read = zipIn.read(bytesIn)) != -1) {
	    bos.write(bytesIn, 0, read);
	}
	bos.close();
    }
}
