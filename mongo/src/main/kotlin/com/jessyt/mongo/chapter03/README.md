## Chapter03
### 도큐먼트 삽입
여러 도큐먼트를 컬렉션에 삽입하려면 insertMany를 사용한다.
```
// insert
db.movies.insertMany(
    [
        {"title" : "Diejdlsdd"},
        {"title" : "E.T."},
        {"title" : "Runner"}
    ]
);
    
// find 
db.movies.find()
{ "_id" : ObjectId("606d5ce467fe246be00de3b7"), "title" : "Diejdlsdd" }
{ "_id" : ObjectId("606d5ce467fe246be00de3b8"), "title" : "E.T." }
{ "_id" : ObjectId("606d5ce467fe246be00de3b9"), "title" : "Runner" }
   
```



#### 삭제
deleteOne을 사용하자. 검색 조건에 여러개가 걸려도 1개만 삭제된다.
```
// find
db.movies.find()
{ "_id" : ObjectId("606d59c263f269c1ddc2505a"), "title" : "Star Wars: kkkkkk Movie", "director" : "Gerod laus", "year" : 1977, "reviews" : [ ] }
{ "_id" : ObjectId("606d5ce467fe246be00de3b7"), "title" : "Diejdlsdd" }
{ "_id" : ObjectId("606d5ce467fe246be00de3b8"), "title" : "E.T." }
{ "_id" : ObjectId("606d5ce467fe246be00de3b9"), "title" : "Runner" }
{ "_id" : 0, "title" : "Diejdlsdd" }
{ "_id" : 1, "title" : "E.T." }

// delete
db.movies.deleteOne({"_id" : 1})
{ "acknowledged" : true, "deletedCount" : 1 }

// find
db.movies.find()
{ "_id" : ObjectId("606d59c263f269c1ddc2505a"), "title" : "Star Wars: kkkkkk Movie", "director" : "Gerod laus", "year" : 1977, "reviews" : [ ] }
{ "_id" : ObjectId("606d5ce467fe246be00de3b7"), "title" : "Diejdlsdd" }
{ "_id" : ObjectId("606d5ce467fe246be00de3b8"), "title" : "E.T." }
{ "_id" : ObjectId("606d5ce467fe246be00de3b9"), "title" : "Runner" }
{ "_id" : 0, "title" : "Diejdlsdd" }
```
deleteMany을 사용하자. 검색 조건에 여러개가 걸리면 전체 삭제

#### 도큐먼트 치환
replaceOne - 가능하다면 _id키를 사용하자!
```
// insert default data 
db.users.insertOne({"name" : "joe", "friends" : 32, "enemies" : 2 })

// select
db.users.findOne()
{
	"_id" : ObjectId("606d61ac67fe246be00de3ba"),
	"name" : "joe",
	"friends" : 32,
	"enemies" : 2
}

var joe = db.users.findOne({"name" : "joe"})
> joe
{
	"_id" : ObjectId("606d61ac67fe246be00de3ba"),
	"name" : "joe",
	"friends" : 32,
	"enemies" : 2
}

joe.relationships = {"friends" : joe.friends, "enemies" : joe.enemies}
joe.username = joe.name

delete joe.friends
delete joe.friends
delete joe.name

db.users.replaceOne({"name" : "joe"}, joe);

db.users.findOne()
{
	"_id" : ObjectId("606d61ac67fe246be00de3ba"),
	"enemies" : 2,
	"relationships" : {
		"friends" : 32,
		"enemies" : 2
	},
	"username" : "joe"
}
```
#### $set 제한자 사용
$set 제한자 사용
$set은 필드 값을 설정한다. 필드가 없으면 새 필드를 생성하고 스키마를 갱신하거나 사용자 정의 키를 추가할 때 편리하다.
```
// save 
db.users.insertOne({
    "name" : "joe",
    "age" : 30,
    "sex" : "male",
    "location" : "asdf"
})
{
	"acknowledged" : true,
	"insertedId" : ObjectId("606d666b290897f1f912c161")
}

// update set
db.users.updateOne({"_id" : ObjectId("606d65d6290897f1f912c160")},
{"$set" : {"favorite book" : "War"}})

db.users.findOne()
> db.users.find()
{ "_id" : ObjectId("606d61ac67fe246be00de3ba"), "enemies" : 2, "relationships" : { "friends" : 32, "enemies" : 2 }, "username" : "joe" }
{ "_id" : ObjectId("606d6295290897f1f912c15e"), "name" : "joe", "friends" : 32, "enemies" : 2, "favorite book" : "War" }
{ "_id" : ObjectId("606d65d6290897f1f912c160"), "name" : "joe", "age" : 30, "sex" : "male", "location" : "asdf", "favorite book" : "War" }
{ "_id" : ObjectId("606d666b290897f1f912c161"), "name" : "joe", "age" : 30, "sex" : "male", "location" : "asdf" }

// update set
db.users.updateOne({"name" : "joe"},
{"$set" : {"favorite book" : ["Cat", "Dogs", "KKKK"] }})

> db.users.find()
{ "_id" : ObjectId("606d61ac67fe246be00de3ba"), "enemies" : 2, "relationships" : { "friends" : 32, "enemies" : 2 }, "username" : "joe" }
{ "_id" : ObjectId("606d6295290897f1f912c15e"), "name" : "joe", "friends" : 32, "enemies" : 2, "favorite book" : [ "Cat", "Dogs", "KKKK" ] }
{ "_id" : ObjectId("606d65d6290897f1f912c160"), "name" : "joe", "age" : 30, "sex" : "male", "location" : "asdf", "favorite book" : "War" }
{ "_id" : ObjectId("606d666b290897f1f912c161"), "name" : "joe", "age" : 30, "sex" : "male", "location" : "asdf" }

// update unset
db.users.updateOne({"name" : "joe"},
{"$unset" : {"favorite book" : 1 }})

> db.users.find()
{ "_id" : ObjectId("606d61ac67fe246be00de3ba"), "enemies" : 2, "relationships" : { "friends" : 32, "enemies" : 2 }, "username" : "joe" }
{ "_id" : ObjectId("606d6295290897f1f912c15e"), "name" : "joe", "friends" : 32, "enemies" : 2 }
{ "_id" : ObjectId("606d65d6290897f1f912c160"), "name" : "joe", "age" : 30, "sex" : "male", "location" : "asdf", "favorite book" : "War" }
{ "_id" : ObjectId("606d666b290897f1f912c161"), "name" : "joe", "age" : 30, "sex" : "male", "location" : "asdf" }
```

#### 증가와 감소
갱신 연산자($inc) - 일반적으로 도큐먼트의 특정 부분만 갱신하는 경우가 많다. 부분 갱신에는 원자적 갱신 연산자를 사용한다. 갱신 연산자는 키를 변경, 추가, 제거하고 배열과 내장 도큐먼트를 조작하는 복잡한 갱신 연산을 지정하는 데 사용하는 특수키이다.
$set와 비슷하지만 숫자를 증감하기 위해 설계되었다. int, long, double, decimal 타입에만 사용
```
// save
db.analytics.insertOne({"url" : "www.example.com", "pageviews" : 52})

// update
db.analytics.updateOne({"url" : "www.example.com"},
{"$inc" : {"pageviews" : 3}})

// pageviews 3증가 
db.analytics.findOne()
{
	"_id" : ObjectId("606d64c3290897f1f912c15f"),
	"url" : "www.example.com",
	"pageviews" : 53
}
```

배열 연산자($push) : 배열을 다루는 데 갱신 연산자를 사용할 수 있다. 배열은 일반적이고 강력한 데이터 구조이고 연산자는 리스트에 대한 인덱스를 지정할 수 있을 뿐 아니라 셋처럼 이중으로 쓸수 있다.
- 요소 추가
    - $push는 배열이 이미 존재하면 배열 끝에 요소를 추가, 존재하지 않으면 새로운 배열을 생성!

```
db.blog.posts.insertOne({"title" : "A Blog", "content" : "..."})

db.blog.posts.findOne()
{
	"_id" : ObjectId("606d6b07290897f1f912c162"),
	"title" : "A Blog",
	"content" : "..."
}

db.blog.posts.updateOne({"title" : "A Blog"},
    {"$push" : {"comment" : 
        {"name" : "joe", "email" : "joe@example.com", "content" : "nice!"}}})
        
db.blog.posts.findOne()
{
	"_id" : ObjectId("606d6b07290897f1f912c162"),
	"title" : "A Blog",
	"content" : "...",
	"comment" : [
		{
			"name" : "joe",
			"email" : "joe@example.com",
			"content" : "nice!"
		}
	]
}

db.blog.posts.updateOne({"title" : "A Blog"},
    {"$push" : {"comment" : 
        {"name" : "bob", "email" : "bob@example.com", "content" : "goog!"}}})   
        
db.blog.posts.findOne()
{
	"_id" : ObjectId("606d6b07290897f1f912c162"),
	"title" : "A Blog",
	"content" : "...",
	"comment" : [
		{
			"name" : "joe",
			"email" : "joe@example.com",
			"content" : "nice!"
		},
		{
			"name" : "bob",
			"email" : "bob@example.com",
			"content" : "goog!"
		}
	]
}          
```

$push에 $each 제한자를 사용하면 작업 한번으로 값을 여러 개 추가할 수 있다.
```
db.stock.ticker.insertOne({"_id" : "GOOG"})

db.stock.ticker.updateOne({"_id" : "GOOG"},
    {"$push" : {"hourly" : {"$each" : [562.776, 562.790, 559.123]}}})

db.stock.ticker.find()
{ "_id" : "GOOG", "hourly" : [ 562.776, 562.79, 559.123 ] }    
```

$push에 $slice를 결합하면 배열을 특정 길이로 늘릴 수 있다. slice로 배열의 최대 길이를 조정할 수 있다.
```
db.temp.insertOne({"genre" : "horror"})

// 100번 반복
db.temp.updateOne({"genre" : "horror"},
    {"$push" : {"top10" : {"$each" : ["Night", "Saw"],
            "$slice" : -10}}})
            
db.temp.find()
{ "_id" : ObjectId("606d75b0eff0581991d9a372"), "genre" : "horror", "top10" : [ "Night", "Saw", "Night", "Saw", "Night", "Saw", "Night", "Saw", "Night", "Saw" ] }            
```

$sort 제한자를 $push 작업에 적용할 수 있다.
```
db.animals.insertOne({"kind" : "cat"})

// 100번 반복
db.animals.updateOne({"kind" : "cat"},
    {"$push" : {"top10" : {"$each" : [{"name" : "navy", "age" : 10}, {"name" : "mongi", "age" : 20}],
            "$slice" : -10,
            "$sort" : {"age" : -1}}}})
           
db.animals.find()
{ "_id" : ObjectId("606d77b5eff0581991d9a373"), "kind" : "cat", "top10" : [ { "name" : "navy", "age" : 10 }, { "name" : "navy", "age" : 10 }, { "name" : "navy", "age" : 10 }, { "name" : "navy", "age" : 10 }, { "name" : "navy", "age" : 10 }, { "name" : "navy", "age" : 10 }, { "name" : "navy", "age" : 10 }, { "name" : "navy", "age" : 10 }, { "name" : "navy", "age" : 10 }, { "name" : "navy", "age" : 10 } ] }            
```

#### 배열을 집합으로 사용하기($ne)
특정값이 배열에 존재하지 않을 때 해당 값을 추가하면서, 배열을 집합처럼 처리하려면 쿼리 도큐먼트에 "$ne"를 사용한다.
```
db.papers.updateOne({"authors cited" : {"$ne" : "Richie"}}, {"$push" : {"authors cited" : "Richie"}})


```

$addToSet을 사용할 수도 있다. $addToSet은 $ne가 작동하지 않을 때나 $addToSet을 사용하면 무슨 일이 일어났는지 더 잘 알 수 있을 때 유용하다.
```
db.people.insertOne({"username" : "joe", "emails" : ["joe1@example.com", "joe2@example.com","joe3@example.com"]})

// update same data
db.people.updateOne({"_id" : ObjectId("606d7ac6eff0581991d9a374")}, 
    {"$addToSet" : {"emails" : "joe1@example.com"}})
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 0 }    

// update difference data
db.people.updateOne({"_id" : ObjectId("606d7ac6eff0581991d9a374")}, 
    {"$addToSet" : {"emails" : "joe4@example.com"}})
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 1 }

db.people.find()
{ "_id" : ObjectId("606d7ac6eff0581991d9a374"), "username" : "joe", "emails" : [ "joe1@example.com", "joe2@example.com", "joe3@example.com", "joe4@example.com" ] }
```

고유한 값을 여러 개 추가하려면 $addToSet과 $each를 결합해 사용한다. $ne/$push 조합으로는 할 수 없는 작업니다.
```
db.people.updateOne({"_id" : ObjectId("606d7ac6eff0581991d9a374")}, 
    {"$addToSet" : {"emails" : {"$each" : 
            ["joe5@example.com", "joe6@example.com", "joe7@example.com"]}}})

{ "_id" : ObjectId("606d7ac6eff0581991d9a374"), "username" : "joe", "emails" : [ "joe1@example.com", "joe2@example.com", "joe3@example.com", "joe4@example.com", "joe5@example.com", "joe6@example.com", "joe7@example.com" ] }            
```

#### 요소 제거하기
- $pop
    - 배열을 큐나 스택처럼 사용하려면 배열의 양쪽 끝에서 요소를 제거한다.
    - {"$pop" : {"key" : 1}} -> 마지막부터 요소 제거
    - {"$pop" : {"key" : 1}} -> 처음부터 요소 제거
- $pull : 주어진 조건에 맞는 배열 요소를 제거
```
db.lists.insertOne({"todo" : ["dishes", "laundry", "dry cleaning"]})

db.lists.updateOne({}, {"$pull" : {"todo" : "laundry"}})

db.lists.find()
{ "_id" : ObjectId("606e8c5c7b290c0589af6c9b"), "todo" : [ "dishes", "dry cleaning" ] }
```

#### 배열의 위치 기반 변경
```
db.blogs.posts.insertOne(
  {
    "content" : "...", 
    "comments" : [
      {"comment" : "good!", "author" : "John", "votes" : 0}, 
      {"comment" : "too short!", "author" : "Claire", "votes" : 3}, 
      {"comment" : "free", "author" : "Alice", "votes" : -5},
      {"comment" : "vacation", "author" : "Lynn", "votes" : -7},
    ]
  }
)

db.blogs.posts.updateOne({"content" : "..."}, {"$inc" : {"comments.0.votes" : 1}})

{
	"_id" : ObjectId("606e90a97b290c0589af6c9d"),
	"content" : "...",
	"comments" : [
		{
			"comment" : "good!",
			"author" : "John",
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
			"votes" : -5
		},
		{
			"comment" : "vacation",
			"author" : "Lynn",
			"votes" : -7
		}
	]
}


db.blogs.posts.updateOne({"comments.author" : "John"}, {"$set" : {"comments.$.author" : "Jim"}})
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 1 }

db.blogs.posts.find().pretty()
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
			"votes" : -5
		},
		{
			"comment" : "vacation",
			"author" : "Lynn",
			"votes" : -7
		}
	]
}  
```

### 배열 필터를 이용한 갱신
```
db.blogs.posts.updateOne(
...   {"content" : "..."},
...   {"$set" : {"comments.$[elem].hidden" : true}},
...   {
...     arrayFilters: [
...       {"elem.votes" : { $lte : -5 } }
...     ]
...   }
... )
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 1 }
> db.blogs.posts.find().pretty()
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
```

### 갱신 입력
갱신 입력은 특수한 형태를 갖는 갱신이다. 갱신 조건에 맞는 도큐먼트가 존재하지 않을 때는 쿼리 ㅋ도큐먼트와 갱신 도큐먼트를 합쳐서 새로운 도큐먼트를 생성한다. 조건에 맞는 도큐먼트가 발견되면 일반적인 갱신을 수행한다.
```
// code...

// 이 페이지에 대한 항목이 있는지 확인
blog = db.analytics.findOne({"url" : "/blog"})

// 항목이 있으면 조회 수에 1을 더하고 저장
if (blog) {
    blog.pageviews++
    db.analytics.save(blog)
}

// 항목이 없으면 이 페이지에 대한 새로운 도큐먼트 생성
else {
    db.analytics.insertOne({"url" : "/blog", "pageviews" : 1})
}

// 갱신 입력 사용

// 첫방문
db.analytics.updateOne({"url" : "/blog"}, {"$inc" : {"pageviews" : 1}}, {"upsert" : true})

db.analytics.find()
{ "_id" : ObjectId("606ea1c10409afa4cebe978c"), "url" : "/blog", "pageviews" : 1 }

// 2회 방문
db.analytics.updateOne({"url" : "/blog"}, {"$inc" : {"pageviews" : 1}}, {"upsert" : true})

db.analytics.find()
{ "_id" : ObjectId("606d64c3290897f1f912c15f"), "url" : "www.example.com", "pageviews" : 56 }
{ "_id" : ObjectId("606ea1c10409afa4cebe978c"), "url" : "/blog", "pageviews" : 2 }
```

"$setOnInsert" insert 할때만 세팅
```
> db.userss.updateOne({}, {"$setOnInsert" : {"createAt" : new Date()}}, {"upsert" : true})
{
	"acknowledged" : true,
	"matchedCount" : 0,
	"modifiedCount" : 0,
	"upsertedId" : ObjectId("606ea3040409afa4cebe97a1")
}

> db.userss.find()
{ "_id" : ObjectId("606ea3040409afa4cebe97a1"), "createAt" : ISODate("2021-04-08T06:30:28.631Z") }

> db.userss.updateOne({}, {"$setOnInsert" : {"createAt" : new Date()}}, {"upsert" : true})
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 0 }

> db.userss.find()
{ "_id" : ObjectId("606ea3040409afa4cebe97a1"), "createAt" : ISODate("2021-04-08T06:30:28.631Z") }
```

### 저장 셀 보조자
- save는 도큐먼트가 존재하지 않으면 도큐먼트를 삽입하고, 존재하면 도큐먼트를 갱신하게 하는 셀 함수!
- 셀 함수는 매개변수가 하나이고 도큐먼트를 넘겨받는다.
- 도큐먼트가 "_id" 키를 포함하면 save는 갱신 입력을 실행하고 포함하지 않으면 삽입을 실행.
```
> db.testcalls.insertOne({"aaaa" : "..."})
{
	"acknowledged" : true,
	"insertedId" : ObjectId("606ea810db3450d20a0cb7a2")
}
> db.testcalls.findOne()
{ "_id" : ObjectId("606ea810db3450d20a0cb7a2"), "aaaa" : "..." }

> var x = db.testcalls.findOne()
> x.num = 42
42

> db.testcalls.save(x)
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })

> db.testcalls.find()
{ "_id" : ObjectId("606ea810db3450d20a0cb7a2"), "aaaa" : "...", "num" : 42 }
```

### 다중 도큐먼트 갱신
다중 스키마를 변경하거나 특정 타겟에게 새로운 정보를 추가할 때는 updateMany를 사용하자!
```
db.testc.insertMany(
    [
        {"birthday": "10/13/1978"},
        {"birthday": "10/13/1978"},
        {"birthday": "10/13/1978"}
    ]
)

> db.testc.updateMany({"birthday" : "10/13/1978"}, {"$set" : {"gift" : "Happy Bir.."}})
{ "acknowledged" : true, "matchedCount" : 3, "modifiedCount" : 3 }

> db.testc.find()
{ "_id" : ObjectId("606ea9a0db3450d20a0cb7a3"), "birthday" : "10/13/1978", "gift" : "Happy Bir.." }
{ "_id" : ObjectId("606ea9a0db3450d20a0cb7a4"), "birthday" : "10/13/1978", "gift" : "Happy Bir.." }
{ "_id" : ObjectId("606ea9a0db3450d20a0cb7a5"), "birthday" : "10/13/1978", "gift" : "Happy Bir.." }
```

### 갱신한 도큐먼트 반환
예시. 
status가 READY, RUNNING, DONE이 있다. 우선순위가 가장 높은 Status = READY를 찾아 프로세스를 실행하고 RUNNING을 걸쳐 DONE으로 가야한다.
한 Step씩 밟아보자
```
var cursor = db.processes.find({"status" : "READY"});
ps = cursor.sort({"priority" : -1}).limit(1).next();  // 1)
db.processes.updateOne({"_id" : ps.id}, ("$set" : {"status" : "RUNNING"}});

do_something(ps)

db.processes.updateOne({"_id" : ps._id}, {"$set" : {"status" : "DONE"}})
```
결론적으로 좋치 못한 소스이다. 
find로 여러개를 조회하면 여러개의 스레드가 생성된다. 
1) 에서 1번 스레드가 먼저 도큐먼트를 얻고 status를 RUNNING으로 갱신하기 전에 다시 1)에서 2번 스레드가 같은 도큐먼트를 받으면 받으면 두 스레드는 같은 프로세스를 시랳ㅇ하게 된다.

```
var cursor = db.processes.find({"status" : "READY"})

cursor.sort({"priority" : -1}).limit(1)
while((ps = sursor.next()) != null) {
    var result = db.processes.updateOne({"_id" : ps._id, "status" : "READY"}, {"$set" : {status" : "RUNNING"}})
    
    if (result.modifiedCount === 1) {
        do_somethind(ps)
        db.processes.updateOne({"_id": ps._id}, {"$set" : {"status" : "DONE"}})
        break;
    }
    cursor = db.processes.find({"status" : "READY"})
    cursor.sort({"priority" : -1}).limit(1)
}
```
이 코드는 동일한 프로세스를 처리하진 않지만 한 스레드가 모든 일을 하고 다른 스레드는 노는 경우가 발생할 수 있다.
findOneAndUpdate를 이용하자
```
db.processes.findOneAndUpdate({"status" : "READY"},
    {"$set" : {"status" : "RUNNING"}},
    {"$sort" : {"priority" : -1}})
    
// Result
{
    "_id" : ObjectId("....ddafd"),
    "priority" : 1,
    "status" : "READY"
}    
```
findOneAndUpdate 메서드는 도큐먼트의 상태를 수정하기 전에 반환한다. 만약 상태값이 변경된 이후의 값을 얻고 싶다면 returnDocument를 사용하자
```
db.processes.findOneAndUpdate({"status" : "READY"},
    {"$set" : {"status" : "RUNNING"}},
    {"$sort" : {"priority" : -1}, "returnNewDocument" : true})
    
// Result
{
    "_id" : ObjectId("....ddafd"),
    "priority" : 1,
    "status" : "RUNNING"
}        
```

최종 코드는 아래와 같다
```
db.processes.findOneAndUpdate({"status" : "READY"},
    {"$set" : {"status" : "RUNNING"}},
    {"$sort" : {"priority" : -1}, "returnNewDocument" : true})

do_something(ps)
db.processes.updateOne({"_id": ps._id}, {"$set" : {"status" : "DONE"}})
```
이외에도 findOneAndReplace는 동일한 매개변수를 사용하며 returnNewDocument의 값에 따라 교체 전이나 후에 필터와 일치하는 도큐먼트를 반환한다.
findOneAndDelete도 유사하지만 갱신 도큐먼트를 매개변수로 사용하지 않으며 다른 두 메서드의 옵션을 부분적으로 가진다. findOneAndDelete는 삭제된 도큐먼트를 반환한다.

