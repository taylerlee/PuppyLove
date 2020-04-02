package com.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import data.model.*;

public class DBHelper {
	
	private String database;
	
	private JDBCMSSQL jdbc = null;
	
	public DBHelper(String driver, 
			String dbserverip, 
			int dbserverport,
			String dbusername,
			String dbpassword,
			String database) throws ClassNotFoundException, SQLException {

		this.database = database;
		
		jdbc = new JDBCMSSQL(driver, 
				dbserverip, 
				dbserverport, 
				dbusername,
				dbpassword);
	}

	public Animal selectAnimalInfoByCheckId(String checkid) {
		Animal animal = new Animal();
		try {
			jdbc.createConnection(database);
			ResultSet result = jdbc.executeQuery("exec sp_select_animal_by_checkid '" + checkid + "'");
			while(result.next()) {
				animal.setCheck_id(result.getString("check_id"));
				animal.setSub_id(result.getString("subid"));
				animal.setShelter_id(result.getInt("shelter_id"));
				animal.setShelter_name(result.getString("shelter_name"));
				animal.setPlace(result.getString("place"));
				animal.setArea(result.getString("area"));
				animal.setKind(result.getString("kind"));
				animal.setSex(result.getString("sex"));
				animal.setBodyType(result.getString("bodytype"));
				animal.setColour(result.getString("colour"));
				animal.setAge(result.getString("age"));
				animal.setSterilization(result.getString("sterilization"));
				animal.setBacterin(result.getString("bacterin"));
				animal.setFoundPlace(result.getString("foundplace"));
				animal.setStatus(result.getString("animal_status"));
				animal.setRemark(result.getString("remark"));
				animal.setOpenDate(result.getDate("animal_opendate"));
				animal.setClosedDate(result.getDate("animal_closeddate"));
				animal.setCreateDate(result.getDate("animal_createdate"));
				animal.setUpDate(result.getDate("animal_update"));
				animal.setAlbum(result.getString("album"));				
			}
			
			System.out.println(animal.toString());
			//jdbc.commit();
			System.out.println("selectAnimalInfoByCheckId():資料查詢成功...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(jdbc.getConn() != null) {
				try {
					jdbc.connectionClose();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}			
		}
		return animal;
	}
	
	public Map<String, String> getAnimal(String type, int startIndex, int number) {
		Map<String, String> animals = new HashMap<String, String>();
		try {
			jdbc.createConnection(database);
			ResultSet result = jdbc.executeQuery("exec sp_select_animal_by_type '" + type + "', " + startIndex + ", " + number);
			while(result.next()) {
				animals.put(result.getString("check_id"), result.getString("album"));			
			}
			System.out.println("getAnimal():資料查詢成功...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(jdbc.getConn() != null) {
				try {
					jdbc.connectionClose();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}			
		}
		
		return animals;		
	}
	
	/**
	 * 
	 * 
	 * @param area
	 * @param kind
	 * @param sex
	 * @param bodytype
	 * @param age
	 * @param colour
	 * @param status
	 * @param sterilization
	 * @param bacterin
	 * @return
	 */
	public Map<String, String> searchAnimalByOption(List<String> optionsList, int startIndex, int number) {
		Map<String, String> animals = new HashMap<String, String>();
		String sqlStr = "exec sp_search_animal ";
		System.out.println("optionsMap.size():" + optionsList.size());
		
		try {
			for(String option : optionsList) {
				if(option != null)
					sqlStr += "'" + option + "',";
				else {
					sqlStr += "default,";
				}
			}
			sqlStr += startIndex + "," + number;
			System.out.println("sqlStr:" + sqlStr);
			
			jdbc.createConnection(database);
			ResultSet result = jdbc.executeQuery(sqlStr);
			while(result.next()) {
				animals.put(result.getString("check_id"), result.getString("album"));			
			}
			System.out.println("searchAnimalByOption():資料查詢成功...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(jdbc.getConn() != null) {
				try {
					jdbc.connectionClose();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}			
		}
		
		return animals;		
	}
	
	public int countAnimal() {
		int num = 0;
		try {
			jdbc.createConnection(database);
			ResultSet result = jdbc.executeQuery("select count(1) as num from animal");
			while(result.next()) {
				num = result.getInt("num");
			}
			System.out.println("countAnimal():資料查詢成功...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(jdbc.getConn() != null) {
				try {
					jdbc.connectionClose();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}			
		}
		return num;
	}
	
	public List<HospitalData> getHospitalDatas(String cityArea) {
		List<HospitalData> datas = new ArrayList<HospitalData>();
		try {
			jdbc.createConnection(database);
			ResultSet result = jdbc.executeQuery("exec sp_select_hospital '" + cityArea + "'");
			while(result.next()){
				HospitalData data = new HospitalData(result.getString(1), result.getString(2), result.getString(3));
				datas.add(data);
			}
			System.out.println("getHospitalDatas():醫院資料查詢成功...");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(jdbc.getConn() != null) {
				try {
					jdbc.connectionClose();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}			
		}
		return datas;
	}
	
	public boolean setContect(String name, String email, String phone, String message) {
		boolean isSuccessful = true;
		try {
			jdbc.createConnection(database);
			int ret = jdbc.executeUpdate("insert Contact (name,address,number,message) values ('"+name+"','"+email+"','"+phone+"','"+message+"')");
			jdbc.commit();
			System.out.println("setContect():" + ret + ":匯入Contect成功...");
		} catch (Exception e) {
			isSuccessful = false;
			System.out.println(e.getMessage());
		}
		finally {
			if(jdbc.getConn() != null) {
				try {
					jdbc.connectionClose();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}			
		}		
		return isSuccessful;
	}
}
