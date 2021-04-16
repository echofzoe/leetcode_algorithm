## JDK8 新特性 Optional

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
- https://www.bilibili.com/video/BV1EJ411c7hd?from=search&seid=15703425006213898588
- min = 24