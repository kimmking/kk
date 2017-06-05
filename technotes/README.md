JSON是一种文本方式展示结构化数据的方式，从产生的时候开始就由于其简单好用、跨平台，特别适合HTTP下数据的传输（例如现在很流行的REST）而被广泛使用。

## 1、JSON是什么
JSON起源于1999年的[JS语言规范ECMA262的一个子集](http://javascript.crockford.com/)（即15.12章节描述了格式与解析），后来2003年作为一个数据格式[ECMA404](http://www.ecma-international.org/publications/files/ECMA-ST/ECMA-404.pdf)（很囧的序号有不有？）发布。
2006年，作为[rfc4627](http://www.ietf.org/rfc/rfc4627.txt)发布，这时规范增加到18页，去掉没用的部分，十页不到。

JSON的应用很广泛，这里有超过100种语言下的JSON库：[json.org](http://www.json.org/)。

更多的可以参考这里，[关于json的一切](https://github.com/burningtree/awesome-json)。


## 2、优缺点、标准与schema
### 结构与类型
这估计是最简单标准规范之一：
- 只有两种结构：对象内的键值对集合结构和数组，对象用{}表示、内部是"key":"value"，数组用[]表示，不同值用逗号分开
- 基本数值有7个： false / null / true / object / array / number / string
- 再加上结构可以嵌套，进而可以用来表达复杂的数据
- 一个简单实例：

```javascript
   {
      "Image": {
          "Width":  800,
          "Height": 600,
          "Title":  "View from 15th Floor",
          "Thumbnail": {
              "Url":    "http://www.example.com/image/481989943",
              "Height": 125,
              "Width":  "100"
          },
          "IDs": [116, 943, 234, 38793]
        }
   }
``` 

### 优点
- 基于纯文本，所以对于人类阅读是很友好的。
- 规范简单，所以容易处理，开箱即用，特别是JS类的ECMA脚本里是内建支持的，可以直接作为对象使用。
- 平台无关性，因为类型和结构都是平台无关的，而且好处理，容易实现不同语言的处理类库，可以作为多个不同异构系统之间的数据传输格式协议，特别是在HTTP/REST下的数据格式。

### 缺点
缺点也很明显：
- 性能一般，文本表示的数据一般来说比二进制大得多，在数据传输上和解析处理上都要更影响性能。
- 缺乏schema，跟同是文本数据格式的XML比，在类型的严格性和丰富性上要差很多。XML可以借由XSD或DTD来定义复杂的格式，并由此来验证XML文档是否符合格式要求，甚至进一步的，可以基于XSD来生成具体语言的操作代码，例如apache xmlbeans。并且这些工具组合到一起，形成一套庞大的生态，例如基于XML可以实现SOAP和WSDL，一系列的ws-*规范。但是我们也可以看到JSON在缺乏规范的情况下，实际上有更大一些的灵活性，特别是近年来REST的快速发展，已经有一些schema相关的发展(例如[理解JSON Schema](https://spacetelescope.github.io/understanding-json-schema/index.html)，[使用JSON Schema](http://usingjsonschema.com/downloads/)， [在线schema测试](http://azimi.me/json-schema-view/demo/demo.html))，也有类似于WSDL的[WADL](https://www.w3.org/Submission/wadl/)出现。

## 常用技术与工具
### 相关技术以及与XML的关系
- 使用JSON实现RPC（类似XML-RPC）：[JSON-RPC](http://www.jsonrpc.org/)
- 使用JSON实现path查询操作（类似XML-PATH）：[JsonPATH](https://github.com/json-path/JsonPath)
- 在线查询工具：[JsonPATH](http://jsonpath.com/)
 
例如上面的示例json，用表达式$.Image.IDs[:1]查询，得到116：
![image](https://raw.githubusercontent.com/kimmking/kk/master/images/json/jsonpath.png)


我们看到JSON与XML是如此之像，实际上这两个格式可以看做一个是学院排，一个是平民派。一个对象从POJO转换成XML与JSON的过程，基本是一致的（绝大部分工作可以复用，以后有机会再详细聊这个过程），10年前我自己也做过一个基于XML的RPC（[http://code.google.com/p/rpcfx/](http://code.google.com/p/rpcfx/)，貌似已经被墙），里面实现了java和dotnet、JS的XML序列化与反序列化，同时作为一个副产品，实现了JSON序列化。

后来thoughtsworks公司出品的XStream就是同时做了XML与JSON的序列化。而创建Jackson库的组织本来叫fasterxml，就是处理xml的。当然从这个角度来看，Fastjson库，稍微改改也是一个高性能的XML序列化库。
只是XML有着更严格的结构，更丰富的工具生态，拿查询与操作来说，XML还有XQuery、XLST等工具。处理方式上也有DOM方式与SAX流模式，这两个绝然不同的技术。

单从性能来考虑，XML更是有[VTD-XML](http://vtd-xml.sourceforge.net/)这种解决了DOM消耗太大内存与SAX只能单向每个节点读一次不能随机读的缺点的高性能处理方式。

### Java类库
- [Fastjson](https://github.com/alibaba/fastjson)
- [Jackson](http://wiki.fasterxml.com/JacksonHome)
- [Gson](https://github.com/google/gson)
- [Xstream](http://x-stream.github.io/)

### 工具
- 格式化工具：[jsbeautifier](http://jsbeautifier.org/)
- chrome插件：[5个Json View插件](http://www.cnplugins.com/zhuanti/five-chrome-json-plugins.html)
- 在线Mock: [在线mock](https://www.easy-mock.com/)
- 其他Mock：[SoapUI](https://www.soapui.org/rest-testing-mocking/rest-service-mocking.html)可以支持，SwaggerUI也可以，[RestMock](https://github.com/andrzejchm/RESTMock)也可以。

![image](https://github.com/kimmking/kk/blob/master/images/json/json01.png?raw=true)
![image](https://github.com/kimmking/kk/blob/master/images/json/json02.png?raw=true)

## Google JSON风格指南
遵循好的设计与编码风格，能提前解决80%的问题:
- 英文版[Google JSON Style Guide](https://google.github.io/styleguide/jsoncstyleguide.xml)：https://google.github.io/styleguide/jsoncstyleguide.xml
- 中文版[Google JSON风格指南](https://github.com/darcyliu/google-styleguide/blob/master/JSONStyleGuide.md)：https://github.com/darcyliu/google-styleguide/blob/master/JSONStyleGuide.md

简单摘录如下：
- 属性名和值都是用双引号，不要把注释写到对象里面，对象数据要简洁
- 不要随意结构化分组对象，推荐是用扁平化方式，层次不要太复杂
- 命名方式要有意义，比如单复数表示
- 驼峰式命名，遵循Bean规范
- 使用版本来控制变更冲突
- 对于一些关键字，不要拿来做key
- 如果一个属性是可选的或者包含空值或null值，考虑从JSON中去掉该属性，除非它的存在有很强的语义原因
- 序列化枚举类型时，使用name而不是value
- 日期要用标准格式处理
- 设计好通用的分页参数
- 设计好异常处理

## 使用JSON实现API
[JSON API](http://jsonapi.org.cn/format/)与Google JSON风格指南有很多可以相互参照之处。

[JSON API](http://jsonapi.org.cn/format/)是数据交互规范，用以定义客户端如何获取与修改资源，以及服务器如何响应对应请求。

JSON API设计用来最小化请求的数量，以及客户端与服务器间传输的数据量。在高效实现的同时，无需牺牲可读性、灵活性和可发现性。

## REST
 todo list
 - dubbox
 - resteasy
 - restlet
 - jersey
 
![image](https://github.com/kimmking/kk/blob/master/images/json/rest.jpg?raw=true)

## SwaggerUI实现API文档管理与在线测试
 todo list
 
![image](https://github.com/kimmking/kk/blob/master/images/json/json03.png?raw=true)

## 场景分析
JSON的使用，依据不同用途，有几个典型的场景：
1. 内部后台系统之间的数据传输，此种情况下基于HTTP的JSON格式其实没有优势。
2. 前后台之间的API调用，典型的是前端作为React/VUE/AngularJS/ExtJS等框架做的，前后端使用JSON交互。
- 此时可以使用类似Dubbox之类的框架，或者原始一些SpringMVC的Controller上直接@ResponseBody或@RestController也可以。
- 强烈建议在Dubbox之类的rest之上再加一个Nginx转发，这样一些策略的控制，比如同源的控制、简单的缓存策略、安全策略等都可以放到Nginx上来做，也利于多个机器时的负载均衡。
- 建议使用swaggerUI来自动实现API文档和在线测试。功能很强大，操作简单，而且可以mock接口，在后台没有做好之前，前台就可以先开发了。
- 可以使用RestUnit或SoapUI来实现自动化测试与压力测试。

3. 提供给第三方的开发接口API
基本同上，可以参考Google JSON风格指南与JSON API章节。

## json的一些经验
最近帮忙处理一下Fastjson的bug问题，发现最常见的其实是大家使用的不规范性，这样碰到各种坑的可能性就很大。根据我平时使用的经验，以及总结大家常见的问题，归纳如下：

### 1.遵循Java Beans规范与JSON规范
实践告诉我们：遵循beans规范和JSON规范的方式，能减少大部分的问题，比如正确实现setter、getter，用别名就加annotation。注意基本类型的匹配转换，比如在fastjson的issue见到试图把"{"a":{}}"中的a转换成List的。

### 2.使用正常的key
尽量不要使用数字等字符开头的key，尽量使用符合Java的class或property命名规范的key，这样会减少不必要的冲突。在jsonpath或js里，a.1可能会被解释成a[1]或a["1"]，这些都会带来不必要的麻烦。

### 3.关于日期处理
这一点前面的Google JSON风格指南里也提到了，尽量使用标准的日期格式。或者序列化和反序列化里都是用同样的datePattern格式。

### 4.关于自定义序列化与反序列化
对于新手来说，自定义序列化是一切罪恶的根源。

尽量不要使用自定义序列化，除非万不得已，优先考虑使用注解过滤，别名等方式，甚至是重新建一个VO类来组装实际需要的属性。使用自定义序列化时一切要小心，因为这样会导致两个问题：
- 改变了pojo <-> jsonstring 的自然对应关系，从而不利于阅读代码和排查问题，你改变的关系无法简单的从bean和json上看出来了；
- 反序列化可能出错，因为对应不上原来的属性了。

如果只是序列化发出去（响应）的是JSON数据、传过来（请求）的数据格式跟JSON无关或者是标准的，此时自定义序列化就无所谓了，反正是要接收方来处理。

### 5.JsonObject的使用
JsonObject是JSON字符串与pojo对象转换过程中的中间表达类型，中间对象，不要混用，尽量不要用

### 6.Hibernate相关问题
懒加载与级联，可能导致出现问题，例如hibernate，建议封装一层VO类型来序列化。使用VO类还有一个好处，就是可以去掉一些没用的属性，减少数据量，同时可以加上额外的属性。

### 7.深层嵌套与泛型问题
尽量不要在使用过多的层次嵌套的同时使用泛型（List、Map等），可能导致类型丢失，而且问题比较难查。

### 8.抽象类型与子类型问题
尽量不要在同一个Bean的层次结构里使用多个子类型对象，可能导致类型丢失，而且问题比较难查。当然我们可以通过代码显示的传递各种正确的类型，但是这样做引入了更多的不确定性。良好的做法应该是一开始设计时就避免出现这些问题。

### 9.避免循环引用
尽量避免循环引用，这个虽然可以通过序列化特性禁掉，但是如果能避免则避免。

## fastjson的最佳实践
### 1.序列化一个对象成JSON字符串

### 2.反序列化一个JSON字符串成Java对象

### 3.日期处理

### 4.自定义序列化与反序列化

### 5.常见序列化特性的使用

### 6.Annotation注解的使用

### 7.与Spring MVC的配合使用

### 8.与Spring Boot的集成使用

## fastjson的设计说明
todo list

