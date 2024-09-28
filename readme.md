# 상품 및 브랜드 가격 관리 API

이 프로젝트는 REST API를 통해 브랜드와 상품을 관리하고, 카테고리별로 가격 정보를 조회할 수 있는 기능을 제공합니다. 브랜드와 상품의 추가, 수정, 삭제 작업을 지원하며, 카테고리별로 최저가 및 가격 범위를 조회할 수 있습니다.

## 구현 기능

1. **브랜드 관리**:브랜드 추가, 수정, 삭제 기능 제공
2. **상품 관리**: 브랜드와 연관된 상품 추가, 수정, 삭제 기능 제공
3. **카테고리 가격 조회**:
    * 모든 카테고리에서 가장 저렴한 상품 가격 조회
    * 특정 카테고리의 최소 및 최대 가격 범위 조회
4. 모든 카테고리의 상품 가격 총합이 가장 낮은 브랜드 조회

## 엔드포인트

### 브랜드 관련 엔드포인트

* **GET**`/v1/brands/all/lowest`: 모든 카테고리의 상품 가격 총합이 가장 낮은 브랜드 조회
* **POST**`/v1/brands/new`: 새로운 브랜드 추가
* **POST**`/v1/brands/{id}`: 특정 브랜드 수정 (ID로 식별)
* **DELETE**`/v1/brands/{id}`: 특정 브랜드 삭제 (ID로 식별)

### 카테고리 관련 엔드포인트

* **GET**`/v1/categories/lowest`: 모든 카테고리에서 가장 저렴한 상품 가격 조회
* **GET**`/v1/categories/{categoryName}/range`: 특정 카테고리의 최소 및 최대 가격 범위 조회

### 상품 관련 엔드포인트

* **PUT**`/v1/products/new`: 새로운 상품 추가
* **POST**`/v1/products/{id}`: 특정 상품 수정 (ID로 식별)
* **DELETE**`/v1/products/{id}`: 특정 상품 삭제 (ID로 식별)

## 빌드, 테스트, 실행 방법

### 사전 요구 사항

* Java 21+
* Spring Boot 3.2


### IntelliJ 기준 세팅 방법

1. File | Settings | Build, Execution, Deployment | Build Tools | Gradle
   Gradle JVM -> Eclipse Teumrin JDK 21 설정
2. File | Settings | Build, Execution, Deployment | Compiler | Java Compiler
   Project Byte Code version -> 21 설정
3. File | Settings | Build, Execution, Deployment | Compiler | Annotation Processors
   Enable Annotation Processing 체크
4. Files | Project Structure | Project
   SDK -> 1에서 설정한 JDK 설정
   Language Level -> default 혹은 21 설정

### IntelliJ 기준 실행 방법

- java/com/shop/display/DisplayApplication.java Run
- 터미널에서 ./gradlew bootRun


### IntelliJ 기준 테스트 방법

- 테스트 파일 / 테스트 패키지 오른쪽 클릭 -> Run Tests
  [](https://)
- 통합테스트 실행 : 터미널에서 ./gradlew integrationTest
