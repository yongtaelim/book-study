//package com.jessyt.`object`.chapter3.sub2
//
///**
// * Created by LYT to 2021/09/15
// */
//class Decorate {
//
//    fun test() {
//        val names =
//            Sorted(  // 정렬
//                Unique(  // 유일
//                    Capitalized(  // 대문자로 변경
//                        Replaced(  // 정규 표현식으로 치환
//                            FileNames(  // 파일 명
//                                Directory(  // 디렉토리
//                                    "/var/users/*.xml"
//                                )
//                            ),
//                            /* 정규식 */,
//                            /* 정규식 */
//                        )
//                    )
//                )
//            )
//    }
//
//    fun test1() {
//        var rate: Float
//        if ( client.age() > 65 ) {
//            rate = 2.5f
//        } else {
//            rate = 3.0f
//        }
//    }
//
//    fun test2() {
//        val rate = If(
//            client.age() > 65,
//            2.5, 3.0
//        )
//    }
//
//    fun test3() {
//        val rate = If(
//            Greater(client.age(),  65),
//            2.5, 3.0
//        )
//    }
//
//    fun test4() {
//        val rate = If(
//            GraterThan(AgeOf(client), 65),
//            2.5, 3.0
//        )
//    }
//}