package com.amdocs.plant.model;

public class Plant{
public int plantId;
public String plantName;
public String plantType;
public String originCountryName;
public String waterSupplyFrequency;
public double cost;
public int sunLightRequired;
public Plant(){

}
public Plant(int pid,String pname,String type,String cname,String wfreq,double cost,int sun){
this.plantId=pid;
this.plantName=pname;
this.plantType=type;
this.originCountryName=cname;
this.setWaterSupplyFrequency(wfreq);
this.cost=cost;
this.setSunLightRequired(sun);
}








public int getSunLightRequired() {
	return sunLightRequired;
}
public void setSunLightRequired(int sunLightRequired) {
	this.sunLightRequired = sunLightRequired;
}
public String getWaterSupplyFrequency() {
	return waterSupplyFrequency;
}
public void setWaterSupplyFrequency(String waterSupplyFrequency) {
	this.waterSupplyFrequency = waterSupplyFrequency;
}
}




