package data.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Animal {
	private String check_id;
	private String sub_id;
	private int shelter_id;
	private String shelter_name;
	private String place;
	private String area;
	private String kind;
	private String sex;
	private String bodyType;
	private String colour;
	private String age;
	private String sterilization;
	private String bacterin;
	private String foundPlace;
	private String status;
	private String remark;
	private Date openDate;
	private Date closedDate;
	private Date createDate;
	private Date upDate;
	private String album;
	private String shelter_address;
	private String shelter_tel;
	
	public Animal() {
		
	}
	
	public Animal (
			       String check_id,
				   String sub_id, 
	               int shelter_id, 
	               String shelter_name,
	               String place,
	               String area,
	               String kind, 
	               String sex, 
	               String bodyType, 
	               String colour, 
	               String age, 
	               String sterilization, 
	               String bacterin, 
	               String foundPlace, 
	               String status, 
	               String remark, 
	               Date openDate, 
	               Date closedDate, 
	               Date createDate, 
	               Date upDate,
	               String album,
	               String shelter_address,
	               String shelter_tel) {
		this.check_id = check_id;
		this.sub_id = sub_id;
		this.shelter_id = shelter_id;
		this.shelter_name = shelter_name;
		this.place = place;
		this.area = area;
		this.kind = kind;
		this.sex = sex;
		this.bodyType = bodyType;
		this.colour = colour;
		this.age = age;
		this.sterilization = sterilization;
		this.bacterin = bacterin;
		this.foundPlace = foundPlace;
		this.status = status;
		this.remark = remark;
		this.openDate = openDate;
		this.closedDate = closedDate;
		this.createDate = createDate;
		this.upDate = upDate;
		this.album = album;
		this.shelter_address = shelter_address;
		this.shelter_tel = shelter_tel;
	}

	public String getCheck_id() {
		return check_id;
	}

	public void setCheck_id(String check_id) {
		this.check_id = check_id;
	}

	public String getSub_id() {
		return sub_id;
	}

	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}

	public int getShelter_id() {
		return shelter_id;
	}

	public void setShelter_id(int shelter_id) {
		this.shelter_id = shelter_id;
	}

	public String getShelter_name() {
		return shelter_name;
	}

	public void setShelter_name(String shelter_name) {
		this.shelter_name = shelter_name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSterilization() {
		return sterilization;
	}

	public void setSterilization(String sterilization) {
		this.sterilization = sterilization;
	}

	public String getBacterin() {
		return bacterin;
	}

	public void setBacterin(String bacterin) {
		this.bacterin = bacterin;
	}

	public String getFoundPlace() {
		return foundPlace;
	}

	public void setFoundPlace(String foundPlace) {
		this.foundPlace = foundPlace;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getShelter_address() {
		return shelter_address;
	}
	
	public void setShelter_address(String shelter_address) {
		this.shelter_address = shelter_address;
	}
	
	public String getShelter_tel() {
		return shelter_tel;
	}
	
	public void setShelter_tel(String shelter_tel) {
		this.shelter_tel = shelter_tel;
	}
	
	@Override
	public String toString() {
		return toJason();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.check_id.equals(((Animal)obj).getCheck_id()))
			return true;
		else
			return false;
	}
	
	private String toJason() {
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat datetimef = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String jsonFormat = "{\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\","
				            + "\"%s\":\"%s\"}";
		
		String jsonString = String.format(jsonFormat,
										  "check_id", check_id,
										  "sub_id", sub_id,
										  "shelter_id", shelter_id,
										  "shelter_name", shelter_name,
										  "place", place,
										  "area", area,
										  "kind", kind,
										  "sex", sex,
										  "bodyType", bodyType,
										  "colour", colour,
										  "age", age,
										  "sterilization", sterilization,
										  "bacterin", bacterin,
										  "foundPlace", foundPlace,
										  "status", status,
										  "remark", remark,
										  "openDate", datef.format(openDate),
										  "closedDate", datef.format(closedDate),
										  "createDate", datetimef.format(createDate),
										  "upDate", datetimef.format(upDate),
										  "album", album,
										  "shelter_address", shelter_address,
										  "shelter_tel", shelter_tel);
		
		return jsonString;
	}
}
