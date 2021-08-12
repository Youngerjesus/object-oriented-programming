## SOLID (객체 지향 설계)

> 컴퓨터 프로그래밍에서 SOLID란 로버트 마틴이 2000년대 초반에 명명한 객체 지향 프로그래밍 및 설계의 다섯 가지 기본 원칙을 마이클 페더스가 두문자어 기억술로 소개한 것이다. 
>
> 프로그래머가 시간이 지나도 유지 보수와 확장이 쉬운 시스템을 만들고자 할 때 이 원칙들을 함께 적용할 수 있다
>
> SOLID 원칙들은 소프트웨어 작업에서 프로그래머가 소스 코드가 읽기 쉽고 확장하기 쉽게 될 때까지 소프트웨어 소스 코드를 리팩터링하여 코드 냄새를 제거하기 위해 적용할 수 있는 지침이다
> 
> 이 원칙들은 애자일 소프트웨어 개발과 적응적 소프트웨어 개발의 전반적 전략의 일부다.

***

### S: SRP (Single responsibility principle) - 단일 책임 원칙
    
    한 클래스는 하나의 책임만 가져야 한다.

객체 지향 프로그래밍에서 단일 책임 원칙(single responsibility principle)이란 모든 클래스는 하나의 책임만 가지며, 클래스는 그 책임을 완전히 캡슐화해야 함을 일컫는다. 클래스가 제공하는 모든 기능은 이 책임과 주의 깊게 부합해야 한다.

로버트 마틴은 책임을 변경하려는 이유로 정의하고, 어떤 클래스나 모듈은 변경하려는 단 하나 이유만을 가져야 한다고 결론 짓는다

예를 들어서 보고서를 편집하고 출력하는 모듈을 생각해 보자. 이 모듈은 두 가지 이유로 변경될 수 있다. 첫 번째로 보고서의 내용 때문에 변경될 수 있다. 두 번째로 보고서의 형식 때문에 변경될 수 있다. 

이 두 가지 변경은 하나는 실질적이고 다른 하나는 꾸미기 위한 매우 다른 원인에 기인한다. 단일 책임 원칙에 의하면 이 문제의 두 측면이 실제로 분리된 두 책임 때문이며, 따라서 분리된 클래스나 모듈로 나누어야 한다

한 클래스를 한 관심사에 집중하도록 유지하는 것이 중요한 이유는, 이것이 클래스를 더욱 튼튼하게 만들기 때문이다.

##### Bad Case - Example  

````java
public class Invoice {

    private Book book;
    private int quantity;
    private double discountRate;
    private double taxRate;
    private double total;

    public Invoice(Book book, int quantity, double discountRate, double taxRate, double total) {
        this.book = book;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.taxRate = taxRate;
        this.total = total;
    }

    public double calculateTotal(){
        double price = (book.price - book.price * discountRate) * this.quantity;

        double priceWithTaxes = price * (1 + taxRate);

        return priceWithTaxes;
    }

    public void printInvoice(){
        System.out.println(quantity + "x " + book.name + " " + book.price + "$");
        System.out.println("Discount Rate: " + discountRate);
        System.out.println("Tax Rate: " + taxRate);
        System.out.println("Total: " + total);
    }

    public void saveToFile(String[] filename){
        // creates a file with given name and writes the invoice
    }
}

public class Book {
    String name;
    String authorName;
    int year;
    int price;
    String isBn;

    public Book(String name, String authorName, int year, int price, String isBn) {
        this.name = name;
        this.authorName = authorName;
        this.year = year;
        this.price = price;
        this.isBn = isBn;
    }
}
````

##### Good Case - Example

```java
public class Invoice {
    Book book;
    int quantity;
    double discountRate;
    double taxRate;
    double total;

    public Invoice(Book book, int quantity, double discountRate, double taxRate, double total) {
        this.book = book;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.taxRate = taxRate;
        this.total = total;
    }

    public double calculateTotal(){
        double price = (book.price - book.price * discountRate) * this.quantity;

        double priceWithTaxes = price * (1 + taxRate);

        return priceWithTaxes;
    }
}

public class InvoicePrinter {
    private Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public void print() {
        System.out.println(invoice.quantity + "x " + invoice.book.name + " " + invoice.book.price + " $");
        System.out.println("Discount Rate: " + invoice.discountRate);
        System.out.println("Tax Rate: " + invoice.taxRate);
        System.out.println("Total: " + invoice.total + " $");
    }
}


public class InvoicePersistence {
    Invoice invoice;

    public InvoicePersistence(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToFile(String filename) {
        // Creates a file with given name and writes the invoice
    }
}

public class Book {
    String name;
    String authorName;
    int year;
    int price;
    String isBn;

    public Book(String name, String authorName, int year, int price, String isBn) {
        this.name = name;
        this.authorName = authorName;
        this.year = year;
        this.price = price;
        this.isBn = isBn;
    }
}
```

***
### O: OCP(Open/closed principle) - 개방 폐쇄 원칙

    소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.

소프트웨어 개발 작업에 이용된 많은 모듈 중에 하나에 수정을 가할 때 그 모듈을 이용하는 다른 모듈을 줄줄이 고쳐야 한다면, 이와 같은 프로그램은 수정하기가 어렵다.

개방-폐쇄 원칙은 시스템의 구조를 올바르게 재조직(리팩토링)하여 나중에 이와 같은 유형의 변경이 더 이상의 수정을 유발하지 않도록 하는 것이다.

개방-폐쇄 원칙이 잘 적용되면, 기능을 추가하거나 변경해야 할 때 이미 제대로 동작하고 있던 원래 코드를 변경하지 않아도, 기존의 코드에 새로운 코드를 추가함으로써 기능의 추가나 변경이 가능하다

#### 개방-폐쇄 원칙의 두 가지 속성

##### 확장에 대해 열려 있다.

이것은 모듈의 동작을 확장할 수 있다는 것을 의미한다. 애플리케이션의 요구 사항이 변경될 때, 이 변경에 맞게 새로운 동작을 추가해 모듈을 확장할 수 있다. 즉, 모듈이 하는 일을 변경할 수 있다.

##### 수정에 대해 닫혀 있다

모듈의 소스 코드나 바이너리 코드를 수정하지 않아도 모듈의 기능을 확장하거나 변경할 수 있다. 그 모듈의 실행 가능한 바이너리 형태나 링크 가능한 라이브러리(예를 들어 윈도의 DLL이나 자바의 .jar)를 건드릴 필요가 없다.


##### Bad Case - Example 

````java
public class InvoicePersistence {
    Invoice invoice;

    public InvoicePersistence(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToFile(String[] filename){
        // Creates a file with given name and writes the invoice
    }

    public void saveToDatabase(){
        // Saves the invoice to database
    }
}
````

##### Good Case - Example 

```java
public interface InvoicePersistence {
    void save();
}

public class FilePersistence implements InvoicePersistence{

    @Override
    public void save() {
        // save To File
    }
}

public class DatabasePersistence implements InvoicePersistence{

    @Override
    public void save() {
        // Save To Database
    }
}


public class PersistenceManager {
    InvoicePersistence invoicePersistence;

    public PersistenceManager(InvoicePersistence invoicePersistence) {
        this.invoicePersistence = invoicePersistence;
    }
}
```

***
### L: LSP(Liskov substitution principle) - 리스코프 치환 원칙

    프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.    
    
치환성(substitutability)은 객체 지향 프로그래밍 원칙이다. 

 컴퓨터 프로그램에서 자료형 S가 자료형 T의 하위형이라면 필요한 프로그램의 속성(정확성, 수행하는 업무 등)의 변경 없이 자료형 T의 객체를 자료형 S의 객체로 교체(치환)할 수 있어야 한다는 원칙이다. 
 
##### Bad Case - Example

````java
public class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

public class Square extends Rectangle{
    public Square() {}

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}

public class Test {
    static void getAreaTest(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea()); // Error - Square Case
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        getAreaTest(rc);

        Rectangle sq = new Square();
        sq.setWidth(5);
        getAreaTest(sq);
    }
}
````

##### Good Case - Example

````java
public class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

public class Square {
    protected int width, height;

    public Square() {}

    public Square(int size) {
        width = height = size;
    }

    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    public void setHeight(int height) {
        this.height = height;
        this.width = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea() {
        return width * height;
    }
}

public class Test {
    static void getAreaTest(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
    }

    static void getAreaTest(Square r) {
        r.setHeight(10);
        System.out.println("Expected area of " + (10 * 10) + ", got " + r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        getAreaTest(rc);

        Square sq = new Square();
        sq.setWidth(5);
        getAreaTest(sq);
    }
}
````
***
### I: ISP(Interface segregation principle) - 인터페이스 분리 원칙

    특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.

인터페이스 분리 원칙은 클라이언트가 자신이 이용하지 않는 메서드에 의존하지 않아야 한다는 원칙이다.

인터페이스 분리 원칙은 큰 덩어리의 인터페이스들을 구체적이고 작은 단위들로 분리시킴으로써 클라이언트들이 꼭 필요한 메서드들만 이용할 수 있게 한다.

이와 같은 작은 단위들을 역할 인터페이스라고도 부른다.

인터페이스 분리 원칙을 통해 시스템의 내부 의존성을 약화시켜 리팩토링, 수정, 재배포를 쉽게 할 수 있다. 

인터페이스 분리 원칙은 SOLID 5원칙의 하나이며, GRASP의 밀착 원칙과 비슷하다.

##### Bad Case - Example 

````java
public interface ParkingLot {
    void parkCar();	// Decrease empty spot count by 1
    void unparkCar(); // Increase empty spots by 1
    void getCapacity();	// Returns car capacity
    double calculateFee(Car car); // Returns the price based on number of hours
    void doPayment(Car car);
}

class Car{

}

public class FreeParkingLot implements  ParkingLot{
    @Override
    public void parkCar() {

    }

    @Override
    public void unparkCar() {

    }

    @Override
    public void getCapacity() {

    }

    @Override
    public double calculateFee(Car car) {
        return 0;
    }

    @Override
    public void doPayment(Car car) {
        throw new RuntimeException("Parking lot is free");
    }
}
````

##### Good Case - Example 

````java
public interface ParkingLot {
    void parkCar();	// Decrease empty spot count by 1
    void unparkCar(); // Increase empty spots by 1
    void getCapacity();	// Returns car capacity
}

public interface PaidParkingLot extends ParkingLot{
    double calculateFee(Car car); // Returns the price based on number of hours
    void doPayment(Car car);
}

class Car {

}

public interface FreeParkingLot extends ParkingLot {
    void doSomething(); 
}
````
***
### D: DIP(Dependency inversion principle) - 의존관계 역전 원칙

    프로그래머는 “추상화에 의존해야지, 구체화에 의존하면 안된다.

객체 지향 프로그래밍에서 의존관계 역전 원칙은 소프트웨어 모듈들을 분리하는 특정 형식을 지칭한다. 

이 원칙을 따르면, 상위 계층(정책 결정)이 하위 계층(세부 사항)에 의존하는 전통적인 의존관계를 반전(역전)시킴으로써 상위 계층이 하위 계층의 구현으로부터 독립되게 할 수 있다.

이 원칙은 다음과 같은 내용을 담고 있다

첫째, 상위 모듈은 하위 모듈에 의존해서는 안된다. 상위 모듈과 하위 모듈 모두 추상화에 의존해야 한다.

둘째, 추상화는 세부 사항에 의존해서는 안된다. 세부사항이 추상화에 의존해야 한다.



