package com.ken.handmadeJewelry.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

/**
 * Upload a product image
 * @author ken
 *
 */
public class Upload {

//	Base directory address for uploading
	static String UPLAOD_FOLDER_CONTEXT = "src/main/resources/static/images/product_photos";
	static String UPLAOD_FOLDER_CLASS = "images/product_photos";

	/**
	 * Create a file name
	 * @param photo
	 * @param names
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String upload(MultipartFile photo, String... names) throws IllegalStateException, IOException {
		String fileName = "";
		Boolean nameFstFlag = false;
		for (String name : names) {
			if (nameFstFlag) {
				fileName += "_";
			}
			fileName += name;
			nameFstFlag = true;
		}
//		Get a current time
		LocalDateTime now = LocalDateTime.now();
//		Get a date formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//		Get a date info in string  convertied from a designated format
		String nowInFormat = now.format(formatter);
//		Create an uploading address
		String fileAddress = UPLAOD_FOLDER_CONTEXT + "/" + fileName + "_" + nowInFormat + ".jpg";
//		Create a path
		Path path = Paths.get(fileAddress);
//		Create a file
		Files.createFile(path);
//		Transfer a file
		photo.transferTo(path);
//		Return a path to resister in database
		return UPLAOD_FOLDER_CLASS + "/" + fileName + "_" + nowInFormat + ".jpg";
	}
}
