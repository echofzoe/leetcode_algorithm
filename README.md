### 简介

**一个基于 Java 的力扣题集，题集内对题目进行了分类，并模拟了输入输出上下文**

---

### 项目依赖
- *JDK14*
- *JUnit4.12*

---

### 一些说明

- 因为`MAVEN`的项目结构嵌套较为复杂，故本项目只使用了普通项目的架构。目前读者在使用时只需要手动引入`junit`的`jar`包即可
- 因为笔者的代码中用到了一些`jdk14`的语法糖（如`switch`等），所以使用低版本`jdk`时会通不过编译。读者可以直接使用`jdk`高版本，或将语法糖改成`jdk8`支持的正常语法
- 普通`idea`项目更改`jdk`编译版本：
  1. `ctrl + alt + s`打开`Settings`面板，进入`Build,Execution,Depolyment -> Compiler -> Java Compiler`，将右侧`Module`下对应项目后的`Target bytecode version`改成你的`jdk`版本
  2. `ctrl + alt + shift + s`打开`Project Structure`面板：
     1. 进入`Project`，将右侧`Project SDK`和下面的`Project language level`改成你的`jdk`版本
     2. 进入`Modules`，在右侧点击左键点击相应项目，再将右侧项目的`Dependencies`下的`Module SDK`改成你的`jdk`版本