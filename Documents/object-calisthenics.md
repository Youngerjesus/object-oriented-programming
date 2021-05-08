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


 


