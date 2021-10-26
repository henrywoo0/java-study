# Java

자바 웹 개발 프레임워크인 Spring을 배우기 전에, 자바 언어와 객체 지향 개념의 기본을 탄탄히 다지기 위해 공부했습니다.

아래 내용은 **Do it! 자바 프로그래밍 입문** 도서를 참고하며, 이해한 내용을 바탕으로 기록했습니다.

더욱 자세한 설명을 원하시면 위 책을 직접 읽어보시는 것을 추천드립니다.

---

- 자바는 1991년 제임스 고슬링을 비롯한 선 마이크로시스템스 연구원들이 처음 개발했다.
- 가전제품이나 휴대용 장치 등에 사용하는 소프트웨어를 만들기 위해 **독립적으로 작동하는 더 안정된 프로그래밍 언어**가 필요하여 자바가 만들어졌다.

아래는 자바의 특징을 나열한 것이다.

### 플랫폼에 종속되지 않는다

참고) 플랫폼(Platform)이란 프로그램이 실행되는 환경을 뜻한다.

- 자바에서는 .exe의 실행 파일 대신 .class의 확장자를 가진 바이트 코드를 생성
- 생성된 바이트 코드를 **자바 가상 머신**에서 실행하여, 각 운영체제에 맞는 실행 파일 생성
- 이러한 특성은 초반에 프로그램의 실행 속도를 저하시킨 원인이 되었지만, 자바 컴파일러가 JIT 컴파일 방식으로 개선되며 실행 속도가 개선됨

참고) JIT(Just In Time) 컴파일러는 실행 시점에 기계어 코드 생성, 같은 코드 반복일 경우 이전에 만든 기계어 재사용의 특징이 있다.

### 객체 지향 언어이다

- 객체 지향 프로그래밍이란 일의 순서가 아닌 **여러 객체의 협력(상호 관계)을 통해 프로그램을 구현하는 것**
- 공통으로 사용하는 부분을 수정하지 않고도 새 기능 쉽게 추가 가능
- **유지보수**가 쉽고 **확장성**이 좋은 프로그램이 될 수 있음

### 프로그램이 안정적이다

- 자바는 포인터 문법을 제공하지 않아 메모리를 직접 제어하지 않음. 그에 따라 오류 위험성을 줄임
- Garbage Collector를 이용해 동적 메모리를 수거하므로 효율적인 메모리 관리가 가능함

### 오픈 소스이다

- **JDK(Java Development Kit)**를 가지고 있어 프로그램을 빠르게 개발할 수 있음
- 자바를 활용한 오픈 소스가 많이 개발되어 있기 때문에 그 소스들을 연동하여 더 풍부한 기능을 빠르게 구현할 수 있음 

참고) JDK는 클래스, 자료 구조, 네트워크, 입출력, 예외 처리 등에 최적화된 알고리즘 라이브러리를 제공

---

## 자바 기초

### 변수명 정하기

- 변수명은 사용 목적에 맞게 의미를 부여하여 만들어야 한다.
- 영문자(대소문자)와 숫자를 사용할 수 있고, 특수문자는 $, _ 등만 사용할 수 있다.
- 숫자로 시작할 수 없다.
- 자바에서 이미 사용 중인 예약어는 사용할 수 없다.
- **Camel Notation**이란 중간에 다른 뜻의 단어가 등장하면 첫 글자를 대문자로 사용하는 것이다. (numberOfStudent, isGood)

### 자바 자료형

- 자바에서 제공하는 자료형에는 기본 자료형과 참조 자료형이 있음
- 참조 자료형은 클래스형임

아래는 기본 자료형이다.
- 정수형 : byte(1byte), short(2byte), **int(4byte)**, long(8byte)
- 실수형 : float(4byte), double(8byte)
- 문자형 : char(2byte)
- 논리형 : boolean(1byte)

아래는 자료형의 특징이다.
- long 형은 숫자 뒤에 L, l의 식별자를 붙여야 한다. 또한 float형은 F, f를 붙여야 함
- 컴퓨터 내부에서 문자를 표현할 때 특정 정수 값으로 정한다. 코드 값을 모아둔 것을 '문자 세트', 문자를 코드 값으로 변환하는 것을 '문자 인코딩', 그 반대를 '문자 디코딩'이라고 함
- 자바는 유니코드(unicode)에 기반하여 문자를 표현하기 때문에, char형은 2byte를 사용

### 상수

- 상수(constant)는 항상 변하지 않는 값임
- 값 초기화 후에 다시 값 변경 불가
- 상수를 선언할 때 자료형 앞에 final을 붙임
- 이름은 주로 대문자를 사용하고, _ 기호를 사용해 여러 단어를 연결함

### 리터럴

- 프로그램에서 사용하는 모든 숫자, 문자, 논리값을 일컫는 말
- 프로그램이 시작할 때 시스템에 같이 로딩되어 constant pool에 놓임
- 이 리터럴은 변수나 상수 값으로 대입할 수 있음







