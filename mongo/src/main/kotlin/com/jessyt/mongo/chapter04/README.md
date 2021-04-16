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

// response is empty
// 완벽히 일치해야하지만 "comment"를 넣지 않아서 안된다.
db.d.find({"comments": {"author" : "joe", "score" : {"$gte" : 1}}})

// 모든 키를 지정하지 않고도 조건을 정확하게 묶으려면 $elemMatch를 사용한다.
db.d.find({"comments" : {"$elemMatch" : {"author" : "joe", "score" : {"$gte": 3}}}}).pretty()
{
	"_id" : ObjectId("606ed486db3450d20a0cb7b1"),
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
```

### $where
키/값 쌍만으로 꽤 다양한 쿼리를 할 수 있지만 정확하게 표현할 수 없는 쿼리도 있다. 이때 "$where" 절을 사용해 임의의 자바스크립트를 쿼리의 일부분으로 실행하면 모든 쿼리를 표현할 수 있다. 따라서 보안상의 이유로 제한해야한다.   
$where 절은 도큐먼트 내 두 키의 값을 비교하는 쿼리에 가장 자주 쓰인다.  
$where 절은 일반쿼리보다 훨씬 느리다. 반드시 필요한 경우가 아니라면 사용하지 말자. 각 도큐먼트는 BSON에서 자바스크립트 객체로 변환되기 때문에 오래걸린다. 또한 "$where"절에는 인덱스를 쓸 수 없다. 따라서 "$where" 절은 달리 쿼리할 방법이 전혀 없을 때만 사용한다. 
"$where" 절은 다른 쿼리 필터와 함께 사용하면 성능 저하를 줄일 수 있따. 가능한 한 $where절이 아닌 조건은 인덱스로 거르고 세부적으로 조정할때만 $where절을 사용하자.
쿼리 언어로 집계 표현식을 사용할 수 있도록 $expr 연산자가 추가되었다. $expr를 사용하면 자바스크립트를 실행하지 않아 더 빨리 쿼리할 수 있으므로 가능한 한 $where 대신 $expr을 사용하자
```
db.e.insertOne({"apple" : 1, "banana" : 6, "peach" : 3})
db.e.insertOne({"apple" : 8, "spinach" : 4, "melon" : 4})

db.e.find({$where: function() {
    for (var current in this) {
        for (var other in this) {
            if (current != other && this[current] == this[other]) {
                return this;
            }
        }
    }
    return false;
}});

{ "_id" : ObjectId("606fbcc0bf7c7afe2ac7e62a"), "apple" : 8, "spinach" : 4, "melon" : 4 }
```

### 커서
결과 개수를 제한하거나 결과 중 몇개를 건너뛰거나, 여러 키를 조합한 결과를 어떤 방향으로든 정렬하는 등 다양하게 조작할 수 있다.
```
for ( i=0; i< 100; i++) {
    db.f.insertOne({"x" : i})
}

var cursor = db.f.find()
> var cursor = db.f.find()
> cursor
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e62b"), "x" : 0 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e62c"), "x" : 1 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e62d"), "x" : 2 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e62e"), "x" : 3 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e62f"), "x" : 4 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e630"), "x" : 5 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e631"), "x" : 6 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e632"), "x" : 7 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e633"), "x" : 8 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e634"), "x" : 9 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e635"), "x" : 10 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e636"), "x" : 11 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e637"), "x" : 12 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e638"), "x" : 13 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e639"), "x" : 14 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e63a"), "x" : 15 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e63b"), "x" : 16 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e63c"), "x" : 17 }
{ "_id" : ObjectId("606fc2acbf7c7afe2ac7e63d"), "x" : 18 }
{ "_id" : ObjectId("606fc2adbf7c7afe2ac7e63e"), "x" : 19 }
Type "it" for more
> cursor.next()
{ "_id" : ObjectId("606fc2adbf7c7afe2ac7e63f"), "x" : 20 }
> cursor.next()
{ "_id" : ObjectId("606fc2adbf7c7afe2ac7e640"), "x" : 21 }
> cursor.next()
{ "_id" : ObjectId("606fc2adbf7c7afe2ac7e641"), "x" : 22 }
```

이처럼 커서기능을 사용하면 수많은 결과중 하나씩 볼 수 있다는 장점이 있다.
```
while (cursor.hasNext()) {
    obj = cursor.next()
    do_something(obj)
}

// 모두 같음
var cursor = db.f.find().sort({"x" : 1}).limit(1).skip(10);
var cursor = db.f.find().limit(1).sort({"x" : 1}).skip(10);
var cursor = db.f.find().skip(10).limit(1).sort({"x" : 1});
// 쿼리는 아직 수행되지 않는다. 쿼리를 만들기만 했을 뿐..
```

### Skip을 사용하지 않고 페이지 나누기
`criteria는 해당하는 Document들을 선택하여 cursor를 반환한다. cursor는 query 요청의 결과값을 가르키는 pointer이다.`

```
// skip의 개수가 많아질 수록 느려진다. 피해야한다.
db.f.find(criteria).limit(100)
db.f.find(criteria).skip(100).limit(100)
db.f.find(criteria).skip(200).limit(100)

var page1 = db.g.find().sort({"date" : -1}).limit(100)

var latest = null
while (page1.hasNext()) {
    latest = page1.next();
    display(latest)
}

// next page
var page2 = db.g.find().sort({"$lt" : latest.date});
page2.sort({"date" : 01}_.limit(100);
```

### 종료되지 않는 커서
클라이언트가 보는 커서와 클라이언트 커서가 나타내는 데이터베이스 커서 2가지가 있다.

서버 측에서 보면 커서는 메모리와 리소르를 점유한다. 커서가 더는 가져올 결과가 없거나 클라이언트로부터 종료 요청을 받으면 데이터베이스는 점유하고 있던 리소스를 해제한다. 그러면 데이터베이스가 리소스를 다른 작업에 사용할 수 있으므로 커서도 신속하게 해제해야한다.

서버 커서를 종료하는 몇 가지 조건이 있다. 
1. 커서는 조건에 일치하는 결과를 모두 살펴본 후에는 스스로 정리한다. 
2. 커서가 클라이언트 측에서 유효 영역을 벗어나면 드라이버는 데이터베이스에 메시지를 보내 커서를 종료해도 된다고 알린다. 
3. 사용자가 아직 결과를 다 살펴보지 않았고 커서가 여전히 유효 영역 내에 있더라도 10분 동안 활동이 없으면 데이터베이스 커서는 자동으로 죽는다. 따라서 클라이언트가 충돌하거나 버그가 있어도 몽고DB에 커서가 수천 개가 열릴일은 없다. 


