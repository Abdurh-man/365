import java.io.*;
import java.lang.*;
import java.util.*;

public class PersonnelTracker{
  public static class Personnel {
    public String name;
    public String ssn;
    public char gender;

    public Personnel(String name, String ssn, char gender){
      this.name = name;
      this.ssn = ssn;
      this.gender = gender;
    }

    public String getName(){
      return name;
    }
    public String getSsn(){
      return ssn;
    }
    public char getGender(){
      return gender;
    }

  }

  public static class Student extends Personnel {
    public float gpa;
    public int year;

    public Student(String name, String ssn, char gender, float gpa, int year){
      super(name,ssn,gender);
      this.year = year;
      this.gpa = gpa;
    }
    public float getGpa(){
      return gpa;
    }
    public int getYear(){
      return year;
    }

   public static final class compare implements Comparator<Student> {
        public int compare(Student s1, Student s2) {
          return s1.ssn.compareTo(s2.ssn);
      }
    }
  }

  public static class Employee extends Personnel {
    public String position;
    public double salary;

    public Employee(String name, String ssn, char gender, String position, double salary){
      super(name,ssn,gender);
      this.position = position;
      this.salary = salary;
    }
    public String getPosition(){
      return position;
    }
    public double getSalary(){
      return salary;
    }
  }

  public static class Staff extends Employee{
    public int vaction;

    public Staff(String name, String ssn, char gender, String position, double salary, int vaction){
      super(name, ssn, gender, position, salary);
      this.vaction = vaction;
    }
    public int getVaction(){
      return vaction;
    }
   public static final class compare implements Comparator<Staff> {
        public int compare(Staff s1, Staff s2) {
          if ( (s1.getVaction() - s2.getVaction()) == 0){
            return s1.ssn.compareTo(s2.ssn);
          }
          return s1.getVaction() - s2.getVaction();
      }
    }
  }

  public static class Faculty extends Employee{
    public String title;
    public Faculty(String name, String ssn, char gender, String position, double salary, String title){
      super(name,ssn,gender,position,salary);
      this.title = title;
    }
    public String getTitle(){
      return title;
    }
   public static final class compare implements Comparator<Faculty> {
        public int compare(Faculty f1, Faculty f2) {
          return (int)(f1.getSalary() - f2.getSalary());
      }
    }
  }

  public static void main(String args[]) throws Exception{
    String line = "";
    String splitBy = ",";

    ArrayList<Student> Student_list=new ArrayList<Student>();
    ArrayList<Faculty> Faculty_list=new ArrayList<Faculty>();
    ArrayList<Staff> Staff_list=new ArrayList<Staff>();
    int num_students = 0;
    int num_staff = 0;
    int num_faculty = 0;
    int num_female_student = 0;
    float avg_female_gpa = 0;
    int num_4_female = 0;
    int num_male_student = 0;
    float avg_male_gpa = 0;
    int num_4_male = 0;
    try{
      BufferedReader br = new BufferedReader(new FileReader("PersonnelEntries-1.csv"));
      while ((line = br.readLine()) != null){
        String[] human = line.split(splitBy);    // use comma as separator

        if(human.length == 6){
          if(human[3].equals("Staff")){
            Staff_list.add(new Staff(human[0], human[1], human[2].charAt(0), human[3], Double.parseDouble(human[4]), Integer.parseInt(human[5])));
            num_staff++;
          }
          else{
            Faculty_list.add(new Faculty(human[0], human[1], human[2].charAt(0), human[3], Double.parseDouble(human[4]), human[5]));
            num_faculty++;
          }
        }
        else{
          Student_list.add(new Student(human[0], human[1], human[2].charAt(0), Float.valueOf(human[3]).floatValue(), Integer.parseInt(human[4])));
          num_students++;
          if(human[2].charAt(0) == 'F'){
            num_female_student++;
            avg_female_gpa+= Float.valueOf(human[3]).floatValue();
            if(Float.valueOf(human[3]).floatValue() == 4.00){
              num_4_female++;
            }
          }
          else{
            num_male_student++;
            avg_male_gpa+= Float.valueOf(human[3]).floatValue();
            if(Float.valueOf(human[3]).floatValue() == 4.00){
              num_4_male++;
            }
          }  
        }
      }
    }
    catch(IOException e){
      e.printStackTrace();
    }

    //public class FacultyComparator implements Comparator<Faculty> {
    Collections.sort(Student_list, new Student.compare());
    Collections.sort(Staff_list, new Staff.compare());
    Collections.sort(Faculty_list, new Faculty.compare());

    BufferedWriter myWriter1 = new BufferedWriter(new FileWriter("Students.csv"));
    for(Student S : Student_list) {
      StringBuilder builder = new StringBuilder();
      // No need give the headers Like: id, Name on builder.append
      builder.append(S.getName() + ",");
      builder.append(S.getSsn() + ",");
      builder.append(S.getGender() + ",");
      builder.append(S.getGpa() + ",");
      builder.append(S.getYear() + "\n");
      myWriter1.write(builder.toString());
    }
    myWriter1.close();

    //Collections.sort(Staff_list);
    BufferedWriter myWriter2 = new BufferedWriter(new FileWriter("Staff.csv"));
    for(Staff S : Staff_list) {
      StringBuilder builder = new StringBuilder();
      // No need give the headers Like: id, Name on builder.append
      builder.append(S.getName() + ",");
      builder.append(S.getSsn() + ",");
      builder.append(S.getGender() + ",");
      builder.append(S.getPosition() + ",");
      builder.append(S.getSalary() + ",");
      builder.append(S.getVaction() + "\n");
      myWriter2.write(builder.toString());
    }
    myWriter2.close();

    BufferedWriter myWriter3 = new BufferedWriter(new FileWriter("Faculty.csv"));
    for(Faculty S : Faculty_list) {
      StringBuilder builder = new StringBuilder();
      // No need give the headers Like: id, Name on builder.append
      builder.append(S.getName() + ",");
      builder.append(S.getSsn() + ",");
      builder.append(S.getGender() + ",");
      builder.append(S.getPosition() + ",");
      builder.append(S.getSalary() + ",");
      builder.append(S.getTitle() + "\n");
      myWriter3.write(builder.toString());
    }
    myWriter3.close();
    BufferedWriter myWriter4 = new BufferedWriter(new FileWriter("Answers.txt"));
    myWriter4.write(String.format("The number of students is :%s\n", num_students));
    myWriter4.write(String.format("The number of staff is :%s\n", num_staff));
    myWriter4.write(String.format("The number of faculty is : %s\n", num_faculty));
    myWriter4.write(String.format("The number of female students is :%d\n", num_female_student));
    myWriter4.write(String.format("The average female student's GPA is :%.2f\n", avg_female_gpa/num_female_student));
    myWriter4.write(String.format("The number of female students with a 4.0 GPA is : %d\n", num_4_female));
    myWriter4.write(String.format("The number of male students is :%d\n", num_male_student));
    myWriter4.write(String.format("The average male student's GPA is :%.2f\n", avg_male_gpa/num_male_student));
    myWriter4.write(String.format("The number of male students with a 4.0 GPA is : %d\n", num_4_male));
    myWriter4.close();
  }
}

