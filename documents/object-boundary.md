## 객체 경계 

[조영호 님의 책 5장 - 객체 경계](5장%20객체%20경계.pdf)

***

### Introduction 

객체가 다른 객체에게 메시지를 전달하기 위해서는 객체에 접근하는게 필요하다.

접근을 위한 가장 간단한 방법으로는 객체 안에 메시지 전송할 객체를 참조하고 있는 것. 

이를 통하면 객체 간의 참조 관계는 거대한 그래프 관계가 된다.
  
대신에 이러면 항상 객체를 포함하고 있으니까 메모리 사용이 문제가 된다.
  
그래서 객체를 관계형 데이터베이스에 저장해놓고 필요한 시점에 객체를 로드하는게 일반적이다. 
  
관계형 데이터베이스를 사용할 때 문제점은 객체와 관계형 데이터베이스 사이의 패러다임의 문제가 있고 애플리케이션의 경우에 기능을 구현하기 위해서는 객체 그래프 사이에서 필요한 객체만 가지고 와야한다. 전체를 가지고 오면 그만큼 데이터베이스가 성능 상에 영향을 줄것이니까.
    
이렇게 필요한 객체를 가지고 오기 위해서는 객체 조회의 시작 지점과 객체 조회를 끝날 지점을 결정해야하고 두 객체 사이에 존재하는 객체들도 모두 조회해야한다.

간단히 말해서 전체 객체 그래프 중에서 현재의 기능을 구현하는데 필요한 객체만 포함하는 `경계` 를 찾아서 조회를 해야한다. 

객체 지향 커뮤니티에서 이 문제를 해결하는 도구가 바로 ORM(Object-Relational-Mapping) 을 이용한 방법이다. 

ORM 을 이용하면 영속성 전이나 지연로딩, 패치 조인 같은 기능을 통해서 객체 그래프 안에 있는 객체 중심으로 경계를 정해서 가지고 오는게 가능하다. 

여기서는 객체 경계 문제를 ORM 을 사용해서 해결하는 예제를 보겠다. 이를 통해 ORM 을 잘못 사용하면 오히려 도메인 관심사와 데이터베이스 사이의 경계가 모호해질 수 있다. 는 걸 이해할 수 있을 것이다.  

ORM 을 제대로 이해하기 위해서는 ORM 의 구조적인 측면과 동작 측면 모두를 이해해야 한다.

ORM 의 구조 측면은 데이터베이스의 테이블 칼럼과 클래스의 인스턴스 변수를 매핑하는 방법에 관한 것이라면 

ORM 의 동작 측면은 데이터베이스에 객체를 저장하거나 객체를 조회할 때 방법에 관한 것이다. 

대부분의 사람들은 ORM 을 사용할 때 매핑을 다루는 구조 측면에서 집중하는 경향이 있지만 마틴 파울러(Martin Fowler) 가 언급한 것처럼 객체를 조회하거나 저장하는 동작을 이해하는 것이 중요하다. 

이번 장에서는 먼저 여행 어플리케이션의 클래스를 테이블과 매핑하면서 ORM 의 구조 측면을 설명하기로 하고 이어서 어플리케이션의 기능을 구현한 코드를 살펴보면서 ORM 의 동작적인 측면을 살펴볼 것이다.

마지막으로 새로운 기능을 추가하면서 객체 경계를 고민하지 않을 때 발생할 수 있는 문제점을 살펴볼 것이다. 

***

### ORM 의 구조 측면 

객체와 데이터베이스의 가장 큰 차이점은 관계를 처리하는 방법이다. 

객체는 다른 객체에 대한 참조를 보관하고 이 참조를 이용해서 다른 객체에 접근하는 반면에 테이블은 외래 키를 통해서 다른 테이블에 접근한다. 

#### 식별자 필드 

객체와 테이블 사이의 관계에 대한 차이점을 해소하기 위해서 먼저 ORM 은 테이블에 매핑되는 모든 클래스에 식별자 필드 (Identity Field) 를 포함시킨다. 식별자 필드는 기본 키(Primary Key) 를 저장하기 위한 인스턴스 변수라고 생각하면 된다. 

아래와 같이 JPA 를 사용하면 객체와 테이블을 매핑하는게 가능하다. 

```java
@Entity
public class Tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
  
    private String name;
}
```

#### 포함 값

객체 지향 설계는 작은 크기의 객체의 조합들을 선호하지만 데이터베이스 설계는 논리적으로 연관된 속성이라면 모두 동일한 테이블에 모을려고 한다. 

이 말은 객체 자체가 테이블과 연관 될 수도 있지만 객체 자체가 테이블의 속성과 연관되어 있는 경우도 있다. 

그러다보니 객체 보다 테이블에 더 많은 속성들이 정의되어 있는 경우가 많다. 

예를 들어 Tourist 클래스 안에는 Location 이라는 객체를 포함하는데 이는 Tourist 테이블에서는 City 와 같은 값이다. 

Location 같은 객체를 JPA 에서는 값 객체(Value Object) 라고 부른다.

```java
@Embeddable
@Access(AccessType.FIELD)
public class Location {
    private String name;

    public static Location at(String name) {
        return new Location(name);
    }

    private Location(String name) {
        this.name = name;
    }

    protected Location() {
    }

    public String getName() {
        return name;
    }
}
```

````java
@Entity
public class Tourist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "CITY"))
    private Location city;
}
````
 
#### 외래 키 매핑

이제 TourPackage 를 만들고 이를 TOUR_MAP 과 TOUR_PLANNER 과 매핑하자. 

JPA 는 외래 키 매핑을 위해 두 가지 종류의 어노테이션을 제공한다. 

하나는 다중성(multiplicity) 를 제공하기 위한 어노테이션이고 종류로 @OneToOne, @OneToMany, @ManyToOne, @ManyToMany 들을 제공한다. 

다른 하나는 외래 키를 지정하기 위한 @JoinColumn 에노테이션을 제공한다. 

TourPackage 테이블과 TourMap 테이블 사이의 관계는 N:1 이므로 @ManyToOne 어노테이션을 사용한다. 반면에 TourPlanner 와의 관계는 1:1 이므로 
@OneToOne 에노테이션을 사용한다. 

이 이후에 TourPlanner 를 테이블과 매핑시키기 위해서는 두 가지 이슈를 해결해야한다. 

첫 번째는 상속 관계를 테이블에 매핑하는 것이고 

두 번째는 TourPlanner 와 TourPackage 사이의 연관관계를 매핑하는 것이다. 

먼저 상속 관계를 보면 ORM 에서 상속 관계를 매핑하는 방식으로는 단일 테이블 상속, 클래스 테이블 상속, 구체 테이블 상속 이렇게 있다. 

여기서는 단일 테이블 상속 방식을 사용하도록 한다. 

이 상속 계층에서는 여러 클래스들을 하나의 테이블에 저장하고 구분하기 위해서 PLANNER_TYPE 이라는 칼럼을 주고 각각의 클래스를 구별하는 용도로 사용한다. 

JPA 에서 상속 계층을 구분하기 위해서는 부모 클래스와 자식 클래스에 어노테이션을 추가해야한다. 

부모 클래스에는 @Inheritance 와 @DiscriminatorColumn 어노테이션을 추가하고 @Inheritance 에는 상속 방식을 지정해줘야 하며, @DiscriminatorColumn 에는 타입을 지정할 칼럼의 이름을 할당해야한다. 

자식 클래스에는 @DiscriminatorValue 어노테이션을 추가하고 타입 칼럼의 값을 할당한다. 

````java
@Entity
public class TourPackage {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "TOUR_MAP_ID")
    private TourMap tourMap;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TOUR_PLANNER_ID")
    private TourPlanner tourPlanner;
}
````

```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PLANNER_TYPE")
public class TourPlanner {

    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne(mappedBy = "tourPlanner")
    private TourPackage tourPackage;
}
```

````java
@Entity
@DiscriminatorValue("LEAST_DISTANCE")
public class LeastDistancePlanner extends TourPlanner{
    
    private double distanceLimit; 
}

@Entity
@DiscriminatorValue("LEAST_STOPS")
public class LeastStopsPlanner extends TourPlanner {
    
    private int stopsLimit;
}
````

#### 연관관계 소유자

연관관계를 설정할 땐 연관관계 소유자의 개념을 알아야 한다. 

테이블에서는 FK 하나로 양방향으로 조회할 수 있다면 객체에서는 객체의 참조를 가지고 있어야 그 객체를 조회하는게 가능하다. 즉 객체는 기본적으로 단방향이다. 

내가 상대방의 객체 참조를 가지고 있어봤자 상대방이 나의 참조를 가지고 있지 않다면 조회할 수 없다. 

정리하자면 테이블은 FK 만 있으면 참조하는데 전혀 지장이 없다. 하지만 객체에서는 어떤 객체를 만들면 연관관계를 맺어주는 작업이 필요하다. 이를 양방향 연관관계라고 한다. 

JPA 에서는 객체의 이런 양방향 연관관계의 동기화를 간단하게 할 수 있는데 @OneToMany 나 @OneToOne 같은 경우에 mappedBy 속성으로 인해 FK 키의 주인을 정해줄 수 있다. 

mappedBy 속성을 사용하는 쪽은 FK 의 주인이 아니고 상대방이 FK 를 가진다 라는 뜻이다. 

그러므로 mappedBy 를 사용하는 쪽에서는 연관 객체의 참조를 가지고 싶다면 연관관계를 맺어주는 작업이 필요하다. 

여기 예제로 한번 더 설명하면 TourPackage 와 TourPlanner 가 있고 TourPackage 가 연관관계의 주인이다. 

양방향 관계를 설정하고 싶다면 TourPackage 객체를 만들고 TourPlanner 를 TourPackage 에 설정하는 메소드에서는 TourPlanner 쪽에서도 TourPackage 를 더해주는 메소드가 필요하다. 

#### 포함 값 컬렉션 

TourMap 클래스는 내부에 리스트로 Path 를 가진다. 

그치만 이 Path 는 Tour 클래스 내부에서도 여행 경로를 표현하기 위해서 Path 클래스를 재사용한다. 

즉 Path 클래스는 다양한 클래스에서 재사용할 수 있도록 설정되었기 때문에 매핑할 테이블을 지정하는 것이 불가능하다. 

이 문제를 해결할 수 있는 여러가지 방법이 있지만 Path 를 포함 값으로 설정하고 TourMap 과 Tour 에서는 컬렉션 형태로 포함시키는 방법이 있다. 

이처럼 포함 값 컬렉션을 매핑할 때 두 가지 기억할 사항이 있는데 첫 번째는 포함 값의 컬렉션을 매핑할 때 클래스의 모든 속성이 테이블의 기본 키를 구성하도록 테이블을 설계하여야 한다. 
기존 처럼 ID 값을 가지도록 설정하면 안된다. 

두 번째는 포함 값의 컬렉션의 한 요소가 수정되면 컬렉션의 모든 요소를 전부 삭제하고 다시 인서트 한다는 점이다. 이 이유는 변한 요소를 추적할 수 있는 ID 값이 없기 때문에 내부적으로 이렇게 동작한다. 

즉 이 두 번째 요소를 기억해서 잘 변하지 않는 요소의 경우에 값 컬렉션으로 사용해야한다. 

포함 값 컬렉션을 매핑하기 위해서는 @ElementCollection 어노테이션을 사용하고 @Collection 을 추가해서 Path 가 매핑될 것임을 명시적으로 지정할 수 있다.    

정리하자면 코드는 다음과 같다. 

```java
@Entity
public class TourMap {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "TOUR_MAP_PATH", joinColumns = @JoinColumn(name = "TOUR_MAP_ID"))
    private List<Path> paths = new ArrayList<>();
}
```

```java
@Embeddable
@Access(AccessType.FIELD)
public class Path {

    @AttributeOverride(name = "name", column = @Column(name = "DEPATRURE"))
    private Location from;

    @AttributeOverride(name = "name", column = @Column(name = "DESTINATION"))
    private Location to;

    private double distance;
}
```

```java
@Entity
@ToString
public class Tour {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Tourist tourist;

    @ManyToOne
    @JoinColumn
    private TourPackage tourPackage;

    @ElementCollection
    @CollectionTable(name = "TOUR_PATH", joinColumns = @JoinColumn(name = "TOUR_ID"))
    private List<Path> paths = new ArrayList<>();
}
```

### ORM 의 동작 측면 

#### 작업 단위

ORM 은 데이터베이스에 영향을 미치는 모든 내역을 추적한 후 트랜잭션을 커밋(commit)할 때 자동으로 수정된 내역을 데이터베이스에 반영한다. 예를 들어 트랜잭션을 시작하고, 데이터베이스로부터 Tourist 객체를 로드한 후, Tourist 객체의 속성을 변경하고, 트랜잭션을 커밋하면 ORM 은 Tourist 의 상태가 변경됐다는 사실을 감지하고 자동으로 TOURIST 테이블을 업데이트한다. 이처럼 변경 내역을 추적하고 자동으로 데이터베이스에 반영하기 위해 ORM 에 구현된 컴포넌트를 작업 단위(Unit of Work)라고 부른다.

작업 단위는 현재 트랜잭션 안에서 수정된 객체들의 집합을 유지한다. 트랜잭션 안에서 수정된 객체들을 가리켜 더티(dirty) 상태에 있다고 말한다. ORM 은 트랜잭션이 커밋될 때 더티 상태에 있는 객체들을 식별한 후 수정된 내역을 반영할 쿼리를 자동으로 생성하고 실행한다. 이처럼 더티 상태에 있는 객체들을 자동으로 데이터베이스에 저장하는 기능을 자동 더티 체킹(automatic dirty checking)이라고 부른다.

ORM 은 동일 트랜잭션 내에서 동일한 객체들이 여러 번 로드되는 것을 방지하기 위해 식별자 맵(Identity Map)을 사용한다.식별자 맵을 데이터베이스의 기본키를 키로 저장하고 로드된 객체를 값으로 가지는 Map 이라고 생각해도 무방하다. ORM 은 객체를 로드하라는 요청을 받으면 데이터베이스로 요청을 보내기 전에 먼저 요청된 객체의 기본 키가 식별자 맵에 존재하는지 조사한다. 기본 키가 존재하지 않으면 데이터베이스로부터 객체를 로드한 후 식별자 맵에 기본 키와 객체를 추가한다.기본 키가 이미 존재하는 경우에는 데이터베이스에 요청을 보내지 않고 식별자 맵을 이용해서 이미 로드된 객체를 찾아 반환한다. 따라서 식별자 맵을 사용하면 객체들을 캐싱하는 효과를 얻을 수 있기 때문에 성능이 향상될 수 있고, ORM 레벨에서 반복 읽기(REPEATABLE READ) 수준의 트랜잭션 격리 레벨을 제공할 수 있다.

일반적으로 식별자 맵은 작업 단위 안에 위치한다. ORM 은 객체의 필드가 수정되거나 객체가 삭제되더라도 변경 내역을 즉시 데이터베이스에 반영하지 않는다. 트랜잭션 안에서 발생한 변경 내역을 추적한 후 트랜잭션이 커밋될 때 한번에 데이터베이스에 반영한다. 이를 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)이라고 부른다. 작업 단위는 트랜잭션 커밋 시점에 식별자 맵에 저장된 모든 객체들의 변경 상태를 확인한 후 외래 키 제약 조건을 위반하지 않도록 SQL 문을 생성한다.

JPA 에서 작업 단위의 역할은 영속성 컨텍스트(Persistence Context)가 수행한다. 식별자 맵 역시 영속성 컨텍스트 안에 위치한다. 영속성 컨텍스트는 JPA 에서 제공하는 EntityManager 라는 객체 안에 생성되며, 항상 그런 것은 아니지만 일반적으로 트랜잭션을 시작할 때 생성되고 트랜잭션을 커밋하거나 롤백할 때 제거된다. 일단 영속성 컨텍스트가 생성되면 그 이후로 EntityManager 를 통해 조회한 모든 객체들의 복사본이 영속성 컨텍스트 안의 식별자 맵에 저장된다. 객체를 수정하고 트랜잭션을 커밋하면 작업 단위 역할을 맡은 영속성 컨텍스트가 식별자 맵에 보관된 객체의 상태를 확인한 후 수정된 객체들을 식별하고 SQL 문을 생성해서 데이터베이스에 변경내역을 반영한다.


#### 지연 로딩 

ORM 의 입장에서 객체를 로드하라는 요청을 받았을 때 어떤 객체를 함께 로드해야 하는지 예상할 수 없다. 사용하지 않을 객체 그래프 전체를 메모리에 로딩하는 것은 비효율적이며 성능 관점에서도 문제가 발생할 수 밖에 없다. 따라서 ORM 은 최초에 필요한 객체만 로드하고 나머지 객체들은 연관 관계를 통해 접근되는 시점에 자동으로 로딩하는 기능을 지원하는데 이를 지연 로딩(Lazy Loading)이라고 부른다. 반대로 어떤 객체를 로드할 때 연관된 객체를 항상 함께 로드하는 것을 즉시 로딩(Eager Loading)이라고 부른다.

JPA 에서 지연 로딩과 즉시 로딩은 @OneToOne, @ManyToOne, @OneToMany, @ElementCollection 처럼 관계의 다중성을 표현하는 어노테이션의 fetch 속성으로 정의할 수 있다. fetch 속성의 값을 LAZY 로 지정하면 지연 로딩이 적용되고 EAGER 로 지정하면 즉시 로딩이 적용된다. @OneToOne, @ManyToOne 과 같이 대상 객체의 다중성이 1 인 경우에는 기본 값으로 EAGER 가 설정되어 있고, @OneToMany, @ElementCollection 과 같이 대상 객체의 다중성이 N 인 경우에는 기본 값으로 LAZY 가 설정되어 있다.


#### 영속성 전이 

지연 로딩과 즉시 로딩이 객체를 로드할 때의 범위를 제어하는데 반해, 객체가 저장될 때의 범위를 제어하는 것을 영속성 전이(transitive persistence)라고 부른다.영속성 전이란 작업 단위에 포함된 객체뿐만 아니라 해당 객체와 연결된 객체들까지 자동으로 영속 대상으로 포함시키는 특성을 말한다.

영속성 전이와 관련해서 알아두면 좋은 개념으로 도달 가능성에 의한 영속성(persistence by reachability)이 있다. 이 개념은 어떤 영속 객체로부터 도달 가능한 모든 객체에게 자동으로 영속성이 전이된다는 것을 의미한다. 쉽게 말해서 EntituManager 를 이용해서 Tour 객체를 저장하면 Tour 객체뿐만 아니라 Tour 를 통해 도달 가능한 Tourist, TourPackage, TourMap, TourPlanner 모두가 영속 가능한 상태를 가지게 된다는 것이다.

쉽게 예상할 수 있겠지만 ORM 은 도달 가능성에 의한 영속성을 완벽하게 지원하지는 않는다. 이 특성을 지원하려면 대상 객체로부터 시작하는 모든 연관관계를 추적해서 객체 그래프 전체에 걸쳐 영속성을 적용해야 하기 때문이다. 대신 영속성이 전파되는 범위를 제어할 수 있는 추가적인 매핑 설정을 제공한다. 따라서 작업 단위에 포함된 객체와 연관된 객체라고 해서 무조건 함께 저장되거나 삭제되지는 않는다는 사실에 주의해야 한다.

JPA 에서 영속성 전이는 @OneToOne, @ManyToOne, @OneToMany 와 같은 다중성을 정의하는 어노테이션에 cascade 속성으로 설정할 수 있다. 여행 애플리케이션에서는 TourPackage 를 조작하는 어떤 경우에도 TourPlanner 로 영속성 상태가 전이될 수 있도록 CascadeType.ALL 을 설정했다. 이 설정에 의해 TourPackage 를 저장하거나, 삭제하거나, 수정하면 자동으로 연관된 TourPlanner 역시 함께 저장되거나, 삭제하거나, 수정된다.


#### JPQL(Java Persistent Query Language)

JPQL 을 사용할 땐 지연 로딩과 즉시 로딩의 설정이 무시된다. 

JPQL 은 SQL 과 유사한 방식의 쿼리를 작성할 수 있는 표준 스펙이지만 데이터베이스 테이블을 대상으로 하는 SQL 과는 달리 객체를 대상으로 쿼리를 작성할 수 있다.


### 트랜잭션 경계 

도메인 관점에서 객체의 경계를 고려하지 않는 경우에는 테디터베이스 측면에서 트랜잭션 경합이 발생할 확률이 높아진다. 

TourService 의 Plan 메소드는 Tour 를 생성하고 TourPackage 의 상태를 수정한다고 생각해보자. 

따라서 Tour 테이블에 INSERT 쿼리가 나가고 이어서 TourPackage 에 UPDATE 쿼리가 나간다. 

여기서 새로운 기능이 추가되었고 TourPackage 의 상태를 수정하는 기능이 생겼다고 생각해보자. 

이 경우에 두 기능이 동시에 데이터베이스에 요청되면 트랜잭션 경계는 TourPackage 가 포함된다. 

이 경우 트랜잭션 격리 수준이 높거나 명시적으로 잠그는 경우에는 한 트랜잭션이 다른 트랜잭션을 기다려야 하는 경우가 생긴다. 

즉 한 트랜잭션이 처리하는 시간이 길어진다면 다른 트랜잭션의 경우에 타임아웃이 발생할 수 있다.

기능이 추가될 수록 개발자들은 현재의 요구사항에 적합한 객체들을 조회하고 연관관계를 따라 탐색하면서 객체들의 상태를 수정할 것이다. 

이런 경우에 데이터베이스에서 트랜잭션이 잠금을 점유하는 시간 등에 대한 고려가 없다면 트래픽이 늘어나는 순간 시스템은 붕괴될 수 있다. 

JPA 를 사용할 때는 객체의 연관관계를 통해 어떠한 객체라도 접근할 수 있기 때문에 객체 경계에 대해 인식하지 못할 수 있다. 타고타고 들어가면 어떤 객체던지 참조가 가능하기 떄문이다. 

여기에서 이야기하고 싶은 바는 JPA 가 다른 기술에 비해서 객체 참조를 통한 이동이 용이하기 때문에 상대적으로 문제가 발생할 확률이 높다는 점이다. 

요약하자면 트랜잭션 안에서 수정되어야 하는 객체의 경계를 결정하고 그 경계를 코드 안에서 명확하게 표현해야 한다는 점이다.   