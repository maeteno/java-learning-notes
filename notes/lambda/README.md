# 简介
lambda表达式是一种匿名函数的语法糖。它是函数式编程范式语言中的特性。可以说将函数作为一等公民的的语言中天生支持匿名函数,所以也很容易的
支持lambda。例如 JS，PHP，Python。 而Java的一种纯面向对象语言，我们如何区理解什么是**纯面向对象**。最直观的感受就是，在Java中一个
方法是不能独立存在的。一个可运行的最小代码单元是类。在Java中的一等公民是值，这里的值包括基本类型以及对象，而对象就是类定义的。

如何确定一个语言的一等公民是什么，就看方法的参数列表可以接受什么。

## 行为参数化

**行为参数化**是指可以通过参数传递行为。一般方法中，我们可以通过传递参数来控制方法的具体行为，例如下面的代码片段。
```java
public void fuc(Integer tag) {
    if(tag > 10){
        // code
    }else{
        // code
    }
}
```
方法通过参数选择具体的逻辑（既行为）。这里的参数是行为控制，而不是行为本身被传递。

如何传递一个具体的行为呢？所谓的行为既执行逻辑，执行逻辑既代码。最合适的代码单元就是**方法**。
> 方法的参数是一个变量。在一个语言中一个变量可以接收什么，那么方法就可以传递什么。例如JS中,变量可以接收一个匿名方法。那么JS的**方法**可以传递方法。
> 在C++中，变量可以是一个指针或者函数指针，所以C++中也可以传递方法的。而在Java中的变量只能是值类型，既基本类型以及对象。所以Java中只可以传递基本类型以及对象。

传递行为就是将代码传递到方法中去执行。所以我们可以传递一个对象到方法中，然后调用这个对象上的方法。我们实现不同的类既可传递不同的行为（传递我们实现的方法）。
这就是策论模式的基本实现，实现不同的策论方法。
```java
public void fuc(ClassA objA) {
    objA.func();
}
```

如果我们为每一个行为构建一个类，就会比较繁琐。有一个稍微好一个点方法就是使用匿名类。
```java
func(new ClassA(){
    public void func(){
        // code
    }
})
```

不管我们是构建一个类还是匿名类，其实有效载荷只有其中的方法。我们写了太多的模板代码。
所以我们期望的是直接传递我们的行为既代码到方法中，可行的方法是传递一个方法。在Go中，方法也是一种类型或者值。通过方法的签名来定义方法的类型。

如果我们需要在Java中传递方法，Java是强类型的语言。所以我们要解决以下问题：
1. 如何定义一个方法的签名，或者说类型
2. 如何去引用一个方法
3. 如何去定义这个方法

Java 解决这些问题分别引入了如下功能：
1. 函数接口
2. Lambda表达式
3. 方法的引用

# Lambda 知识点

1. 匿名函数
2. 函数接口
   1. 默认方法
3. 方法引用
4. 构造函数引用

## 匿名函数

一个函数包括返回值，方法名，型参列表。 匿名函数和匿名类一样是没有方法名称的。Java中没有匿名函数，这里说这个是为了引申Lambda

## 函数接口

函数接口指只定义一个抽象方法的接口。其实就是一种特殊的接口。用于定义函数的类型或者说是函数的签名。

函数签名只和形参列表以及返回值有关。和方法名无关。

## 方法引用

方法引用是一种Lambda表达式的简写， Lambda接受和函数接口签名一致的方法。并不需要引用的类或者对象实现该接口。

只要引用的方法和函数接口的签名一致即可引用，主要有类的静态方法引用，实例对象成员方法引用。以及笔记特殊的参数一的实例方法引用；

| 类型                 | Lambda                                      | 方法引用                    |
| -------------------- | ------------------------------------------- | --------------------------- |
| 静态方法引用         | `(args)->ClassName.staticMethod(args)`      | `ClassName::staticMethod`   |
| 实例成员方法引用     | `(args)->expr.instanceMethod(args)`         | `expr::instanceMethod`      |
| 参数实例成员方法引用 | `(args0，rest)->args0.instanceMethod(rest)` | `ClassName::instanceMethod` |

### 重点

- 静态方法引用: Lambda 的方法签名和类的静态方法一致
- 实例成员方法引用: Lambda 的方法签名和实例的成员方法签名一致
- 参数实例成员方法引用: 这里用的是类名引用的实例方法，这个类是Lambda型参列表的第一参数的类型，且剩下的参数和实例的成员方法签名一致

## 构造函数引用

构造函数引用使用`new`关键字，形式为`ClassName::new`

当一个函数接口的签名的形式是返回一个对象且型参列表和该对象的构造函数一致时可使用构造函数引用

| 函数描述符(签名)    | Lambda                         | 函数引用       |
| ------------------- | ------------------------------ | -------------- |
| () -> ClassName     | () -> new ClassName()          | ClassName::new |
| (T) -> ClassName    | (T t) -> new ClassName(t)      | ClassName::new |
| (T...) -> ClassName | (T t...) -> new ClassName(t..) | ClassName::new |

1. 型参列表可以匹配类的构造函数
2. 返回类似是该类的类型

## Java中的函数式接口
| 函数式接口        | 函数描述符     | 原始类型特化                                                       |
| ----------------- | -------------- | ------------------------------------------------------------------ |
| Predicate<T>      | T->boolean     | IntPredicate,LongPredicate,DoublePredicate                         |
| Consumer<T>       | T->void        | IntConsumer,LongConsumer,DoubleConsumer                            |
| Function<T,R>     | T->R           | [Function](#function-原始类型特化)                                 |
| Supplier<T>       | ()->T          | BooleanSupplier,IntSupplier,LongSupplier,DoubleSupplier            |
| UnaryOperator<T>  | T->T           | IntUnaryOperator,LongUnaryOperator,DoubleUnaryOperator             |
| BinaryOperator<T> | (T,T)->T       | IntBinaryOperator,LongBinaryOperator,DoubleBinaryOperator          |
| BiPredicate<L,R>  | (L,R)->boolean |                                                                    |
| BiConsumer<T,U>   | (T,U)->void    | ObjIntConsumer<T>,ObjLongConsumer<T>,ObjDoubleConsumer<T>          |
| BiFunction<T,U,R> | (T,U)>R        | ToIntBiFunction<T,U>,ToLongBiFunction<T,U>,ToDoubleBiFunction<T,U> |

### Function 原始类型特化 
- IntFunction<R>
- IntToDoubleFunction
- IntToLongFunction
- LongFunction<R>
- LongToDoubleFunction
- LongToIntFunction
- DoubleFunction<R>
- ToIntFunction<T>
- ToDoubleFunction<T>
- ToLongFunction<T>

# 总结

- 函数签名

将函数接口当做一种函数签名类型来认识，Lambda 以及方法的引用都是可以赋值个一个具体的函数接口。也就是说Java8中函数也是有类型的，并且函数类型的关键是签名一致即可，不需要实现对应的函数接口，有点像鸭子类型。