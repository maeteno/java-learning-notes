# Stream 的操作

对数据的流进行操作，通过这些中间操作来处理数据。在RxJava中叫操作符。

# 筛选

筛选类的操作会过滤数据

## filter

**过滤操作**  
该方法接受一个谓词操作。及一个`T -> boolean`签名的方法。返回`true`保留。否则过滤掉。

## distinct

**对数据进行去重**  
这个操作方法不接受任何Lambda表达式，他会根据流所生成元素的`hashCode`和`equals`方法实现。 也就是说，如果你想自定义实现对流对象中的元素去重，你就需要自己实现对象`hashCode`和`equals`的实现。

# 截断流

## limit

## skip

# 映射

## map

## flatMap

# 查找和匹配

## allMatch

## anyMatch

## noneMatch

## findFirst

## findAny