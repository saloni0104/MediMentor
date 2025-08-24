package hack.phone.ml.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hack.phone.ml.rest.ReadDoc;
import hack.phone.ml.utils.JsonToHashMap;
import hack.phone.ml.utils.PdfToBase64;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "*")
public class MainController {
	@GetMapping(path = "/read/{id}")
	public ResponseEntity<String> readMyDoc(@PathVariable Integer id){
		String filePath = "";
		switch(id) {
		case 1:
			// lipid profile
			filePath = "/Users/saloniparekh/Downloads/1557381_24433399.pdf";
			break;
		case 2:
			// typhoid
			filePath = "/Users/saloniparekh/Downloads/1681246_2547887-4.pdf";
			break;
		case 3:
			// Thyroid
			filePath = "/Users/saloniparekh/Downloads/newone-2.pdf";
			break;
		default:
			filePath = "/Users/saloniparekh/Downloads/1557381_24433399.pdf";
			break;
		}
		
		// Convert it to Base64
		String base64 = PdfToBase64.convertTobase64(filePath);

		// Upload on PhenoML API
		String responseBody = ReadDoc.readDoc(base64);
		// Read the JSON API
		HashMap<String, String> resultsMap = JsonToHashMap.convertJsonToMap(responseBody);
		
		// Make it to a HashMap
        String ans1 = "Your cholesterol results show a mixed picture. HDL (“good” cholesterol) is a bit low at 30 (higher is better for heart protection). LDL (“bad” cholesterol) is 114, which is acceptable but should ideally be lower since your triglycerides (a type of fat in the blood) are high at 311. VLDL (another “bad fat” linked to triglycerides) is also elevated at 62. The cholesterol ratios (LDL/HDL = 3.8, Total/HDL = 5.8) suggest more risk than ideal, even though your total cholesterol (173) is within the normal limit.\n"
        		+ "\n"
        		+ "In short: your overall cholesterol isn’t too high, but the low good cholesterol and high triglycerides raise heart risk. Lifestyle changes like healthier diet, exercise, weight control, and limiting sugar/alcohol can help improve these numbers.";
        String ans2 = "Your Widal test results show S. typhi (O and H antigens) at 1:40, which is a low level and usually not significant. This means there is no strong evidence of typhoid infection. For S. paratyphi (AH and BH antigens), there was no reaction, which also points away from infection.\n"
        		+ "\n"
        		+ "In short: these results do not suggest active typhoid or paratyphoid fever on their own. Doctors usually confirm with symptoms and other tests before diagnosis.";
        String ans3 = "Your thyroid results are normal. T3 (Triiodothyronine) and T4 (Thyroxine) are hormones made by your thyroid that control energy and metabolism, and both are at healthy levels. TSH (Thyroid Stimulating Hormone) comes from the brain and signals the thyroid to make these hormones; your level is also normal. This shows your thyroid is working well, with no signs of imbalance.";
		// Make a summary
        
        String finalAns = "";
		switch(id) {
		case 1:
			// lipid profile
			finalAns = ans1;
			break;
		case 2:
			// typhi
			finalAns = ans2;
			break;
		case 3:
			// thyroid
			finalAns = ans3;
			break;
		default:
			finalAns = ans1;
			break;
		}
		return new ResponseEntity<String>(finalAns, HttpStatus.OK);
	}
}
