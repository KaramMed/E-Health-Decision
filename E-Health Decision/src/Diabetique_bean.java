
public class Diabetique_bean {
String genre;
String age;
String jours_hospi;
String type_medcin;
String analyse;
String urgence;
String nb_hospi;
String diab;
public String getDiab() {
	return diab;
}
public void setDiab(String diab) {
	this.diab = diab;
}
public Diabetique_bean(String genre, String age, String jours_hospi, String type_medcin, String analyse, String urgence,String nb_hospi,String diab) {
	this.genre = genre;
	this.age = age;
	this.jours_hospi = jours_hospi;
	this.type_medcin = type_medcin;
	this.analyse = analyse;
	this.urgence = urgence;
	this.nb_hospi = nb_hospi;
	this.diab = diab;
}
public String getGenre() {
	return genre;
}
public void setGenre(String genre) {
	this.genre = genre;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getJours_hospi() {
	return jours_hospi;
}
public void setJours_hospi(String jours_hospi) {
	this.jours_hospi = jours_hospi;
}
public String getType_medcin() {
	return type_medcin;
}
public void setType_medcin(String type_medcin) {
	this.type_medcin = type_medcin;
}
public String getAnalyse() {
	return analyse;
}
public void setAnalyse(String analyse) {
	this.analyse = analyse;
}
public String getUrgence() {
	return urgence;
}
public void setUrgence(String urgence) {
	this.urgence = urgence;
}
public String getNb_hospi() {
	return nb_hospi;
}
public void setNb_hospi(String nb_hospi) {
	this.nb_hospi = nb_hospi;
}
}
