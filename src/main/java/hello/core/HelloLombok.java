package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("TEST NAME");

        System.out.println("helloLombok.getName() = " + helloLombok.getName());
        System.out.println("helloLombok = " + helloLombok); // @ToString 을 사용시, "helloLombok = HelloLombok(name=TEST NAME, age=0)" 로 출력됨
    }

}
