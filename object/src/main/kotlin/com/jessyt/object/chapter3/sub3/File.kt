package com.jessyt.`object`.chapter3.sub3

/**
 * Created by LYT to 2021/09/16
 */
class File {

    /**
     * 디렉토리 탐색
     *  - mask에 일치하는 모든 파일 찾기
     *  - mask가 null이면 모든 파일 찾기
     * @param mask String?
     * @return Iterable<File>
     */
    fun find(mask: String?): Iterable<File> {
        if (mask == null) {
            // 모든 파일 검색
        } else {
            // mask를 이용한 파일 검색
        }
    }

    fun find(mask: String): Iterable<File>
    fun findAll(): Iterable<File>

    fun find(mask: Mask): Iterable<File> {
        if (mask.empty()) {
            // 모든 파일 검색
        } else {
            // mask를 이용한 파일 검색
        }
    }

    fun find(mask: Mask): Iterable<File> {
        val files = mutableListOf<File>()
        for (file in /* All File */) {
            if (mask.matches(file))
                files.add(file)
        }
        return files
    }

    fun find(mask: Mask?): Iterable<File> {
        if (mask == null) {
            throw IllegalArgumentException("Mask can't be NULL")
        }

        // mask를 사용하여 file 검색
    }

    fun find(mask: Mask): Iterable<File> {
        // mask를 사용하여 file 검색
    }
}

interface Mask {
    fun matches(file: File): Boolean
}

class AnyFile: Mask {
    override fun matches(file: File): Boolean {
        return true
    }
}