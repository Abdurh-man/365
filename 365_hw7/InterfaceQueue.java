//Abdurhman Bahour
//abahour2
import java.util.*;
import java.io.*;
import java.lang.*;

interface Queue{
  static int length = 0;
  public void push(Object o);
  public Object pop();
  public Object peek();
  public void loop();
  public boolean isEmpty();
  public int length();
}

class ArrayQueue implements Queue{
  private int length;
  
  ArrayList<Object> list = new ArrayList<Object>();
  
  public void push(Object o){
    list.add(o);
    length++;
  }
  public Object pop(){
    if(list.size() == 0)
      return 0;

    Object deleted_item = list.get(0);
    list.remove(0);
    length--;
    return deleted_item;
  }
  public Object peek(){
    return list.get(0);
  }
  public void loop(){
    push(pop());
  }
  public boolean isEmpty(){
    return (length == 0);
  }
  public int length(){
    return length;
  }
}

class LinkListQueue implements Queue{
  private int length;
  Node head;
  Node tail;

  class Node{
    Object data;
    Node next;

    Node(Object o){
      this.data = o;
      this.next = null;
    }
  }
  public void push(Object o){
    length++;
    Node node = new Node(o);
    
    if(head == null){
      head = node;
      tail = node;
      return;
    }

    tail.next = node; 
    tail = node;
  }
  public Object pop(){
    Object data = head.data;
    head = head.next;

    length--;
    return data;
  }
  public Object peek(){
    return head.data;
  }
  public void loop(){
    push(pop());
  }
  public boolean isEmpty(){
    return (length == 0);
  }
  public int length(){
    return length;
  }
}
public class InterfaceQueue{
  public static void main(String[] args) throws Exception{
    LinkListQueue LQueue = new LinkListQueue();
    ArrayQueue AQueue = new ArrayQueue();

    String line = "";    
    try{
      BufferedReader br = new BufferedReader(new FileReader("RandomNumbers.txt"));
      while( (line = br.readLine()) != null){
        try{
          int data = Integer.parseInt(line);
          AQueue.push(data);
        }
        catch(NumberFormatException e){
          LQueue.push(line);
        }
      } 
    }
    catch(IOException e){
      e.printStackTrace();
    }

    System.out.println("Number of integers: " + AQueue.length());
    System.out.println("Number of Non-integers: " + LQueue.length());

    try{
      FileWriter myWriter = new FileWriter("output.txt");

      for(int process = 0; AQueue.isEmpty() == false; process++){
        int amount = (Integer)AQueue.peek();
        int run_it = 0;
        while(amount > 0){
          run_it += amount % 10;
          amount /= 10;
        }
        for(int i = 0; i < run_it; i++){
          AQueue.loop();
        }

        run_it = (Integer)AQueue.pop() / 1000;
        for(int i = 0; i < run_it; i++){
          LQueue.loop();
        }

        myWriter.write(LQueue.peek() + "\n");
        for(int i = 0; i <= process; i++){
          AQueue.pop();
        }
      }
      myWriter.write("\n");
      myWriter.close();
    }
    catch(IOException e){
      e.printStackTrace();
    } 
  }
}
