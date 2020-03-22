##JSON条件匹配

####连接符
- and
 ````
 {
    "and":[
        {"version": "1.0.0"},
        {"clientType": 2}
    ]
 }
 ````
 - or
 ````
 {
    "or":[
        {"version": "1.0.0"},
        {"version": "1.0.1"}
    ]
 }
 ````
 
 - not
 ````
 {
    "not":{
        "version": "1.0.0",
        "clientType": 2
    }
 }
 ````
 
 ####判断符
 - &gt;
 ````
 {
    "version": {
        "> | &gt | _gt" : "1.0.0"
    }
 }
 ````
 - &gt;=
 - &lt;
 - &lt;=
 - =
 - !=
 - in
 ````
 {
    "version": {
        "in" : [
            "1.0.0",
            "1.0.1",
            "1.0.2"
        ]
    }
 }
 ````
 - not in
 
#### 自定义比较器
 ````
 public interface IComparater {
 
     boolean support(Class type, Map<Class, Annotation> annotationMap);
 
     int compare(Object dataValue, Object jsonValue);
 }
 ````
目前除了默认的基础类型 Number, Date, String(默认) 外  
提供了 Version 版本比较器, 具体见 VersionComparator

#### 示例
 ````
 {
   "or": [
     {
       "clientType": [
         1,
         2,
         3
       ],
       "vip": {
         ">": 3,
         "<=": 5
       }
     },
     {
       "clientType": 1,
       "vip": 3,
       "version": {
         ">=": "3.0.0"
       }
     },
     {
       "balance": {
         ">=": 1000
       }
     }
   ]
 }
 ````
 ````
     DslResolver dslResolver = new DslResolver(RuleInfo.class);
     JSONObject rule = JSON.parseObject("-- 条件 json格式 --");
     RuleInfo ruleInfo = new RuleInfo();
     ruleInfo.setClientType(4);
     ruleInfo.setVip(2);
     ruleInfo.setVersion("3.1.0");
     ruleInfo.setBalance(new BigDecimal("1000"));
     boolean isMatch = dslResolver.judge(rule, ruleInfo);
 ````
 
 