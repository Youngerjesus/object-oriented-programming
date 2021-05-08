package com.example.demo.law_of_demeter;

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
