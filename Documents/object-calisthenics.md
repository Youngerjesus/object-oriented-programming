## Object Calisthenics (객체 지향 생활 체조)

좋은 객체지향 설계의 원칙을 자기 것으로 하기 위해 실행활에서 쓰도록 도와주는 훈련을 소개한다. 

훈련의 규칙은 다음과 같다. 

__1. 한 메소드에 오직 한 단계의 들여쓰기만 한다.__

__2. else 예약어(keyword) 를 쓰지 않는다.__

__3. 모든 원시값과 문자열을 포장(wrap)한다.__

__4. 한 줄에 점을 하나만 찍는다.__

__5. 줄여쓰지 않는다.__

__6. 모든 엔터티(entity)를 작게 유지한다.__

__7. 2개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.__

__8. 일급 클래스(first-class) 컬렉션을 쓴다.__

__9. getter / setter / property 를 쓰지 않는다.__


***

### 1. 한 메소드에 오직 한 단계의 들여쓰기만 한다. 

한 메소드에 들여쓰기가 여러 개 존재한다면 해당 메소드는 여러가지 일을 하고 있다는 증거다. 

메소드는 맡은 일이 적을수록 재사용성이 높고 가독성도 좋다.

메소드 당 들여쓰기를 한 단계만 함으로써 각 메소드가 정확히 한 가지의 일을 하도록 제어할 수 있도록 설계할 수 있다. 

예제는 다음과 같다. 


##### Bad Case 

```java
public class Board {
    ... 
    public String board(){
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buf.append(data[i][j]);
            }
            buf.append("\n");
        }
        return buf.toString();
    }
}
```

## Good Case

````java
public class Board {
    ...

    // Good Case
    public String board(){
        StringBuffer buf = new StringBuffer();
        collectRows(buf);
        return buf.toString();
    }

    private void collectRows(StringBuffer buf) {
        for (int i = 0; i < 10; i++) {
            collectRow(buf, i);
        }
    }

    private void collectRow(StringBuffer buf, int i) {
        for (int j = 0; j < 10; j++) {
            buf.append(data[i][j]);
        }
        buf.append("\n");
    }
}

````
***

### 2. else 예약어 금지

모든 프로그래머는 if / else 를 이해하고 모든 프로그래밍 언어에는 이 예약어들이 있다. 

그리고 if / else 로 이뤄진 중첩문들이나 switch - case 의 엄청난 중첩문들을 본 적도 있다.

분기가 많으면 그 만큼 가독성이 떨어진다. 

else 문을 안쓰고 어떻게 프로그래밍을 짜느냐 하면 if() 절을 통해서 return 을 일찍 하던가

if() 절 조차 쓰지 않고 디자인 패턴 중 전략 패턴이나 상태 패턴을 이용해 다형성을 통해서 해결할 수도 있다. 
 
예제는 다음과 같다.

##### if() - early return

````java
public class MyStatus {

    String status(int hour, boolean isStudy){
        if(hour > 4 && hour <= 12){
            return "sleep";
        }

        return isStudy ? "study" : "vacation";
    }
}
````

##### design-pattern 

````java
public abstract class Bird {
    int numberOfCounts;

    public Bird(int numberOfCounts) {
        this.numberOfCounts = numberOfCounts;
    }

    public double getBaseSpeed(){
        return 50;
    }
    abstract double getSpeed();
}

class European extends Bird{
    public European(int numberOfCounts) {
        super(numberOfCounts);
    }

    @Override
    double getSpeed() {
        return getBaseSpeed();
    }
}

class African extends Bird{
    public African(int numberOfCounts) {
        super(numberOfCounts);
    }

    @Override
    double getSpeed() {
        return getBaseSpeed() - getLoadFactor() * numberOfCounts;
    }

    private double getLoadFactor() {
        return 2;
    }
}

class NorwegianBlue extends Bird{
    private boolean isNailed;

    public NorwegianBlue(int numberOfCounts, boolean isNailed) {
        super(numberOfCounts);
        this.isNailed = isNailed;
    }

    @Override
    double getSpeed() {
        return isNailed ? 0 : getBaseSpeed();
    }
}

speed = bird.getSpeed();
```` 

***

### 3. 원시값과 문자열 포장(wrap)

int 형 원시 값 자체는 아무 의미 없는 스칼라 값일 뿐이다. 

만약 어떤 메소드에서 시간을 int 원시형으로 받는다고 가정해보자. 메소드 선언에서 피라미터 이름을 `time`
으로 지정해도 다른 누군가는 연도를 넘기는 사태가 일어날 수 있다. 

이렇게 원시 값을 그대로 쓰게 된다면 내가 만든 메소드의 의도를 정확하게 표현할 수 없다.

그러므로 원시 값을 감싸고 있는 객체 클래스로 만들어서 전달하게 한다면 이를 해결할 수 있다. 

그리고 이 객체 클래스에서 내부적으로 validate 를 할 수 있고 원하다면 Immutable 하게 설정할 수도 있다. 

즉 객체로 감싸면 얻는 이점이 많다. 

예제는 다음과 같다. 

##### Bad Case 

```java
public class Order {
    int totalAmount;

    public Order(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int CalculateMoney(int money){
        validMoney(money);
        return money - totalAmount;
    }

    private void validMoney(int money) {

    }
}
```

##### Good Case

```java
public class Order {
    int totalAmount;

    public Order(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int CalculateMoney(Money money){
        return money.get() - totalAmount;
    }

    private void validMoney(int money) {

    }
}

public class Money {
    int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        // do something()
    }

    public int get(){
        return money;
    }
}
```

***

### 4. 한 줄에 한 점만 사용

객체의 메소드 안에 점이 두 개 이상있다면 너무 깊이 관여하고 있다는 증거다. 

그 객체는 너무 많은 객체를 깊이 관여하고 있다는 뜻이고 이는 캡슐화를 어기고 있다는 증거다. 

객체의 메소드에서는 자기 속을 들춰내기보다는 뭔가 작업을 하도록 만들어야 한다. 

이를 위한 방법으로는 디미터 법칙 (Law of Demeter) 와 유사하다. 이런 식으로 생각하자.

객체의 관여에서는 자기 소유의 장난감, 자기가 만든 장난감, 그리고 누군가가 자기에게 준 장난감과만 객체는 놀 수 있다.

절대 장난감의 장난감과 놀면 안된다. 

예제는 다음과 같다. 

##### Bad Case 

```java

public class Person {
    Wallet wallet;

    public Person(Wallet wallet) {
        this.wallet = wallet;
    }

    public int getMoney(){
        return wallet.getTotalMoney().get();
    }
}

public class Wallet {
    List<Money> moneyList;

    public Wallet(List<Money> moneyList) {
        this.moneyList = moneyList;
    }

    public Money getTotalMoney() {
        int totalMoney = 0;
        for (Money money : moneyList){
            totalMoney += money.get();
        }
        return new Money(totalMoney);
    }
}

public class Money {
    int money;

    public Money(int money) {
        this.money = money;
    }

    public int get(){
        return money;
    }
}
``` 

##### Good Case 

````java
public class Person {
    Wallet wallet;

    public Person(Wallet wallet) {
        this.wallet = wallet;
    }

    public int getMoney(){
        return wallet.getTotalMoney();
    }
}

public class Wallet {
    List<Money> moneyList;

    public int getTotalMoney() {
       int totalMoney = 0;

       for(Money money : moneyList){
           totalMoney += money.get();
       }

       return totalMoney;
    }
}

public class Money {
    int money;

    public Money(int money) {
        this.money = money;
    }

    public int get(){
        return money;
    }
}
````

***

 


