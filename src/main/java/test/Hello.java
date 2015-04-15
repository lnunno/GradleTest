package test;

/**
 * Created by Lucas on 4/14/2015.
 */
public class Hello {
    public String sayHello(){
        return "Hello";
    }

    public static void main(String args[]){
        System.out.println(new Hello().sayHello());
    }
}
