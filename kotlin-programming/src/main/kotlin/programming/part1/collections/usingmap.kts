val sites = mapOf("naver" to "naver.com", "daum" to "daum.net")

println(sites.containsKey("naver"))
println(sites.containsValue("naver.com"))
println(sites.contains("naver"))
println("naver" in sites)

// get은 map에 해당하는 키가 없다면 nullable 타입을 리턴한다... 아래의 코드는 동작하지 않는다.
//val siteName: String = sites.get("naver1")
//println(siteName)

// Nullable 참조 타입 사용하자
val siteName: String? = sites.get("naver1")
println(siteName)

val siteName1: String? = sites["naver"]
println(siteName1)

// null이라면 default값 리턴
val siteName2: String? = sites.getOrDefault("naver1", "localhost.com")
println(siteName2)

// map도 마찬가지로 + - 연산자 이용가능하다.
val copySites = sites + ("kakao" to "kakao.com")
println(copySites)

val copySites2 = copySites - "kakao"
println(copySites2)

// loop
for (site in sites) {
    println("${site.key} ---- ${site.value}")
}

for ((key, value) in sites) {
    println("$key ---- $value")
}
