失物招领模块
===================
-----------------

### 0. 登记：
请求格式：

	{
    "function":"2",
	"done":"0",
    "swuid":"xxxxxxxxxxxxx",
    "text":"xxxx",
    "details":"xxxx",
    "time":"xxxxxxx",
    "place":"xxxxx"
	}

> 请仔细阅读该参数表，该模块所有参数都相当简单，**`相同参数不再赘述`**。

|参数|说明|
|---|---|
|function|需要调用失物招领模块，function参数必须设置为2|
|done|done对应调用的功能号,如需调用`0.登记功能`，则done应设置为0|
|swuid|学号，目前仅支持本科生|
|text|失主要设置的标题信息，如`一运遗失红色暴龙太阳镜`，不超过255字节|
|details|失物要设置的备注信息，如`4月8号下午2点本人在一运遗失红色暴龙太阳镜一副……`,不超过255字节|
|time|掉落时间，UNIX时间戳|
|place|掉落地点，不超过255字节|

响应格式：

	true

或者
	
	false

### 1.撤销登记：
请求格式：

	{
    "function":"2",
	"done":"1",
    "id":"xxxxx"
	}

|参数|说明|
|:---:|---|
|id|需通过调用功能3获得某一用户的id|

响应格式：

	true

或者
	
	false

### 2.展示所有失物招领信息：
请求信息：

	{
    "function":"2",
	"done":"2"
	}

响应信息：

	[
	{
	    "swuid":"xxxxxxxxx",
	    "text":"xxxx",
	    "details":"xxxx",
	    "time":"xxxxxxx",
	    "place":"xxxxx"
	
	}
	,
	{ ......},
	{ ......}
	]

*响应体为Json数组*

### 3.展示某一用户发布的招领信息
请求信息：

	{
    "function":"2",
	"done":"3",
    "swuid":"xxxxx"
	}

响应信息：
	
	[
	{
		"id":"xxxxx",
	    "swuid":"xxxxxxxx",
	    "text":"xxxx",
	    "details":"xxxx",
	    "time":"xxxxxxx",
	    "place":"xxxxx"
	
	}
	,
	{ ......},
	{ ......}
	]

*响应体为Json数组*

|参数|说明|
|---|---|
|id|每一条登记的记录有一个单独的id来标记|

### 4.发布招领
请求格式：

	{
    "function":"2",
	"done":"4",
    "swuid":"xxxxxxx",
    "text":"xxxx",
    "details":"xxxx",
    "time":"xxxxxxx",
    "place":"xxxxx"
	}

响应格式：

	true

或者
	
	false



