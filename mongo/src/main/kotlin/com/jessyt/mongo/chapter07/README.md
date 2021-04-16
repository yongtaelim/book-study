# 집계 프레임워크

## 파이프라인, 단계 및 조정 가능 항목
집계 프레임워크는 몽고DB 내 분석 도구 모음으로, 하나 이상의 컬렉션에 있는 도큐먼트에 대한 분석을 수행한다.  
집계 프레임워크는 파이프라인개념을 기반으로 한다. 우리는 집계 파이프라인을 통해 몽고DB 컬렉션에서 입력을 받고, 컬렉션에서 나온 도큐먼트를 하나 이상의 단계를 거쳐 전달한다. 

파이프라인은 몽고DB 컬렉션과 함께 작동한다. 
파이프라인은 단계로 구성되며 각 단계는 입력에 서로 다른 데이터 처리 작업을 수행하고 출력으로 독큐먼트를 생성해 다음 단계로 전달한다.
처리 작업 끝에서 생성한 출력은 애플리케이션에서 작업하는 데 사용하거나 컬렉션에 보내 나중에 사용한다. 

```
db.company.insertOne(
    {
        "name" : "Facebook", 
        "category_code" : "social", 
        "founded_year" : 2004, 
        "description" : "Social network", 
        "funding_rounds" : [
            {
                "id": 4,
                "round_code" : "b",
                "raised_amount" : 2600000,
                "raised_currency_code" : "USD"
                "funded_year" : 2006,
                "inverstments: 
                    [{
                        "company" : null,
                        "financial_org" : {
                            "name" : Greylock",
                            "permalink" : "greylock"
                        },
                        "person" : null
                    },{
                        "company" : null,
                        "financial_org" : {
                            "name" : Merithec",
                            "permalink" : "meritech-captial"
                        },
                        "person" : null
                    },{
                        "company" : null,
                        "financial_org" : {
                            "name" : Foundres",
                            "permalink" : "foundres"
                        },
                        "person" : null
                    },{
                        "company" : null,
                        "financial_org" : {
                            "name" : SV Angel",
                            "permalink" : "sv-angel"
                        },
                        "person" : null
                    }]
            },
            {
                "id": 2197,
                "round_code" : "c",
                "raised_amount" : 150084000,
                "raised_currency_code" : "USD"
                "funded_year" : 2000,
                "inverstments: 
                    [{
                        "company" : null,
                        "financial_org" : {
                            "name" : European",
                            "permalink" : "european"
                        },
                        "person" : null
                    }]
            }
        ],
        "ipo" : {
            "valuation_amount" : 12000000,
            "valuation_currency_code": "USD",
            "pub_year" : 2012,
            "pub_month" : 5,
            "pub_day" : 18,
            "stock_symbol" : "BASDAQ:FB"
        }
    }
)
```