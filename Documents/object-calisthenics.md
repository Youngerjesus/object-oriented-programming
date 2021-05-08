## Object Calisthenics (객체 지향 생활 체조)

좋은 객체지향 설계의 원칙을 자기 것으로 하기 위해 실행활에서 쓰도록 도와주는 훈련을 소개한다. 

훈련의 규칙은 다음과 같다. 

__ 1. 한 메소드에 오직 한 단계의 들여쓰기만 한다. __

__ 2. else 예약어(keyword) 를 쓰지 않는다. __

__ 3. 모든 원시값과 문자열을 포장(wrap)한다. __

__ 4. 한 줄에 점을 하나만 찍는다. __

__ 5. 줄여쓰지 않는다. __

__ 6. 모든 엔터티(entity)를 작게 유지한다. __

__ 7. 2개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다. __

__ 8. 일급 클래스(first-class) 컬렉션을 쓴다. __

__ 9. getter / setter / property 를 쓰지 않는다. 


***

### 1. 한 메소드에 오직 한 단계의 들여쓰기만 한다. 

한 메소드에 들여쓰기가 여러 개 존재한다면 해당 메소드는 여러가지 일을 하고 있다는 증거다. 

메소드는 맡은 일이 적을수록 재사용성이 높고 가독성도 좋다.

메소드 당 들여쓰기를 한 단계만 함으로써 각 메소드가 정확히 한 가지의 일을 하도록 제어할 수 있도록 설계할 수 있다. 

예제는 다음과 같다. 

```java
// Bad Case 
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

// Good Case 
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

```

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




 


