# Optional

Optional 是对一个对象的包装类，通过该包装类操作对象。规避空指针异常的问题。减少对null的判断逻辑。

| 方法名      | 功能 | 简介                                                           |
| ----------- | ---- | -------------------------------------------------------------- |
| empty       | 创建 | 一个空的 Optional                                              |
| of          | 创建 | 通过一个对象创建Optional, 对象为null会包空指针异常             |
| ofNullable  | 创建 | 通过一个对象创建Optional，当接收值为null时返回一个空的Optional |
| map         | 操作 | 转换映射，和Stream 类似。返回的是一个Optional的包装类          |
| flatMap     | 操作 | 转换映射，和Stream 类似。返回的是一个原始类型                  |
| get         | 取值 | 获取Optional包装的值，如果值不存在会抛异常                     |
| orElse      | 取值 | 获取Optional包装的值，提供一个默认值                           |
| orElseGet   | 取值 | 一个延迟操作，接收一个Supplier。当Optional为空的时候执行       |
| orElseThrow | 取值 | 一个延迟操作，当值不存在是抛出对应的异常                       |
| ifPresent   | 取值 | 接收一个Consumer函数接口，和orElseGet相反。当值存在是执行      |