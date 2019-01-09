package captain.wonjong.mybanner.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class JsonToMap {
//	public static HashMap<String, Object> getJsonToMap(String strValue) throws Exception 
//	{
//		JSONObject json = new JSONObject( strValue.trim() );
//		HashMap<String, Object> retMap = new HashMap<String, Object>();
//		if(json == JSONObject.NULL) 
//			return retMap;
//		
//		// response 추출
//		json = new JSONObject( json.getString("response").trim() );
//        if(json != JSONObject.NULL) 
//        {
//            retMap = toMap(json);
//        }
//        return retMap;
//    }
	
	
	public static HashMap<String, Object> getJsonToMapBase(String strValue) throws Exception {
		JSONObject json = new JSONObject(strValue.toString());
		HashMap<String, Object> retMap = new HashMap<String, Object>();

		if (json != JSONObject.NULL) {
			retMap = toMap(json);
		}

		return retMap;
	}

	public static HashMap<String, Object> toMap(JSONObject object) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keysItr = object.keys();

		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}

			map.put(key, value);
		}
		
		return map;
	}

	public static ArrayList<Object> toList(JSONArray array) throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();

		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);

			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}

			list.add(value);
		}
		
		return list;
	}
   
}
