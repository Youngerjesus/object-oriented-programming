## 디미터 법칙 

디미터 법칙은 객체 간 관계를 설정할 때 객체간의 결합도를 효과적으로 낮출수 있는 유용한 지침 중 하나로 꼽히며 객체 지향 생활 체조 원칙 중 __한 줄에 점을 하나만 찍는다.__ 로 요약 되기도 한다.

### Don't Talk to AStrangers

디미터 법칙의 핵심은 객체 구조의 경로를 따라 멀리 떨어져 있는 낯선 객체에 메시지를 보내는 설계를 피하라고 한다. 

이 법칙에 따르면 객체는 자신이 내부적으로 가지고 있는 객체들에게 메시지를 보내야 하지 __다른 객체를 탐색해내서 정보를 넣거나 뭔가를 하면 안된다.__ 라는 법칙이다.

이러한 핵심적인 내용 때문에 디미터 법칙은 Don't Talk to Strangers 라고 불리며 
한 객체가 알아야 하는 다른 객체를 최소한으로 유지하라는 의미로 Principle of least knowledge 라고도 불린다.

### 규칙화

디미터 법칙은 객체에서 다음에 해당하는 메소드만을 호출해야 한다고 말한다. 

- __객체 자신의 메소드__

- __메소드 피라미터로 넘어온 피라미터 메소드__

- __메소드 내부에서 생성된 객체의 메소드__

- __객체가 가지고 있는 인스턴스 변수의 메소드__  

예는 다음과 같다. 
```java
public class Demeter {
    private Member member;

    public void myMethod(){

    }

    public void okLawOfDemeter(Parameter parameter){
        myMethod(); // 1. 객체 자신의 메소드 호출
        parameter.paramMethod(); // 2. 메소드 파라미터로 넘어온 객체의 메소드 호출
        LocalVariable localVariable = new LocalVariable();
        localVariable.localMethod(); // 3. 메소드 내부 로컬 변수에서 메소드 호출
        member.memberMethod(); // 4. 클래스 인스턴스 변수가 가지고 있는 메소드 호출
   }
}
```