package hack.phone.ml.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class PdfToBase64 {
	public static String convertTobase64(String filePath) {
		String base64 = "";
        try {
            // Read file as bytes
            byte[] fileContent = Files.readAllBytes(new File(filePath).toPath());

            // Encode to Base64
            base64 = Base64.getEncoder().encodeToString(fileContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
		return base64;
	}
}
