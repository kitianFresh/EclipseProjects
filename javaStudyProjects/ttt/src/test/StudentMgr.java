package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class CourseGrade {
	private String sid;
	private String cid;
	private double classScore;
	private double midTermScore;
	private double finalTermScore;
	private double sumScore;
	
	public CourseGrade(String sid, String cid) {
		this.sid = sid;
		this.cid = cid;
	}
	
	public CourseGrade(String sid, String cid, double classScore,
			double midTermScore, double finalTermScore) {
		this(sid, cid);
		this.classScore = classScore;
		this.midTermScore = midTermScore;
		this.finalTermScore = finalTermScore;
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	public double getClassScore() {
		return classScore;
	}
	public void setClassScore(double classScore) {
		this.classScore = classScore;
	}
	public double getMidTermScore() {
		return midTermScore;
	}
	public void setMidTermScore(double midTermScore) {
		this.midTermScore = midTermScore;
	}
	public double getFinalTermScore() {
		return finalTermScore;
	}
	public void setFinalTermScore(double finalTermScore) {
		this.finalTermScore = finalTermScore;
	}
	public double getSumScore() {
		this.sumScore = (classScore+midTermScore+finalTermScore)/3;
		return sumScore;
	}

	public void displayGrade(){
		//System.out.println("\tsid\tcid\tclassScore\tmidTermScore\tfinalTermScor\tsumScore");
		System.out.println("\t"+getSid()+"\t"+getCid()+"\t"+getClassScore()+"\t"+getMidTermScore()
					+"\t"+getFinalTermScore()+"\t"+getSumScore());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(((CourseGrade)obj).getSid() == this.sid && ((CourseGrade)obj).getCid() == this.cid) return true;
		return false;
	}
}

class Course {
	private String cid;
	private String cname;
	private List<Student> selectors;
	public Course(String courseId, String courseName){
		cid = courseId;
		cname = courseName;
		selectors = new ArrayList<Student>();
	}
	
	public boolean courseSelectedByStudent(Student student){
		if(selectors.contains(student)) return false;
		selectors.add(student);
		return true;
	}
	
	public String getCourseId() {
		return cid;
	}
	
	public List<Student> getSelectors() {
		return selectors;
	}
	
	public void setCourseId(String courseId) {
		this.cid = courseId;
	}
	
	public String getCourseName() {
		return cname;
	}
	
	public void setCourseName(String courseName) {
		this.cname = courseName;
	}
	
	public void displayAllStudentsSumScore(){
		System.out.println("\tcid\tsid\tsumScore");
		for(Student s:selectors){
			List<CourseGrade> cgs = s.getCgrades();
			for(CourseGrade cg:cgs){
				if(cg.getCid().equals(this.cid)){
					System.out.println("\t"+this.cid+"\t"+s.getSid()+"\t"+cg.getSumScore());
				}
			}
			
		}
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(((Course)obj).getCourseId() == this.cid) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return this.cid+"\t"+this.cname;
	}
}

class Student {
	private String sid;
	private String sname;
	private List<CourseGrade> cgrades;
	private List<Course> selectedCourses;
	
	public Student(String sid, String sname) {
		super();
		this.sid = sid;
		this.sname = sname;
		cgrades = new ArrayList<CourseGrade>();
		selectedCourses = new ArrayList<Course>();
	}
	
	public boolean selectCourse(Course course){
		if(selectedCourses.contains(course)) return false;
		selectedCourses.add(course);
		course.courseSelectedByStudent(this);
		return true;
	}
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public List<CourseGrade> getCgrades() {
		return cgrades;
	}

	public void setCgrades(List<CourseGrade> cgrades) {
		this.cgrades = cgrades;
	}

	public List<Course> getSelectedCourses() {
		return selectedCourses;
	}

	/*public void setSelectedCourses(List<Course> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}*/
	
	public void displayAllGrades(){
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\tsid\tcid\tclassScore\tmidTermScore\tfinalTermScore\tsumScore");
		for(CourseGrade cg:cgrades){
			cg.displayGrade();
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	}

	@Override
	public boolean equals(Object obj) {
		if(((Student)obj).getSid() == this.sid) return true;
		return false;
	}

	@Override
	public String toString() {
		return this.sid+"\t"+this.sname;
	}
	
}
public class StudentMgr {
	public static StudentMgr studentMgr = new StudentMgr();
	private List<Course> courses;
	private List<Student> students;
	List<CourseGrade> cgs;
	private StudentMgr(){
		courses = new ArrayList<Course>();
		students = new ArrayList<Student>();
		cgs = new ArrayList<CourseGrade>();
	}
	
	public boolean addStudent(Student student){
		if(students.contains(student)) return false;
		students.add(student);
		return true;
	}
	
	public boolean addCourse(Course course){
		if(courses.contains(course)) return false;
		courses.add(course);
		return true;
	}
	
	public static StudentMgr studentMgrFactory(){
		return studentMgr;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public List<Student> getStudents() {
		return students;
	}
	
	public List<CourseGrade> getCourseGrades(){
		return cgs;
	}
	
	public void displayGradesByStudent(Student s){
		s.displayAllGrades();
	}
	
	public void displayGradesByCourse(Course c){
		c.displayAllStudentsSumScore();
	}
	
	//固定分5个区间
	public void displayCourseGradesByRegion(Course c,double[] regions){
		ArrayList<CourseGrade> regs1 = new ArrayList<CourseGrade>();
		ArrayList<CourseGrade> regs2 = new ArrayList<CourseGrade>();
		ArrayList<CourseGrade> regs3 = new ArrayList<CourseGrade>();
		ArrayList<CourseGrade> regs4 = new ArrayList<CourseGrade>();
		ArrayList<CourseGrade> regs5 = new ArrayList<CourseGrade>();
		for(CourseGrade cg : cgs){
			if(c.getCourseId() == cg.getCid()){
				if(cg.getSumScore()<regions[0]){
					regs1.add(cg);
				}
				else if(cg.getSumScore()<regions[1]){
					regs2.add(cg);
				}
				else if(cg.getSumScore()<regions[2]){
					regs3.add(cg);
				}
				else if(cg.getSumScore()<regions[3]){
					regs4.add(cg);
				}
				else {
					regs5.add(cg);
				}
			}
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\tcid\tsid\tsumScore");
		System.out.println("\tcg<"+regions[0]);
		for(CourseGrade cg : regs1){
			System.out.println("\t"+cg.getCid()+"\t"+cg.getSid()+"\t"+cg.getSumScore());
		}
		System.out.println("\t"+regions[0]+"<=cg<"+regions[1]);
		for(CourseGrade cg : regs2){
			System.out.println("\t"+cg.getCid()+"\t"+cg.getSid()+"\t"+cg.getSumScore());
		}
		System.out.println("\t"+regions[1]+"<=cg<"+regions[2]);
		for(CourseGrade cg : regs3){
			System.out.println("\t"+cg.getCid()+"\t"+cg.getSid()+"\t"+cg.getSumScore());
		}
		System.out.println("\t"+regions[2]+"<=cg<"+regions[3]);
		for(CourseGrade cg : regs4){
			System.out.println("\t"+cg.getCid()+"\t"+cg.getSid()+"\t"+cg.getSumScore());
		}
		System.out.println("\tcg>="+regions[3]);
		for(CourseGrade cg : regs5){
			System.out.println("\t"+cg.getCid()+"\t"+cg.getSid()+"\t"+cg.getSumScore());
		}
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	}
	
	public void displayAllStudents(){
		for(Student s:students){
			System.out.println(s);
		}
	}
	
	public void displayAllCourses(){
		for(Course c:courses){
			System.out.println(c);
		}
	}
	
	public static void main(String args[]){
		StudentMgr stuMgr = StudentMgr.studentMgrFactory();
		List<Course> courses = null;
		List<Student> students = null;
		List<CourseGrade> cgs = null;
		//模拟学生录入
		for(int i=0;i<10;i++){
			stuMgr.addStudent(new Student("20123102006"+i, "kiki"+i));
		}
		
		//模拟课程录入
		for(int i=0;i<5;i++){
			stuMgr.addCourse(new Course(""+i, "course"+i));
		}
		courses = stuMgr.getCourses();
		students = stuMgr.getStudents();
		cgs = stuMgr.getCourseGrades();
		
		stuMgr.displayAllStudents();
		stuMgr.displayAllCourses();
		
		Random r = new Random();
		//模拟选课，每个学生选课数不得超过5门
		for(int i=0;i<students.size();i++){
			Student s = students.get(i);
			int count = 0;
			while(count<r.nextInt(5)+1){
				if(s.selectCourse(courses.get(r.nextInt(5)))){ count++;}
			}
		}
		
		//模拟课程成绩录入
		for(Course c:courses){
			List<Student> selectors = c.getSelectors();  //得到所有的选课学生
			for(Student selector:selectors){
				List<CourseGrade> stuCgs = selector.getCgrades(); //得到选课学生的成绩记录表
				stuCgs.add(new CourseGrade(selector.getSid(),c.getCourseId(), //录入该学生该门课程的成绩
						r.nextDouble()*100,r.nextDouble()*100,r.nextDouble()*100));
			}
		}
		
		for(Student s:students){
			cgs.addAll(s.getCgrades()); //将所有学生的成绩加入全局成绩记录表
		}
		
		//展示某个学生所有课程的成绩
		Student s = students.get(0);
		s.displayAllGrades();
		
		//展示所有学生所有课程的成绩
		for(Student s1:students){
			//s1.displayAllGrades();
			stuMgr.displayGradesByStudent(s1);
		}
		
		//展示某门课程所有选课生的成绩
		Course c = courses.get(0);
		c.displayAllStudentsSumScore();
		
		//展示所有课程所有选课者的总评成绩
		for(Course c1:courses){
			//c1.displayAllStudentsSumScore();
			stuMgr.displayGradesByCourse(c1);
		}
		
		
		//按区段展示某门课程的成绩
		double[] regions = {60,70,80,90};
		for(Course c1:courses){
			//c1.displayAllStudentsSumScore();
			stuMgr.displayCourseGradesByRegion(c1, regions);
		}
	}

	
}
