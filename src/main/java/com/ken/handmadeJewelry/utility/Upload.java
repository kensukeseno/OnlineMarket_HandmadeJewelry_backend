package com.ken.handmadeJewelry.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

public class Upload {

//	アップロードの基底フォルダアドレス
	static String UPLAOD_FOLDER_CONTEXT = "src/main/resources/static/images/product_photos";
	static String UPLAOD_FOLDER_CLASS = "images/product_photos";

//	受け取った文字列からファイル名を生成し、引数のファイルを転送
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
//		現在日時を取得
		LocalDateTime now = LocalDateTime.now();
//		日付フォーマッターを取得
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//		日時情報を指定フォーマットの文字列で取得
		String nowInFormat = now.format(formatter);
//		アップロード先アドレスを生成
		String fileAddress = UPLAOD_FOLDER_CONTEXT + "/" + fileName + "_" + nowInFormat + ".jpg";
//		パスを生成
		Path path = Paths.get(fileAddress);
//		ファイルを生成
		Files.createFile(path);
		// ファイル転送
		photo.transferTo(path);
//		DBに登録するパスを返す
		return UPLAOD_FOLDER_CLASS + "/" + fileName + "_" + nowInFormat + ".jpg";
	}
}
