package data_base;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import model_sistema.Adress;
import model_sistema.CourseChair;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Grade;
import model_sistema.IndexNum;
import model_sistema.Professor;
import model_sistema.Semester;
import model_sistema.Status;
import model_sistema.Student;
import model_sistema.Title;

public class MockDB {

	public Student Student1;
	public Student Student2;
	public Student Student3;
	public Student Student4;
	public Student Student5;
	public Student Student6;
	public Student Student7;
	public Student Student8;
	public Student Student9;
	public Student Student10;
	public Student Student11;
	public Student Student12;
	public Student Student13;
	public Student Student14;
	public Student Student15;
	public Student Student16;
	public Student Student17;
	public Student Student18;
	public Student Student19;
	public Student Student20;
	public Student Student21;
	public Student Student22;
	public Student Student23;
	public Student Student24;
	public Student Student25;
	public Student Student26;
	public Student Student27;
	
	public CourseClass Class1;
	public CourseClass Class2;
	public CourseClass Class3;
	public CourseClass Class4;
	public CourseClass Class5;
	public CourseClass Class6;
	public CourseClass Class7;
	public CourseClass Class8;
	public CourseClass Class9;
	public CourseClass Class10;
	public CourseClass Class11;
	public CourseClass Class12;
	public CourseClass Class13;
	public CourseClass Class14;
	public CourseClass Class15;
	public CourseClass Class16;
	public CourseClass Class17;
	public CourseClass Class18;
	public CourseClass Class19;
	public CourseClass Class20;
	public CourseClass Class21;
	public CourseClass Class22;
	public CourseClass Class23;
	public CourseClass Class24;
	public CourseClass Class25;
	public CourseClass Class26;
	public CourseClass Class27;
	public CourseClass Class28;
	public CourseClass Class29;
	public CourseClass Class30;
	
	public Professor Professor1;
	public Professor Professor2;
	public Professor Professor3;
	public Professor Professor4;
	public Professor Professor5;
	public Professor Professor6;
	public Professor Professor7;
	public Professor Professor8;
	public Professor Professor9;
	public Professor Professor10;
	public Professor Professor11;
	public Professor Professor12;
	public Professor Professor13;
	public Professor Professor14;
	public Professor Professor15;
	public Professor Professor16;
	public Professor Professor17;
	public Professor Professor18;
	public Professor Professor19;
	
	public Grade Grade1;
	public Grade Grade2;
	public Grade Grade3;
	public Grade Grade4;
	public Grade Grade5;
	public Grade Grade6;
	public Grade Grade7;
	public Grade Grade8;
	public Grade Grade9;
	
	public CourseChair CourseChair1;
	public CourseChair CourseChair2;
	public CourseChair CourseChair3;
	public CourseChair CourseChair4;
	public CourseChair CourseChair5;
	public CourseChair CourseChair6;
	
	public Student[] studentsArr;
	public CourseClass[] coursesArr;
	public Professor[] profsArr;
	public CourseChair[] chairsArr;
	public Grade[] gradesArr;
	
	public List<Student> students = new ArrayList<Student>();
	public List<CourseClass> courses = new ArrayList<CourseClass>();
	public List<Professor> profs = new ArrayList<Professor>();
	public List<CourseChair> chairs = new ArrayList<CourseChair>();
	public List<Grade> grades = new ArrayList<Grade>();
	
	public MockDB() {
		// init all
		
		Student1 = new Student("Marko", "Miloševiæ", new Date(12, 3, 2001), new Adress("Šafarikova", "2", "Novi Sad", "Srbija"), "021/333-555", "marko.milosevic@mailinator.com", new IndexNum("RA", 2, 2020),
				2020, 1, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		
		Student2 = new Student("Marija", "Miliæ", new Date(12, 1, 2000), new Adress("Nikole Tesle", "56", "Novi Sad", "Srbija"), "021/555-2222", "marija.milic@mailinator.com", new IndexNum("RA", 3, 2019), 2019, 2, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student3 = new Student("Nikola", "Nikoliæ", new Date(30, 8, 2001), new Adress("Bulevar Patrijarha Pavla", "3", "Beograd", "Srbija"), "021/135-463", "nikola.nikolic@mailinator.com", new IndexNum("RA", 3, 2017), 2017, 1, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student4 = new Student("Pera", "Periæ", new Date(7, 6, 1995), new Adress("Stražilovska", "6a", "Novi Sad", "Srbija"), "021/903-463", "pera.peric@mailinator.com", new IndexNum("RA", 134, 2015), 2015, 3, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student5 = new Student("Sofija", "Iliæ", new Date(6, 5, 1999), new Adress("Nikole Pašiæa", "2d", "Kikinda", "Srbija"), "021/731-067", "sofija.ilic@mailinator.com", new IndexNum("RA", 5, 2019), 2019, 3, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student6 = new Student("Martina", "Lukiæ", new Date(16, 5, 1999), new Adress("Bulevar Kralja Petra", "22", "Niš", "Srbija"), "011/4333-800", "martina.lukic@mailinator.com", new IndexNum("RA", 8, 2018), 2018, 3, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student7 = new Student("Stojan", "Stojakoviæ", new Date(19, 10, 2001), new Adress("Tolstojeva", "31", "Novi Sad", "Srbija"), "011/3130-007", "stojan.stojakovic@mailinator.com", new IndexNum("RA", 10, 2017), 2017, 1, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student8 = new Student("Milan", "Milanoviæ", new Date(2, 11, 2000), new Adress("Mariæeva", "11", "Kragujevac", "Srbija"), "015/313-061", "milan.milanovic@mailinator.com", new IndexNum("RA", 12, 2017), 2017, 2, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student9 = new Student("Miroslav", "Miliæ", new Date(11, 10, 2000), new Adress("Stražilovska", "3", "Beograd", "Srbija"), "021/351-091", "miroslav.milic@mailinator.com", new IndexNum("RA", 16, 2019), 2019, 2, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student10 = new Student("Stefan", "Gojiæ", new Date(1, 5, 1999), new Adress("Nikole Pašiæa", "6a", "Novi Sad", "Srbija"), "015/324-500", "stefan.gojic@mailinator.com", new IndexNum("RA", 21, 2015), 2015, 3, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student11 = new Student("Anastasija", "Jokiæ", new Date(11, 7, 1999), new Adress("Bulevar Kralja Petra", "2d", "Niš", "Srbija"), "011/2333-900", "anastasija.jokic@mailinator.com", new IndexNum("RA", 9, 2020), 2020, 3, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student12 = new Student("Bogdan", "Bogdanoviæ", new Date(23, 7, 1999), new Adress("Knez Mihajlova", "22", "Beograd", "Srbija"), "021/231-231", "bogdan.bogdanovic@mailinator.com", new IndexNum("RA", 4, 2017), 2017, 3, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student13 = new Student("Ana", "Daboviæ", new Date(12, 12, 2001), null, "014/303-007", "ana.dabovic@mailinator.com", new IndexNum("RA", 30, 2019), 2019, 1, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student14 = new Student("Mika", "Mikiæ", new Date(5, 11, 2001), new Adress("Nikole Tesle", "56", "Novi Sad", "Srbija"), "015/101-909", "mika.mikic@mailinator.com", new IndexNum("RA", 1, 2020), 2020, 1, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student15 = new Student("Jovan", "Deretiæ", new Date(10, 9, 1998), new Adress("Bulevar Patrijarha Pavla", "3", "Beograd", "Srbija"), "002/200-300", "jovan.deretic@mailinator.com", new IndexNum("RA", 11, 2018), 2018, 4, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student16 = new Student("Nikola", "Miškoviæ", new Date(3, 8, 1998), new Adress("Stražilovska", "6a", "Novi Sad", "Srbija"), "022/123-456", "nikola.miskovic@mailinator.com", new IndexNum("RA", 12, 2018), 2018, 4, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student17 = new Student("Martin", "Stojanoviæ", new Date(1, 5, 1998), null, "024/321-775", "martin.stojanovic@mailinator.com", new IndexNum("RA", 13, 2018), 2018, 4, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student18 = new Student("Tomislav", "Novakoviæ", new Date(25, 2, 1996), new Adress("Bulevar Kralja Petra", "22", "Niš", "Srbija"), "011/1188-379", "tomislav.novakovic@mailinator.com", new IndexNum("RA", 14, 2018), 2018, 4, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student19 = new Student("Lena", "Iviæ", new Date(11, 5, 1998), new Adress("Tolstojeva", "31", "Novi Sad", "Srbija"), "024/333-555", "lena.ivic@mailinator.com", new IndexNum("RA", 154, 2016), 2016, 4, Status.B,  new HashSet<Grade>(), new HashSet<CourseClass>());
		Student20 = new Student("Jovan", "Laziæ", new Date(22, 1, 2001), new Adress("Mariæeva", "11", "Kragujevac", "Srbija"), "025/1189-479", "jovan.lazic@mailinator.com", new IndexNum("RA", 23, 2020), 2020, 1, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student21 = new Student("Isidora", "Mikiæ", new Date(31, 12, 2000), new Adress("Stražilovska", "3", "Beograd", "Srbija"), "011/1122-366", "isidora.mikic@mailinator.com", new IndexNum("RA", 1, 2019), 2019, 2, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student22 = new Student("Vladimir", "Iliæ", new Date(31, 8, 1998), new Adress("Nikole Pašiæa", "6a", "Novi Sad", "Srbija"), "021/1122-367", "vladimir.ilic@mailinator.com", new IndexNum("SW", 4, 2014), 2014, 4, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student23 = new Student("Mirko", "Alièiæ", new Date(21, 7, 1999), new Adress("Bulevar Kralja Petra", "2d", "Niš", "Srbija"), "012/1122-368", "mirko.alicic@mailinator.com", new IndexNum("SW", 17, 2015), 2015, 3, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student24 = new Student("Milisav", "Perkoviæ", new Date(28, 9, 1998), new Adress("Knez Mihajlova", "22", "Beograd", "Srbija"), "012/1122-369", "milisav.perkovic@mailinator.com", new IndexNum("SW", 17, 2016), 2016, 4, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student25 = new Student("Puriša", "Ðorðeviæ", new Date(29, 2, 2000), new Adress("Bulevar Patrijarha Pavla", "3", "Beograd", "Srbija"), "011/1543-370", "purisa.djordjevic@mailinator.com", new IndexNum("SW", 27, 2018), 2018, 2, Status.B, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student26 = new Student("Mikica", "Kovaèeviæ", new Date(23, 3, 1999), new Adress("Nikole Pašiæa", "2d", "Kikinda", "Srbija"), "011/1992-371", "mikica.kovacevic@mailinator.com", new IndexNum("RA", 226, 2017), 2017, 3, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		Student27 = new Student("Miloš", "Miliæ", new Date(21, 10, 2001), new Adress("Nikole Tesle", "56", "Novi Sad", "Srbija"), "011/8172-372", "milos.milic@mailinator.com", new IndexNum("SW", 12, 2021), 2021, 1, Status.S, new HashSet<Grade>(), new HashSet<CourseClass>());
		
	
		Professor1 = new Professor( "Miloš", "Nikoliæ", new Date( 12, 12, 1965), new Adress("Šafarikova", "2", "Novi Sad", "Srbija"), "021/356-785", "milos.nikolic@mailinator.com", "10", 123123123, Title.redovni_profesor, 30, new HashSet<CourseClass>());
		Professor2 = new Professor( "Nikola", "Mirkoviæ", new Date( 1, 1, 1978), new Adress("Nikole Tesle", "56", "Novi Sad", "Srbija"), "021/368-456", "nikola.mirkovic@mailinator.com", "10", 321321321, Title.redovni_profesor, 22, new HashSet<CourseClass>());
		Professor3 = new Professor( "Ilija", "Petkoviæ", new Date( 3, 9, 1988), new Adress("Bulevar Patrijarha Pavla", "3", "Beograd", "Srbija"), "021/215-314", "ilija.petkovic@mailinator.com", "10", 456456456, Title.vanredni_profesor, 22, new HashSet<CourseClass>());
		Professor4 = new Professor( "Mitar", "Petroviæ", new Date( 25, 7, 1976), new Adress("Stražilovska", "6a", "Novi Sad", "Srbija"), "021/884-640", "mitar.petrovic@mailinator.com", "10", 789789789, Title.vanredni_profesor, 27, new HashSet<CourseClass>());
		Professor5 = new Professor( "Vasa", "Miciæ", new Date( 14, 2, 1970), new Adress("Nikole Pašiæa", "2d", "Kikinda", "Srbija"), "021/212-114", "vasa.micic@mailinator.com", "10", 100100144, Title.akademik, 24, new HashSet<CourseClass>());
		Professor6 = new Professor( "Srðan", "Miletiæ", new Date( 20, 4, 1966), new Adress("Bulevar Kralja Petra", "22", "Niš", "Srbija"), "021/978-225", "srdjan.miletic@mailinator.com", "10", 200020244, Title.akademik, 31, new HashSet<CourseClass>());
		Professor7 = new Professor( "Branislav", "Mihajloviæ", new Date( 28, 6, 1980), new Adress("Tolstojeva", "31", "Novi Sad", "Srbija"), "021/778-323", "branislav.mihajlovic@mailinator.com", "10", 559585632, Title.redovni_profesor, 13, new HashSet<CourseClass>());
		Professor8 = new Professor( "Marko", "Markoviæ", new Date( 31, 1, 1985), new Adress("Mariæeva", "11", "Kragujevac", "Srbija"), "021/899-659", "marko.markovic@mailinator.com", "10", 334968855, Title.redovni_profesor, 17, new HashSet<CourseClass>());
		Professor9 = new Professor( "Miloš", "Milakoviæ", new Date( 21, 9, 1975), new Adress("Stražilovska", "3", "Beograd", "Srbija"), "021/122-326", "milos.milakovic@mailinator.com", "10", 730703654, Title.vanredni_profesor, 12, new HashSet<CourseClass>());
		Professor10 = new Professor( "Lazar", "Bratiæ", new Date( 13, 11, 1973), new Adress("Nikole Pašiæa", "6a", "Novi Sad", "Srbija"), "021/156-326", "lazar.bratic@mailinator.com", "10", 600378644, Title.vanredni_profesor, 3, new HashSet<CourseClass>());
		Professor11 = new Professor( "Ljeposava", "Dražiæ", new Date( 11, 8, 1964), new Adress("Bulevar Kralja Petra", "2d", "Niš", "Srbija"), "021/888-156", "ljeposava.drazic@mailinator.com", "10", 158496152, Title.akademik, 31, new HashSet<CourseClass>());
		Professor12 = new Professor( "Miroljub", "Dragiæ", new Date( 2, 3, 1959), new Adress("Knez Mihajlova", "22", "Beograd", "Srbija"), "021/456-125", "miroljub.dragic@mailinator.com", "10", 777348595, Title.akademik, 42, new HashSet<CourseClass>());
		Professor13 = new Professor( "Bogdan", "Rekaviæ", new Date( 23, 6, 1977), new Adress("Bulevar Kralja Petra", "22", "Niš", "Srbija"), "021/886-455", "bogdan.rekavic@mailinator.com", "10", 721254363, Title.vanredni_profesor, 18, new HashSet<CourseClass>());
		Professor14 = new Professor( "Stanka", "Miliæ", new Date( 3, 3, 1991), new Adress("Bulevar Patrijarha Pavla", "3", "Beograd", "Srbija"), "021/945-155", "stanka.milic@mailinator.com", "10", 225533448, Title.akademik, 7, new HashSet<CourseClass>());
		Professor15 = new Professor( "Milica", "Vukoviæ", new Date( 18, 10, 1967), new Adress("Mariæeva", "11", "Kragujevac", "Srbija"), "021/746-659", "milica.vukovic@mailinator.com", "10", 111555888, Title.vanredni_profesor, 14, new HashSet<CourseClass>());
		Professor16 = new Professor( "Miša", "Mišiæ", new Date( 20, 10, 1969), new Adress("Šafarikova", "2", "Novi Sad", "Srbija"), "021/489-326", "misa.misic@mailinator.com", "10", 300300344, Title.akademik, 19, new HashSet<CourseClass>());
		Professor17 = new Professor( "Branko", "Marièiæ", new Date( 18, 1, 1973), new Adress("Nikole Tesle", "56", "Novi Sad", "Srbija"), "021/487-265", "branko.maricic@mailinator.com", "10", 400400444, Title.akademik, 22, new HashSet<CourseClass>());
		Professor18 = new Professor( "Branislav", "Lukoviæ", new Date( 8, 4, 1982), new Adress("Bulevar Patrijarha Pavla", "3", "Beograd", "Srbija"), "021/159-478", "branislav.lukovic@mailinator.com", "10", 500500544, Title.redovni_profesor, 9, new HashSet<CourseClass>());
		Professor19 = new Professor( "Branimir", "Obradoviæ", new Date( 7, 1, 1979), new Adress("Šafarikova", "2", "Novi Sad", "Srbija"), "021/922-333", "branimir.obradovic@mailinator.com", "10", 600600644, Title.akademik, 17, new HashSet<CourseClass>());
		
		Class1 = new CourseClass((short) 1, "Osnove programiranja", Semester.z, 1, Professor2, 7, new HashSet<Student>(), new HashSet<Student>());
		Class2 = new CourseClass((short) 2, "Statistika", Semester.l, 3, Professor2, 8, new HashSet<Student>(), new HashSet<Student>());
		Class3 = new CourseClass((short) 3, "Algoritmi i strukture podataka", Semester.l, 2, Professor2, 9, new HashSet<Student>(), new HashSet<Student>());
		Class4 = new CourseClass((short) 4, "LPRS", Semester.z, 3, Professor2, 7, new HashSet<Student>(), new HashSet<Student>());
		Class5 = new CourseClass((short) 5, "Matematika", Semester.l, 1, null, 11, new HashSet<Student>(), new HashSet<Student>());
		Class6 = new CourseClass((short) 6, "Xml i web servisi", Semester.l, 4, null, 6, new HashSet<Student>(), new HashSet<Student>());
		Class7 = new CourseClass((short) 7, "Metode optimizacije", Semester.z, 3, null, 6, new HashSet<Student>(), new HashSet<Student>());
		Class8 = new CourseClass((short) 8, "Osnove elektrotehnike", Semester.l, 1, Professor5, 11, new HashSet<Student>(), new HashSet<Student>());
		Class9 = new CourseClass((short) 9, "Sociologija", Semester.z, 1, Professor5, 10, new HashSet<Student>(), new HashSet<Student>());
		Class10 = new CourseClass((short) 10, "Filozofija", Semester.z, 1, Professor5, 4, new HashSet<Student>(), new HashSet<Student>());
		Class11 = new CourseClass((short) 11, "ORT", Semester.l, 2, null, 7, new HashSet<Student>(), new HashSet<Student>());
		Class12 = new CourseClass((short) 12, "NANS", Semester.l, 2, Professor6, 5, new HashSet<Student>(), new HashSet<Student>());
		Class13 = new CourseClass((short) 13, "Organizacija podataka", Semester.z, 2, Professor6, 7, new HashSet<Student>(), new HashSet<Student>());
		Class14 = new CourseClass((short) 14, "Baze podataka", Semester.z, 2, Professor6, 6, new HashSet<Student>(), new HashSet<Student>());
		Class15 = new CourseClass((short) 15, "Paralelno programiranje", Semester.z, 2, Professor7, 8, new HashSet<Student>(), new HashSet<Student>());
		Class16 = new CourseClass((short) 16, "Konkurentno programiranje", Semester.l, 2, Professor7, 9, new HashSet<Student>(), new HashSet<Student>());
		Class17 = new CourseClass((short) 17, "Operativni sistemi", Semester.l, 2, null, 8, new HashSet<Student>(), new HashSet<Student>());
		Class18 = new CourseClass((short) 18, "Algebra", Semester.z, 1, null, 15, new HashSet<Student>(), new HashSet<Student>());
		Class19 = new CourseClass((short) 19, "Diskretna matematika", Semester.l, 3, null, 14, new HashSet<Student>(), new HashSet<Student>());
		Class20 = new CourseClass((short) 20, "Upravljaèki sistemi", Semester.l, 3, null, 8, new HashSet<Student>(), new HashSet<Student>());
		Class21 = new CourseClass((short) 21, "Osnovi elektronike", Semester.z, 2, null, 7, new HashSet<Student>(), new HashSet<Student>());
		Class22 = new CourseClass((short) 22, "Sluèajni procesi", Semester.l, 4, null, 9, new HashSet<Student>(), new HashSet<Student>());
		Class23 = new CourseClass((short) 23, "Raèunarstvo visokih performansi", Semester.l, 4, null, 10, new HashSet<Student>(), new HashSet<Student>());
		Class24 = new CourseClass((short) 24, "Analiza 1", Semester.z, 1, null, 20, new HashSet<Student>(), new HashSet<Student>());
		Class25 = new CourseClass((short) 25, "Informaciona bezbednost", Semester.l, 4, Professor17, 9, new HashSet<Student>(), new HashSet<Student>());
		Class26 = new CourseClass((short) 26, "Elektronsko plaæanje", Semester.z, 3, Professor18, 8, new HashSet<Student>(), new HashSet<Student>());
		Class27 = new CourseClass((short) 27, "Distribuirani sistemi", Semester.l, 4, Professor19, 6, new HashSet<Student>(), new HashSet<Student>());
		Class28 = new CourseClass((short) 28, "Projektovanje softvera", Semester.z, 3, Professor18, 5, new HashSet<Student>(), new HashSet<Student>());
		Class29 = new CourseClass((short) 29, "Informacioni sistemi", Semester.z, 4, Professor17, 6, new HashSet<Student>(), new HashSet<Student>());
		Class30 = new CourseClass((short) 30, "Mašinsko uèenje", Semester.l, 4, null, 7, new HashSet<Student>(), new HashSet<Student>());
		
		
		Grade1 = new Grade(Student4, Class5, 10, new Date(12, 12, 2017));
		Grade2 = new Grade(Student4, Class3, 9, new Date(10, 11, 2019));
		Grade3 = new Grade(Student4, Class1, 8, new Date(11, 11, 2020));
		Grade4 = new Grade(Student2, Class1, 10, new Date(20, 11, 2020));
		Grade5 = new Grade(Student2, Class5, 10, new Date(30, 10, 2021));
		Grade6 = new Grade(Student15, Class15, 7, new Date(10, 1, 2021));
		Grade7 = new Grade(Student16, Class15, 10, new Date(12, 1, 2018));
		Grade8 = new Grade(Student15, Class16, 10, new Date(1, 1, 2021));
		Grade9 = new Grade(Student16, Class16, 9, new Date(4, 2, 2019));
		
		
		CourseChair1 = new CourseChair(42, "Katedra za matematiku", Professor2, new HashSet<Professor>());
		CourseChair2 = new CourseChair(43, "Katedra za fiziku", Professor3, new HashSet<Professor>());
		CourseChair3 = new CourseChair(44, "Katedra za elektrotehniku", Professor4, new HashSet<Professor>());
		CourseChair4 = new CourseChair(45, "Katedra za primenjene raèunarske nauke", Professor7, new HashSet<Professor>());
		CourseChair5 = new CourseChair(46, "Katedra za informatiku", Professor13, new HashSet<Professor>());
		CourseChair6 = new CourseChair(47, "Katedra za automatiku", Professor18, new HashSet<Professor>());
		
		//dodavanje studenata koji su polozili predmet
		Student4.addPassed(Grade1);
		Student4.addPassed(Grade2);
		Student4.addPassed(Grade3);
		Student2.addPassed(Grade4);
		Student2.addPassed(Grade5);
		Student15.addPassed(Grade6);
		Student15.addPassed(Grade8);
		Student16.addPassed(Grade7);
		Student16.addPassed(Grade9);
		
		Class1.addPassed(Student2);
		Class1.addPassed(Student4);
		Class3.addPassed(Student4);
		Class5.addPassed(Student2);
		Class5.addPassed(Student4);
		Class15.addPassed(Student15);
		Class15.addPassed(Student16);
		Class16.addPassed(Student15);
		Class16.addPassed(Student16);
		
		Student4.addFailed(Class2);
		Student4.addFailed(Class4);
		Student17.addFailed(Class15);
		Student18.addFailed(Class15);
		Student22.addFailed(Class18);
		Student23.addFailed(Class18);
		Student24.addFailed(Class18);
		Student26.addFailed(Class18);
		Student22.addFailed(Class19);
		Student23.addFailed(Class19);
		Student24.addFailed(Class19);
		Student26.addFailed(Class19);
		
		Class2.addFailed(Student4);
		Class4.addFailed(Student4);
		Class15.addFailed(Student17);
		Class15.addFailed(Student18);
		Class18.addFailed(Student22);
		Class18.addFailed(Student23);
		Class18.addFailed(Student24);
		Class18.addFailed(Student26);
		Class19.addFailed(Student22);
		Class19.addFailed(Student23);
		Class19.addFailed(Student24);
		Class19.addFailed(Student26);
		
		Professor2.addCourses(Class1);
		Professor2.addCourses(Class2);
		Professor2.addCourses(Class3);
		Professor2.addCourses(Class4);
		Professor5.addCourses(Class8);
		Professor5.addCourses(Class9);
		Professor5.addCourses(Class10);
		Professor6.addCourses(Class12);
		Professor6.addCourses(Class13);
		Professor6.addCourses(Class14);
		Professor7.addCourses(Class15);
		Professor7.addCourses(Class16);
		Professor17.addCourses(Class25);
		Professor17.addCourses(Class29);
		Professor18.addCourses(Class26);
		Professor18.addCourses(Class28);
		Professor19.addCourses(Class27);
		
		CourseChair1.addProfs(Professor1);
		CourseChair1.addProfs(Professor2);
		CourseChair2.addProfs(Professor3);
		CourseChair3.addProfs(Professor4);
		CourseChair5.addProfs(Professor5);
		CourseChair6.addProfs(Professor6);
		CourseChair4.addProfs(Professor7);
		CourseChair2.addProfs(Professor8);
		CourseChair3.addProfs(Professor9);
		CourseChair4.addProfs(Professor10);
		CourseChair5.addProfs(Professor11);
		CourseChair6.addProfs(Professor12);
		CourseChair5.addProfs(Professor13);
		CourseChair2.addProfs(Professor14);
		CourseChair3.addProfs(Professor15);
		CourseChair4.addProfs(Professor16);
		CourseChair5.addProfs(Professor17);
		CourseChair6.addProfs(Professor18);
		CourseChair1.addProfs(Professor19);
	
		studentsArr = new Student[] {Student1, Student2, Student3, Student4, Student5, Student6, Student7, Student8, Student9,
								  Student10, Student11, Student12, Student13, Student14, Student15, Student16, Student17, Student18,
								  Student19, Student20, Student21, Student22, Student23, Student24, Student25, Student26, Student27};
		
		coursesArr = new CourseClass[] {Class1, Class2, Class3, Class4, Class5, Class6, Class7, Class8, Class9, Class10,
									 Class11, Class12, Class13, Class14, Class15, Class16, Class17, Class18, Class19, Class20,
									 Class21, Class22, Class23, Class24, Class25, Class26, Class27, Class28, Class29, Class30};
		
		profsArr = new Professor[] {Professor1, Professor2, Professor3, Professor4, Professor5, Professor6, Professor7, Professor8, Professor9, Professor10,
								 Professor11, Professor12, Professor13, Professor14, Professor15, Professor16, Professor17, Professor18, Professor19};
		
		chairsArr = new CourseChair[] {CourseChair1, CourseChair2, CourseChair3, CourseChair4, CourseChair5, CourseChair6};
		
		gradesArr = new Grade[] {Grade1, Grade2, Grade3, Grade4, Grade5, Grade6, Grade7, Grade8, Grade9};
		
		Collections.addAll(students, studentsArr);
		Collections.addAll(courses, coursesArr);
		Collections.addAll(profs, profsArr);
		Collections.addAll(chairs, chairsArr);
		Collections.addAll(grades, gradesArr);
	}
	
	public void SaveMockStudents() throws FileNotFoundException, IOException, ClassNotFoundException{
		File f = new File("Database\\Students.txt");
		
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		try {
			oos.writeObject(students);
		}
		finally {
			oos.close();
		}
	}
	
	public void SaveMockCourses() throws FileNotFoundException, IOException, ClassNotFoundException{
		File f = new File("Database\\Courses.txt");
		
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		try {
			oos.writeObject(courses);
		}
		finally {
			oos.close();
		}
	}
	
	public void SaveMockChairs() throws FileNotFoundException, IOException, ClassNotFoundException{
		File f = new File("Database\\Chairs.txt");
		
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		try {
			oos.writeObject(chairs);
		}
		finally {
			oos.close();
		}
	}
	
	public void SaveMockGrades() throws FileNotFoundException, IOException, ClassNotFoundException{
		File f = new File("Database\\Grades.txt");
		
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		try {
			oos.writeObject(grades);
		}
		finally {
			oos.close();
		}
	}
	
	public void SaveMockProfs() throws FileNotFoundException, IOException, ClassNotFoundException{
		File f = new File("Database\\Professors.txt");
		
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		try {
			oos.writeObject(profs);
		}
		finally {
			oos.close();
		}
	}
	
}
