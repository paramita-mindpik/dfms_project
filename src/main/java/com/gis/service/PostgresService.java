package com.gis.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * Postgres Service
 * @author mkrout
 **/
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gis.model.DevInfo;
import com.gis.model.Document;
import com.gis.model.Layer;
import com.gis.model.ResponseData;
import com.gis.model.User;
import com.gis.model.WayPoints;

import net.sf.json.JSONObject;

public interface PostgresService {

	public boolean reloginRequired(HttpServletRequest request);

	public int executeQuery(String query) throws Exception;

	public int[] executeQueryBatch(String query[]) throws Exception;

	public String fetch(String query) throws Exception;
	
	public String fetchGis(String query) throws Exception;

	public List getList(String query) throws Exception;
	
	public List getTableDataGis(String table);
	
	public String select(String query);

	public List<Map<String, Object>> getListWithParameter(String query,
			String... arg) throws Exception;

	public User login(String username, String password, String platform);

	public List getAllLayers();
	
	public Map<String, String> getAllLayersDropdown();

	public Map<String, String> getAllActiveLayers();
	
	public List getAllActiveLayersList();

	public void addNewUser(User user);

	public boolean findUser(String userName , String mobileNumber) ;

	public String getNewToken(int userId , String platform);

	public void updateTokenTime(String token);

	public void insertUpdateUserLog(User user);

	public boolean validateToken(String token);

	public boolean InvalidateToken(String token);

	public Map<String, String[]> getDatabaseMetaData(String tablename);

	public Connection getConnection() throws SQLException;

	public boolean addUpdateLayerMapping(Layer layer);

	public boolean addDevInfo(DevInfo devInfo);

	public boolean checkIMEI(DevInfo devInfo);

	public ResponseData livelocation(List<Map<String, Object>> pointData , int id);
	
	public Map<String, String> getAllCircle() ;
	
	public Map<String, String> getDistrict(String circleCode); 
	
	public Map<String, String> getDivision(String districtCode); 
		
	public Map<String, String> getRange(String divisionCode); 
	
	public Map<String, String> getBeat(String rangeCode) ;
	
	public boolean uploadAndInsert(Document document)  throws Exception;

	public File getFile(String type, String fileName) throws FileNotFoundException; 
	
	public File getFileFromLocation(String fileName) throws FileNotFoundException ;
	
	public List getUserList() throws Exception ;
	
	public boolean updateUser(User user);
	
	public List getMettingrList() throws Exception ;
	
	public boolean validateDuplicateLogin(String userId);
	
	public boolean invalidateDuplicateLogin(int userId, String platform) ;
	
	public ResponseData getInspectorLocations(List<Map<String, Object>> pointData) ;
	
	public JSONObject getTranformData(String layerName) ;
	
	public boolean forceClose(int userId);
	
	public Map<String, Object> getPPName() ;
	
	default String covert(String lat, String lon) {
		/* CONVERT LAT LONG TO EPSG:4326 */
		double x = Double.parseDouble(lon) * 20037508.34 / 180;
		double y = Math.log(Math.tan((90 + Double.parseDouble(lat)) * Math.PI
				/ 360))
				/ (Math.PI / 180);
		y = y * 20037508.34 / 180;
		return "[" + x + "," + y + "]";
	}
	
	default String covertToGeom(String lat, String lon) {
		/* CONVERT LAT LONG TO EPSG:4326 */
		double x = Double.parseDouble(lon) * 20037508.34 / 180;
		double y = Math.log(Math.tan((90 + Double.parseDouble(lat)) * Math.PI
				/ 360))
				/ (Math.PI / 180);
		y = y * 20037508.34 / 180;
		return   x +  " "+ y ;
	}
	
	public boolean findUser(String userName);

	public List getUsersLog() throws Exception ;
	
	public List<Map<String, Object>> getFireList(String where) throws Exception;
	
	public List<Map<String, Object>> getPatrollingList() throws Exception;
	
	public List<Map<String, Object>> getFireSMS() ;

	public Map<String, String> getCompList();
	
	public Map<String, String> getAllFireSource();
	
	public Map<String, String> getAllPatSource();
	
	public boolean addWayPoint(WayPoints wayPoints);
	
	public boolean deleteExpiredToken();
}
