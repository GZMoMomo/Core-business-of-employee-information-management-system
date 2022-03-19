# 这是一个员工信息管理系统的核心业务~java

具有增删查改的基本功能，并且在许多地方作出了优化改进。<br>
## 上手指南
将项目克隆到本地后，打开/ssm-crud/src/main/resources/dbconfig.properties文件，将其中数据库地址、用户名、密码更改为自己的即可。<br>
![image](https://user-images.githubusercontent.com/91240419/159115663-f173a1a0-3e8c-4577-8481-501b6968c0de.png)

## 项目特点
#### 1.此项目使用 **Maven** 依赖管理和构建,无需担心jar包问题。<br><br>
#### 2.前端框架使用了 **Bootstrap** 页面效果美观，js框架使用 **JQUERY**，jsp代码更加简洁。<br><br>
#### 3.前端页面使用 **AJAX** 显示数据和发送请求，服务端返回 **json** 文件，使得网页在多平台都可正常使用，如安卓、Windows、iMac。<br><br>
#### 4.对于添加信息进行了 **双重校验** ，前端校验和 **服务端JS303校验** 使得数据更加安全。并且前端校验都为无极刷新校验，实时监听用户输入。<br><br>
#### 5.数据显示采用了分页工具 **pagehelper**，使开发更加方便<br><br>
#### 6.后端整合了 **Spring, SpringMVC, Mybatis** 三种框架进行开发。启用了 **事务管理**，使用了 **Restful风格**，服务器根据method的不同来判断浏览器期望做的业务行为。<br><br>
#### 7.项目主要由 **Mybatis Generator** （用于**Mybatis逆向工程**的工具）生成，大大简化了程序开发。



## 基本功能如下
#### 主页面
![image](https://user-images.githubusercontent.com/91240419/159114882-34c52495-f99e-45b6-bca3-f798aea1ff5b.png)<br>
#### 新增窗口
![image](https://user-images.githubusercontent.com/91240419/159116151-e2c49dbf-7dad-4df4-a33a-97b0443de37c.png)<br>
#### 校验显示
![image](https://user-images.githubusercontent.com/91240419/159116213-7920f5ca-98f4-4eaf-a0ea-833d2ba3a254.png)<br>
![image](https://user-images.githubusercontent.com/91240419/159116240-55d259ed-126c-42c1-968d-03b45825cb6a.png)<br>
![image](https://user-images.githubusercontent.com/91240419/159116246-f4f2a2d7-7be1-402d-8f1b-93cbdc23e911.png)<br>
#### 成功后自动跳转最后一页
![image](https://user-images.githubusercontent.com/91240419/159116280-19db8015-3d66-4709-b01e-edc2e6cf7ade.png)<br>
#### 编辑窗口
###### 自动读取需要编辑的员工信息
![image](https://user-images.githubusercontent.com/91240419/159116315-985342de-09c2-4e9f-a755-64e12e3ff094.png)<br>
#### 删除
![image](https://user-images.githubusercontent.com/91240419/159116343-7ec88b63-5c04-4d15-b27f-4fe4a74ffd49.png)
#### 批量删除
![image](https://user-images.githubusercontent.com/91240419/159116359-1ced9247-33fd-45b4-bf03-0d0e831b13cf.png)
# 版本控制
该项目使用Maven进行版本管理。您可以在pom.xml参看当前可用版本。




