package hack.phone.ml.utils;

import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonToHashMap {
	public static HashMap<String, String> convertJsonToMap(String json) {
	       ObjectMapper mapper = new ObjectMapper();
	        HashMap<String, String> resultMap = new HashMap<>();

	        try {
	            JsonNode root = mapper.readTree(json);
	            JsonNode items = root.path("item");

	            if (items.isArray()) {
	                for (JsonNode item : items) {
	                    String text = item.path("text").asText();
	                    JsonNode answerOption = item.path("answerOption");
	                    if (answerOption.isArray() && answerOption.size() > 0) {
	                        String value = answerOption.get(0).path("valueString").asText();
	                        resultMap.put(text, value);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultMap;
	}
}
