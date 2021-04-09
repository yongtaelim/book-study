## Chapter2
기본 개념을 소개한다.
```
- 데이터의 기본 단위는 도큐먼트다. 
- 컬렉션은 동적 스키마가 없는 테이블과 같다
- 단일 인스턴스는 자체적인 컬렉션을 갖는 여러 개의 독립적인 데이터베이스를 호스팅한다.
- 모든 도큐먼트는 컬렉션 내에서 고유한 특수키인 "_id"를 가진다.
- 몽고 셀이라는 간단하지만 강력한 도구와 함께 배포된다. 몽고 셀은 모고디비 인스턴스를 관리하고 몽고디비 쿼리 언어로 데이터를 조작하기 위한 내장 지원을 제공한다.
```

### 도큐먼트
- 정렬된 키와 연결된 값의 집합으로 이뤄진 도큐먼트.
```
{"count" : 5}
{"count" : "5"}

// 대소문자 구분
{"count" : 5}
{"Count" : 5}

// 같은 키는 불가
{"greeting" : "Hello", "greeting" : "World"}
```

### 컬렉션
- 도큐먼트의 모음

#### 컬렉션은 동적 스키마를 갖는다.
- 도큐먼트들의 키, 키의 개수, 데이터형의 값은 모두 다르다.
```
{"greeting" : "Hello", "view" : 3}
{"signoff" : "Good"}
```
그렇다면 왜 컬렉션이 필요할까?
- 같은 컬렉션에 다른 종류의 도큐먼트를 저장하면 개발자와 관리자에게 번거로운 일이 발생한다.
- 컬렉션별로 목록을 뽑으면 한 컬렉션 내 특정 데이터형별로 쿼리해서 목록을 뽑을 때보다 훨씬 빠르다.
- 같은 종류의 데이터를 하나의 컬렉션에 모다우면 데이터 지역성에 좋다.
- 인덱스를 만들면 도큐먼트는 특정 구조를 가져야한다. 이러한 인덱스는 컬렉션별로 정의한다.

### 셀
- 셀은 관리 기능이나, 실행 중인 인스턴스를 점검하거나 간단한 기능을 시험하는 데 매우 유용.

### 몽고DB 클라이언트
- db : database 확인
- use video : database 선택

### 셀 기본 작업
#### 생성

##### insertOne 함수는 컬렉션에 도큐먼트를 추가한다.
```
// movie 지역변수 생성
movie = 
{
    "title" : "Star Wars: kkkkkk Movie",
    "director" : "Gerod laus",
    "year" : 1977
}

// moviews 컬렉션에 저장할 수 있다.
db.movies.insertOne(movie)

// 조회
db.movies.find().pretty()
{
	"_id" : ObjectId("606d5809852386250330a6fe"),
	"title" : "Star Wars: kkkkkk Movie",
	"director" : "Gerod laus",
	"year" : 1977
}
```

##### order
````
/error 
db.movies.insertMany(
    [
        {"_id": 0, "title" : "Diejdlsdd"},
        {"_id": 1, "title" : "E.T."},
        {"_id": 1, "title" : "E.T.2"},
        {"_id": 2, "title" : "Runner"}
    ]
);
````

#### 읽기
find와 findOne은 컬렉션을 쿼리하는 데 사용
```
// 단건 조
db.movies.findOne()
{
	"_id" : ObjectId("606d5809852386250330a6fe"),
	"title" : "Star Wars: kkkkkk Movie",
	"director" : "Gerod laus",
	"year" : 1977
}
```

#### 갱신
updateOne을 사용
```
// update
> db.movies.updateOne( {"title" : "Star Wars: kkkkkk Movie"},
... {$set : {reviews: []}})
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 1 }

// 확인
db.movies.find().pretty()
{
	"_id"             : ObjectId("606d59c263f269c1ddc2505a"),
	"title" : "Star Wars: kkkkkk Movie",
	"director" : "Gerod laus",
	"year" : 1977,
	"reviews" : [ ]
}
```

#### Document Drop
deleteMany보다는 drop이 빠르다
```
db.movies.deleteMany({})  <<<  db.movies.drop()
```