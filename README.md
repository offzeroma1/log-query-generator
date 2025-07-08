# log-query-generator

Elasticsearch DSL 쿼리 중
자주 쓰는 match + range 조합을 빠르게 만들 수 있게 CLI 도구



## 기능

- 필드명 + 키워드 입력 → match 쿼리 생성
- 날짜 범위 입력 → range 쿼리 생성
- 전체 쿼리는 bool.must로 묶어서 출력



## 실행 예시

```bash
$ ./gradlew run

[필드명 입력] : service_name
[검색어 입력] : gateway
[시작일 입력] (예: 2025-01-01): 2024-01-01
[종료일 입력] (예: 2025-02-10): 2024-02-10
```
출력:
```json
{
  "query": {
    "bool": {
      "must": [
        { "match": { "service_name": "gateway" } },
        {
          "range": {
            "@timestamp": {
              "gte": "2024-01-01",
              "lte": "2024-01-10"
            }
          }
        }
      ]
    }
  }
}
```


## 개발환경
Java 17, Gradle, Gson



## TODO
- [] CLI 옵션 처리 (--field, --start, --end)
- [] 결과 JSON 파일 저장
- [] 쿼리 템플릿 확장 (filter, should 등)
