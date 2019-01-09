package captain.wonjong.mybanner.util;

public class Utils {

    /**
     * Object -> String 변환
     * @param cObj
     * @return
     */
    public static String getObjToStr(Object cObj) {
        String strValue = "";
        if(cObj == null) {
            return strValue;
        }

        try {
            strValue = cObj.toString().trim();
        } catch (Exception e) {
            try {
                int nValue = (Integer) cObj;
                strValue = String.valueOf(nValue).trim();
            } catch (Exception e1) {
                strValue = "";
            }
        }

        return strValue;
    }

    /**
     * Object -> Integer 변환
     * @param cObj
     * @return
     */
    public static int getObjToInteger(Object cObj) {
        int nValue = 9999;
        if(cObj == null) {
            return nValue;
        }

        try {
            nValue = (Integer) cObj;
        } catch (Exception e) {
            return nValue;
        }

        return nValue;
    }
}
