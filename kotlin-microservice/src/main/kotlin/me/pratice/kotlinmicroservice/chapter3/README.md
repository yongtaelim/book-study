#api 예시

### 단일 리소스
```
get             200 OK              
get             404 NOT FOUND           조회 시 데이터 없을 경우
post            201 CREATED
post            400 BAD REQUEST         생성 요청 시 데이터가 올바르지 않음
put             202 ACCEPTED
put             404 NOT FOUND           고객 정보 업데이트 요청했으나 고객 정보를 찾을 수 없다
delete          200 OK
delete          404 NOT FOUND           고객 정보 삭제를 요청했으나 고객 정보를 찾을 수 없음
```

### Collection
```
get             200 OK                  
get             204 NO CONTENT          고객 목록을 요청했으나 존재하지 않음
get     ?name=  204 NO CONTENT          필터링된 고객 정보가 없다
get     ?name=  400 BAD REQUEST         필터링을 잘못 보낼 경우
```

### Basic
```
                400 BAD REQUEST             요청이 잘못되었을 경우
                401 UNAUTHORIZED            해당 작업에 대한 자격 증명이 없다
                403 FORBIDDEN               자격 증명이 있을 수 있지만 해당 작업을 수행할 수 없다
                422 UNPROCESSABLE ENTITY    요청을 처리할 수 없음. 올바른 요청일 수 있으나, 해당 작업에는 유요하지 않다.
                500 INTERNAL SERVER ERROR   요청을 처리할 수 없다.
```

## field에 null값을 직렬화하고 싶지 않을 경우

`@JsonInclude(Include.NON_NULL)` 설정으로 null인 필드값은 직렬화하지 않을 수 있다. or application.yml로도 설정가능
spring.jackson.default-property-inclusion: NON_NULL


