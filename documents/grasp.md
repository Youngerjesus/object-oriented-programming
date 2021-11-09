
## GRASP (General Responsibility Assignment Software Patterns)

*** 

GRASP 는 객체에 책임을 할당하는 설계 방법이다. 

- 책임이라는 건 하나의 객체가 할 수도 있고 여러 객체가 모여서 하나의 책임을 수행할 수도 있다. 

GRASP 에서는 다음과 같은 패턴이 있다.

__1. 정보 전문가 (Information Expert)__

__2. 소유 권한 (Creator)__

__3. 컨트롤러 (Controller)__

__4. 느슨한 결합 (Low Coupling)__

__5. 높은 응집도 (High Cohesion)__

__6. 간접 참조 (Indirection)__

__7. 다형성 (Polymorphism)__

__8. 순수 조립 (Pure Fabrication)__

__9. 변화 보호 (Protected Variations)__

***

### 1) 정보 전문가 (Information Expert)

> _Problem: 객체에 책임을 할당하는 기본적인 원칙은 무엇인가?_
>
> _Solution: 역할을 수행할 수 있는 충분한 정보를 가지고 있는 객체에게 역할을 부여하고 동작하도록하자._

__Related Pattern or Principle__: Low Coupling, High Cohesion

***

### 2) 소유 권한 (Creator)

> _Problem: 누가 A 객체를 만드는가?_
>
> _Solution: 누가 A 객체를 만들 수 있는가는 객체와 객체 사이에 Interaction 과 관련이 있다. (Container 와 Contained 사이의 관계인 경우.)_  
> 
__Related Pattern or Principle__: Low Coupling, Factory pattern
***

### 3) 컨트롤러 (Controller)

> _Problem: 들어오는 시스템 이벤트 처리를 담당하는 객체는 누구인가?_ 
>
> _Solution: 컨트롤러를 통해 시스템 이벤트를 처음으로 맞이하도록 해야한다. 그 다음 컨트롤러에서 이 요청을 처리할 수 있는 적절한 객체에게 위임해야한다._

컨트롤러 패턴은 전체 시스템의 사용 시나리오에서 이벤트를 전달받고 처리하는 객체이다. 주의할 점은 뚱뚱한 컨트롤러가 되지 않도록 하는 것 (Bloated Controller). 

컨트롤러가 너무 많은 책임을 가지고 있다면, 컨트롤러 객체를 더 만들자.

컨트롤러가 너무 많은 일을 하고 있다면 (위임을 적절하게 하지 않고 있다면), 적절한 객체에게 위임하는 식으로 변경하자.

__Related Pattern or Principle__: Command, Facade, Layers, Pure Fabrication

***

### 4) 느슨한 결합 (Low Coupling)

> _Problem: 어떻게 변화에 대한 충격을 줄일 수 있는가? 어떻게 의존성을 줄이고 재사용성을 높일 수 있는가?_
>
> _Solution: (불필요한) 연결을 줄이고 책임을 할당한다._

결합은 하나의 요소가 다른 요소와 얼마나 관련이 있는지 나타내는 지표다.

결합이 높다면 (High Coupling)은 의존성이 높다는 뜻으로 하나의 요소가 바뀌면 다른 요소도 바뀔 가능성이 높단 뜻이다.

느슨한 결합을 통해 객체들끼리 서로 독립적이면서도 분리되어 있는 것을 말한다. 

이를 통해 한 객체가 변경이 있더라도 영향을 최소한으로 받는 걸 말한다. 

__Related Pattern or Principle__: Cohesion
***

### 5) 높은 응집도 (High Cohesion)

> _Problem: 객체를 이해하기 쉽고 관리하기 편하고 느슨한 연결을 추구하려면 어떻게 해야할까?_
>
> _Solution: 한 객체 객체가 응집도가 높아지도록 책임을 할당해야 한다._

응집도는 객체가 책임이 얼마나 높은지 나타내는 지표다. 이 말은 내부 요소들이 얼마나 긴밀하게 관련이 있는지를 말한다. 

즉 관련없는 책임이 많아지면 응집도는 떨어지게 된다.  

__Related Pattern or Principle__: Low Coupling
 
***

### 6) 간접 참조 (Indirection)

> _Problem: 두 객체 이상에서 직접적인 연결을 피하도록 할려면 어디에 책임을 할당해야 할까?_
>
> _Solution: 두 개의 서비스나 컴포넌트를 직접 연결하지 말고 중간 매개체에 책임을 할당하라_

***

### 7) 다형성 (Polymorphism)

> _Problem: 서로 같지만 조금 다른 타입인 경우에는 행동을 다르게 할려면 어떻게 해야하는가?_ 
>
> _Solution: 다형성을 갖도록 책임을 할당하라_

겍체의 종류에 따라 행동을 바꾸도록 코드를 짤 땐 조건문보다 다형성을 이용하자. 

***

### 8) 순수 조립(Pure Fabrication)
> _Problem: 높은 응집도와 느슨한 결합을 깨고 싶지 않으면서 객체에게 책임을 주고 싶을 땐 어떻게 해야할까?__
> 
> _Solution: 문제를 해결할 수 있는 편의를 제공하는 객체에게 할당하라_

로그 정보를 기록하는 일과 같은 공통적인 역할은 한 곳에 모우도록 처리하는 것이 좋다.

Information Expert 법칙에 따르면 로그는 해당 객체가 처리하는 것이 맞지만 그렇게 하면 모든 객체가 그 일을 해야하므로 의존성이 생겨서 Low Coupling 이 되지 않는다. 그러므로 그런 공통적인 기능은 한 곳으로 모우는 객체나 시스템을 만드는게 좋다.

전략 패턴과 어댑터 패턴을 생각해보면 된다.

***

### 9) 변화 보호 (Protected Variations)

> _Problem: 불안정적이고 변화에 따라서 다른 요소에 영향을 덜 주도록 객체나 시스템을 설계하는 방법은 무엇인가?_
>
> _Solution: 안정적인 인터페이스를 설계하라._

소프트웨어에서 가장 중요한 점은 쉽게 변화를 주는 것이다. 아키텍트나 개발자 모두 이런 변화에 대비가 되어 있어야 한다. 

인터페이스 자체를 질 설계했다면, 추상화를 잘 했다면 안의 구현은 바뀔 수 있지만 여파는 없을 것.

이를 위해서는 수많은 추상화나 디자인 패턴을 공부해야한다. 

아래는 그 중 일부일 뿐이다.

- SOLID
- Design Pattern
- Law of Demeter
- Service Discovery
- Virtualization and Containerization
- Orchestration 



 

  