## JDK8 新特性之 Optional

### 简介

- 为了避免程序抛出`NullPointerException`，我们通常会在代码中加入非空检查，使得代码的阅读性和维护性都比较差
- `jdk8`提供了`Optional`泛型类来优化非空检查的写法
- `Optional`类只是对类的简单封装，当类变量不存在时，缺失的值就会被构建成一个空的`Optional`对象，由方法`Optional.empty()`返回
- `null`与`Optional`在语义上的重要区别：
  - 在声明变量时使用`Optional`进行封装，而不是使用对象类型本身，就能够非常清除地表明这里的变量缺失是允许的
  - 如果不使用`Optional`进行封装，则我们只能通过对业务模型的理解，来判断一个`null`值是否属于该变量的有效值又或是异常情况

### 方法

#### 创建`Optional`

- `of`方法：创建一个不允许空值的`Optional`对象。如果构造参数为空，立即抛出异常，而不是等到访问这个空值的属性时才返回一个错误

  ```java
  public static <T> Optional<T> of(T value) {
      return new Optional<>(value);
  }
  
  private Optional(T value) {
      this.value = Objects.requireNonNull(value);
  }
  
  // requireNonNull 为 Object 类的方法
  public static <T> T requireNonNull(T obj) {
      if (obj == null)
          throw new NullPointerException();
      return obj;
  }
  ```

- `ofNullable`方法：创建一个允许空值的`Optional`对象

  ```java
  public static <T> Optional<T> ofNullable(T value) {
      return value == null ? empty() : of(value);
  }
  ```

- `empty`方法：创建一个空的`Optional`对象

  ```java
  public static<T> Optional<T> empty() {
      @SuppressWarnings("unchecked")
      Optional<T> t = (Optional<T>) EMPTY;
      return t;
  }
  
  private static final Optional<?> EMPTY = new Optional<>();
  ```

#### 常用方法

- `get()`：最简单但也最不安全的方法。如果变量存在，直接返回封装的变量值，否则抛出`NoSuchElementException`异常

  ```java
  User u = null;
  User u1 = Optional.ofNullable(u).get();  // 会抛出异常
  ```

- `orElse(T other)`：允许在`Optional`封装对象不包含值时提供一个默认值

  ```java
  User u = null;
  User u1 = Optional.ofNullable(u).orElse(helper());  // 相当于 u1 = u == null ? new User() : u;
  
  public static User helper() {
      return new User();
  }
  ```

- `orElseGet(Supplier<? extends T> supplier)`：允许在`Optional`封装对象不包含值时调用`Supplier`方法

  ```java
  User u = null;
  User u1 = Optional.ofNullable(u).orElseGet(() -> {
      return helper();
  });  // 相当于 u1 = u == null ? new User() : u;
  
  public static User helper() {
      return new User();
  }
  ```

- `orElseThrow(Supplier<? extends X> exceptionSupplier)`：和`get()`方法类似，当`Optional`封装对象为空时都会抛出一个异常，区别是此方法可以定制希望跑出的异常类型

  ```java
  User u = null;
  User u1 = Optional.ofNullable(u).orElseThrow(() -> new ArrayIndexOutOfBoundsException());
  ```

- `isPresent()`：返回当前`Optional`封装对象是否为空，与`isEmpty()`方法相反

  ```java
  User u = null;
  boolean b = Optional.ofNullable(u).isPresent();  // 返回 false
  ```

- `orElse(T other)`与`orElseGet(Supplier<? extends T> supplier)`方法的区别：

  - 前者当`value`不为`null`时，依然会执行返回`T`的方法，而后者不回

#### 映射与过滤

- `map(Function<? super T, ? extends U> mapper)`：将入参`T`按需转换成`U`

  ```java
  User u = new User();
  String s = Optional.ofNullable(u).map(x -> x.getClass() + "...");  // 将 u 转换成字符串"User..."
  ```

  ```java
  String s = Optional.of("abc").map(x -> x.toUpperCase()).get();  // 将小写"abc"转换成大写"ABC"
  ```

- `filter(Predicate<? super T> predicate)`：方法接受一个谓词作为参数，如果`Optional`封装对象存在，并符合谓词的条件，该方法就返回其值，否则返回一个空`Optional`对象

  ```java
  String s1 = Optional.of("abc").filter(x -> x.contains("a")).orElse("efg");
  System.out.println(s1);  // "abc"
  
  String s2 = Optional.of("abc").filter(x -> x.contains("e")).orElse("efg");
  System.out.println(s2);  // "efg"
  ```

