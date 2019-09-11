# 黑马旅游网项目
## 技术选型
### 1.web层
    1.Servlet:前端控制器
    2.html：视图
    3.Filter：过滤器
    4.BeanUtils:数据库封装
    5.jackson:json序列化工具
### 2.Service层
    1.javamail:java发送邮件工具
    2.Redis:nosql内存数据库
    3.jedis：java的redis客户端
### 3.Dao层
    1.Mysql:数据库
    2.druid：数据库连接池
    3.jdbcTemplate：jdbc的工具
## 创建数据库

## 功能实现
### 功能功能分析
### 代码实现
    1. 前台效果：
        1.表单校验
            <script>
                <!--表单校验-->
                /*1.用户名：单词字符长度8-20位
                2.密码：单词字符长度8-20位
                3.邮箱:正则
                4.姓名：非空
                5.手机号：正则
                6.日期：非空
                7.验证码：非空*/
    
                //例： 校验用户名
                function checkUsername() {
                    // 1.获取用户名
                    var username = $("#username").val();
                    // 2.定义正则
                    var reg_username = /^\w{6,20}$/;
                    // 3.判断，给出提示信息
                    var flag = reg_username.test(username);
                    if(flag){
                        // 1.用户名合法
                        $("#username").css("border","");
                    }else {
                        // 2.用户名非法
                        $("#username").css("border","1px solid red");
                    }
                    return flag;
                };
                
                // 通过load函数对校验进行触发
                $(function () {
                    // 1.当表单提交去校验所有的方法
                    $("#registerForm").submit(function () {
                     // 1.如果当前这个方法没有返回值，或者返回true，则表单提交，返回false则表单不提交
                      return  checkUsername() && checkPassword() && checkEmail(); 
                    })
    
                    // 2.当某一个组件失去焦点时校验对应方法
                    $("#username").blur(checkUsername);
                    $("#password").blur(checkPassword);
                    $("#email").blur(checkEmail);
                });
            </script>
            
        2.ajax提交表单
            1.说明：
                使用异步提交表单是为了获取服务器的数据，因为我们前台使用的是html作为视图层
                所以不能直接从servlet相关的域对象获取数据，只能通过ajax获取响应数据
            2.ajax异步提交
                $(function () {
                    // 当表单提交去校验所有的方法
                    $("#registerForm").submit(function () {
                        // 1.表单已提交就发送数据到后台服务器
                        // 2.跳转页面
                        //2.1 ajax异步提交数据
                        if(checkUsername() && checkPassword() && checkEmail()){
                            // 如果校验通过，发送ajax请求，提交表单的数据
                            // Jq ajax :registUserServlet为请求的servlet，第二个参数为请求是上传的数据，function为回调函数，data是response响应的数据
                            // 通过 Jq对表单的serialize() 方法将数据获取序列化成key=value && key = value
                            // 当前this就代表着#registerForm 表单对象，通过$()将js的表单对象转换成为Jq的表单对象
                            $.post("registUserServlet",$(this).serialize(),function(data){
                                // 处理服务响应的数据data
                            });
                        }
                        return false;
                      // return  checkUsername() && checkPassword() && checkEmail(); // 2.2 这个方式为非异步方式，将使用action的请求路径对数据进行提交
                        // 如果这个方法没有返回值，或者返回true，则表单提交，入过返回false则表单不提交
                    })
    
                    // 当某一个组件失去焦点时校验对应方法
                    $("#username").blur(checkUsername);
                    $("#password").blur(checkPassword);
                    $("#email").blur(checkEmail);
                });
    
    2.后台代码实现：
        1.编写 RegistUserServlet
        2.编写 UserService以及UserServiceImpl
        3.编写 UserDao以及UserDaoImpl   
    