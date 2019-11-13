# XssTest
写的一个测试xss的demo

使用三种方法防御xss测试：

第一种：自定义htmlEncode，将特殊字符转义；

第二种：使用ESAPI进行xss过滤；

第三种：定义XssHttpServletRequestWrapper拦截器对输入进行过滤；
