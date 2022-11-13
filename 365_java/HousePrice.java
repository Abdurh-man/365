//Abdurhman Bahour
//Abahour2
public class HousePrice {
  public static class Person{
    String name;
    String house_color;
    int house_price;

    public Person(String name, int house_price, String house_color){
      this.name = name;
      this.house_price = house_price;
      this.house_color = house_color;
    }
  }
  public enum color {
    // Defined with properties - which day of the week and whether a weekday or not
    RED (2010),
    BLUE (2011),
    GREEN (2012),
    WHITE (2013);

    final int year;
    color(int Y) {
      year = Y;
    }
    public int getYear() {
      return year;
    }
  }
  public static void main(String args[]){
    if ((args.length)%3 != 0 || args.length < 3){
      System.err.println("Wrong argument.");
      System.exit(1);
    }
    // this has all the houses in the block
    Person list[] = new Person[args.length/3];
    float avg = 0;
    int house_num = 0;
    // name price color
    for(int i = 0; i < args.length; i++){
      list[house_num] = new Person(args[i], Integer.valueOf(args[++i]), args[++i]);
      //this give us house price need number of block to find avg
      avg += Integer.valueOf(args[i-1]);
      house_num++;
    }
    avg/=house_num;
    System.out.printf("average house price = %,10.2f%n", avg);
    boolean T_or_F;
    for(Person p : list){
      color c;
      if (p.house_color.equals("red"))
        c = color.RED;
      else if (p.house_color.equals("blue"))
        c = color.BLUE;
      else if (p.house_color.equals("green"))
        c = color.GREEN;
      else 
        c = color.WHITE;

      if(Integer.valueOf(p.house_price) > avg) 
        T_or_F = true;
      else 
        T_or_F = false;

      System.out.printf("%-15s %,10d %b%n",p.name, p.house_price, T_or_F);
      System.out.printf("This %s house was build in the year %d%n",p.house_color, c.getYear());
    }
  }
}
