# Chapter04
## find
```
db.users.find()
db.users.find({})
db.users.find({"age": 27})
db.users.find({"name": "joe", "age": 27})
```

### 반환받을 키 지정
모든 데이터를 항상 가져오면 네트워크상의 데이터 전송량과 클라이언트 ㅋ측에서 도큐먼트를 디코딩하는 데 드는 시간과 메모리를 줄여준다.
db.users.find()를 하면 항상 _id는 반환된다. 만약 키를 쓰지 않는다면 fatal_weakness를 선언한다.
```
> db.users.find()
{ "_id" : ObjectId("606d61ac67fe246be00de3ba"), "enemies" : 2, "relationships" : { "friends" : 32, "enemies" : 2 }, "username" : "joe" }
{ "_id" : ObjectId("606d6295290897f1f912c15e"), "name" : "joe", "friends" : 32, "enemies" : 2 }
{ "_id" : ObjectId("606d65d6290897f1f912c160"), "name" : "joe", "age" : 30, "sex" : "male", "location" : "asdf", "favorite book" : "War" }
{ "_id" : ObjectId("606d666b290897f1f912c161"), "name" : "joe", "age" : 30, "sex" : "male", "location" : "asdf" }

> db.users.find({}, {"name" : 1, "_id":0})
{  }
{ "name" : "joe" }
{ "name" : "joe" }
{ "name" : "joe" }
```

### 제약 사항
데이터베이스에서 쿼리 도큐먼트 값은 반드시 상수여야한다. 도큐먼트 내 다른 키의 값을 참조할 수 없음을 의미한다.  
단편적인 예를 들어본다면 재고 도큐먼트에 재고 수량 키 "in_stock"과 판매 수량 키 "num_sold"가 있으면 키 값을 다음과 같은 쿼리로 비교한다.
```
db.stock.find({"in_stock" : "this.num_sold"}) // 작동 X
```

일반 쿼리로 처리할 수 있게 도큐먼트 구조를 재구성하여 나은 성능을 얻어보자. 누군가가 상품을 구매할 때마다 "in_stock"값을 감소시키면 품절 상품을 확인하는 쿼리는 다음과 같다.
```
db.stock.find({"in_stock" : 0})
```

## 쿼리 조건
쿼리는 완전일치(exact match), 범위, OR절, 부정조건(negation) 등 복잡한 조건으로 검색이 가능하다.

### 쿼리 조건절
간단한 조건절은 예시만 작성하고 넘어가자
```
db.users.find({"age" : {"$gte" : 18, "lte" : 30}})  // 18 < age < 30
db.users.find({"age" : {"$ne" : 18}})  // age != 18 
db.users.find({"age" : {"$in" : [18, 19, 20, 21]}})  // age in [18,19,20,21]
db.users.find({"age" : {"$nin" : [18, 19, 20, 21]}})  // age nin [18,19,20,21]
db.users.find({"$or" : [{"age" : 18}, {"name" : "Joe"}])  // age == 18 or name == "Joe"
db.users.find({"$or" : [{"age" : ["$in" : [18,19,20]]}, {"name" : "Joe"}])  // age in [18,19,20] or name == "Joe"  
db.users.find({"age" : {"$mod" : [5, 1]}})  // result : 1, 6, 11, 16, 21, 26...
db.users.find({"age" : {"$not" : {"$mod" : [5, 1]}}})  // result : 1, 6, 11, 16, 21, 26가 아닌 값
```

### 형특정 쿼리 
#### null
```
// insert test data
> db.c.insertMany([{"Y" : 1},{"Y" : 2},{"Y" : null}])
{
	"acknowledged" : true,
	"insertedIds" : [
		ObjectId("606ec5ffdb3450d20a0cb7a6"),
		ObjectId("606ec5ffdb3450d20a0cb7a7"),
		ObjectId("606ec5ffdb3450d20a0cb7a8")
	]
}

// 확인
> db.c.find()
{ "_id" : ObjectId("606ec5ffdb3450d20a0cb7a6"), "Y" : 1 }
{ "_id" : ObjectId("606ec5ffdb3450d20a0cb7a7"), "Y" : 2 }
{ "_id" : ObjectId("606ec5ffdb3450d20a0cb7a8"), "Y" : null }

// Y가 null인 것을 찾으면 원하는 값이 나옴
> db.c.find({"Y" : null})
{ "_id" : ObjectId("606ec5ffdb3450d20a0cb7a8"), "Y" : null }

// Z가 없지만 null을 찾으면 전부 다 나옴. null은 존재하지 않는 것과도 동일
> db.c.find({"Z" : null})
{ "_id" : ObjectId("606ec5ffdb3450d20a0cb7a6"), "Y" : 1 }
{ "_id" : ObjectId("606ec5ffdb3450d20a0cb7a7"), "Y" : 2 }
{ "_id" : ObjectId("606ec5ffdb3450d20a0cb7a8"), "Y" : null }

// 내가 생각하면 값을 얻으려면 $exists 사용
> db.c.find({"Z" : {"$eq" : null, "$exists" : true}})
```

#### 배열에 쿼리
```
> db.food.insertOne({"fruit" : ["apple", "banana", "peach"] })
{
	"acknowledged" : true,
	"insertedId" : ObjectId("606ec7a4db3450d20a0cb7a9")
}
> db.food.find({"fruit" : "banana"})
{ "_id" : ObjectId("606ec7a4db3450d20a0cb7a9"), "fruit" : [ "apple", "banana", "peach" ] }
```

$all 연산자
배열의 순서와 상관없이 조회할 떄 사용된다.
```
db.food.insertOne({"fruit" : ["apple", "peach", "banana"] })
db.food.insertOne({"fruit" : ["peach", "banana", "apple"] })

db.food.find({"fruit" : {"$all" : ["apple", "banana"]}})
{ "_id" : ObjectId("606ec7a4db3450d20a0cb7a9"), "fruit" : [ "apple", "banana", "peach" ] }
{ "_id" : ObjectId("606ec884db3450d20a0cb7aa"), "fruit" : [ "apple", "peach", "banana" ] }
{ "_id" : ObjectId("606ec884db3450d20a0cb7ab"), "fruit" : [ "peach", "banana", "apple" ] }

db.food.find({"fruit" : ["apple", "banana"]})  // 결과값은 나오지 않는다.
```

$size 연산자
```
db.food.find({"fruit" : {"$size" : 3}})
{ "_id" : ObjectId("606ec7a4db3450d20a0cb7a9"), "fruit" : [ "apple", "banana", "peach" ] }
{ "_id" : ObjectId("606ec884db3450d20a0cb7aa"), "fruit" : [ "apple", "peach", "banana" ] }
{ "_id" : ObjectId("606ec884db3450d20a0cb7ab"), "fruit" : [ "peach", "banana", "apple" ] }
```

$slice 연산자

```
> db.blogs.posts.findOne()
{
	"_id" : ObjectId("606e90a97b290c0589af6c9d"),
	"content" : "...",
	"comments" : [
		{
			"comment" : "good!",
			"author" : "Jim",
			"votes" : 1
		},
		{
			"comment" : "too short!",
			"author" : "Claire",
			"votes" : 3
		},
		{
			"comment" : "free",
			"author" : "Alice",
			"votes" : -5,
			"hidden" : true
		},
		{
			"comment" : "vacation",
			"author" : "Lynn",
			"votes" : -7,
			"hidden" : true
		}
	]
}

> db.blogs.posts.findOne({}, {"comments" : {"$slice" : 2}})
{
	"_id" : ObjectId("606e90a97b290c0589af6c9d"),
	"content" : "...",
	"comments" : [
		{
			"comment" : "good!",
			"author" : "Jim",
			"votes" : 1
		},
		{
			"comment" : "too short!",
			"author" : "Claire",
			"votes" : 3
		}
	]
}
> db.blogs.posts.findOne({}, {"comments" : {"$slice" : -2}})
{
	"_id" : ObjectId("606e90a97b290c0589af6c9d"),
	"content" : "...",
	"comments" : [
		{
			"comment" : "free",
			"author" : "Alice",
			"votes" : -5,
			"hidden" : true
		},
		{
			"comment" : "vacation",
			"author" : "Lynn",
			"votes" : -7,
			"hidden" : true
		}
	]
}

> db.blogs.posts.findOne({}, {"comments" : {"$slice" : [1,3]}})
{
	"_id" : ObjectId("606e90a97b290c0589af6c9d"),
	"content" : "...",
	"comments" : [
		{
			"comment" : "too short!",
			"author" : "Claire",
			"votes" : 3
		},
		{
			"comment" : "free",
			"author" : "Alice",
			"votes" : -5,
			"hidden" : true
		},
		{
			"comment" : "vacation",
			"author" : "Lynn",
			"votes" : -7,
			"hidden" : true
		}
	]
}
```

일치하는 배열 요소의 반환
원하는 조건에 해당하는 첫번쨰 데이터를 가져오고 싶다!
```
db.blogs.posts.findOne({"comments.author" : "Alice"}, {"comments.$" : 1})
{
	"_id" : ObjectId("606e90a97b290c0589af6c9d"),
	"comments" : [
		{
			"comment" : "free",
			"author" : "Alice",
			"votes" : -5,
			"hidden" : true
		}
	]
}
```

배열 및 범위 쿼리의 상호작용
```
// 요론게 있다.
 db.a.find()
{ "_id" : ObjectId("606ed156db3450d20a0cb7ac"), "x" : 5 }
{ "_id" : ObjectId("606ed159db3450d20a0cb7ad"), "x" : 15 }
{ "_id" : ObjectId("606ed15adb3450d20a0cb7ae"), "x" : 25 }
{ "_id" : ObjectId("606ed15edb3450d20a0cb7af"), "x" : [ 5, 25 ] }

// 아래와 같이 조회하는 예상하는 것과 다르다. 배열같은 경우는 각 각 비교한다. 
> db.a.find({"x" : {"$gt" : 10, "$lt" : 20}})
{ "_id" : ObjectId("606ed159db3450d20a0cb7ad"), "x" : 15 }
{ "_id" : ObjectId("606ed15edb3450d20a0cb7af"), "x" : [ 5, 25 ] }

// elemMatch를 쓰면 된.. 안된다. 배열만 검색한다.
> db.a.find({"x" : {"$elemMatch" : {"$gt" : 10, "$lt" : 20}}})

// min, max를 사용하자.
db.a.find({"x" : {"$gt" : 10, "$lt" : 20}}).min({"x" : 10}).max({"x" : 20})
```
결론 배열을 검색할때는 $gt $lt는 쓰지말자.

내장 도큐먼트에 쿼리
```
db.b.findOne()
{
	"_id" : ObjectId("606ed322db3450d20a0cb7b0"),
	"name" : {
		"first" : "Joe",
		"last" : "Schmoe"
	},
	"age" : 45
}

> db.b.find({"name" : {"first" : "Joe", "last" : "Schmoe"}})
{ "_id" : ObjectId("606ed322db3450d20a0cb7b0"), "name" : { "first" : "Joe", "last" : "Schmoe" }, "age" : 45 }

> db.b.find({"name" : {"last" : "Schmoe", "first" : "Joe"}})

> db.b.find({"name.last" : "Schmoe", "name.first" : "Joe"})
{ "_id" : ObjectId("606ed322db3450d20a0cb7b0"), "name" : { "first" : "Joe", "last" : "Schmoe" }, "age" : 45 }
```
. 표기법을 사용하자.

```
db.d.insertOne(
    {
        "context" : "...", 
        "comments" : [
            {
                "author" : "joe",
                "score" : 3,
                "comment" : "nice post"
            }, 
            {
                "author" : "mary",
                "score" : 6,
                "comment" : "terrible post"
            }
        ]
    }
)
```
