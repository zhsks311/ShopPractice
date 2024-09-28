# 상품 및 브랜드 가격 관리 API

이 프로젝트는 REST API를 통해 브랜드와 상품을 관리하고, 카테고리별로 가격 정보를 조회할 수 있는 기능을 제공합니다. 브랜드와 상품의 추가, 수정, 삭제 작업을 지원하며, 카테고리별로 최저가 및 가격 범위를 조회할 수 있습니다.

## 구현 기능

1. 브랜드 관리:브랜드 추가, 수정, 삭제 기능 제공
2. 상품 관리: 브랜드와 연관된 상품 추가, 수정, 삭제 기능 제공
3. 카테고리 가격 조회:
   * 모든 카테고리에서 가장 저렴한 상품 가격 조회
   * 특정 카테고리의 최소 및 최대 가격 범위 조회
4. 모든 카테고리의 상품 가격 총합이 가장 낮은 브랜드 조회

## 엔드포인트

### 브랜드 관련 엔드포인트

* **GET**`/v1/brands/all/lowest`: 모든 카테고리의 상품 가격 총합이 가장 낮은 브랜드 조회
  * ```
    curl --location "http://localhost:8080/v1/brands/all/lowest"
    ```
* **PUT**`/v1/brands/new`: 새로운 브랜드 추가
  * ```
    curl --location --request PUT "http://localhost:8080/v1/brands/new" \
    --header 'Content-Type: application/json' \
    --data '{
        "name" : "AAAAA"
    }'
    ```
* **POST**`/v1/brands/{id}`: 특정 브랜드 수정 (ID로 식별)
  * ```
    curl --location "http://localhost:8080/v1/brands/1" \
    --header 'Content-Type: application/json' \
    --data '{
        "name" : "AAAAA"
    }'
    ```
* **DELETE**`/v1/brands/{id}`: 특정 브랜드 삭제 (ID로 식별)
  * ```
    curl --location --request DELETE 'http://localhost:8080/v1/brands/11' \
    --header 'Content-Type: application/json'
    ```

### 카테고리 관련 엔드포인트

* **GET**`/v1/categories/lowest`: 모든 카테고리에서 가장 저렴한 상품 가격 조회
  * ```
    curl --location GET "http://localhost:8080/v1/categories/lowest"
    ```
* **GET**`/v1/categories/{categoryName}/range`: 특정 카테고리의 최소 및 최대 가격 범위 조회
  * ```
    curl --location "http://localhost:8080/v1/categories/TOPS/range"
    ```

### 상품 관련 엔드포인트

* **PUT**`/v1/products/new`: 새로운 상품 추가
  * ```
    curl --location --request PUT "http://localhost:8080/v1/products/new" \
    --header 'Content-Type: application/json' \
    --data '{
        "brand": "A",
        "category":"TOPS",
        "price" : 213
    }'
    ```
* **POST**`/v1/products/{id}`: 특정 상품 수정 (ID로 식별)
  * ```
    curl --location "http://localhost:8080/v1/products/1" \
    --header 'Content-Type: application/json' \
    --data '{
        "brand": "A",
        "category":"TOPS",
        "price" : 213
    }'
    ```
* **DELETE**`/v1/products/{id}`: 특정 상품 삭제 (ID로 식별)
  * ```
    curl --location --request DELETE "http://localhost:8080/v1/products/1"
    ```

## 빌드, 테스트, 실행 방법

### 사전 요구 사항

* Java 21+
* Spring Boot 3.3.4

### IntelliJ 기준 세팅 방법

1. File | Settings | Build, Execution, Deployment | Build Tools | Gradle
   * Gradle JVM -> Eclipse Teumrin JDK 21 설정
2. File | Settings | Build, Execution, Deployment | Compiler | Java Compiler
   * Project Byte Code version -> 21 설정
3. File | Settings | Build, Execution, Deployment | Compiler | Annotation Processors
   * Enable Annotation Processing 체크
4. Files | Project Structure | Project
   * SDK -> 1에서 설정한 JDK 설정
   * Language Level -> default 혹은 21 설정

### IntelliJ 기준 실행 방법

- java/com/shop/display/DisplayApplication.java Run
- 터미널에서 ./gradlew bootRun

### IntelliJ 기준 테스트 방법

- 테스트 파일 / 테스트 패키지 오른쪽 클릭 -> Run Tests
- 터미널에서
  - 테스트 실행 : ./gradlew test
  - 통합테스트 실행 : ./gradlew integrationTest

## 추가정보

### 서비스 관련

* **데이터 유효성 검사**:
  * `@Valid` 어노테이션을 통해 입력 데이터를 유효성 검사하며, 올바른 요청인지 확인합니다.
  * 잘못된 입력에 대해 맞춤형 오류 메시지가 반환됩니다. 예: 잘못된 카테고리 이름 또는 필수 필드 누락.
* **오류 처리**:
  * 존재하지 않는 ID 또는 유효하지 않은 카테고리 이름 등의 요청에 대해 적절한 오류 처리를 제공합니다.
  * 로그 시스템을 넣지 않았기 때문에 오류 제공 시 50자까지만 메시지 노출토록 해 시스템 내부 유출을 최소화했습니다.
* **서비스 레이어**:
  * `LowestPriceService`와 `PriceSearchService`는 브랜드 및 카테고리별 가격 정보를 조회하는 비즈니스 로직을 처리합니다.
  * `ProductService`와 `BrandService`는 상품과 브랜드 데이터를[](https://) 생성, 수정, 삭제하는 작업을 담당합니다.

### TODO

- 데이터베이스 비밀번호 설정 및 암호화
- 부족한 테스트 추가
- Swagger 등 Open Api 추가
- 테이블 Index 추가
- 각 클래스, 메서드, 변수에 더 적절한 이름 명명
- 로그 시스템 추가
- 에러 메시지 통한 내부 유추 방지
