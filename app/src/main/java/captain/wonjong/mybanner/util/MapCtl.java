package captain.wonjong.mybanner.util;

import java.util.ArrayList;
import java.util.HashMap;

public class MapCtl
{
	static MapCtl m_cMapCtl;
	
	static HashMap<String, Object> m_mapData;
	static ArrayList<HashMap<String, Object>> m_arrData;
	
	
	public static MapCtl newInstance(HashMap<String, Object> mapData)
	{
		if(m_cMapCtl == null)
			m_cMapCtl = new MapCtl();
		
		m_mapData = mapData;
		
		return m_cMapCtl;
	}
	
	
	public static MapCtl newInstance(ArrayList<HashMap<String, Object>> arrData)
	{
		if(m_cMapCtl == null)
			m_cMapCtl = new MapCtl();
		
		m_arrData = arrData;
		
		return m_cMapCtl;
	}

	 /**
     * HashMap -> HashMap 추출
     * @param mapData
     * @param strKey
     * @return
     */
	@SuppressWarnings("unchecked")
	public MapCtl getMtoMData(String strKey)
    {
    	if(m_mapData == null)
    	{
    		m_mapData = new HashMap<String, Object>();
    		return m_cMapCtl;
    	}
    	try {
    		m_mapData  = (HashMap<String, Object>) m_mapData.get(strKey);
		} catch (Exception e) {
			m_mapData = null;
		}
    	return m_cMapCtl;
    }
    
    /**
     * HashMap -> ArrayList 추출
     * @param mapData
     * @param strKey
     * @return
     */
    @SuppressWarnings("unchecked")
	public MapCtl getMtoArrData(String strKey)
    {
    	if(m_mapData == null)
    	{
    		m_mapData = new HashMap<String, Object>();
    		return m_cMapCtl;
    	}
    	
    	m_arrData  = (ArrayList<HashMap<String, Object>>) m_mapData.get(strKey);
    	
    	return m_cMapCtl;
    }
    
    /**
     * ArrayList -> Map 추출
     * @param arrData
     * @param nNum
     * @param strKey
     * @return
     */
    @SuppressWarnings("unchecked")
	public MapCtl getArrtoMapData(int nNum, String strKey)
    {
    
    	if(m_arrData == null)
    	{
    		m_arrData = new ArrayList<HashMap<String, Object>>();
    		return m_cMapCtl;
    	}
    	
    	m_mapData =  (HashMap<String, Object>) m_arrData.get(nNum).get(strKey);
    	
    	return m_cMapCtl;
    }
    
    /**
     * ArrayList -> Map 추출
     * @param arrData
     * @param nNum
     * @param strKey
     * @return
     */
    public MapCtl getArrtoMapData(int nNum)
    {
    	
    	if(m_arrData == null)
    	{
    		m_arrData = new ArrayList<HashMap<String, Object>>();
    		return m_cMapCtl;
    	}
    	
    	m_mapData =  (HashMap<String, Object>) m_arrData.get(nNum);
    	
    	return m_cMapCtl;
    }
    
    /**
     * ArrayList -> ArrayList 추출
     * @param arrData
     * @param nNum
     * @param strKey
     * @return
     */
    @SuppressWarnings("unchecked")
	public MapCtl getArrtoArrData( int nNum, String strKey)
    {
    	
    	if(m_arrData == null)
    	{
    		m_arrData = new ArrayList<HashMap<String, Object>>();
    		return m_cMapCtl;
    	}
    	
    	m_arrData = (ArrayList<HashMap<String, Object>>) m_arrData.get(nNum).get(strKey);
    	
    	
    	return m_cMapCtl;
    }
    
    /**
     * HashMap -> String 추출
     * @param mapData
     * @param strKey
     * @return
     */
    public String getMaptoStr(String strKey)
    {
    	if(m_mapData == null)
    		return "";
    	
    	String strValue = Utils.getObjToStr( m_mapData.get(strKey) );
    	
    	return strValue;
    }
    
    /**
     * ArrayList -> String 추출
     * @param mapData
     * @param strKey
     * @return
     */
    public String getArrtoStr(int nNum, String strKey)
    {
    	if(m_arrData == null)
    		return "";
    	
    	String strValue = Utils.getObjToStr( m_arrData.get(nNum).get(strKey) );
    	
    	return strValue;
    }
    
    
    public HashMap<String, Object> getMap()
	{
		return m_mapData;
	}
	
	public ArrayList<HashMap<String, Object>> getArrayMap()
	{
		return m_arrData;
	}
}
